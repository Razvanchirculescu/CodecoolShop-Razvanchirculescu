package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.util.HashMap;

public class CartDaoJdbc implements CartDao{

    private DataSource dataSource;

    public CartDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void add(Product product) {

    }

    @Override
    public void addQuantity(Product product, Integer quantity) {

    }

    @Override
    public void removeAll() {

    }

    @Override
    public void removeOne(Product product) {

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
}
