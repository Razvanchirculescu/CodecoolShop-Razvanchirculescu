package com.codecool.shop.model;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart extends BaseModel {

    private HashMap<Product, Integer> cartDict;


    public Cart (String name) {
        super(name);
        this.cartDict = new HashMap<>();
    }

    //adds 1 for each product sected for cart with add to cart button
    public void addtoCart (Product product) {
        cartDict.merge(product, 1, Integer::sum);
    }

    public HashMap<Product, Integer> getProductDict () {
        return cartDict;
    }


}
