package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDaoJdbc implements CartDao{

    private DataSource dataSource;

    public CartDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {
        List<String> allCurrentProducts = getAllProductsForCart();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE cart SET product_list = ? where user_id = 0";
            PreparedStatement statement = conn.prepareStatement(sql);
            String[] list = new String[]{String.valueOf(product.getId())};
            Array array = conn.createArrayOf("text", list);
            statement.setArray(1, array);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllProductsForCart() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT product_list FROM cart where user_id = 0";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<String> str_products = new ArrayList<>();
            if (rs.next()) {
                Array products = rs.getArray(1);
                str_products = (List<String>) products.getArray();
                for (String str_product : str_products) {
                    System.out.println(str_product);
                }
            }
            return str_products;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addQuantity(Product product, Integer quantity) {

    }

    @Override
    public void removeAll() {
    }

    @Override
    public void removeOne (Product product) {

    }

    @Override
    public HashMap<Product, Integer> getAll() {
      return null;
    }

    @Override
    public void setDiscount(Integer discount) {
    }

    @Override
    public Integer getDiscount() {
        return null;
    }

    public void delAllProductID (Product product) {
    }

    public int saveEmptyCart() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cart (user_id, product_list) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, 1);
            statement.setString(2, null);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);            }
            resultSet.next();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
