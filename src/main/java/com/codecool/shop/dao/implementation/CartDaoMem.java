package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDaoMem implements CartDao {


    private List<Product> data = new ArrayList<>();
    private HashMap<Product, Integer> dataMap = new HashMap<>();

    private static CartDaoMem instance = null;

    private CartDaoMem () {}


    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
//    public void add(Product product) {
//        data.add(product);
//    }
    public void add (Product product) {
        dataMap.merge(product, 1, Integer::sum);
    }


//    @Override
//    public Product find(int id) {
//        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
//    }


    @Override
//    public void remove(int id) {
//        data.remove(find(id));
//    }
    public void remove () {

    }

    @Override
    public List<Product> getAll() {
        return data;
    }
}
