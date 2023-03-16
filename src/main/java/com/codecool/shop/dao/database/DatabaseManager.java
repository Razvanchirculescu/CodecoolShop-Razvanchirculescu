package com.codecool.shop.dao.database;

import com.codecool.shop.dao.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.ProductDaoJdbc;
import com.codecool.shop.dao.SupplierDaoJdbc;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;


public class DatabaseManager {

    private ProductDaoJdbc productDao;
    private ProductCategoryDaoJdbc productCategoryDao;
    private SupplierDaoJdbc supplierDao;

    public void setup() throws SQLException, FileNotFoundException {
        DataSource dataSource = connect();
        productDao = new ProductDaoJdbc(dataSource);
        supplierDao = new SupplierDaoJdbc(dataSource);
        productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
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

    public List<ProductCategory> getAllCategories() {
        return productCategoryDao.getAll();
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAll();
    }

    public ProductDaoJdbc getProductDao() {
        return productDao;
    }

    public ProductCategoryDaoJdbc getProductCategoryDao() {
        return productCategoryDao;
    }

    public SupplierDaoJdbc getSupplierDao() {
        return supplierDao;
    }
}



