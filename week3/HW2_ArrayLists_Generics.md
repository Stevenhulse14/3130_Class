# Homework 2: ArrayList-Based Student Management System

## Due Date
**See course schedule**

## Learning Objectives

By completing this homework, you will:
- Master ArrayList operations and methods
- Understand ArrayList's dynamic sizing capabilities
- Apply generics to create type-safe collections
- Build a complete application using ArrayLists
- Compare ArrayList performance and behavior with arrays
- Use generics to create reusable, type-safe code
- Implement generic algorithms and data structures

## Assignment Overview

You will build a **Student Management System** for a university that uses ArrayLists to manage students, courses, and enrollments. The system will demonstrate ArrayList capabilities and integrate generics throughout.

---

## Part 1: Student and Course Management (ArrayLists Basics)

### Task 1.1: Student Class

Create a `Student` class with the following:

**Fields:**
- `studentId` (String) - unique identifier (e.g., "S001")
- `firstName` (String)
- `lastName` (String)
- `email` (String)
- `gpa` (double) - grade point average (0.0 to 4.0)
- `major` (String) - student's major (e.g., "Computer Science", "Mathematics")
- `year` (int) - year in school (1 = Freshman, 2 = Sophomore, 3 = Junior, 4 = Senior)

**Methods:**
- Constructor(s) - at least one that takes all parameters
- Getters and setters for all fields
- `toString()` - returns formatted string representation
- `equals(Object obj)` - compares students by studentId
- `hashCode()` - consistent with equals
- `String getFullName()` - returns "firstName lastName"

### Task 1.2: Course Class

Create a `Course` class:

**Fields:**
- `courseCode` (String) - unique identifier (e.g., "CISC3130")
- `courseName` (String) - full course name
- `credits` (int) - number of credit hours
- `instructor` (String) - instructor name
- `maxEnrollment` (int) - maximum number of students
- `prerequisites` (ArrayList<String>) - list of prerequisite course codes

**Methods:**
- Constructor(s)
- Getters and setters
- `toString()`
- `void addPrerequisite(String courseCode)` - adds prerequisite
- `boolean hasPrerequisite(String courseCode)` - checks if courseCode is a prerequisite
- `ArrayList<String> getPrerequisites()` - returns copy of prerequisites list

### Task 1.3: StudentManager Class

Create a `StudentManager` class that uses an `ArrayList<Student>`:

**Field:**
- `ArrayList<Student> students`

**Methods:**
1. `void addStudent(Student student)` - adds student (check for duplicate studentId)
2. `boolean removeStudent(String studentId)` - removes student by ID
3. `Student findStudent(String studentId)` - finds student, returns null if not found
4. `ArrayList<Student> getStudentsByMajor(String major)` - returns students in major
5. `ArrayList<Student> getStudentsByYear(int year)` - returns students in year
6. `ArrayList<Student> getHonorStudents(double minGpa)` - returns students with GPA >= minGpa
7. `double getAverageGpa()` - calculates average GPA of all students
8. `double getAverageGpaByMajor(String major)` - calculates average GPA for major
9. `void printAllStudents()` - prints all students in formatted table
10. `int getTotalStudents()` - returns number of students
11. `ArrayList<String> getAllMajors()` - returns list of all unique majors

**Requirements:**
- Use ArrayList methods (add, remove, get, size, contains, etc.)
- Handle edge cases (empty list, student not found, etc.)
- Include proper error handling

**Test:**
```java
StudentManager manager = new StudentManager();

manager.addStudent(new Student("S001", "Alice", "Smith", "alice@university.edu", 3.8, "Computer Science", 2));
manager.addStudent(new Student("S002", "Bob", "Jones", "bob@university.edu", 3.5, "Mathematics", 3));
manager.addStudent(new Student("S003", "Charlie", "Brown", "charlie@university.edu", 3.9, "Computer Science", 2));

manager.printAllStudents();
System.out.println("Average GPA: " + manager.getAverageGpa());
System.out.println("CS Students: " + manager.getStudentsByMajor("Computer Science").size());
```

---

## Part 2: Enrollment Management (ArrayList Operations)

### Task 2.1: Enrollment Class

Create an `Enrollment` class:

**Fields:**
- `enrollmentId` (String) - unique identifier
- `studentId` (String)
- `courseCode` (String)
- `grade` (String) - "A", "B", "C", "D", "F", or null if not graded yet
- `semester` (String) - e.g., "Fall 2024", "Spring 2024"

**Methods:**
- Constructor(s)
- Getters and setters
- `toString()`
- `double getGradePoints()` - converts letter grade to points (A=4.0, B=3.0, C=2.0, D=1.0, F=0.0)
- `boolean isPassing()` - returns true if grade is A, B, C, or D

### Task 2.2: EnrollmentManager Class

Create an `EnrollmentManager` class using `ArrayList<Enrollment>`:

**Field:**
- `ArrayList<Enrollment> enrollments`

**Methods:**
1. `void enrollStudent(String studentId, String courseCode, String semester)` - creates enrollment
2. `boolean dropEnrollment(String enrollmentId)` - removes enrollment
3. `Enrollment findEnrollment(String enrollmentId)` - finds enrollment
4. `ArrayList<Enrollment> getEnrollmentsByStudent(String studentId)` - returns student's enrollments
5. `ArrayList<Enrollment> getEnrollmentsByCourse(String courseCode)` - returns course enrollments
6. `void assignGrade(String enrollmentId, String grade)` - assigns grade to enrollment
7. `double calculateStudentGpa(String studentId)` - calculates GPA from enrollments
8. `ArrayList<String> getStudentsInCourse(String courseCode)` - returns list of student IDs
9. `int getEnrollmentCount(String courseCode)` - returns number of students in course
10. `void printAllEnrollments()` - prints all enrollments

**Requirements:**
- Check if course is at capacity before enrolling
- Validate grades (A, B, C, D, F only)
- Handle cases where student has no enrollments

### Task 2.3: CourseManager Class

Create a `CourseManager` class using `ArrayList<Course>`:

**Field:**
- `ArrayList<Course> courses`

**Methods:**
1. `void addCourse(Course course)` - adds course
2. `Course findCourse(String courseCode)` - finds course
3. `ArrayList<Course> getCoursesByInstructor(String instructor)` - returns instructor's courses
4. `ArrayList<Course> getAvailableCourses(String studentId, StudentManager studentManager, EnrollmentManager enrollmentManager)` - returns courses student can take (not already enrolled, prerequisites met)
5. `void printAllCourses()` - prints all courses
6. `int getTotalCourses()` - returns number of courses

**Test:**
```java
CourseManager courseManager = new CourseManager();
courseManager.addCourse(new Course("CISC3130", "Data Structures", 3, "Dr. Smith", 30));
courseManager.addCourse(new Course("MATH101", "Calculus I", 4, "Dr. Johnson", 25));

EnrollmentManager enrollmentManager = new EnrollmentManager();
enrollmentManager.enrollStudent("S001", "CISC3130", "Fall 2024");
enrollmentManager.assignGrade("E001", "A");
System.out.println("Student GPA: " + enrollmentManager.calculateStudentGpa("S001"));
```

---

## Part 3: ArrayList-Specific Features

### Task 3.1: ArrayList Operations

Create an `ArrayListOperationsDemo` class:

1. **Convert Array to ArrayList:**
   - Create an array of Student objects
   - Convert to ArrayList using `Arrays.asList()` and `new ArrayList<>()`
   - Demonstrate adding/removing after conversion

2. **ArrayList to Array:**
   - Create ArrayList of Students
   - Convert to array using `toArray()`
   - Print both versions

3. **SubList Operations:**
   - Create ArrayList of students
   - Use `subList()` to get a portion
   - Demonstrate that modifications to sublist affect original

4. **ArrayList Sorting:**
   - Sort students by GPA (descending)
   - Sort students by last name (alphabetical)
   - Use `Collections.sort()` with `Comparator`

5. **ArrayList Searching:**
   - Use `indexOf()` to find student
   - Use `contains()` to check existence
   - Use `Collections.binarySearch()` (must sort first)

### Task 3.2: ArrayList vs Array Comparison

Create a `ArrayListVsArrayDemo` class:

1. Create both `Student[]` array and `ArrayList<Student>`
2. Perform operations:
   - Add elements (show array limitation)
   - Remove elements (show array limitation)
   - Resize (show ArrayList advantage)
3. Measure performance:
   - Time to add 10,000 students
   - Time to access 1,000 random students
4. Print comparison report

**Write summary** explaining when to use each.

---

## Part 4: Generics Integration

### Task 4.1: Generic Utility Methods

Create an `ArrayListUtils` class with generic methods:

#### Method 1: Generic Swap
```java
public static <T> void swap(ArrayList<T> list, int index1, int index2)
```
- Swaps two elements in any ArrayList

#### Method 2: Generic Find Maximum
```java
public static <T extends Comparable<T>> T findMax(ArrayList<T> list)
```
- Finds maximum element
- Works with `Comparable` types
- Make `Student` implement `Comparable<Student>` (compare by GPA or name)

#### Method 3: Generic Filter
```java
public static <T> ArrayList<T> filter(ArrayList<T> list, Predicate<T> condition)
```
- Returns new ArrayList with elements matching condition
- **Note**: Create simple `Predicate<T>` interface or use approach that works for you

**Simpler alternative**: Create specific filters first, then generalize:
```java
public static ArrayList<Student> filterByMajor(ArrayList<Student> students, String major)
public static <T extends Student> ArrayList<T> filterGeneric(ArrayList<T> list, ...)
```

#### Method 4: Generic Reverse
```java
public static <T> void reverse(ArrayList<T> list)
```
- Reverses ArrayList in-place

#### Method 5: Generic Merge
```java
public static <T> ArrayList<T> merge(ArrayList<T> list1, ArrayList<T> list2)
```
- Merges two ArrayLists into one

### Task 4.2: Generic Container Class

Create a `GenericList<T>` class that wraps `ArrayList<T>`:

**Field:**
- `ArrayList<T> items`

**Methods:**
1. `void add(T item)` - adds item
2. `T get(int index)` - gets item
3. `boolean remove(T item)` - removes item
4. `int size()` - returns size
5. `boolean isEmpty()` - checks if empty
6. `void clear()` - clears list
7. `boolean contains(T item)` - checks if contains
8. `ArrayList<T> getAll()` - returns copy
9. `void addAll(ArrayList<T> other)` - adds all from other list
10. `<U extends T> void addAllFrom(GenericList<U> other)` - adds from another GenericList (if U extends T)

**Bonus Methods:**
11. `void sort()` - sorts if T implements Comparable
12. `T findMax()` - finds max if T implements Comparable

**Test with different types:**
```java
GenericList<String> strings = new GenericList<>();
strings.add("Hello");

GenericList<Integer> numbers = new GenericList<>();
numbers.add(42);

GenericList<Student> students = new GenericList<>();
students.add(new Student(...));
```

### Task 4.3: Bounded Generics

Add methods to `ArrayListUtils` for Numbers:

#### Method: Sum Numbers
```java
public static <T extends Number> double sum(ArrayList<T> numbers)
```
- Sums any Number type ArrayList

#### Method: Average Numbers
```java
public static <T extends Number> double average(ArrayList<T> numbers)
```
- Calculates average

#### Method: Filter Numbers Above Threshold
```java
public static <T extends Number & Comparable<T>> ArrayList<T> filterAbove(ArrayList<T> numbers, T threshold)
```
- Returns numbers greater than threshold
- Uses multiple bounds

**Test:**
```java
ArrayList<Integer> ints = new ArrayList<>();
ints.add(10);
ints.add(20);
ints.add(30);
System.out.println("Sum: " + ArrayListUtils.sum(ints));
System.out.println("Average: " + ArrayListUtils.average(ints));
```

### Task 4.4: Wildcards

Create methods in `ArrayListUtils` using wildcards:

#### Method 1: Upper Bounded Wildcard
```java
public static double sumNumbers(ArrayList<? extends Number> numbers)
```
- Accepts `ArrayList<Integer>`, `ArrayList<Double>`, etc.
- Can read, cannot write (except null)

#### Method 2: Lower Bounded Wildcard
```java
public static void addNumbers(ArrayList<? super Integer> list)
```
- Can add Integers to list
- Accepts `ArrayList<Integer>`, `ArrayList<Number>`, `ArrayList<Object>`

#### Method 3: Unbounded Wildcard
```java
public static void printList(ArrayList<?> list)
```
- Accepts any ArrayList
- Can only read as Object

**Test and explain** when to use each type of wildcard.

---

## Part 5: Advanced Generic Data Structures

### Task 5.1: Generic Stack

Create a `GenericStack<T>` class using `ArrayList<T>`:

**Methods:**
- `void push(T item)` - adds to top
- `T pop()` - removes and returns top
- `T peek()` - returns top without removing
- `boolean isEmpty()` - checks if empty
- `int size()` - returns size
- `void clear()` - clears stack

**Test:**
```java
GenericStack<String> stack = new GenericStack<>();
stack.push("First");
stack.push("Second");
System.out.println(stack.pop());  // Second
```

### Task 5.2: Generic Queue

Create a `GenericQueue<T>` class using `ArrayList<T>`:

**Methods:**
- `void enqueue(T item)` - adds to back
- `T dequeue()` - removes and returns front
- `T peek()` - returns front without removing
- `boolean isEmpty()` - checks if empty
- `int size()` - returns size

**Test:**
```java
GenericQueue<Integer> queue = new GenericQueue<>();
queue.enqueue(10);
queue.enqueue(20);
System.out.println(queue.dequeue());  // 10
```

### Task 5.3: Generic Pair Class

Create a `Pair<K, V>` class:

**Fields:**
- `K first`
- `V second`

**Methods:**
- Constructor(s)
- Getters and setters
- `toString()`
- `equals()` and `hashCode()`

**Use Pair in your system:**
- Create `ArrayList<Pair<String, Double>>` to store course codes and grades
- Create `ArrayList<Pair<Student, Course>>` to represent enrollments (alternative approach)

---

## Part 6: Integration and Main Application

### Task 6.1: Main Application Class

Create `StudentManagementSystemMain.java`:

**Features:**
1. Create `StudentManager`, `CourseManager`, `EnrollmentManager`
2. Add sample data:
   - At least 10 students
   - At least 5 courses
   - Multiple enrollments
3. Demonstrate all operations:
   - Student management
   - Course management
   - Enrollment operations
   - GPA calculations
   - Reporting
4. Use generic utility methods
5. Demonstrate generic data structures

**Menu System (Recommended):**
```
1. Add Student
2. Remove Student
3. Find Student
4. List All Students
5. Add Course
6. Enroll Student in Course
7. Assign Grade
8. Calculate Student GPA
9. Generate Reports
10. Exit
```

### Task 6.2: Reporting System

Create a `ReportGenerator` class with methods:

1. `void generateStudentReport(String studentId, StudentManager sm, EnrollmentManager em)` - prints student info, courses, GPA
2. `void generateCourseReport(String courseCode, CourseManager cm, EnrollmentManager em)` - prints course info, enrolled students, average grade
3. `void generateMajorReport(String major, StudentManager sm)` - prints all students in major, average GPA
4. `void generateHonorRollReport(StudentManager sm, double minGpa)` - prints honor students

Use ArrayLists and generics throughout.

---

## Submission Requirements

### Files to Submit

1. `Student.java`
2. `Course.java`
3. `StudentManager.java`
4. `Enrollment.java`
5. `EnrollmentManager.java`
6. `CourseManager.java`
7. `ArrayListUtils.java` (generic utility methods)
8. `GenericList.java`
9. `GenericStack.java`
10. `GenericQueue.java`
11. `Pair.java`
12. `ReportGenerator.java`
13. `StudentManagementSystemMain.java`
14. `ArrayListOperationsDemo.java`
15. `ArrayListVsArrayDemo.java`
16. `README.txt` - explanation of implementation

### Code Requirements

- All code must compile without errors
- All code must run and produce expected output
- Code must be properly commented
- Use meaningful variable and method names
- Follow Java naming conventions
- Handle edge cases appropriately
- Include proper error handling
- **Use generics throughout** - avoid raw types

### Documentation

Your `README.txt` should include:
- Brief description of each class
- How to compile and run your program
- Design decisions you made
- Challenges encountered
- What you learned about ArrayLists and generics
- Comparison of ArrayList vs Array based on your experience

---

---

## Hints and Tips

1. **Start with Basics**: Complete Part 1 and Part 2 first. Get the core functionality working.

2. **Test Incrementally**: Test each class as you write it. Don't write everything then test.

3. **ArrayList Methods**: Review ArrayList API. Methods like `subList()`, `toArray()`, `containsAll()` can be useful.

4. **Generics Gradually**: Start with simple generics (`ArrayList<String>`) before bounded generics.

5. **Comparable Interface**: To use `Collections.sort()` or `findMax()`, make classes implement `Comparable<T>`.

6. **Wildcards**: Upper bounded (`? extends`) for reading, lower bounded (`? super`) for writing.

7. **Error Handling**: Consider:
   - Empty ArrayLists
   - Invalid indices
   - Student/Course not found
   - Duplicate IDs
   - Invalid input

8. **Testing**: Create comprehensive test cases:
   - Empty lists
   - Single element
   - Multiple elements
   - Edge cases

9. **Performance**: Don't worry about performance optimization unless working with very large datasets (10,000+ elements).

10. **Documentation**: Comment your code, especially generic methods explaining type parameters.

---

## Learning Outcomes

After completing this homework, you should be able to:

✅ Use ArrayList methods confidently  
✅ Understand ArrayList's dynamic sizing  
✅ Create and use generic methods  
✅ Create and use generic classes  
✅ Understand bounded generics and wildcards  
✅ Build complete applications using collections  
✅ Compare ArrayList with arrays effectively  
✅ Implement generic data structures  

---

## Questions?

If you have questions about this assignment, please:
1. Review the notes and examples from class
2. Check the Java API documentation for ArrayList
3. Review generics documentation
4. Ask your instructor during office hours or via email

**Good luck!**

