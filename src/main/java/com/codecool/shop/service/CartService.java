package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;

public class CartService {

    private CartDao cartDao;

    public CartService (CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public CartDao getCartDao() {
        return cartDao;
    }
}
