## API Usage



### Add a Task
POST /tasks

#### Request Body (JSON)

You need to send a JSON object with the **description** of the task.  
The `status`, `createdAt`, and `updatedAt` fields will be automatically set by the server.

### Example

Send a `POST` request to `http://localhost:8080/tasks` with the following JSON:

```json
{
  "description": "Finish writing project documentation draft"
}



### Get the list of tasks
GET /tasks
	
### Example	
	Send a `GET` request to `http://localhost:8080/tasks`



### Get a specific task
GET /tasks/{id}

### Example
	Send a `GET` request to `http://localhost:8080/tasks/1`



### Get the list of done tasks
GET /tasks/done

### Example
	Send a `GET` request to `http://localhost:8080/tasks/done`



### Get the list of pending tasks
GET /tasks/todo

### Example
	Send a `GET` request to `http://localhost:8080/tasks/todo` 



### Get the list of ongoing tasks
GET /tasks/in-progress

### Example
	Send a `GET` request to `http://localhost:8080/tasks/in-progress` 



### Updating a task
PATCH /tasks/{id}

#### Request Body (JSON)

You need to send a JSON object with the **description** of the task.  
The `updatedAt` field will be automatically set by the server.

### Example
	Send a `PATCH` request to `http://localhost:8080/tasks/1` with the following JSON:

	```json
	{
	  "description": "Finish writing project documentation"
	}



### Marking a task as done
PATCH /tasks/in-progress/{id}

### Example
	Send a 'PATCH' request to `http://localhost:8080/tasks/in-progress/1`



### Marking a task as ongoing
PATCH /tasks/done/{id}

### Example
	Send a 'PATCH' request to `http://localhost:8080/tasks/done/1`



### Deleting a task
DELETE /tasks/{id}

### Example
	Send a 'DELETE' request to `http://localhost:8080/tasks/1`