package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class CartDaoJdbc implements CartDao{

    private DataSource dataSource;


    public CartDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //!!!
    @Override
    public void add(Product product) {

        int user_id = 0;

        int productInitialQuantity = getProductQuantity(product);

        try (Connection conn = dataSource.getConnection()) {
            String sql;

            if (productInitialQuantity != 0) {
                sql = "UPDATE cart_b SET product_quantity=? WHERE product_id = ? AND user_id = ?";
            } else {
                sql = "INSERT INTO cart_b (product_quantity, product_id, user_id)" +
                    "VALUES (?, ?, ?)";
            }

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(3, user_id);//user_id
            statement.setInt(2, product.getId());//product_id
            statement.setInt(1, productInitialQuantity + 1);//product_quantity
//            statement.executeQuery();
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


//!!!
    public int getProductQuantity(Product product){
        int user_id=0;
        int result = 0;

        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT product_quantity FROM cart_b " +
                    "WHERE user_id = ? AND product_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,user_id);//user_id
            statement.setInt(2, product.getId());//product_id
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }



    //!!!
    // return hashMap product_id, product_quantity
    public HashMap<Integer,Integer> getAllProductsFromCart() {
        int user_id = 0;

      HashMap<Integer, Integer> cartProducts = new HashMap<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM cart_b where user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);//user_id
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cartProducts.put(resultSet.getInt(3), resultSet.getInt(4));
                System.out.println("cart products: " + resultSet.getInt(3) + " : " + resultSet.getInt(4));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return cartProducts;
    }

    @Override
    public void addQuantity(Product product, Integer quantity) {

    }

    @Override
        public void removeAll(){

    }


    @Override
    public void removeOne (Product product) {

        int user_id = 0;

        int productInitialQuantity = getProductQuantity(product);

        try (Connection conn = dataSource.getConnection()) {
            String sql;

            if (productInitialQuantity >= 2) {
                sql = "UPDATE cart_b SET product_quantity=? WHERE product_id = ? AND user_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(3, user_id);//user_id
                statement.setInt(2, product.getId());//product_id
                statement.setInt(1, productInitialQuantity - 1);//product_quantity
                statement.executeUpdate();

            } else {
                sql = "DELETE FROM cart_b WHERE product_id = ? AND user_id = ? ";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(2, user_id);//user_id
                statement.setInt(1, product.getId());//product_id
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delAllProductID(Product product){
        int user_id = 0;

//        int productInitialQuantity = getProductQuantity(product);

        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM cart_b WHERE product_id = ? AND user_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(2, user_id);//user_id
            statement.setInt(1, product.getId());//product_id
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }




//    !!!
    @Override
    public HashMap<Product, Integer> getAll() {

        HashMap <Integer, Integer> allProductsFromCart = getAllProductsFromCart();
        HashMap<Product, Integer> result = new HashMap<>();
        ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(dataSource);


        for (Integer key : allProductsFromCart.keySet()) {
            Product product = productDaoJdbc.find(key);
            result.put(product,allProductsFromCart.get(key));
        }
        return result;
    }

    //!!!
    @Override
    public void setDiscount(Integer discount) {
        if (discount == null) {
            discount = 0;
        }

        int user_id = 0;

        try (Connection connection = dataSource.getConnection()){
            String sql = "UPDATE user SET user_discount=? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, discount);//user discount
            statement.setInt(2, user_id);//user_id
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    //!!!
    @Override
    public Integer getDiscount() {
        int user_id=0;
        int result = 0;

        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT user_discount FROM user " +
                    "WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,user_id);//user_id
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
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
