package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.HashMap;
import java.util.List;

public interface CartDao {

    void add(Product product);

    void addQuantity(Product product, Integer quantity);

//    Product find(Product id);

    void removeAll();

    void removeOne(Product product);

    HashMap<Product, Integer> getAll();

    void setDiscount(Integer discount);

    Integer getDiscount ();

}
