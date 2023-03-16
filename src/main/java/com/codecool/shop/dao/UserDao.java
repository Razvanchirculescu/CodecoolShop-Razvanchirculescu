package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.user.User;

public interface UserDao {

    void add(Supplier supplier);

    User find(int id);
}
