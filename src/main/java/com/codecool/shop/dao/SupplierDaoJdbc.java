package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO supplier (name, description) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM supplier WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            Supplier result = null;
            if (rs.next()) { // while result set pointer is positioned before or on last row read authors
                result = new Supplier(rs.getString(2), rs.getString(3));
                result.setId(rs.getInt(1));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding category by id.", e);
        }
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<Supplier> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM supplier";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<Supplier> result = new ArrayList<>();

            while (rs.next()) {
                Supplier supplier = new Supplier(rs.getString(2), rs.getString(3));
                supplier.setId(rs.getInt(1));
                result.add(supplier);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
