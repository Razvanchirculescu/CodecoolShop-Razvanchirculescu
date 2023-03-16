package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.user.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJdbc implements UserDao{

    private DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public User find(int id) {
//        User user = null;
//        try (Connection connection = dataSource.getConnection()) {
//            String sql = "SELECT * FROM product WHERE id = ?";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setInt(1, id);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                user = new Product(rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5),
//                        getCategoryById(rs.getInt(6)), getSupplierById(rs.getInt(7)));
//                product.setId(id);
//            }
//            return product;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }
}
