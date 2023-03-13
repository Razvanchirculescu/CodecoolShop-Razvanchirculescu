package com.codecool.shop.dao.database;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.SQLException;


public class DatabaseManager {

    private ProductDaoMem productDaoMem;
    private ProductCategoryDaoMem productCategoryDaoMem;
    private SupplierDaoMem supplierDaoMem;

    public void setup() throws SQLException, FileNotFoundException {
        DataSource dataSource = connect();
    }

    private DataSource connect() throws SQLException, FileNotFoundException {

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        ApplicationProperties properties = new ApplicationProperties();

        dataSource.setDatabaseName(properties.getProperty("database"));
        dataSource.setUser(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}



