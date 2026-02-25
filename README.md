# Hello API 

Simple Spring Boot app for the "Hello World" name check task.

### How it works
The app has one endpoint: `GET /hello-world?name=...`
- Returns a greeting if the name starts with A-M.
- Returns 400 (Invalid Input) for N-Z or if the name is missing/weird.

I added a bit of formatting to the output so it capitalizes the name (e.g. alice -> Alice). It also trims extra spaces just in case.

### Running it
To start the server:
`mvn spring-boot:run`

Server runs on port 8080 by default.
Ex : 
localhost:8080/hello-world?name= zharith


### Tests
Just run:
`mvn test`

I've included basic tests for names like Alice and Noah, plus checks for empty inputs and numbers.