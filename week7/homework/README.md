# Spring Boot Homework Series
## Building a Campus Task Board API

Over the next four homework assignments (Homework 5, 6, 7, and 8), you will learn how to build a production-ready backend application using Spring Boot while building a real backend service.

Instead of only learning Spring Boot concepts, you will build something functional every week that demonstrates enterprise-level development practices.

By the end of this series, you will have built a backend API that allows users to:

• create tasks with validation
• view tasks with filtering and pagination
• update tasks with proper error handling
• delete tasks with soft-delete capabilities
• store tasks in a database with relationships
• authenticate users and secure endpoints
• handle errors gracefully
• monitor application health

The project we will build is called:

**Campus Task Board API**

Each homework builds on the previous one, introducing progressively more advanced concepts.

---

## Technologies Used

You will gain experience with:

- **Java 17+**
- **Spring Boot 3.x**
- **REST APIs** (RESTful design principles)
- **Spring Data JPA** (Database abstraction)
- **H2 Database** (In-memory database for development)
- **Spring Validation** (Input validation)
- **Spring Security** (Authentication & Authorization)
- **Spring Boot Actuator** (Application monitoring)
- **Lombok** (Reducing boilerplate code)
- **Postman / Browser testing** (API testing)
- **Exception Handling** (Global error handling)
- **DTOs** (Data Transfer Objects)
- **Pagination & Sorting** (Efficient data retrieval)

---

## Helpful Resources

### Official Documentation

- **Spring Boot**: https://docs.spring.io/spring-boot/docs/current/reference/html/
- **Spring Initializr**: https://start.spring.io
- **Spring Data JPA**: https://spring.io/projects/spring-data-jpa
- **Spring Security**: https://spring.io/projects/spring-security
- **Spring Validation**: https://docs.spring.io/spring-framework/reference/core/validation.html
- **Lombok**: https://projectlombok.org/
- **H2 Database**: https://www.h2database.com/html/main.html
- **Maven**: https://maven.apache.org/guides/getting-started/

### Beginner Friendly Videos

- **Spring Boot Crash Course**: https://youtu.be/9SGDpanrc8U
- **Spring Boot REST API Basics**: https://youtu.be/vtPkZShrvXQ
- **Spring Data JPA Basics**: https://youtu.be/zvR-Oif_nxg
- **Spring Security Tutorial**: https://youtu.be/her_7pa0vrg
- **Exception Handling in Spring Boot**: https://youtu.be/Pzys4qjWpRc
- **Postman Tutorial**: https://youtu.be/VywxIQ2ZXw4
- **REST API Design Best Practices**: https://youtu.be/7YcG25ybnss

You are encouraged to watch these videos if you get stuck.

### Additional Learning Resources

- **Spring Boot Reference Guide**: Comprehensive guide to all Spring Boot features
- **Baeldung Spring Tutorials**: https://www.baeldung.com/spring-tutorial - Excellent tutorials with examples
- **Spring Academy**: https://spring.academy/ - Free Spring courses
- **Java Documentation**: https://docs.oracle.com/javase/tutorial/ - Essential Java concepts

### Tools You'll Need

- **Java JDK 17+**: Runtime and development kit
- **Maven**: Build tool (included with Spring Boot project)
- **IDE**: IntelliJ IDEA (recommended) or VS Code
- **Postman**: API testing tool
- **Git**: Version control (for submitting assignments)
- **GitHub Account**: For repository hosting

---

## Video Submission Requirement (Applies to ALL Homeworks)

For each homework, you must submit a short video explaining your work.

### Video Length
**3–5 minutes** (aim for quality over length)

### In your video you must explain:

1. **What you built** for this homework
   - Show the main features you implemented
   - Explain the purpose of each feature
   
2. **Demonstrate the feature working** (run your server and show the endpoint working with Postman or browser)
   - Start your Spring Boot application
   - Show at least 2-3 endpoints working
   - Show both success and error cases (if applicable)
   
3. **One thing that was challenging** during this homework
   - Be specific about what problem you encountered
   - Explain how you solved it (or attempted to solve it)
   
4. **One thing that you learned** about Spring Boot or backend development
   - Share a new concept or technique you learned
   - Explain why it's useful
   
5. **Show your code** (at least one key class or method)
   - Walk through the code briefly
   - Explain what it does
   - Point out any interesting parts

### Video Quality Tips

- **Audio**: Speak clearly, use a good microphone if possible
- **Screen**: Make sure code is readable (use larger font if needed)
- **Pacing**: Don't rush, but stay within time limit
- **Practice**: Record a practice run first
- **Editing**: Basic editing is fine, but not required

### Video Submission Format

- Upload to YouTube (unlisted), Loom, or similar platform
- Include the link in your GitHub repository README
- Make sure the video is accessible (test the link)

### Recommended tools:

• **Loom** (https://www.loom.com) - Easy to use, free tier available
• **Zoom recording** - If you have access
• **OBS Studio** - Free, open-source screen recorder
• **QuickTime screen recording** - Built into macOS

Submit the video link along with your GitHub repository.

---

## Homework Assignments

The homework series is divided into four assignments, each building on the previous one:

### [📝 Homework 5: Creating Your First Spring Boot API with Validation](HW5.md)
Learn to create a REST API with Spring Boot, implement controllers, services, and input validation.

**Key Topics:**
- Spring Boot project structure
- REST API endpoints (GET, POST, PUT, DELETE)
- Input validation with Spring Validation
- Lombok annotations
- Error handling

---

### [📝 Homework 6: Adding Database Persistence with Spring Data JPA](HW6.md)
Add database persistence using Spring Data JPA and H2 database.

**Key Topics:**
- JPA entities and annotations
- Repository interfaces
- Custom query methods
- Pagination and sorting
- Database relationships

---

### [📝 Homework 7: Advanced Features and Exception Handling](HW7.md)
Implement advanced features including global exception handling, DTOs, and soft delete.

**Key Topics:**
- Global exception handling
- Custom exceptions
- DTOs (Data Transfer Objects)
- Soft delete implementation
- Request/response logging
- Spring Boot Actuator

---

### [📝 Homework 8: Security and Final Enhancements](HW8.md)
Add security, API versioning, testing, and comprehensive documentation.

**Key Topics:**
- Spring Security configuration
- CORS setup
- API versioning
- Integration testing
- API documentation (Swagger/OpenAPI)
- Production-ready practices

---

## Grading Rubrics

All grading rubrics for individual homeworks and the overall project are available in:

**[📋 Grading Rubrics →](GRADING_RUBRICS.md)**

The grading rubrics document includes:
- Detailed rubrics for Homework 5, 6, 7, and 8
- Overall project grading rubric
- Point breakdowns and scoring criteria
- Bonus opportunities

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
