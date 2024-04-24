

# Event Finder API

This project is an Event Finder API that allows users to find events near them. The API fetches events from a database, calculates the distance from the user to each event, fetches the weather for each event location, and returns the events sorted by date.

## Tech Stack

- **Java**: The main programming language used for developing the application.
- **Spring Boot**: Used for creating the RESTful APIs.
- **Maven**: Dependency management.
- **JPA/Hibernate**: Used for object-relational mapping and data persistence.
- **H2 Database**: In-memory database used for storing the events.

## Design Decisions

- **Database Indexing**: An index was added to the `date` field in the `EventModel` class to optimize queries that filter by date.

## Setup and Run

1. Clone the repository: `git clone https://github.com/SomayajuluSharma/EventManagementSystem.git`
2. Navigate to the project directory: `cd event-finder-api`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

## API Endpoints

- **GET /events/find**: Fetches events near the user.

    Request parameters:
    - `latitude`: The latitude of the user's location.
    - `longitude`: The longitude of the user's location.
    - `date`: The date for which to fetch events.

    Response format: A list of events sorted by date. Each event includes the event name, city name, date, weather, and distance from the user.

    Error codes:
    - `400 Bad Request`: If the request parameters are invalid.
    - `500 Internal Server Error`: If an error occurs while processing the request.
    - 
## Screenshots
- screen shots added in screenShots file
