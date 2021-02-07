# Product Management Application - Persistence Layer

## Introduction
Given a set of products and categories, this application will expose an API 
endpoint to retrieve the data

## Requirements
* Maven installed to compile and run the application
* Java 1.8 installed to compile and run the application
* Connectivity to the Internet to download dependencies needed to compile 
and run the application

## How to compile and run the application
1. Locate the root directory (location of the pom.xml file)
2. If this is the first time you are compiling the application, run 
`mvn clean install` in order to ensure all dependencies are downloaded.
Otherwise, simply run `mvn clean package`. By default, both the commands
mentioned will execute the unit tests
3. Start the Jetty server using `mvn jetty:run`
4. By default, the application will start on port 8080