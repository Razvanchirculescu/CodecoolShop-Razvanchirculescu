package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao{

    private DataSource dataSource;

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<Product> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT name, defaultprice, description, currencystring, product_category_id, supplier_id, id FROM product";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<Product> result = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product(rs.getString(1), rs.getBigDecimal(2), rs.getString(4),
                        rs.getString(3), getCategoryById(rs.getInt(5)), getSupplierById(rs.getInt(6)));
                product.setId(rs.getInt(7));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Supplier getSupplierById(int id) {
        Supplier supplier = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT name, description FROM supplier WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                supplier = new Supplier(rs.getString(1), rs.getString(2));
            }
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ProductCategory getCategoryById(int id) {
        ProductCategory productCategory = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT name, department, description FROM product_category WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                productCategory = new ProductCategory(rs.getString(1), rs.getString(2), rs.getString(3));
            }
            return productCategory;
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
