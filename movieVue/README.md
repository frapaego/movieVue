vuejs-springmvc-sample
==========================

An example application using VueJS/Bootstrap as frontend and Spring MVC as REST API producer.

## Requirements

* JDK 8

   Oracle Java 8 is required, go to [Oracle Java website](http://java.oracle.com) to download it and install into your system. 
 
   Optionally, you can set **JAVA\_HOME** environment variable and add *&lt;JDK installation dir>/bin* in your **PATH** environment variable.

* Apache Maven

   Download the latest Apache Maven from [http://maven.apache.org](http://maven.apache.org), and uncompress it into your local system. 

   Optionally, you can set **M2\_HOME** environment varible, and also do not forget to append *&lt;Maven Installation dir>/bin* your **PATH** environment variable.  

## Run this project

1. Clone the codes.

   ```
    git clone https://github.com/frapaego/movieVue
   ```
  
2. And enter the root folder, run `mvn tomcat7:run-war` to start up an embedded tomcat7 to serve this application.
  
   ```
    mvn tomcat7:run
   ```

3. Go to [http://localhost:8080/movieVue/](http://localhost:8080/movieVue/) to test it. If you want to explore the REST API docs online, there is a *Swagger UI* configured for visualizing the REST APIs, just go to [http://localhost:8080/movieVue/swagger-ui.html](http://localhost:8080/movieVue/swagger-ui.html).
