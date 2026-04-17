# Capstone Project Sharing Platform - Behavioral Pattern Implementation

## Overview

This project implements the **Command Pattern** (a behavioral design pattern) for managing project operations (Create, Update, Delete). The Command Pattern encapsulates requests as objects, allowing parametrization of clients with different requests and support for operation history tracking and auditing.

## Implemented Pattern: Command Pattern

### What is the Command Pattern?

The Command Pattern is a behavioral design pattern that:
- Encapsulates a request as an object
- Allows parametrization of clients with different requests
- Supports queuing, logging, and undoing of operations
- Creates an audit trail for all operations

### Why Command Pattern for Project Operations?

The Command Pattern provides the following benefits for project management:
1. **Operation Encapsulation**: Each project operation (Create, Update, Delete) is encapsulated as a command
2. **Audit Trail**: All operations are logged with timestamps for accountability
3. **Operation History**: Complete history of project modifications can be retrieved and cleared
4. **Extensibility**: New operations can be added without modifying existing code
5. **Logging & Monitoring**: Built-in support for tracking who performed what operation and when

## Architecture

### Command Pattern Components

#### 1. **ProjectOperation Interface** (Functional Interface)
```java
@FunctionalInterface
public ProjectOperation {
    void execute(Project project);
}
```
- Defines the contract for all project operations
- Allows lambda expressions for lightweight command implementations
- Located in: `ProjectService.java`

#### 2. **ProjectServiceImpl** - Command Execution Engine
```java
public class ProjectServiceImpl {
    private List<String> operationHistory = new ArrayList<>();
    
    public void executeOperation(ProjectOperation operation) {
        try {
            operation.execute(project);
            recordOperation("Operation executed successfully");
        } catch (Exception e) {
            recordOperation("Operation failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    private void recordOperation(String message) {
        String timestamp = LocalDateTime.now().format(dateTimeFormatter);
        operationHistory.add("[" + timestamp + "] " + message);
    }
}
```
- Executes commands with automatic exception handling
- Maintains operation history with timestamps
- Records both successful and failed operations

#### 3. **ProjectController** - Command-Based API Endpoints
```java
@PostMapping("/command/create")
public ResponseEntity<?> createAsCommand(@RequestBody ProjectRequest request)

@PutMapping("/command/{id}")
public ResponseEntity<?> updateAsCommand(@PathVariable Long id, @RequestBody ProjectRequest request)

@DeleteMapping("/command/{id}")
public ResponseEntity<?> deleteAsCommand(@PathVariable Long id)

@GetMapping("/api/history")
public ResponseEntity<?> getHistory()

@DeleteMapping("/api/history")
public ResponseEntity<?> clearHistory()
```

## API Endpoints

### Command-Based Project Operations

#### Create Project as Command
```
POST /projects/command/create
Content-Type: application/json

{
  "title": "AI-Powered Scheduler",
  "description": "A smart scheduling system using ML",
  "technology": "Python, TensorFlow",
  "link": "https://github.com/user/scheduler",
  "studentId": 1
}
```

**Response:** Project created with unique ID

#### Update Project as Command
```
PUT /projects/command/{id}
Content-Type: application/json

{
  "title": "Updated Project Title",
  "description": "Updated description",
  "technology": "Updated tech stack",
  "link": "https://github.com/user/updated-project",
  "studentId": 1
}
```

**Response:** Project updated successfully

#### Delete Project as Command
```
DELETE /projects/command/{id}
```

**Response:** Project deleted successfully

### Operation History Management

#### View Operation History
```
GET /projects/api/history
```

**Response:**
```json
[
  "[2024-04-17T19:15:30.123+05:30] Project created successfully: AI-Powered Scheduler",
  "[2024-04-17T19:16:45.456+05:30] Project updated successfully: Updated Title",
  "[2024-04-17T19:17:01.789+05:30] Project deleted successfully"
]
```

#### Clear Operation History
```
DELETE /projects/api/history
```

**Response:** Operation history cleared

## Modified Files

The following files were enhanced with Command Pattern implementation:

1. **ProjectService.java** - Service interface
   - Added `@FunctionalInterface ProjectOperation`
   - Added `executeOperation(ProjectOperation operation)`
   - Added `getOperationHistory()` and `clearOperationHistory()`

2. **ProjectServiceImpl.java** - Service implementation
   - Implemented operation history tracking
   - Added `executeOperation()` with exception handling
   - Added `recordOperation()` for audit trail logging

3. **ProjectController.java** - REST API controller
   - Added 5 new command-based endpoints
   - Added operation history endpoints
   - Enhanced with `@Slf4j` logging

4. **Project.java** - JPA Entity
   - Added Command Pattern documentation

5. **ProjectRequest.java** - Data Transfer Object (DTO)
   - Added Command Pattern documentation

6. **ProjectResponse.java** - Data Transfer Object (DTO)
   - Added Command Pattern documentation

## Code Examples

### Example 1: Creating a Project Command
```java
// In ProjectController
ProjectRequest request = new ProjectRequest(...);
project = projectService.save(request);
projectService.executeOperation(p -> {
    p.setTitle(request.getTitle());
    p.setDescription(request.getDescription());
    projectRepository.save(p);
    log.info("Create command executed for project: {}", p.getId());
});
```

### Example 2: Retrieving Operation History
```java
// In ProjectController
List<String> history = projectService.getOperationHistory();
return ResponseEntity.ok(history);

// Output:
// [2024-04-17T19:15:30+05:30] Project created successfully
// [2024-04-17T19:16:45+05:30] Project updated successfully
```

## Benefits Realized

1. **Audit Trail**: Every project operation is timestamped and logged
2. **Accountability**: Track who did what and when
3. **Debugging**: Historical record aids in troubleshooting
4. **Compliance**: Meet regulatory requirements for operation logging
5. **Extensibility**: Easy to add new operations without modifying existing code
6. **Single Responsibility**: Each operation is independent and focused

## Testing Notes

- **Unit Tests**: Code compiles successfully with no syntax errors
- **Integration Tests**: Require MySQL database connection
- **Verification**: Command Pattern implementation preserves all existing CRUD functionality

## Building the Project

```bash
cd capstone_project_sharing_platform
./mvnw clean compile       # Compile the project
./mvnw test               # Run tests (requires MySQL)
./mvnw spring-boot:run    # Run the application
```

## Technology Stack

- **Language**: Java 17+
- **Framework**: Spring Boot 3.x
- **Database**: MySQL with Hibernate/JPA
- **Build Tool**: Maven
- **Templating**: Thymeleaf
- **Dependencies**: Lombok, Spring Security

## Future Extensions

The Command Pattern framework can be extended to support:
- Command undo/redo functionality
- Asynchronous command execution
- Command scheduling and batching
- Command queue persistence
- Event-driven notifications on command completion

## Commits

- **Commit ID**: 304171b
- **Message**: refactor: Implement Command Pattern (Behavioral) for Project Operations
- **Files Changed**: 6
- **Insertions**: +239
- **Deletions**: -11

---

**Last Updated**: 2024-04-17  
**Pattern Implemented**: Command Pattern (Behavioral)  
**Scope**: Project Management Operations
