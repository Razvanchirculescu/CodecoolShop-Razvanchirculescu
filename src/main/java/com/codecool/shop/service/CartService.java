package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.List;

public class CartService {

    private CartDao cartDao;

    public CartService (CartDao cartDao) {
        this.cartDao = cartDao;
    }

//    public List<Product> getCartProducts() {
//        return cartDao.getAll();
//    }

    public HashMap<Product, Integer> getCartProducts() {
        return cartDao.getAll();
    }


}
