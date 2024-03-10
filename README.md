
## Overview

This document provides instructions and details for using the API.
Please follow the guidelines below to integrate with the API successfully.

## Table of Contents

## Getting Started

### Prerequisites

- Ensure you have 
- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) 
- and [Maven](https://maven.apache.org/download.cgi) installed.
- Set up your MySQL database.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/saurabhkumarniit/project-management-system.git
   cd project-management-system
   
2. ```bash
   mvn clean install
   mvn spring-boot:run
   
3. API Endpoints
   Add a New Project
   Endpoint: POST /api/projects/add

## Add a new project.

Request Body:

json
{
"name": "Project Name",
"description": "Project Description",
"startDate": "2022-03-15",
"endDate": "2022-03-30"
}
Response:

json
{
"id": 1,
"name": "Project Name",
"description": "Project Description",
"startDate": "2022-03-15",
"endDate": "2022-03-30"
}

## Update a Project
Endpoint: PUT /api/projects/update/{id}

Description: Update an existing project.

Request Body:

json
{
"name": "Updated Project Name",
"description": "Updated Project Description",
"startDate": "2022-03-20",
"endDate": "2022-04-05"
}
Response:

json
{
"id": 1,
"name": "Updated Project Name",
"description": "Updated Project Description",
"startDate": "2022-03-20",
"endDate": "2022-04-05"
}


## Get All Projects
Endpoint: GET /api/projects/all

Description: Retrieve a list of all projects.

Response:

json
[
{
"id": 1,
"name": "Project Name",
"description": "Project Description",
"startDate": "2022-03-15",
"endDate": "2022-03-30"
},
{
"id": 2,
"name": "Another Project",
"description": "Another Project Description",
"startDate": "2022-04-01",
"endDate": "2022-04-15"
}
]

## Get Project by ID
Endpoint: GET /api/projects/{id}

Description: Retrieve a project by ID.

Response:

json
Copy code
{
"id": 1,
"name": "Project Name",
"description": "Project Description",
"startDate": "2022-03-15",
"endDate": "2022-03-30"
}

## Delete a project
Endpoint: /projects/delete/{id}
Method: DELETE
Description: Delete a project.
Parameters:
id: Project ID to delete.
Response:
200 OK if the project is successfully deleted.
404 Not Found if the project with the given ID does not exist.