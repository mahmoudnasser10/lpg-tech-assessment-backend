package com.lpg.service;

import com.lpg.model.Product;
import com.lpg.util.Constants;
import com.lpg.util.TestConstants;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductPullTest {

    @Test
    public void verifyDocumentsExist() {
        // Given
        File productsFile = new File(Constants.PRODUCTS_FILE_LOCATION);
        File categoriesFile = new File(Constants.CATEGORIES_FILE_LOCATION);

        // Then
        Assert.assertTrue(productsFile.exists());
        Assert.assertTrue(categoriesFile.exists());
    }

    @Test
    public void verifyProductsFileColumns() throws IOException, CsvValidationException {
        // Given
        String productsFile = Constants.PRODUCTS_FILE_LOCATION;
        CSVReader productReader = new CSVReader(new FileReader(productsFile));

        // When
        String[] columnNames = productReader.readNext();

        // Then
        Assert.assertEquals("ID", columnNames[0]);
        Assert.assertEquals("NAME", columnNames[1]);
        Assert.assertEquals("DESCRIPTION", columnNames[2]);
        Assert.assertEquals("CATEGORY_ID", columnNames[3]);
        Assert.assertEquals("CREATION_DATE", columnNames[4]);
        Assert.assertEquals("UPDATE_DATE", columnNames[5]);
        Assert.assertEquals("LAST_PURCHASED_DATE", columnNames[6]);
    }

    @Test
    public void verifyCategoriesFileColumns() throws IOException, CsvValidationException {
        // Given
        String productsFile = Constants.CATEGORIES_FILE_LOCATION;
        CSVReader categoryReader = new CSVReader(new FileReader(productsFile));

        // When
        String[] columnNames = categoryReader.readNext();

        // Then
        Assert.assertEquals("ID", columnNames[0]);
        Assert.assertEquals("CATEGORY_NAME", columnNames[1]);
    }

    @Test
    public void verifySuccessfulRequest() throws IOException, CsvValidationException {
        // Given
        ProductPull productPull = new ProductPull();

        // When
        Response response = productPull.getProducts();

        // Then
        Assert.assertEquals(response.toString(), 200, response.getStatus());
    }

    @Test
    public void verifyCsvReadSuccess() throws IOException, CsvValidationException {
        // Given
        CsvLogic csvLogic = new CsvLogic();
        List<Product> products;
        String productsFile = TestConstants.TEST_PRODUCTS_FILE_LOCATION;
        String categoriesFile = TestConstants.TEST_CATEGORIES_FILE_LOCATION;

        // When
        products = csvLogic.read(productsFile, categoriesFile);

        // Then
        Assert.assertNotNull(products);
    }

    // TODO: test error scenarios
}
