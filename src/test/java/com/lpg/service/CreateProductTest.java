package com.lpg.service;

import com.lpg.model.Product;
import com.lpg.util.TestConstants;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.FileReader;
import java.io.IOException;

public class CreateProductTest {

    @Test
    public void verifyCreateSuccess() throws IOException, CsvValidationException {

        // Given
        CsvLogic csvLogic = new CsvLogic();
        Product product = new Product();
        product.setName("Test name");
        product.setCategoryName("Kitchen");
        product.setDescription("Test description");
        Boolean nameFound, descriptionFound;
        nameFound  = descriptionFound = false;

        // When
        csvLogic.write(product, TestConstants.TEST_PRODUCTS_FILE_LOCATION, TestConstants.TEST_CATEGORIES_FILE_LOCATION);

        // Then
        CSVReader productReader = new CSVReader(new FileReader(TestConstants.TEST_PRODUCTS_FILE_LOCATION));
        String [] eachProductColumn;
        while ((eachProductColumn = productReader.readNext()) != null) {
            if (product.getName().equalsIgnoreCase(eachProductColumn[1])) { nameFound = true; }
            if (product.getDescription().equalsIgnoreCase(eachProductColumn[2])) { descriptionFound = true; }
        }
        Assert.assertTrue(nameFound);
        Assert.assertTrue(descriptionFound);
    }

    // TODO: test error scenarios
}
