# To-Do API

A simple RESTful API for managing tasks using Spring Boot.

---

## API Usage

### Add a Task
**POST** `/tasks`

**Request Body:**
```
{
  "description": "Finish writing project documentation draft"
}
```

You need to send a JSON object with the **description** of the task.  
The `status`, `createdAt`, and `updatedAt` fields will be automatically set by the server.  



### Get tasks
**GET** `/tasks`				-	returns the list of all tasks.  
**GET** `/tasks/{id}`			-	returns a specific task.  
**GET** `/tasks/done`			-	returns the list of all completed tasks.  
**GET** `/tasks/todo`			-	returns the list of all pending tasks.  
**GET** `/tasks/in-progress`	-	returns the list of all ongoing tasks.  



### Updating a task
**PATCH** `/tasks/{id}`

**Request Body:**
```
{
  "description": "Finish writing project documentation"
}
```

The `updatedAt` field will be automatically set by the server.



### Marking a task as done
**PATCH** `/tasks/in-progress/{id}`



### Marking a task as ongoing
**PATCH** `/tasks/done/{id}`



### Deleting a task
**DELETE** `/tasks/{id}`



### Base URL for Local Testing
http://localhost:8080



### Instructions before using
1. **Create a Database in MySQL:**

	Open your MySQL client or use the command line to create a database. For example:
	```
	CREATE DATABASE todo;
	```
	
2. **Configure Database Connection:**

	- Navigate to src/main/resources in your project.

	- Open the application.properties file.

		- Update the following properties with your MySQL database information:
	
		- spring.datasource.url=jdbc:mysql://localhost:3306/{your_database_name}

			- Replace {your_database_name} with the name of the database you created in Step 1 (e.g., todo).

		- spring.datasource.username={your_mysql_username}

			- Replace {your_mysql_username} with your MySQL connection username (e.g., root).

		- spring.datasource.password={your_mysql_password}

			- Replace {your_mysql_password} with your MySQL connection password.

3. Run the Application:

	- After configuring the application.properties file, you can run your Spring Boot application.

	- The application will automatically create the tables in the database.



### Notes:

- Ensure that your MySQL server is running before attempting to connect.

- You can change the database name, username, and password as needed, but make sure to update the application.properties accordingly.