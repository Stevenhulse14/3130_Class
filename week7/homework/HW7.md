## Homework 7: Advanced Features and Exception Handling

### Learning Goals

In this homework you will learn:

• how to implement global exception handling
• how to create custom exceptions
• how to use DTOs (Data Transfer Objects)
• how to implement soft delete
• how to add request/response logging
• how to implement proper error responses
• how to add API documentation

At the end of this assignment, your API will have robust error handling and better structure.

---

### Prerequisites

Complete Homework 6 before starting this assignment.

---

### Step 1 — Create Custom Exceptions

Create a package:

**`exception`**

Create:

**`TaskNotFoundException.java`**

```java
package edu.brooklyn.cisc3130.taskboard.exception;

public class TaskNotFoundException extends RuntimeException {
    
    public TaskNotFoundException(Integer id) {
        super("Task with ID " + id + " not found");
    }
}
```

Create:

**`InvalidTaskDataException.java`**

```java
package edu.brooklyn.cisc3130.taskboard.exception;

public class InvalidTaskDataException extends RuntimeException {
    
    public InvalidTaskDataException(String message) {
        super(message);
    }
}
```

---

### Step 2 — Create Error Response DTO

Create a package:

**`dto`**

Create:

**`ErrorResponse.java`**

```java
package edu.brooklyn.cisc3130.taskboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    
    public ErrorResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
```

---

### Step 3 — Create Global Exception Handler

Create:

**`exception/GlobalExceptionHandler.java`**

```java
package edu.brooklyn.cisc3130.taskboard.exception;

import edu.brooklyn.cisc3130.taskboard.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFound(
            TaskNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InvalidTaskDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTaskData(
            InvalidTaskDataException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", java.time.LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Failed");
        response.put("message", "Input validation failed");
        response.put("errors", errors);
        response.put("path", request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            "An unexpected error occurred",
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

**Key Concepts:**

- `@RestControllerAdvice` - Global exception handler for all controllers
- `@ExceptionHandler` - Handles specific exception types
- Centralized error handling - Consistent error responses

---

### Step 4 — Update Service to Throw Exceptions

Update:

**`service/TaskService.java`**

```java
public Task getTaskById(Integer id) {
    return taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
}

public Task updateTask(Integer id, Task updatedTask) {
    Task task = getTaskById(id); // This will throw if not found
    task.setTitle(updatedTask.getTitle());
    task.setDescription(updatedTask.getDescription());
    task.setCompleted(updatedTask.getCompleted());
    task.setPriority(updatedTask.getPriority());
    return taskRepository.save(task);
}

public void deleteTask(Integer id) {
    if (!taskRepository.existsById(id)) {
        throw new TaskNotFoundException(id);
    }
    taskRepository.deleteById(id);
}
```

---

### Step 5 — Implement Soft Delete

Add to **`Task.java`**:

```java
@Column(nullable = false)
private Boolean deleted = false;
```

Add to **`TaskRepository.java`**:

```java
// Find only non-deleted tasks
List<Task> findByDeletedFalse();

// Find only deleted tasks
List<Task> findByDeletedTrue();

// Override findAll to exclude deleted
@Query("SELECT t FROM Task t WHERE t.deleted = false")
List<Task> findAllActive();
```

Update **`TaskService.java`**:

```java
public List<Task> getAllTasks() {
    return taskRepository.findByDeletedFalse();
}

public void deleteTask(Integer id) {
    Task task = getTaskById(id);
    task.setDeleted(true);
    taskRepository.save(task);
}

public void restoreTask(Integer id) {
    Task task = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
    task.setDeleted(false);
    taskRepository.save(task);
}
```

---

### Step 6 — Create Task DTOs

Create:

**`dto/TaskRequest.java`**

```java
package edu.brooklyn.cisc3130.taskboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequest {
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    private Boolean completed;
    
    private String priority;
}
```

Create:

**`dto/TaskResponse.java`**

```java
package edu.brooklyn.cisc3130.taskboard.dto;

import edu.brooklyn.cisc3130.taskboard.model.Task;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Integer id;
    private String title;
    private String description;
    private Boolean completed;
    private String priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public static TaskResponse fromEntity(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setCompleted(task.getCompleted());
        response.setPriority(task.getPriority().name());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        return response;
    }
}
```

**Why use DTOs?**

- **Separation of Concerns**: API contract separate from database model
- **Security**: Don't expose internal fields (like `deleted`, internal IDs)
- **Flexibility**: Different endpoints can return different data structures
- **Versioning**: Can change DTOs without changing entities
- **Performance**: Can include/exclude fields to reduce payload size
- **Validation**: Can have different validation rules for API vs database

**DTO Pattern Example:**

```
Client Request (JSON)
    ↓
TaskRequest DTO (validated)
    ↓
Service converts to Entity
    ↓
Entity saved to Database
    ↓
Entity retrieved from Database
    ↓
Service converts to TaskResponse DTO
    ↓
Client Response (JSON)
```

**Benefits:**

1. **TaskRequest** - Only fields client can send (no ID, timestamps)
2. **Task Entity** - Full database model with all fields
3. **TaskResponse** - Only fields client should see (no internal fields)

**Example:**

Entity might have:
```java
private Integer id;
private String title;
private Boolean deleted;  // Internal field
private LocalDateTime createdAt;
```

TaskResponse DTO only exposes:
```java
private Integer id;
private String title;
private LocalDateTime createdAt;
// No 'deleted' field - client doesn't need to know
```

---

### Step 7 — Add Request/Response Logging

Create:

**`config/LoggingFilter.java`**

```java
package edu.brooklyn.cisc3130.taskboard.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        
        long startTime = System.currentTimeMillis();
        
        filterChain.doFilter(request, response);
        
        long duration = System.currentTimeMillis() - startTime;
        
        logger.info("{} {} - Status: {} - Duration: {}ms",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                duration);
    }
}
```

---

### Step 8 — Update Controller to Use DTOs

Update **`TaskController.java`** to use DTOs:

```java
@PostMapping
public ResponseEntity<TaskResponse> createTask(
        @Valid @RequestBody TaskRequest taskRequest) {
    Task task = new Task();
    task.setTitle(taskRequest.getTitle());
    task.setDescription(taskRequest.getDescription());
    task.setCompleted(taskRequest.getCompleted() != null ? taskRequest.getCompleted() : false);
    task.setPriority(Task.Priority.valueOf(
        taskRequest.getPriority() != null ? 
        taskRequest.getPriority().toUpperCase() : "MEDIUM"));
    
    Task createdTask = taskService.createTask(task);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(TaskResponse.fromEntity(createdTask));
}
```

---

### Step 9 — Add Actuator for Health Monitoring

Update **`pom.xml`** (if not already added):

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Update **`application.properties`**:

```properties
# Actuator Configuration
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=when-authorized
```

**Access health endpoint:**

**http://localhost:8080/actuator/health**

**Expected Response:**
```json
{
  "status": "UP"
}
```

**Other Actuator Endpoints:**

- `/actuator/info` - Application information
- `/actuator/metrics` - Application metrics
- `/actuator/metrics/{metric.name}` - Specific metric details

**Understanding Actuator:**

Spring Boot Actuator provides production-ready features:
- **Health checks**: Is the application running?
- **Metrics**: Performance data, request counts, etc.
- **Info**: Application version, build info
- **Environment**: Configuration properties

**Why Use Actuator?**

- **Monitoring**: Check if application is healthy
- **Debugging**: See metrics and configuration
- **Production**: Essential for production deployments
- **DevOps**: Integrates with monitoring tools

**Security Note:**

In production, secure actuator endpoints! Don't expose sensitive information publicly.

---

### Homework 7 Deliverables

You must submit:

1. **GitHub repository** with all code
2. **Screenshots** showing:
   - Error responses (404, 400, 500)
   - Soft delete working
   - Health endpoint
   - Request logging in console
3. **3–5 minute explanation video**

**Your video must explain:**

• how exception handling works
• why DTOs are useful
• how soft delete works
• one challenge you faced
• one thing you learned about error handling

---

**📋 [View Grading Rubric for Homework 7 →](GRADING_RUBRICS.md#homework-7)**
