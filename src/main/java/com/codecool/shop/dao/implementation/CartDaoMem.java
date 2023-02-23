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

    private static Integer discount = 0;

    private CartDaoMem () {}


    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }


    public Integer getDiscount () {
        return discount;
    }

    public void setDiscount (Integer discount) {
        this.discount = discount;
    }

    @Override
//    public void add(Product product) {
//        data.add(product);
//    }
    public void add (Product product) {
        dataMap.merge(product, 1, Integer::sum);
    }

    @Override
    public void addQuantity(Product product, Integer quantity) {
        dataMap.put(product, quantity);
    }


//    @Override
//    public Product find(int id) {
//        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
//    }


    @Override
    public void removeAll () {
        this.dataMap = null;
    }
//    public void remove(int id) {
//        data.remove(find(id));
//    }


    public void removeOne (Product product) {
        if (dataMap.get(product)>1) {
            dataMap.put(product,dataMap.get(product)-1);
        } else {
            this.dataMap.remove(product);
        }

    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return dataMap;
    }

}
