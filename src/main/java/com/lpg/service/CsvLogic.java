package com.lpg.service;

import com.lpg.model.Product;
import com.lpg.util.Constants;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvLogic {

    public void write(Product product, String productsFile, String categoriesFile)
                    throws IOException, CsvValidationException {
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

        // TODO: iterate over the csv file in reverse rather than loop from the beginning
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
    }

    public List<Product> read(String productsFile, String categoriesFile) throws IOException, CsvValidationException {

        List<Product> products = new ArrayList<>();

        // Create a map in order to map the category Ids from the Products and Categories data
        Map<String, String> categories = new HashMap<>();

        CSVReader categoryReader = new CSVReader(new FileReader(categoriesFile));

        boolean skipFirstIteration = false;

        // Read each category
        String [] eachCategoryColumn;
        while ((eachCategoryColumn = categoryReader.readNext()) != null) {
            categories.put(eachCategoryColumn[0], eachCategoryColumn[1]);
        }

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

        return products;
    }
}
