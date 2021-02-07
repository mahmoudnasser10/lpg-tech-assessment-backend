package com.lpg.service;

import com.lpg.util.Constants;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
}
