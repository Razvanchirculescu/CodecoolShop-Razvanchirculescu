package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao{

    private DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, department, description FROM product_category WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            ProductCategory result = null;
            if (rs.next()) { // while result set pointer is positioned before or on last row read authors
                result = new ProductCategory(rs.getString(2), rs.getString(3), rs.getString(4));
                result.setId(rs.getInt(1));
            }
            System.out.println(result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding category by id.", e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, department, description FROM product_category";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<ProductCategory> result = new ArrayList<>();

            while (rs.next()) {
                ProductCategory category = new ProductCategory(rs.getString(2), rs.getString(3), rs.getString(4));
                category.setId(rs.getInt(1));
                result.add(category);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
