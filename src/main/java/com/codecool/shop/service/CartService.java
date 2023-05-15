package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CartService {

    private CartDao cartDao;

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }


    public Map<Product, Integer> getCartProducts() {

        HashMap<Product, Integer> unorderedCartProducts = cartDao.getAll();

        HashMap<Product, Integer> orderedHashMap = new LinkedHashMap<>();

        List<Integer> productIdList = new ArrayList<>();

        for (Product product : unorderedCartProducts.keySet()) {
            productIdList.add(product.getId());
        }

        Collections.sort(productIdList);

        for (Integer id : productIdList) {
            for (Product product : unorderedCartProducts.keySet()) {
                if (product.getId() == id) {
                    int quantity = unorderedCartProducts.get(product);
                    orderedHashMap.put(product, quantity);
                }
            }
        }

        return orderedHashMap;
//        return cartDao.getAll();
    }


    public Integer setCartDiscount(Integer discount) {
        cartDao.setDiscount(discount);
        return discount;
    }
}
