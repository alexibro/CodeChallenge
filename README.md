# Code Challenge

## How to run

```
docker-compose up
```

### Execute API calls

Postman collection has been provided to test both microservices.

**Important:** In order to make calls to the proxy (and this redirects to the java microservice) it is necessary to login and copy the generated Token in the authentication headers of the other calls.

Tweets reader base url: http://localhost:8080/api/ (expose it in order to test it directly)

      Get Tweets: GET http://localhost:8080/api/tweet
      
      Get Tweet: GET http://localhost:8080/api/tweet/{id}
      
      Validate Tweet: PUT http://localhost:8080/api/tweet/{id}/validate
      
      Get validated Tweets by user: GET http://localhost:8080/api/tweet/validated?user={user}
      
      Get Top Hashtags: GET http://localhost:8080/api/tweet/hashtags
          - Query param: limit (Default 10)
      

Auth proxy base url: http://localhost:3000/

      Login: POST http://localhost:8080/login
      
          - BODY: {
	                  "name":"user",
	                  "pass":"pass"
                  }
                  
      Get Tweets: GET http://localhost:8080/api/tweet
      
      Get Tweet: GET http://localhost:8080/api/tweet/{id}
      
      Validate Tweet: PUT http://localhost:8080/api/tweet/{id}/validate
      
      Get validated Tweets by user: GET http://localhost:8080/api/tweet/validated?user={user}
      
      Get Top Hashtags: GET http://localhost:8080/api/tweet/hashtags

          - Query param: limit (Default 10)

### Modify filters

Change the parameters in the src/main/resources/**filter.properties**

**Important:** To track keywords is required (Default: java)

### Additional Information

H2 embedded database restricts Tweet text to VARCHAR(255). If a Tweet exceeds this length, the database throws an exception but saves the truncated tweet. This behavior has been kept for efficiency reasons during development. Easily modifiable.

H2 database console: http://localhost:8080/h2-console
- user: tweets
- password: pass

## Swagger Documentation

The application has been documented using a swagger.
Accessible from the endpoint: http://localhost:8080/swagger-ui.html#/

## Test

Unit tests has beed added for controllers layer. 
(As a sample. Unit tests for persistance and services layer could be implemented, as well as integration tests)

## Deployment:

### Multi-stage Dockerfile

"Tweets reader" has a **multi-stage Dockerfile**:

* **Builds the application JAR**
* **Executes the JAR and expose it**

### Docker-compose

* **tweets-reader service:** Builds image from Dockerfile, exposes and connects it to internal network. It uses H2 embedded database.
* **auth-proxy service:** Builds image from Dockerfile, exposes and redirects connections to tweets-reader service. **The parameterized implementation** allows redirect auth-proxy to tweets-reader endpoint inside the container and internal network.
* **Internat network:** It allows and manages communication between containers.
