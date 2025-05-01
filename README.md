# To-Do API

A simple RESTful API for managing tasks using Spring Boot.

---

## API Usage

### Add a Task
**POST** `/tasks`

**Request Body:**
```json
{
  "description": "Finish writing project documentation draft"
}

You need to send a JSON object with the **description** of the task.  
The `status`, `createdAt`, and `updatedAt` fields will be automatically set by the server.



### Get tasks
**GET** `/tasks`				-	get all tasks.
**GET** `/tasks/{id}`			-	get a specific task.
**GET** `/tasks/done`			-	get all completed tasks.
**GET** `/tasks/todo`			-	get all pending tasks.
**GET** `/tasks/in-progress`	-	get all ongoing tasks.



### Updating a task
**PATCH** `/tasks/{id}`

**Request Body:**
```json
{
  "description": "Finish writing project documentation"
}

The `updatedAt` field will be automatically set by the server.



### Marking a task as done
**PATCH** `/tasks/in-progress/{id}`



### Marking a task as ongoing
**PATCH** `/tasks/done/{id}`



### Deleting a task
**DELETE** `/tasks/{id}`



### Base URL for Local Testing
http://localhost:8080