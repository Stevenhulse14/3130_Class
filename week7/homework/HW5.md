## Homework 5: Creating Your First Spring Boot API with Validation

### Learning Goals

In this homework you will learn:

• how Spring Boot projects are structured
• how to create a REST API with proper HTTP methods
• how controllers work and handle requests
• how Spring Boot returns JSON responses
• how to validate input using Spring Validation
• how to use Lombok to reduce boilerplate code
• how to structure a Spring Boot project properly

At the end of this assignment you will have a working backend server with input validation.

---

### Step 1 — Install Required Tools

#### Install Java

Install **Java 17 or newer** (Java 21 recommended).

**Download from:**

https://adoptium.net

**Verify installation:**

```bash
java -version
```

You should see something like:
```
openjdk version "21.0.1" 2023-10-17
```

#### Install an IDE

Use one of the following:

• **IntelliJ IDEA** (recommended) - Community Edition is free
  - Download: https://www.jetbrains.com/idea/download/
• **VS Code** with Java extensions
  - Install: Extension Pack for Java

#### Install Postman (Recommended)

Download Postman for API testing:
https://www.postman.com/downloads/

---

### Step 2 — Generate a Spring Boot Project

Go to:

**https://start.spring.io**

Configure the project:

| Setting | Value |
|---------|-------|
| **Project** | Maven |
| **Language** | Java |
| **Spring Boot** | 3.2.x (latest stable) |
| **Group** | edu.brooklyn.cisc3130 |
| **Artifact** | campus-taskboard |
| **Name** | CampusTaskboardApplication |
| **Package name** | edu.brooklyn.cisc3130.taskboard |
| **Packaging** | Jar |
| **Java** | 17 or 21 |

**Dependencies to add:**

- ✅ **Spring Web** - For REST API support
- ✅ **Spring Boot DevTools** - For hot reload during development
- ✅ **Validation** - For input validation
- ✅ **Lombok** - For reducing boilerplate code
- ✅ **Spring Data JPA** - For database operations (we'll use it in HW6)
- ✅ **H2 Database** - In-memory database (we'll use it in HW6)

**Click "Generate"** and download the ZIP file.

**Extract and open** the project in your IDE.

**💡 Important Notes:**

- Make sure you select **Java 17 or 21** (not Java 8 or 11)
- The **Group** and **Artifact** names will become your package structure
- **Maven** is the build tool - it manages dependencies automatically
- After downloading, extract the ZIP file to a location you can easily find

**Troubleshooting Project Generation:**

- If the download doesn't start, check your browser's download settings
- If the ZIP file is corrupted, try generating again
- Make sure you have enough disk space (project is ~50MB when extracted)

---

### Step 3 — Understand the Project Structure

After opening the project, you should see this structure:

```
campus-taskboard/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/brooklyn/cisc3130/taskboard/
│   │   │       └── CampusTaskboardApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── edu/brooklyn/cisc3130/taskboard/
└── pom.xml
```

**Key Files:**

- `CampusTaskboardApplication.java` - Main entry point (the class with `@SpringBootApplication`)
- `application.properties` - Configuration file (server port, database settings, etc.)
- `pom.xml` - Maven dependencies (lists all libraries your project uses)

**Understanding the Project Structure:**

```
campus-taskboard/
├── .mvn/                          # Maven wrapper (allows running without Maven installed)
├── src/
│   ├── main/
│   │   ├── java/                  # Your Java source code goes here
│   │   │   └── edu/brooklyn/cisc3130/taskboard/
│   │   │       └── CampusTaskboardApplication.java
│   │   └── resources/             # Configuration files, static resources
│   │       └── application.properties
│   └── test/                      # Unit tests go here
│       └── java/
│           └── edu/brooklyn/cisc3130/taskboard/
├── pom.xml                        # Maven project file (dependencies)
├── .gitignore                     # Files Git should ignore
└── mvnw / mvnw.cmd               # Maven wrapper scripts
```

**Package Structure Explanation:**

- Packages in Java are like folders for organizing code
- `edu.brooklyn.cisc3130.taskboard` is the root package
- All your classes will be in sub-packages like:
  - `edu.brooklyn.cisc3130.taskboard.model` - Data models
  - `edu.brooklyn.cisc3130.taskboard.controller` - REST controllers
  - `edu.brooklyn.cisc3130.taskboard.service` - Business logic
  - `edu.brooklyn.cisc3130.taskboard.repository` - Database access

---

### Step 4 — Understand the Main Class

Open:

**`CampusTaskboardApplication.java`**

You should see:

```java
package edu.brooklyn.cisc3130.taskboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CampusTaskboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusTaskboardApplication.class, args);
    }
}
```

**What does `@SpringBootApplication` do?**

This annotation is a combination of three annotations:
- `@SpringBootConfiguration` - Marks this as a Spring configuration class
- `@EnableAutoConfiguration` - Enables Spring Boot's auto-configuration
- `@ComponentScan` - Scans for Spring components in the package

**When this file runs, Spring Boot:**

• starts an embedded web server (Tomcat by default)
• loads application configuration
• enables API endpoints
• scans for Spring components (controllers, services, repositories)

---

### Step 5 — Create the Task Model with Validation

Create a package called:

**`model`**

Inside: `src/main/java/edu/brooklyn/cisc3130/taskboard/model/`

Create:

**`Task.java`**

```java
package edu.brooklyn.cisc3130.taskboard.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    
    private Integer id;
    
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    private Boolean completed;
    
    private String priority; // "LOW", "MEDIUM", "HIGH"
    
    // Constructor for creating tasks without ID (ID will be auto-generated)
    public Task(String title, String description, Boolean completed, String priority) {
        this.title = title;
        this.description = description;
        this.completed = completed != null ? completed : false;
        this.priority = priority != null ? priority : "MEDIUM";
    }
}
```

**Explanation:**

- `@Data` (Lombok) - Generates getters, setters, toString, equals, hashCode
- `@NoArgsConstructor` - Generates a no-argument constructor
- `@AllArgsConstructor` - Generates a constructor with all fields
- `@NotBlank` - Ensures the field is not null, empty, or whitespace
- `@Size` - Validates string length

**💡 Hint:** If Lombok isn't working in your IDE, you may need to install the Lombok plugin:
- **IntelliJ IDEA**: 
  1. Go to File → Settings → Plugins
  2. Search for "Lombok"
  3. Click Install
  4. Restart IDE
  5. Go to File → Settings → Build, Execution, Deployment → Compiler → Annotation Processors
  6. Check "Enable annotation processing"
- **VS Code**: 
  1. Install "Language Support for Java" extension
  2. Install "Lombok Annotations Support" extension
  3. Restart VS Code

**Understanding Lombok Annotations:**

- `@Data` generates: getters, setters, `toString()`, `equals()`, `hashCode()`
- Without Lombok, you'd write ~50 lines of boilerplate code
- Lombok generates this code at compile time (not runtime)
- Your IDE may show warnings until Lombok plugin is installed

**Alternative (Without Lombok):**

If you prefer not to use Lombok, you can write getters and setters manually:

```java
public class Task {
    private Integer id;
    private String title;
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    // ... repeat for all fields
}
```

However, Lombok is widely used in Spring Boot projects and saves significant time.

---

### Step 6 — Create a Service Layer

Create a package:

**`service`**

Create:

**`TaskService.java`**

```java
package edu.brooklyn.cisc3130.taskboard.service;

import edu.brooklyn.cisc3130.taskboard.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskService {
    
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);
    
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
    
    public Optional<Task> getTaskById(Integer id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }
    
    public Task createTask(Task task) {
        task.setId(idGenerator.getAndIncrement());
        if (task.getCompleted() == null) {
            task.setCompleted(false);
        }
        if (task.getPriority() == null || task.getPriority().isEmpty()) {
            task.setPriority("MEDIUM");
        }
        tasks.add(task);
        return task;
    }
    
    public Optional<Task> updateTask(Integer id, Task updatedTask) {
        return getTaskById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.getCompleted());
            task.setPriority(updatedTask.getPriority());
            return task;
        });
    }
    
    public boolean deleteTask(Integer id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }
}
```

**Why use a Service layer?**

- **Separation of Concerns**: Controllers handle HTTP requests, services handle business logic
- **Testability**: You can test business logic without starting a web server
- **Reusability**: Service methods can be called from multiple controllers
- **Single Responsibility Principle**: Each class has one job
- **Easier to maintain**: Changes to business logic don't affect controller code

**Understanding Dependency Injection:**

Notice the constructor:
```java
public TaskController(TaskService taskService) {
    this.taskService = taskService;
}
```

Spring Boot automatically creates a `TaskService` instance and passes it to the controller. This is called **Dependency Injection** (DI). You don't need to write `new TaskService()` - Spring handles it!

**Service Layer Pattern:**

```
Controller (HTTP Layer)
    ↓ calls
Service (Business Logic Layer)
    ↓ uses
Repository (Data Access Layer) - We'll add this in HW6
```

This is a common pattern in Spring Boot applications.

---

### Step 7 — Create a Controller with Validation

Create a package:

**`controller`**

Create:

**`TaskController.java`**

```java
package edu.brooklyn.cisc3130.taskboard.controller;

import edu.brooklyn.cisc3130.taskboard.model.Task;
import edu.brooklyn.cisc3130.taskboard.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private final TaskService taskService;
    
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Integer id,
            @Valid @RequestBody Task task) {
        Optional<Task> updatedTask = taskService.updateTask(id, task);
        return updatedTask.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        boolean deleted = taskService.deleteTask(id);
        return deleted ? ResponseEntity.noContent().build() 
                       : ResponseEntity.notFound().build();
    }
    
    // Exception handler for validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
```

**Key Annotations Explained:**

- `@RestController` - Combines `@Controller` and `@ResponseBody` (returns JSON)
- `@RequestMapping("/api/tasks")` - Base path for all endpoints in this controller
- `@GetMapping` - Handles HTTP GET requests
- `@PostMapping` - Handles HTTP POST requests
- `@PutMapping` - Handles HTTP PUT requests
- `@DeleteMapping` - Handles HTTP DELETE requests
- `@PathVariable` - Extracts value from URL path
- `@RequestBody` - Converts JSON request body to Java object
- `@Valid` - Triggers validation on the object
- `ResponseEntity` - Allows control over HTTP status codes and headers

**💡 Hint:** Notice how we return `ResponseEntity` instead of just the object. This gives us control over HTTP status codes:
- `200 OK` - Successful GET/PUT
- `201 CREATED` - Successful POST
- `204 NO CONTENT` - Successful DELETE
- `404 NOT FOUND` - Resource not found

**Understanding HTTP Methods:**

- **GET**: Retrieve data (should not modify anything)
- **POST**: Create new resources
- **PUT**: Update entire resource (replace it)
- **PATCH**: Update part of a resource (we'll use this later)
- **DELETE**: Remove a resource

**RESTful API Design Principles:**

1. **Use nouns, not verbs** in URLs: `/api/tasks` not `/api/getTasks`
2. **Use HTTP methods** to indicate actions: GET, POST, PUT, DELETE
3. **Use plural nouns**: `/api/tasks` not `/api/task`
4. **Use status codes** appropriately: 200, 201, 404, 400, etc.
5. **Return JSON** for data exchange

**Understanding `@PathVariable` vs `@RequestParam`:**

- `@PathVariable`: Extracts from URL path: `/api/tasks/{id}` → `id` is a path variable
- `@RequestParam`: Extracts from query string: `/api/tasks?completed=true` → `completed` is a request parameter

**Example:**
```java
@GetMapping("/{id}")                    // Path variable
public Task getTask(@PathVariable Integer id) { ... }

@GetMapping("/search")                   // Request parameter
public List<Task> search(@RequestParam String keyword) { ... }
```

**Understanding Optional and Map:**

```java
Optional<Task> task = taskService.getTaskById(id);
return task.map(ResponseEntity::ok)
           .orElse(ResponseEntity.notFound().build());
```

This code:
1. Gets an `Optional<Task>` (may or may not contain a task)
2. If task exists: return `ResponseEntity.ok(task)` (200 status)
3. If task doesn't exist: return `ResponseEntity.notFound()` (404 status)

This is a clean way to handle "maybe" values in Java.

---

### Step 8 — Configure Application Properties

Open:

**`src/main/resources/application.properties`**

Add these configurations:

```properties
# Server Configuration
server.port=8080

# Application Name
spring.application.name=campus-taskboard-api

# Logging
logging.level.edu.brooklyn.cisc3130.taskboard=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n

# H2 Database (for future use)
spring.datasource.url=jdbc:h2:mem:taskboarddb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration (for future use)
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false
```

---

### Step 9 — Run the Application

**Option 1: Using IDE**

Right-click on `CampusTaskboardApplication.java` → Run

**Option 2: Using Command Line**

```bash
cd campus-taskboard
./mvnw spring-boot:run
```

**On Windows:**

```bash
mvnw.cmd spring-boot:run
```

Spring Boot will start a server at:

**http://localhost:8080**

You should see output like:

```
Started CampusTaskboardApplication in 2.456 seconds
```

---

### Step 10 — Test the Endpoints

#### Setting Up Postman

1. **Create a new Collection:**
   - Click "New" → "Collection"
   - Name it "Campus Task Board API"

2. **Set Collection Variables:**
   - Click on collection → Variables tab
   - Add variable: `base_url` = `http://localhost:8080`
   - Use `{{base_url}}` in requests

3. **Create Environment (Optional but Recommended):**
   - Click gear icon → Add
   - Name: "Local Development"
   - Add variable: `base_url` = `http://localhost:8080`
   - Select this environment

#### Using Postman

**1. GET all tasks:**

**Request:**
- Method: `GET`
- URL: `http://localhost:8080/api/tasks`
- Headers: (none needed for GET)

**Expected Response:**
- Status: `200 OK`
- Body: `[]` (empty array initially)

**Screenshot what to show:**
- Request URL
- Response status code
- Response body (JSON)

**2. POST create a task:**

**Request:**
- Method: `POST`
- URL: `http://localhost:8080/api/tasks`
- Headers:
  ```
  Content-Type: application/json
  ```
- Body (raw JSON):
  ```json
  {
    "title": "Complete Homework 5",
    "description": "Finish Spring Boot API assignment",
    "completed": false,
    "priority": "HIGH"
  }
  ```

**Expected Response:**
- Status: `201 Created`
- Body: Task object with ID assigned:
  ```json
  {
    "id": 1,
    "title": "Complete Homework 5",
    "description": "Finish Spring Boot API assignment",
    "completed": false,
    "priority": "HIGH"
  }
  ```

**💡 Postman Tips:**
- Click "Save" to save requests
- Use "Send and Save" to save response examples
- Use "Tests" tab to write automated tests (optional)

**2. POST create a task:**

```
POST http://localhost:8080/api/tasks
Content-Type: application/json

{
  "title": "Complete Homework 5",
  "description": "Finish Spring Boot API assignment",
  "completed": false,
  "priority": "HIGH"
}
```

Expected response: `201 Created` with the created task including an ID

**3. GET task by ID:**

```
GET http://localhost:8080/api/tasks/1
```

**4. PUT update a task:**

```
PUT http://localhost:8080/api/tasks/1
Content-Type: application/json

{
  "title": "Complete Homework 5",
  "description": "Finish Spring Boot API assignment - UPDATED",
  "completed": true,
  "priority": "HIGH"
}
```

**5. DELETE a task:**

```
DELETE http://localhost:8080/api/tasks/1
```

Expected response: `204 No Content`

#### Test Validation

Try creating a task with invalid data:

```
POST http://localhost:8080/api/tasks
Content-Type: application/json

{
  "title": "AB",
  "description": "This description is way too long and exceeds the maximum allowed length of 500 characters. " + 
                  "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                  "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                  "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris. " +
                  "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum. " +
                  "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia."
}
```

Expected response: `400 Bad Request` with validation errors:

```json
{
  "title": "Title must be between 3 and 100 characters",
  "description": "Description cannot exceed 500 characters"
}
```

---

### Step 11 — Test Using Browser (GET requests only)

Open your browser:

**http://localhost:8080/api/tasks**

You should see JSON data (or an empty array `[]` if no tasks exist).

---

### Common Issues and Solutions

**Issue 1: Port 8080 already in use**

**Error Message:** `Port 8080 was already in use`

**Solution:** 
1. Find what's using port 8080:
   - **Mac/Linux**: `lsof -i :8080` or `netstat -an | grep 8080`
   - **Windows**: `netstat -ano | findstr :8080`
2. Stop the process or change your port:
   ```properties
   server.port=8081
   ```

**Issue 2: Lombok not working**

**Symptoms:** 
- IDE shows errors on `@Data`, `@NoArgsConstructor`, etc.
- Getters/setters not available
- Code won't compile

**Solution:** 
1. **IntelliJ IDEA**:
   - File → Settings → Plugins → Search "Lombok" → Install → Restart
   - File → Settings → Build → Compiler → Annotation Processors → Enable
2. **VS Code**:
   - Install "Language Support for Java" extension
   - Install "Lombok Annotations Support" extension
3. Verify: Right-click on class → Generate → You should see Lombok options

**Issue 3: Validation not working**

**Symptoms:**
- Invalid data is accepted without errors
- No validation error messages returned

**Solution:** 
1. Check `pom.xml` has validation dependency:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-validation</artifactId>
   </dependency>
   ```
2. Make sure `@Valid` is before `@RequestBody`:
   ```java
   public ResponseEntity<Task> createTask(@Valid @RequestBody Task task)
   ```
3. Check validation annotations are on the model fields
4. Restart the application

**Issue 4: 404 Not Found**

**Symptoms:**
- Endpoint returns 404 even though code looks correct

**Solution:** 
1. Check controller has `@RestController` annotation
2. Check `@RequestMapping` path matches exactly (case-sensitive)
3. Check server is running (look for "Started CampusTaskboardApplication")
4. Check URL is correct: `http://localhost:8080/api/tasks` (not `/api/task`)
5. Check HTTP method matches: GET vs POST vs PUT vs DELETE
6. Check for typos in URL path

**Issue 5: 500 Internal Server Error**

**Symptoms:**
- Server crashes or returns 500 error
- Check console logs for stack trace

**Common Causes:**
- Null pointer exception
- Missing dependency
- Database connection issue
- Invalid data format

**Solution:**
- Read the error message in console
- Check stack trace for line number
- Verify all dependencies are in `pom.xml`
- Check that required fields are not null

**Issue 6: Cannot resolve symbol errors**

**Symptoms:**
- IDE shows red underlines on imports
- "Cannot resolve symbol" errors

**Solution:**
1. Right-click project → Maven → Reload Project
2. File → Invalidate Caches / Restart (IntelliJ)
3. Check `pom.xml` has correct dependencies
4. Run `mvn clean install` in terminal
5. Make sure JDK is set correctly in IDE settings

**Issue 7: Application won't start**

**Symptoms:**
- Error on startup
- "Port already in use" or other errors

**Solution:**
1. Check console for error messages
2. Verify Java version: `java -version` (should be 17+)
3. Check `application.properties` for syntax errors
4. Verify all required dependencies are present
5. Try: `mvn clean install` then restart

**Issue 8: JSON parsing errors**

**Symptoms:**
- "Cannot deserialize" errors
- 400 Bad Request when sending JSON

**Solution:**
1. Check JSON syntax (use JSON validator online)
2. Verify field names match exactly (case-sensitive)
3. Check that request has `Content-Type: application/json` header
4. Verify model class has matching field names
5. Check for missing getters/setters (if not using Lombok)

**Debugging Tips:**

1. **Enable debug logging:**
   ```properties
   logging.level.edu.brooklyn.cisc3130.taskboard=DEBUG
   ```

2. **Check application logs:**
   - Look for error messages in console
   - Spring Boot logs are very helpful

3. **Use Postman Console:**
   - View → Show Postman Console
   - See exact request/response

4. **Add breakpoints:**
   - Set breakpoints in IDE
   - Debug mode to step through code

5. **Test incrementally:**
   - Test one endpoint at a time
   - Start with simple GET request

---

### Homework 5 Deliverables

You must submit:

1. **GitHub repository** with all code
   - Include a README.md with:
     - Project description
     - How to run the application
     - API endpoints documentation
     - Video link
   - Make sure repository is public or accessible to instructor
   - Include `.gitignore` file (Spring Initializr creates this)

2. **Screenshots** showing:
   - Application starting successfully (console output)
   - GET /api/tasks endpoint working (Postman or browser)
   - POST /api/tasks creating a task (show request and response)
   - GET /api/tasks/{id} retrieving a task
   - PUT /api/tasks/{id} updating a task
   - DELETE /api/tasks/{id} deleting a task
   - Validation error example (show invalid request and error response)
   - At least 2-3 tasks created and displayed

3. **Postman collection** (exported JSON file) OR screenshots showing all endpoints:
   - Save Postman collection: Collection → Export → Collection v2.1
   - Include this file in your repository
   - OR provide clear screenshots of each endpoint

4. **3–5 minute explanation video**
   - Upload to YouTube (unlisted), Loom, or similar
   - Include link in GitHub README

**Your video must explain:**

• what you built (all endpoints)
• how the controller works (show code and explain annotations)
• how validation works (demonstrate with invalid data)
• one challenge you faced (be specific)
• one thing you learned about Spring Boot (explain why it's useful)

---

**📋 [View Grading Rubric for Homework 5 →](GRADING_RUBRICS.md#homework-5)**

