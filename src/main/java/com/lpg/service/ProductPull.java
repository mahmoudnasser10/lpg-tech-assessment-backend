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

        CsvLogic csvLogic = new CsvLogic();
        String productsFile = Constants.PRODUCTS_FILE_LOCATION;
        String categoriesFile = Constants.CATEGORIES_FILE_LOCATION;

        products = csvLogic.read(productsFile, categoriesFile);

        return Response.status(200).entity(products).build();
    }

}
