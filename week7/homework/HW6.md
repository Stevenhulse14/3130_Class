## Homework 6: Adding Database Persistence with Spring Data JPA

### Learning Goals

In this homework you will learn:

• how to use Spring Data JPA for database operations
• how to create Entity classes
• how to use Repository interfaces
• how to configure H2 database
• how to use database relationships
• how to implement pagination and sorting
• how to write custom query methods

At the end of this assignment, tasks will be stored permanently in a database.

---

### Prerequisites

Complete Homework 5 before starting this assignment.

---

### Step 1 — Create Task Entity

Convert the Task model to a JPA Entity.

Update:

**`model/Task.java`**

```java
package edu.brooklyn.cisc3130.taskboard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(nullable = false, length = 100)
    private String title;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(length = 500)
    private String description;
    
    @Column(nullable = false)
    private Boolean completed = false;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Priority priority = Priority.MEDIUM;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}
```

**New Annotations:**

- `@Entity` - Marks this class as a JPA entity
- `@Table(name = "tasks")` - Specifies the table name
- `@Id` - Marks the primary key field
- `@GeneratedValue` - Auto-generates ID values
- `@Column` - Maps to database column with constraints
- `@Enumerated` - Stores enum as string in database
- `@PrePersist` - Runs before entity is saved
- `@PreUpdate` - Runs before entity is updated

---

### Step 2 — Create Task Repository

Create a package:

**`repository`**

Create:

**`TaskRepository.java`**

```java
package edu.brooklyn.cisc3130.taskboard.repository;

import edu.brooklyn.cisc3130.taskboard.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    
    // Find all completed tasks
    List<Task> findByCompletedTrue();
    
    // Find all incomplete tasks
    List<Task> findByCompletedFalse();
    
    // Find tasks by priority
    List<Task> findByPriority(Task.Priority priority);
    
    // Find tasks by title containing (case-insensitive)
    List<Task> findByTitleContainingIgnoreCase(String title);
    
    // Find completed tasks by priority
    List<Task> findByCompletedAndPriority(Boolean completed, Task.Priority priority);
    
    // Custom query using JPQL
    @Query("SELECT t FROM Task t WHERE t.title LIKE %:keyword% OR t.description LIKE %:keyword%")
    List<Task> searchTasks(@Param("keyword") String keyword);
    
    // Paginated query for completed tasks
    Page<Task> findByCompletedTrue(Pageable pageable);
    
    // Count tasks by priority
    long countByPriority(Task.Priority priority);
}
```

**Key Concepts:**

- `JpaRepository<Task, Integer>` - Provides CRUD operations
- Method naming convention - Spring generates queries from method names
- `@Query` - Custom SQL/JPQL queries
- `Pageable` - For pagination support

**💡 Hint:** Spring Data JPA automatically implements these methods based on naming conventions:
- `findByCompletedTrue()` → `SELECT * FROM tasks WHERE completed = true`
- `findByTitleContainingIgnoreCase()` → `SELECT * FROM tasks WHERE LOWER(title) LIKE LOWER('%?%')`

**Understanding Repository Method Naming:**

Spring Data JPA uses a convention-based approach. The method name tells Spring what query to generate:

| Method Name Pattern | Generated Query |
|---------------------|-----------------|
| `findByCompletedTrue()` | `WHERE completed = true` |
| `findByCompletedFalse()` | `WHERE completed = false` |
| `findByPriority(Priority p)` | `WHERE priority = ?` |
| `findByTitleContaining(String s)` | `WHERE title LIKE '%?%'` |
| `findByTitleContainingIgnoreCase(String s)` | `WHERE LOWER(title) LIKE LOWER('%?%')` |
| `findByCompletedAndPriority(Boolean c, Priority p)` | `WHERE completed = ? AND priority = ?` |
| `findByCreatedAtAfter(LocalDateTime d)` | `WHERE created_at > ?` |
| `countByPriority(Priority p)` | `SELECT COUNT(*) WHERE priority = ?` |

**Key Words:**
- `findBy` - SELECT query
- `countBy` - COUNT query
- `deleteBy` - DELETE query
- `And` - AND condition
- `Or` - OR condition
- `Containing` - LIKE '%?%'
- `IgnoreCase` - Case-insensitive search
- `True`/`False` - Boolean conditions
- `After`/`Before` - Date comparisons

**Custom Queries:**

When naming conventions aren't enough, use `@Query`:

```java
@Query("SELECT t FROM Task t WHERE t.title LIKE %:keyword% OR t.description LIKE %:keyword%")
List<Task> searchTasks(@Param("keyword") String keyword);
```

This uses JPQL (Java Persistence Query Language), which is similar to SQL but works with entities.

---

### Step 3 — Update Service to Use Repository

Update:

**`service/TaskService.java`**

```java
package edu.brooklyn.cisc3130.taskboard.service;

import edu.brooklyn.cisc3130.taskboard.model.Task;
import edu.brooklyn.cisc3130.taskboard.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {
    
    private final TaskRepository taskRepository;
    
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
    
    public Optional<Task> getTaskById(Integer id) {
        return taskRepository.findById(id);
    }
    
    public Task createTask(Task task) {
        if (task.getCompleted() == null) {
            task.setCompleted(false);
        }
        if (task.getPriority() == null) {
            task.setPriority(Task.Priority.MEDIUM);
        }
        return taskRepository.save(task);
    }
    
    public Optional<Task> updateTask(Integer id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.getCompleted());
            task.setPriority(updatedTask.getPriority());
            return taskRepository.save(task);
        });
    }
    
    public boolean deleteTask(Integer id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // New methods using repository queries
    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompletedTrue();
    }
    
    public List<Task> getIncompleteTasks() {
        return taskRepository.findByCompletedFalse();
    }
    
    public List<Task> getTasksByPriority(Task.Priority priority) {
        return taskRepository.findByPriority(priority);
    }
    
    public List<Task> searchTasks(String keyword) {
        return taskRepository.searchTasks(keyword);
    }
    
    public Page<Task> getCompletedTasks(Pageable pageable) {
        return taskRepository.findByCompletedTrue(pageable);
    }
}
```

**Key Changes:**

- Uses `TaskRepository` instead of `ArrayList`
- `@Transactional` - Ensures database operations are atomic
- `save()` - Saves or updates entity
- `findById()` - Finds by primary key
- `deleteById()` - Deletes by primary key

---

### Step 4 — Update Controller with New Endpoints

Update:

**`controller/TaskController.java`**

Add these new endpoints:

```java
@GetMapping("/completed")
public ResponseEntity<List<Task>> getCompletedTasks() {
    List<Task> tasks = taskService.getCompletedTasks();
    return ResponseEntity.ok(tasks);
}

@GetMapping("/incomplete")
public ResponseEntity<List<Task>> getIncompleteTasks() {
    List<Task> tasks = taskService.getIncompleteTasks();
    return ResponseEntity.ok(tasks);
}

@GetMapping("/priority/{priority}")
public ResponseEntity<List<Task>> getTasksByPriority(
        @PathVariable String priority) {
    try {
        Task.Priority priorityEnum = Task.Priority.valueOf(priority.toUpperCase());
        List<Task> tasks = taskService.getTasksByPriority(priorityEnum);
        return ResponseEntity.ok(tasks);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }
}

@GetMapping("/search")
public ResponseEntity<List<Task>> searchTasks(@RequestParam String keyword) {
    List<Task> tasks = taskService.searchTasks(keyword);
    return ResponseEntity.ok(tasks);
}

@GetMapping("/paginated")
public ResponseEntity<Page<Task>> getTasksPaginated(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    Page<Task> tasks = taskService.getAllTasks(pageable);
    return ResponseEntity.ok(tasks);
}
```

**Don't forget to add imports:**

```java
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
```

---

### Step 5 — Configure H2 Database Console

Update:

**`application.properties`**

```properties
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:taskboarddb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# H2 Console (for viewing database)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

**Access H2 Console:**

After starting the application, visit:

**http://localhost:8080/h2-console**

**Connection settings:**
- JDBC URL: `jdbc:h2:mem:taskboarddb`
- Username: `sa`
- Password: (leave empty)
- Click "Connect"

**Using H2 Console:**

1. **View Tables:**
   ```sql
   SHOW TABLES;
   ```

2. **View Table Structure:**
   ```sql
   DESCRIBE tasks;
   ```

3. **Query All Tasks:**
   ```sql
   SELECT * FROM tasks;
   ```

4. **Query Specific Task:**
   ```sql
   SELECT * FROM tasks WHERE id = 1;
   ```

5. **Count Tasks:**
   ```sql
   SELECT COUNT(*) FROM tasks;
   ```

**💡 Important Notes:**

- H2 is an **in-memory database** - data is lost when application stops
- For persistent storage, you'd use PostgreSQL, MySQL, etc. (we'll cover this later)
- H2 Console is only enabled in development (not production)
- The database is created automatically when application starts (due to `ddl-auto=update`)

**Understanding `ddl-auto` Values:**

- `none` - Don't create/update schema (you manage it)
- `update` - Update schema if entities change (good for development)
- `create` - Drop and recreate schema on startup (⚠️ deletes all data!)
- `create-drop` - Create on startup, drop on shutdown
- `validate` - Validate schema matches entities (no changes)

For development, `update` is usually best. For production, use `none` or `validate`.

---

### Step 6 — Add Sample Data (Optional)

Create:

**`data/DataInitializer.java`**

```java
package edu.brooklyn.cisc3130.taskboard.data;

import edu.brooklyn.cisc3130.taskboard.model.Task;
import edu.brooklyn.cisc3130.taskboard.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final TaskRepository taskRepository;
    
    public DataInitializer(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    @Override
    public void run(String... args) {
        if (taskRepository.count() == 0) {
            taskRepository.save(new Task(
                null, "Complete Homework 6", 
                "Finish Spring Data JPA assignment", 
                false, Task.Priority.HIGH, null, null));
            
            taskRepository.save(new Task(
                null, "Study for Midterm", 
                "Review chapters 1-5", 
                false, Task.Priority.HIGH, null, null));
            
            taskRepository.save(new Task(
                null, "Buy groceries", 
                "Milk, eggs, bread", 
                true, Task.Priority.LOW, null, null));
        }
    }
}
```

---

### Step 7 — Test Database Operations

**Test endpoints:**

1. **Create tasks** - They will be saved to database
   - POST several tasks with different priorities and completion statuses
   - Verify they appear in GET /api/tasks

2. **View in H2 Console** - See your data in the database
   - Connect to H2 console
   - Run `SELECT * FROM tasks;`
   - Verify data matches what you created via API

3. **Test search** - `GET /api/tasks/search?keyword=homework`
   - Should return tasks containing "homework" in title or description
   - Test with different keywords
   - Test case-insensitive search

4. **Test pagination** - `GET /api/tasks/paginated?page=0&size=5&sortBy=title`
   - Create at least 10 tasks
   - Test with different page numbers (0, 1, 2)
   - Test with different page sizes (5, 10, 20)
   - Test sorting by different fields (id, title, createdAt)

5. **Test filtering:**
   - `GET /api/tasks/completed` - Should return only completed tasks
   - `GET /api/tasks/incomplete` - Should return only incomplete tasks
   - `GET /api/tasks/priority/HIGH` - Should return only HIGH priority tasks

**Understanding Pagination Response:**

When you call `/api/tasks/paginated?page=0&size=5`, you get:

```json
{
  "content": [
    { "id": 1, "title": "Task 1", ... },
    { "id": 2, "title": "Task 2", ... },
    ...
  ],
  "pageable": { ... },
  "totalElements": 25,
  "totalPages": 5,
  "size": 5,
  "number": 0,
  "first": true,
  "last": false
}
```

- `content`: Array of tasks for this page
- `totalElements`: Total number of tasks
- `totalPages`: Total number of pages
- `size`: Page size
- `number`: Current page number (0-indexed)
- `first`: Is this the first page?
- `last`: Is this the last page?

**Why Use Pagination?**

- **Performance**: Don't load all data at once
- **Memory**: Reduces memory usage
- **User Experience**: Better for large datasets
- **Network**: Smaller response sizes

**Example Pagination Scenarios:**

1. Page 0, size 5: Tasks 1-5
2. Page 1, size 5: Tasks 6-10
3. Page 2, size 5: Tasks 11-15

**Restart server** - ⚠️ Note: With H2 in-memory database, data is lost when application stops. This is expected behavior. In production, you'd use a persistent database.

---

### Homework 6 Deliverables

You must submit:

1. **GitHub repository** with all code
   - Update README with new endpoints
   - Include database schema screenshot

2. **Screenshots** showing:
   - H2 Console with your data (show table structure and sample data)
   - GET /api/tasks/completed endpoint working
   - GET /api/tasks/incomplete endpoint working
   - GET /api/tasks/priority/HIGH endpoint working
   - GET /api/tasks/search?keyword=... endpoint working
   - GET /api/tasks/paginated?page=0&size=5 endpoint working (show pagination metadata)
   - SQL queries visible in console (if `show-sql=true`)

3. **3–5 minute explanation video**

**Your video must explain:**

• how JPA entities work (show @Entity, @Id, @Column annotations)
• how repositories work (explain method naming conventions)
• how you implemented search/pagination (show code and demonstrate)
• one challenge you faced (database setup, queries, etc.)
• one thing you learned about Spring Data JPA (repositories, queries, etc.)

### Grading Rubric (Homework 6)

| Criteria | Points | Description |
|----------|--------|-------------|
| **JPA Entity** | 20 | <ul><li>@Entity annotation present (3 points)</li><li>@Id and @GeneratedValue configured correctly (5 points)</li><li>@Column annotations with proper constraints (5 points)</li><li>@PrePersist and @PreUpdate for timestamps (4 points)</li><li>Enum for Priority properly configured (3 points)</li></ul> |
| **Repository** | 20 | <ul><li>Repository extends JpaRepository (5 points)</li><li>At least 3 custom query methods using naming conventions (10 points)</li><li>Custom @Query method for search (5 points)</li></ul> |
| **Service Updates** | 15 | <ul><li>Service uses repository instead of ArrayList (5 points)</li><li>@Transactional annotation on service (3 points)</li><li>All CRUD operations use repository methods (5 points)</li><li>New service methods for filtering/search (2 points)</li></ul> |
| **New Endpoints** | 20 | <ul><li>GET /api/tasks/completed - Returns completed tasks (4 points)</li><li>GET /api/tasks/incomplete - Returns incomplete tasks (4 points)</li><li>GET /api/tasks/priority/{priority} - Filters by priority (4 points)</li><li>GET /api/tasks/search?keyword=... - Search functionality (4 points)</li><li>GET /api/tasks/paginated - Pagination with sorting (4 points)</li></ul> |
| **Database Integration** | 10 | <ul><li>H2 database configured in application.properties (3 points)</li><li>H2 console accessible and working (3 points)</li><li>Data persists correctly (shows in H2 console) (4 points)</li></ul> |
| **Documentation** | 10 | <ul><li>README updated with new endpoints (3 points)</li><li>Screenshot of H2 console with data (3 points)</li><li>Screenshots of new endpoints working (4 points)</li></ul> |
| **Video Submission** | 5 | <ul><li>Explains JPA entities and annotations (2 points)</li><li>Demonstrates repository queries (2 points)</li><li>Shows pagination/search working (1 point)</li></ul> |

**Total: 100 points**

**Detailed Scoring Breakdown:**

- **JPA Entity (20 points):**
  - Basic entity setup: 8 points
  - Timestamp handling: 4 points
  - Column constraints: 5 points
  - Enum configuration: 3 points

- **Repository (20 points):**
  - JpaRepository extension: 5 points
  - Custom query methods: 10 points
  - @Query annotation: 5 points

- **New Endpoints (20 points):**
  - Each endpoint: 4 points (5 endpoints total)

**Common Mistakes to Avoid:**

1. ❌ Forgetting `@Entity` annotation on Task class
2. ❌ Not updating `application.properties` with database settings
3. ❌ Forgetting to change `ddl-auto` from `none` to `update`
4. ❌ Not importing `Pageable`, `Page`, `Sort` classes
5. ❌ Incorrect repository method naming (case-sensitive!)
6. ❌ Not handling `Optional` correctly in service
7. ❌ Forgetting `@Transactional` on service methods that modify data

---

**📋 [View Grading Rubric for Homework 6 →](GRADING_RUBRICS.md#homework-6)**
