# Alumini-Networking-System

Simple alumni networking backend built with Spring Boot and a basic frontend built with HTML, CSS, and JavaScript.

## Project Structure

- `alumini-network/` contains the Spring Boot application
- `alumini-network/src/main/resources/static/` contains the frontend UI

## Features

- Register alumni
- View alumni directory
- Post jobs
- Create alumni connections
- Use the in-memory H2 database for quick local testing

## Run the Project

1. Open a terminal in `/Users/pranavnandkumarmahajan/Desktop/Alumini-Networking-System/alumini-network`
2. Start the app with:

```bash
./mvnw spring-boot:run
```

3. Open the UI in your browser:

```text
http://localhost:8080/
```

You can also use:

```text
http://localhost:8080/home
http://localhost:8080/ui
```

4. Optional H2 console:

```text
http://localhost:8080/h2-console
```

## Main API Endpoints

- `POST /api/alumni/register`
- `GET /api/alumni/all`
- `GET /api/jobs`
- `POST /api/jobs`
- `GET /api/connections`
- `POST /api/connections`
