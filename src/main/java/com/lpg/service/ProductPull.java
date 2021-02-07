package com.lpg.service;

import com.lpg.model.Product;
import com.lpg.util.Constants;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    METHOD: GET
    EXPECTED RESPONSE: array of objects containing products data
*/

@Path("/products")
public class ProductPull {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() throws IOException, CsvValidationException {

        // Create an ArrayList which will hold all of the products
        List<Product> products = new ArrayList<>();

        // Create a map in order to map the category Ids from the Products and Categories data
        Map<String, String> categories = new HashMap<>();

        String categoriesFile = Constants.CATEGORIES_FILE_LOCATION;
        CSVReader categoryReader = new CSVReader(new FileReader(categoriesFile));

        boolean skipFirstIteration = false;

        // Read each category
        String [] eachCategoryColumn;
        while ((eachCategoryColumn = categoryReader.readNext()) != null) {
            categories.put(eachCategoryColumn[0], eachCategoryColumn[1]);
        }

        String productsFile = Constants.PRODUCTS_FILE_LOCATION;
        CSVReader productReader = new CSVReader(new FileReader(productsFile));

        // Read each product, row by row, and create the associated object to be included into the ArrayList
        String [] eachProductColumn;
        while ((eachProductColumn = productReader.readNext()) != null) {

            // Required so we do not include the column titles in the returned object
            if (!skipFirstIteration) {
                skipFirstIteration = true;
                continue;
            }

            Product product = new Product();
            product.setId(eachProductColumn[0]);
            product.setName(eachProductColumn[1]);
            product.setDescription(eachProductColumn[2]);
            product.setCategoryId(eachProductColumn[3]);
            product.setCreationDate(eachProductColumn[4]);
            product.setUpdateDate(eachProductColumn[5]);
            product.setLastPurchasedDate(eachProductColumn[6]);

            if (categories.get(product.getCategoryId()) != null) {
                product.setCategoryName(categories.get(product.getCategoryId()));
            }

            products.add(product);
        }

        return Response.status(200).entity(products).build();
    }

}
