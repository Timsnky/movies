# README #

## Safaricom Movies Webservice ##

### What is this repository for? ###

A simple spring boot movies restful web service that provides the following functions:

* User Registration and Login
* Access of Paginated List of Movies
* Creation and Edit of Movies
* Access to Details of a Specific Movie
* Deletion of a Movie
* Access to List of Watched and Unwatched Movies

Applied technologies include:
* Spring Boot 2.1.5
* Spring Security 2.1.5
* JWT Authentication
* HTTPS Redirect
* JPA and H2 Database
* Swagger Documentation
* Actuator
* Jacoco Code Coverage
* Docker


## Running Application ##

#### Using Host JVM ####

I have included a  `run.sh` shell file on application root that you can run to package the application and run it.
Use the command below to initiate the process

```
    ./run.sh
```

#### Using Docker ####

I have included a `docker.sh` shell file on the `docker` folder on the application root that you can use to create a docker 
container abd run the application. Use the command below to initiate the process while inside the docker folder

```
    ./docker.sh
```


SSL is implemented for the system using a self signed certificicate therefore all requests are redirected
to HTTPS port `8443`. You will therefore need to allow the ssl certificate verification error on the browser at first access

Testing the API requests via Postman also requires you to turn off the SSL Certificate Verification in Postman 
preferences to successfully receive responses due to the self signed certificate

## Development Tools ##
* IntelliJ IDEA
* Postman
* Docker
