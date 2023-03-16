package com.codecool.shop.model;

import com.codecool.shop.model.Product;
import com.codecool.shop.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart extends BaseModel {

    private HashMap<Product, Integer> cartDict;
    private User user;


    public Cart (User user) {
        this.name = "cart";
        this.cartDict = new HashMap<>();
        this.user = user;
    }

    //adds 1 for each product sected for cart with add to cart button
    public void addtoCart (Product product) {
        cartDict.merge(product, 1, Integer::sum);
    }

    public HashMap<Product, Integer> getProductDict () {
        return cartDict;
    }

    public User getUser() {
        return user;
    }
}
