package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.user.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;

public class CartDaoJdbc implements CartDao{

    private DataSource dataSource;

    public CartDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {
        String [] allProducts = getAllProductsForCart();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE cart SET product_list = ? where user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, 0);
            String[] newList = Arrays.copyOf(allProducts, allProducts.length + 1);
            newList[allProducts.length] = String.valueOf(product.getId());
            Array array = conn.createArrayOf("text", newList);
            statement.setArray(1, array);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String[] getAllProductsForCart() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT product_list FROM cart where user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, 0);
            ResultSet rs = statement.executeQuery();
            String[] str_products = new String[0];
            if (rs.next()) {
                Array products = rs.getArray(1);
                str_products = (String[]) products.getArray();
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
//        String[] productsId = getAllProductsForCart()

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
