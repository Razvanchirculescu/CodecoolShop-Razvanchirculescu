package com.codecool.shop.dao.database;

import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.SQLException;


public class DatabaseManager {

    private ProductDaoJdbc productDaoJdbc;
    private ProductCategoryDaoJdbc productCategoryDaoJdbc;
    private SupplierDaoJdbc supplierDaoJdbc;

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



