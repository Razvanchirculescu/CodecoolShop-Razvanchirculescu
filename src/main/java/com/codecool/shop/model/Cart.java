package com.codecool.shop.model;

import java.util.HashMap;

public class Cart extends BaseModel {

    private HashMap<Product, Integer> cartDict;
    private int user_id;


    public Cart(int user_id) {
        this.name = "cart";
        this.cartDict = new HashMap<>();
        this.user_id = user_id;
    }

    public void addToCart(Product product) {
        cartDict.merge(product, 1, Integer::sum);
    }

    public HashMap<Product, Integer> getProductDict() {
        return cartDict;
    }

    public int getUser() {
        return user_id;
    }

    public void setCartDict(HashMap<Product, Integer> cartDict) {
        this.cartDict = cartDict;
    }
}
