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

@Path("products/create")
public class CreateProduct {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) throws IOException, CsvValidationException {

        String productsFile = Constants.PRODUCTS_FILE_LOCATION;
        String categoriesFile = Constants.CATEGORIES_FILE_LOCATION;

        CSVReader categoryReader = new CSVReader(new FileReader(categoriesFile));
        CSVReader productReader = new CSVReader(new FileReader(productsFile));

        String lastId = "";
        String categoryId = "";

        String [] eachProductColumn;
        String [] eachCategoryColumn;

        // Get the category Id from the category name provided
        while ((eachCategoryColumn = categoryReader.readNext()) != null) {
            if (product.getCategoryName().toLowerCase().equalsIgnoreCase(eachCategoryColumn[1])) {
                categoryId = eachCategoryColumn[0];
            }
        }

        // TODO: iterate over the csv file in reverse
        while ((eachProductColumn = productReader.readNext()) != null) {
            lastId = eachProductColumn[0];
        }

        Integer id = Integer.parseInt(lastId) + 1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        LocalDateTime creationAndUpdateDate = LocalDateTime.now();

        BufferedWriter writer = new BufferedWriter(new FileWriter(productsFile, true));

        writer.newLine();
        writer.write(id + "," + product.getName() + ","
                + product.getDescription() + "," + categoryId +
                "," + dtf.format(creationAndUpdateDate) + ","
                + dtf.format(creationAndUpdateDate) + "," + "N/A");
        writer.close();

        return Response.status(200).build();
    }

}
