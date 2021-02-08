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

## Using the application
The application exposes two separate API endpoints

### Retrieving the products
* When a request is made on `GET /products`, the list of products will be
returned as an array of objects
* The list of products are defined in `src/main/resources/products.csv`

### Creating a new product
* When a request is made on `POST /products/create`, the inputted data will
be inserted into the existing `products.csv` file found in 
`src/main/resources/data`

## Technical debt items
Even though the application functions as expected with regards to the two
points mentioned above, there are a few technical debt items that could
be worked on in the future

`ProductPull.java` and `CreateProduct.java`
* Add error handling scenarios and return different status codes to the
application based on the error in question
* As part of the error handling, include messages in the body to make
it clear to the calling application of the error in question

`CreateProductTest.java` and `ProductPullTest.java`
* Include further tests for error handling scenarios