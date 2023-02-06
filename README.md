# Point System

A Point System that records payments and returns the sale data based on the payment date. This system uses PostgreSQL as the database, Git as version control, and implements good coding practices and patterns to ensure maintainability, testability, and scalability.

## Requirements

- PostgreSQL
- Git

## Architecture

The system consists of two main components:

- Payment Repository: This component is responsible for storing and retrieving payment data from the PostgreSQL database.
- Query Service: This component exposes a gRPC service to clients for retrieving the sales data based on the payment date. It communicates with the Payment Repository to retrieve the required data and returns it to the client.

## Code

The codebase follows a clear and maintainable structure, with good coding practices and patterns implemented to ensure extensibility and testability.

The system has been implemented with consideration for multi-threading, multiple servers, and scalability with newer payment methods.

## Execution

To execute the system, follow these steps:

1. Clone the repository.

git clone https://github.com/{username}/point-system.git



2. Navigate to the project directory.

cd point-system



3. Run the database and create the required tables.

psql -U {username} -d {database_name} -f database.sql

_Schema and initial data are in classpath or the project_


4. Start the Query Service.

```
mvn clean install
mvn spring-boot:run
```


5. Test the Query Service by sending a gRPC request.

## Testing

The system has been tested for correctness and performance. The test cases cover different scenarios and edge cases to ensure the robustness of the system.

## Conclusion

The Point System is a scalable and maintainable solution for recording payments and retrieving sales data based on the payment date. The system has been implemented with consideration for good coding practices and patterns, testability, scalability, and extensibility.