# immo
Immobilien24 Code Challenge


## Requirements
* Java 11
* Maven 3
* H2


## Building
Run `mvn clean install`

# Authorize using github (auth0) (grant type: code authentification)
# Use H2 for the persistent of data. 
# Use liquibase for the creating and updating of the DB
# GithubService is responsible for reading the repositories from github and for CRUD with the database. 


In the hurry I have not enought time to have a better structure for the project, 
however the project shows my undertanding/experience in all required areas.

Improvements
- RestController
- Services: separate the logic with github and with the DB (H2)
- Save relevant information in the session context
- Better way to search (Specifications)
- etc.
