## Cypso Labs
### Create a simple Spring Boot application that provides a RESTful API for managing a list of tasks.

### Postman collection = https://documenter.getpostman.com/view/25235261/2sA3dyjBhp

## Running the Application

1. Clone the repository.
2. Navigate to the project directory.
3. Run the application
   `./mvnw spring-boot:run`

## API Endpoints
POST /tasks: Create a new task.
GET /tasks: Retrieve all tasks.
GET /tasks/{id}: Retrieve a task by its ID.
PUT /tasks/{id}: Update a task by its ID.
DELETE /tasks/{id}: Delete a task by its ID.

### Swagger/OpenAPI documentation for the API = http://localhost:8080/swagger-ui/index.html#/task-controller/createTask
