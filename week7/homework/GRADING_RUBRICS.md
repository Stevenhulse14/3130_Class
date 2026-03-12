# Grading Rubrics

This document contains all grading rubrics for the Spring Boot Homework Series (Homework 5-8) and the overall project rubric.

---

## Homework 5

### Grading Rubric (Homework 5)

| Criteria | Points | Description |
|----------|--------|-------------|
| **Code Quality** | 25 | <ul><li>Proper package structure (model, controller, service)</li><li>Clean, readable code with appropriate naming</li><li>Proper use of annotations (@RestController, @Service, etc.)</li><li>No commented-out code or debugging artifacts</li><li>Follows Java conventions</li></ul> |
| **Functionality** | 30 | <ul><li>GET /api/tasks - Returns all tasks (10 points)</li><li>GET /api/tasks/{id} - Returns task by ID with 404 handling (5 points)</li><li>POST /api/tasks - Creates task with auto-generated ID (10 points)</li><li>PUT /api/tasks/{id} - Updates task with 404 handling (5 points)</li></ul> |
| **Validation** | 15 | <ul><li>@NotBlank validation on title field (5 points)</li><li>@Size validation on title (3-100 chars) and description (max 500) (5 points)</li><li>Validation error handler returns proper error messages (5 points)</li></ul> |
| **Documentation** | 10 | <ul><li>README includes project description (2 points)</li><li>README includes setup/run instructions (3 points)</li><li>README includes API endpoint documentation (3 points)</li><li>Screenshots showing endpoints working (2 points)</li></ul> |
| **Video Submission** | 15 | <ul><li>Video length 3-5 minutes (2 points)</li><li>Explains what was built (3 points)</li><li>Demonstrates endpoints working (5 points)</li><li>Discusses one challenge and solution (3 points)</li><li>Explains one thing learned (2 points)</li></ul> |
| **GitHub Repository** | 5 | <ul><li>Repository is accessible (2 points)</li><li>Includes all required files (.gitignore, README, code) (2 points)</li><li>Proper commit history (1 point)</li></ul> |

**Total: 100 points**

**Detailed Scoring Breakdown:**

- **Code Quality (25 points):**
  - Package structure: 5 points
  - Code readability and naming: 5 points
  - Proper annotations: 5 points
  - No debugging artifacts: 5 points
  - Java conventions: 5 points

- **Functionality (30 points):**
  - GET all tasks: 10 points
  - GET by ID: 5 points
  - POST create: 10 points
  - PUT update: 5 points

- **Validation (15 points):**
  - Title validation: 5 points
  - Description validation: 5 points
  - Error handling: 5 points

**Bonus Points (Optional):**
- Add more validation rules (+5)
- Add request/response logging (+5)
- Write unit tests (+10)
- Deploy to cloud (Heroku, Railway, etc.) (+10)

---

## Homework 6

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

## Homework 7

### Grading Rubric (Homework 7)

| Criteria | Points | Description |
|----------|--------|-------------|
| **Exception Handling** | 25 | <ul><li>Custom exceptions created (TaskNotFoundException, InvalidTaskDataException) (8 points)</li><li>GlobalExceptionHandler with @RestControllerAdvice (5 points)</li><li>Handles TaskNotFoundException (404) (4 points)</li><li>Handles validation exceptions (400) (4 points)</li><li>Handles generic exceptions (500) (4 points)</li></ul> |
| **DTOs** | 20 | <ul><li>TaskRequest DTO created with validation (8 points)</li><li>TaskResponse DTO created with fromEntity method (8 points)</li><li>Controller uses DTOs instead of Entity directly (4 points)</li></ul> |
| **Soft Delete** | 15 | <ul><li>Deleted field added to Task entity (3 points)</li><li>Repository methods exclude deleted tasks (5 points)</li><li>Delete endpoint sets deleted=true instead of removing (5 points)</li><li>Restore endpoint implemented (2 points)</li></ul> |
| **Error Response** | 10 | <ul><li>ErrorResponse DTO created with proper fields (5 points)</li><li>Error responses include timestamp, status, error, message, path (5 points)</li></ul> |
| **Actuator/Health** | 5 | <ul><li>Actuator dependency added (2 points)</li><li>Health endpoint accessible and working (3 points)</li></ul> |
| **Request Logging** | 10 | <ul><li>LoggingFilter created (5 points)</li><li>Logs method, URI, status, duration (5 points)</li></ul> |
| **Documentation** | 10 | <ul><li>README updated with new features (3 points)</li><li>Screenshots of error responses (3 points)</li><li>Screenshots of soft delete and health endpoint (4 points)</li></ul> |
| **Video Submission** | 5 | <ul><li>Explains exception handling (2 points)</li><li>Explains DTOs and soft delete (2 points)</li><li>Discusses challenge and learning (1 point)</li></ul> |

**Total: 100 points**

**Detailed Scoring Breakdown:**

- **Exception Handling (25 points):**
  - Custom exceptions: 8 points
  - Global handler setup: 5 points
  - Specific exception handlers: 12 points

- **DTOs (20 points):**
  - TaskRequest: 8 points
  - TaskResponse: 8 points
  - Controller integration: 4 points

- **Soft Delete (15 points):**
  - Entity modification: 3 points
  - Repository updates: 5 points
  - Service logic: 7 points

---

## Homework 8

### Grading Rubric (Homework 8)

| Criteria | Points | Description |
|----------|--------|-------------|
| **Security Configuration** | 25 | <ul><li>Spring Security dependency added (3 points)</li><li>SecurityConfig class created with @EnableWebSecurity (5 points)</li><li>CORS configuration implemented (5 points)</li><li>CSRF disabled appropriately (3 points)</li><li>Endpoint access rules configured (5 points)</li><li>H2 console access configured (4 points)</li></ul> |
| **API Versioning** | 15 | <ul><li>v1 package/controller created (5 points)</li><li>Endpoints accessible at /api/v1/tasks (5 points)</li><li>Versioning properly implemented (5 points)</li></ul> |
| **Testing** | 20 | <ul><li>Integration test class created (5 points)</li><li>At least 3 test methods (create, get, error cases) (10 points)</li><li>Tests use MockMvc correctly (3 points)</li><li>All tests pass (2 points)</li></ul> |
| **API Documentation** | 15 | <ul><li>SpringDoc/OpenAPI dependency added (3 points)</li><li>OpenAPI configuration created (3 points)</li><li>Swagger UI accessible and shows endpoints (5 points)</li><li>OR comprehensive API README with all endpoints (4 points)</li></ul> |
| **Code Quality** | 10 | <ul><li>Code follows best practices (3 points)</li><li>Proper separation of concerns (3 points)</li><li>Clean, readable code (2 points)</li><li>No debugging artifacts (2 points)</li></ul> |
| **Video Submission** | 10 | <ul><li>Explains security configuration (3 points)</li><li>Demonstrates API versioning (2 points)</li><li>Shows tests running (2 points)</li><li>Discusses challenge and learning (2 points)</li><li>Overall reflection on series (1 point)</li></ul> |
| **Overall Reflection** | 5 | <ul><li>Thoughtful reflection on entire homework series (3 points)</li><li>Discusses what was valuable (1 point)</li><li>Discusses what would be done differently (1 point)</li></ul> |

**Total: 100 points**

**Detailed Scoring Breakdown:**

- **Security Configuration (25 points):**
  - Dependency and basic setup: 8 points
  - CORS configuration: 5 points
  - Access rules: 9 points
  - H2 console: 3 points

- **Testing (20 points):**
  - Test setup: 5 points
  - Test methods: 10 points
  - Test execution: 5 points

- **API Documentation (15 points):**
  - Setup: 6 points
  - Content: 9 points

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

## Overall Project Grading Rubric

This rubric evaluates the **entire Campus Task Board API project** across all four homework assignments (Homework 5-8).

| Criteria | Points | Description |
|----------|--------|-------------|
| **Individual Homework Scores** | 60 | <ul><li>Homework 5: 15 points (15% of 100)</li><li>Homework 6: 15 points (15% of 100)</li><li>Homework 7: 15 points (15% of 100)</li><li>Homework 8: 15 points (15% of 100)</li></ul> |
| **Project Completeness** | 15 | <ul><li>All CRUD operations working end-to-end (5 points)</li><li>Database persistence functioning correctly (3 points)</li><li>All advanced features implemented (exception handling, DTOs, soft delete) (4 points)</li><li>Security and testing implemented (3 points)</li></ul> |
| **Code Architecture** | 10 | <ul><li>Proper layered architecture (Controller → Service → Repository) (4 points)</li><li>Separation of concerns (DTOs vs Entities) (3 points)</li><li>Consistent code style across all homeworks (3 points)</li></ul> |
| **Documentation Quality** | 10 | <ul><li>Comprehensive README covering all features (4 points)</li><li>API documentation (Swagger or detailed README) (3 points)</li><li>Clear setup and run instructions (2 points)</li><li>All screenshots and examples included (1 point)</li></ul> |
| **Video Submissions** | 5 | <ul><li>All 4 videos submitted (2 points)</li><li>Videos demonstrate understanding of concepts (2 points)</li><li>Final video includes overall reflection (1 point)</li></ul> |

**Total: 100 points**

**Scoring Breakdown:**

- **Individual Homework Scores (60 points):**
  - Each homework is worth 15 points (scaled from 100-point rubric)
  - Late penalties apply per homework assignment
  - Partial credit given for incomplete work

- **Project Completeness (15 points):**
  - All core features must work together
  - Database integration must be functional
  - Advanced features properly integrated
  - Security and testing demonstrate production-readiness

- **Code Architecture (10 points):**
  - Follows Spring Boot best practices
  - Consistent patterns across all homeworks
  - Proper use of design patterns (Repository, Service, DTO)

- **Documentation Quality (10 points):**
  - README serves as complete project guide
  - API documentation is accurate and comprehensive
  - Examples and screenshots support understanding

- **Video Submissions (5 points):**
  - All videos submitted on time
  - Videos demonstrate learning and understanding
  - Final reflection shows growth throughout series

**Grade Scale:**

- **90-100 points**: A - Excellent work, all features implemented, high-quality code and documentation
- **80-89 points**: B - Good work, most features implemented, solid code quality
- **70-79 points**: C - Satisfactory work, core features implemented, acceptable code quality
- **60-69 points**: D - Needs improvement, some features missing or incomplete
- **Below 60 points**: F - Incomplete or non-functional project

**Bonus Opportunities (Up to 10 additional points):**

- Deploy to cloud platform (Heroku, Railway, AWS): +5 points
- Additional features from "Additional Challenges" section: +2-5 points each
- Comprehensive unit test coverage (>80%): +5 points
- Professional frontend integration: +5 points
- Performance optimizations (caching, query optimization): +3 points

**Note:** Bonus points are capped at 10 total points and cannot raise grade above 100%.

---

## Final Notes

### What You've Accomplished

By completing this series, you've built:
- A complete REST API
- Database integration
- Error handling
- Security configuration
- API documentation
- Tests

This is a **real-world backend application** that demonstrates enterprise-level Spring Boot development!

### Next Steps

After completing this series, consider:
- Building a frontend (React, Vue, Angular)
- Deploying to cloud (AWS, Heroku, Railway)
- Adding more features
- Learning microservices
- Contributing to open source

### Portfolio Project

This project is perfect for your portfolio! Make sure to:
- Write a good README
- Include screenshots
- Deploy it somewhere
- Link to GitHub
- Explain what you learned

---

**Good luck with your assignments!** 🚀

Remember: Learning Spring Boot takes time. Don't get discouraged if things don't work immediately. Debugging is part of the learning process!
