package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CartDaoJdbc implements com.codecool.shop.dao.CartDao {


    private List<Product> data = new ArrayList<>();
    private HashMap<Product, Integer> dataMap = new HashMap<>();

    private static CartDaoJdbc instance = null;

    private static Integer discount = 0;

    private CartDaoJdbc() {}


    public static CartDaoJdbc getInstance() {
        if (instance == null) {
            instance = new CartDaoJdbc();
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

    public void delAllProductID (Product product) {
        this.dataMap.remove(product);
    }

    public String getTotalSum() {

        BigDecimal count = new BigDecimal("0");
       for (Product p : dataMap.keySet())
           count = count.add(p.getDefaultPrice().multiply(new BigDecimal(dataMap.get(p))));


        System.out.println(count+"asd");
        return String.valueOf(count);
    }

    public String getAllProducts() {
        List<String> list = new ArrayList<>();
        for (Product p : dataMap.keySet())
            list.add(p.getName());
        String allCheckedOutProducts = Arrays.toString(list.toArray()).replace("[", "").replace("]", "");

        System.out.println(allCheckedOutProducts + "  ALL PRODS");
        return allCheckedOutProducts;
    }

    @Override
    public HashMap<Product, Integer> getAll() {
        System.out.println(dataMap);
        return dataMap;
    }

}
