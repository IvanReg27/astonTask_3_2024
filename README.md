# astonTask_3_2024

You need to create REST: Spring (not spring boot) + hibernate + postgresql + liquibase.

1)  Data structure:

Locality
----------------------------------------- 
Identifier (serial number)
Name of locality
Population
Attraction
Metro availability

Attraction
----------------------------------------- 
Identifier
Attraction name
Date of creation
Brief description
Type of attraction (enum, for example: Palaces/Parks/Museums/Archaeological sites/Nature reserves)
Locality
Service

Service (for example: guide, car tour...)
----------------------------------------- 
Identifier
Service name
Brief description
Attraction

2)  REST - The service should provide the following methods:

- Add attraction
- Get all attractions (pass the option to sort by the name of the attraction, option to filter by the type of attraction)
- Get all the attractions of a particular town
- Change of the data for the tourist attraction (only the Short description field can be changed)
- Remove the landmark from the directory
- Add city
- Change of city data (possible to change only fields: Population, availability of metro)

3)  The service must create the required objects in the database itself with Liquibase at first start.
4)  Implement error handling, loging, add Javadoc.
5)  You must use Spring (not Spring Boot), Hibernate, PostgreSQL, Liquibase to implement.
6)  Run the application to perform using Docker-compose.
7)  Source code to be uploaded to GitHub and in the description specify a brief instruction on how to launch the application.
8) The delivery of D3 through sending in personal message in Telegram link to pull request from GitHub.

# **How to run the application:**

1. run Docker Desktop on your computer (it will install the Postgres database);
2. in the development environment IntelliJ IDEA run Maven Install;
3. run the docker-compose.yml file;
4. the application will run on Tomcat (version 10.X); 
5. in the line of your browser type the path: http://localhost:8080/travelagency/sight;
6. you can use Postman and add a new place for visiting tourists (POST: http:///localhost:8080/travelagency/place/save), use the JSON format.