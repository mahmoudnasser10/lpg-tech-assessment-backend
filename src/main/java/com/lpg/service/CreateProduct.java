package com.lpg.service;

import com.lpg.model.Product;
import com.lpg.util.Constants;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
    METHOD: POST
    EXPECTED BODY:
    {
        "name":"{name}}",
        "categoryName":"{categoryName}",
        "description":"{description}"
    }
    METHOD-TYPE: Application/json
*/

@Path("products/create")
public class CreateProduct {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) throws IOException, CsvValidationException {

        CsvLogic csvLogic = new CsvLogic();

        String productsFile = Constants.PRODUCTS_FILE_LOCATION;
        String categoriesFile = Constants.CATEGORIES_FILE_LOCATION;

        csvLogic.write(product, productsFile, categoriesFile);

        return Response.status(200).build();
    }

}
