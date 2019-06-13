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

<p>Applied technologies include:
<ul>
<li>Spring Boot 2.1.5</li>
<li>Spring Security 2.1.5</li>
<li>JWT Authentication</li>
<li>HTTPS Redirect</li>
<li>JPA and H2 Database</li>
<li>Swagger Documentation</li>
<li>Actuator</li>
<li>Jacoco Code Coverage</li>
</ul>

## Running Application ##
<p>I have included a  <b>movies-0.0.1-SNAPSHOT.jar</b> file on application root
that you can run using the following JVM command</p>

```
    jar -jar movies-0.0.1-SNAPSHOT.jar.jar
```

<p>SSL is implemented for the system using a self signed certificicate therefore all requests are redirected
to HTTPS port <b>8443</b>. You will therefore need to allow the ssl certificate verification error on the browser at first access
</p>
<p>Testing the API requests via Postman also requires you to turn off the SSL Certificate Verification in Postman 
preferences to successfully receive responses due to the self signed certificate</p>

## Development Tools ##
* IntelliJ IDEA
* Postman
