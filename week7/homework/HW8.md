## Homework 8: Security and Final Enhancements

### Learning Goals

In this homework you will learn:

• how to implement Spring Security
• how to add authentication
• how to secure endpoints
• how to add CORS configuration
• how to implement API versioning
• how to add comprehensive testing
• how to document your API

At the end of this assignment, your API will be production-ready with security.

---

### Prerequisites

Complete Homework 7 before starting this assignment.

---

### Step 1 — Add Spring Security Dependency

Update **`pom.xml`**:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

---

### Step 2 — Create Security Configuration

Create:

**`config/SecurityConfig.java`**

```java
package edu.brooklyn.cisc3130.taskboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable()) // Disable for API (use token-based auth in production)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/tasks/**").permitAll() // Allow all task endpoints
                .requestMatchers("/h2-console/**").permitAll() // Allow H2 console
                .requestMatchers("/actuator/**").permitAll() // Allow actuator
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions().disable()); // Allow H2 console frames
        
        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
```

**💡 Note:** This is a basic security setup. In production, you would:
- Use JWT tokens for authentication
- Store user credentials securely (hashed passwords)
- Implement role-based access control (RBAC)
- Use HTTPS instead of HTTP
- Enable CSRF protection for state-changing operations
- Rate limiting to prevent abuse
- Input sanitization
- Security headers (CORS, CSP, etc.)

**Understanding Spring Security:**

Spring Security provides:
- **Authentication**: Who are you? (login)
- **Authorization**: What can you do? (permissions)
- **Protection**: CSRF, XSS, SQL injection protection

**Security Filter Chain:**

```
Request
    ↓
Security Filter Chain
    ↓
CORS Filter
    ↓
CSRF Filter (if enabled)
    ↓
Authentication Filter
    ↓
Authorization Filter
    ↓
Controller
```

**Current Setup:**

- **CORS**: Enabled for localhost:3000 and localhost:8080
- **CSRF**: Disabled (common for REST APIs using tokens)
- **Authentication**: Not implemented (all endpoints public)
- **Authorization**: Not implemented

**For Production:**

You would add:
```java
@Bean
public UserDetailsService userDetailsService() {
    // Load users from database
}

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

But that's beyond the scope of this homework!

---

### Step 3 — Add API Versioning

Create:

**`controller/v1/TaskControllerV1.java`**

```java
package edu.brooklyn.cisc3130.taskboard.controller.v1;

import edu.brooklyn.cisc3130.taskboard.dto.TaskRequest;
import edu.brooklyn.cisc3130.taskboard.dto.TaskResponse;
import edu.brooklyn.cisc3130.taskboard.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskControllerV1 {
    
    private final TaskService taskService;
    
    public TaskControllerV1(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks().stream()
                .map(TaskResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasks);
    }
    
    // ... other endpoints similar to before
}
```

**Why version APIs?**

- Allows breaking changes without affecting existing clients
- Enables gradual migration
- Better API lifecycle management

---

### Step 4 — Add Integration Tests

Create:

**`test/TaskControllerIntegrationTest.java`**

```java
package edu.brooklyn.cisc3130.taskboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.brooklyn.cisc3130.taskboard.dto.TaskRequest;
import edu.brooklyn.cisc3130.taskboard.model.Task;
import edu.brooklyn.cisc3130.taskboard.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
    }
    
    @Test
    void testCreateTask() throws Exception {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("Test Task");
        taskRequest.setDescription("Test Description");
        taskRequest.setCompleted(false);
        taskRequest.setPriority("HIGH");
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.id").exists());
    }
    
    @Test
    void testGetTaskById() throws Exception {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setCompleted(false);
        task.setPriority(Task.Priority.HIGH);
        Task savedTask = taskRepository.save(task);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/" + savedTask.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }
    
    @Test
    void testGetTaskByIdNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/999"))
                .andExpect(status().isNotFound());
    }
}
```

---

### Step 5 — Add API Documentation with SpringDoc

Add dependency to **`pom.xml`**:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

Create:

**`config/OpenApiConfig.java`**

```java
package edu.brooklyn.cisc3130.taskboard.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Campus Task Board API")
                        .version("1.0.0")
                        .description("API for managing campus tasks"));
    }
}
```

**Access API documentation:**

**http://localhost:8080/swagger-ui.html**

**Using Swagger UI:**

1. **View All Endpoints:**
   - See all available endpoints
   - See HTTP methods (GET, POST, PUT, DELETE)
   - See request/response schemas

2. **Test Endpoints:**
   - Click "Try it out" on any endpoint
   - Fill in parameters
   - Click "Execute"
   - See response

3. **View Schemas:**
   - See TaskRequest structure
   - See TaskResponse structure
   - See ErrorResponse structure

**Benefits of API Documentation:**

- **Self-documenting**: Code generates documentation
- **Interactive**: Test endpoints directly
- **Always up-to-date**: Changes in code update docs
- **Team collaboration**: Everyone sees same API contract

**Adding More Documentation:**

You can add descriptions using annotations:

```java
@Operation(summary = "Get all tasks", description = "Retrieve a list of all tasks")
@GetMapping
public ResponseEntity<List<TaskResponse>> getAllTasks() { ... }

@Parameter(description = "Task ID", required = true)
@GetMapping("/{id}")
public ResponseEntity<TaskResponse> getTaskById(@PathVariable Integer id) { ... }
```

**Alternative: OpenAPI YAML**

You can also write OpenAPI specification manually, but SpringDoc generates it automatically from your code.

---

### Step 6 — Add Request Validation Enhancements

Create custom validator:

**`validation/ValidPriority.java`**

```java
package edu.brooklyn.cisc3130.taskboard.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PriorityValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPriority {
    String message() default "Invalid priority. Must be LOW, MEDIUM, or HIGH";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

---

### Step 7 — Create README for API

Create:

**`API_README.md`**

```markdown
# Campus Task Board API Documentation

## Base URL
http://localhost:8080/api

## Endpoints

### Get All Tasks
GET /api/tasks

### Get Task by ID
GET /api/tasks/{id}

### Create Task
POST /api/tasks
Body: { "title": "...", "description": "...", "completed": false, "priority": "HIGH" }

### Update Task
PUT /api/tasks/{id}

### Delete Task
DELETE /api/tasks/{id}

## Error Responses

### 400 Bad Request
{ "timestamp": "...", "status": 400, "error": "Bad Request", "message": "..." }

### 404 Not Found
{ "timestamp": "...", "status": 404, "error": "Not Found", "message": "..." }
```

---

### Homework 8 Deliverables

You must submit:

1. **GitHub repository** with all code
   - Well-organized project structure
   - Complete README with all endpoints documented
   - Include Postman collection

2. **Screenshots** showing:
   - Security configuration (show SecurityConfig class)
   - API documentation (Swagger UI with endpoints visible)
   - Tests passing (show test results in IDE or console)
   - CORS working (if testing with frontend, show network tab)
   - Health endpoint working (`/actuator/health`)

3. **Complete API documentation**
   - Either Swagger UI (preferred) OR detailed README
   - Include all endpoints
   - Include request/response examples
   - Include error responses

4. **3–5 minute explanation video**

**Your video must explain:**

• how Spring Security works (show SecurityConfig, explain filters)
• how you implemented security (CORS, endpoint protection)
• how API versioning works (show v1 controller)
• one challenge you faced (be specific)
• one thing you learned about securing APIs (explain why it matters)
• **Overall reflection** on the entire homework series:
  - What was most valuable?
  - What would you do differently?
  - What concepts were hardest to understand?
  - How has your understanding of backend development changed?

---

**📋 [View Grading Rubric for Homework 8 →](GRADING_RUBRICS.md#homework-8)**

**Final Project Checklist:**

- ✅ All endpoints working (CRUD + filtering + search + pagination)
- ✅ Database integration working
- ✅ Exception handling implemented
- ✅ DTOs used properly
- ✅ Security configured
- ✅ Tests written
- ✅ Documentation complete
- ✅ Code is clean and well-organized
- ✅ README is comprehensive
- ✅ Video submitted

---

## Final Learning Outcome

By the end of this series (Homework 5-8), you will understand:

✅ **Spring Boot project structure** - How to organize a Spring Boot application
✅ **REST API design** - RESTful principles and best practices
✅ **CRUD backend development** - Complete CRUD operations
✅ **Database integration** - JPA, repositories, queries
✅ **Input validation** - Ensuring data integrity
✅ **Exception handling** - Graceful error management
✅ **Security basics** - Protecting your API
✅ **Testing** - Writing integration tests
✅ **API documentation** - Documenting your endpoints
✅ **Production-ready practices** - DTOs, versioning, logging

You will also have a **complete backend project** for your portfolio that demonstrates enterprise-level Spring Boot development skills.

---

## Additional Challenges (Optional)

If you want to go further, try implementing:

1. **User Authentication** - JWT tokens, user registration/login
   - Create User entity
   - Implement JWT token generation/validation
   - Secure endpoints with authentication
   - Add user ownership to tasks

2. **Task Categories** - Many-to-many relationship with categories
   - Create Category entity
   - Implement many-to-many relationship
   - Add endpoints to manage categories
   - Filter tasks by category

3. **Task Comments** - One-to-many relationship
   - Create Comment entity
   - Add comments to tasks
   - Implement comment CRUD operations

4. **File Uploads** - Attach files to tasks
   - Use Spring's MultipartFile
   - Store files (local filesystem or cloud storage)
   - Add file download endpoints

5. **Email Notifications** - Send emails when tasks are created
   - Use Spring Mail
   - Configure SMTP server
   - Send email on task creation/update

6. **Task Scheduling** - Add due dates and reminders
   - Add dueDate field to Task
   - Implement reminder system
   - Filter tasks by due date

7. **Statistics Endpoint** - Get task statistics
   - Total tasks count
   - Completed vs incomplete ratio
   - Tasks by priority
   - Tasks created per day/week/month

8. **Export to CSV** - Export tasks to CSV file
   - Use OpenCSV library
   - Generate CSV file
   - Download endpoint

9. **Caching** - Improve performance
   - Use Spring Cache
   - Cache frequently accessed data
   - Implement cache invalidation

10. **Rate Limiting** - Prevent abuse
    - Use Spring AOP
    - Limit requests per IP
    - Return 429 Too Many Requests

---

## Study Tips and Best Practices

### How to Approach Each Homework

1. **Read the entire assignment first** - Understand what you need to build
2. **Set up your environment** - Make sure tools are installed
3. **Follow steps sequentially** - Don't skip ahead
4. **Test frequently** - Test after each major change
5. **Commit often** - Use Git to save your progress
6. **Ask for help** - Use office hours, forums, or study groups

### Common Patterns You'll Learn

1. **Layered Architecture:**
   ```
   Controller → Service → Repository → Database
   ```

2. **Dependency Injection:**
   - Spring creates objects for you
   - Pass dependencies through constructors

3. **Annotation-Driven Development:**
   - `@RestController`, `@Service`, `@Repository`
   - `@Autowired`, `@Valid`, `@PathVariable`

4. **Exception Handling:**
   - Custom exceptions
   - Global exception handlers
   - Proper error responses

### Debugging Strategies

1. **Read Error Messages Carefully:**
   - Spring Boot errors are usually very helpful
   - Look for line numbers and stack traces

2. **Check Logs:**
   - Enable DEBUG logging
   - Look for warnings or errors

3. **Use Breakpoints:**
   - Set breakpoints in IDE
   - Step through code execution

4. **Test Incrementally:**
   - Test one feature at a time
   - Don't build everything then test

5. **Compare with Working Examples:**
   - Look at Spring Boot documentation examples
   - Check GitHub for similar projects

### Time Management

- **Homework 5**: 4-6 hours
- **Homework 6**: 5-7 hours
- **Homework 7**: 6-8 hours
- **Homework 8**: 7-10 hours

**Total Estimated Time: 22-31 hours**

Plan accordingly! Don't wait until the last minute.

### Getting Help

1. **Office Hours**: Attend instructor office hours
2. **Forums**: Post questions on course forum
3. **Documentation**: Read Spring Boot docs
4. **Stack Overflow**: Search for similar problems
5. **Study Groups**: Work with classmates

### What Makes a Good Submission

✅ **Code Quality:**
- Clean, readable code
- Proper naming conventions
- Comments where needed
- No commented-out code

✅ **Functionality:**
- All requirements met
- Edge cases handled
- Error handling implemented

✅ **Documentation:**
- Clear README
- Code comments
- API documentation

✅ **Testing:**
- Screenshots showing functionality
- Postman collection or tests
- Video demonstration

✅ **Professionalism:**
- Proper Git commits
- Organized repository
- Clear communication

---

