package com.codecool.shop.user;

import java.util.UUID;

public class User {
    private int id;

    private UUID idUser;

    private String name;


    public User(String name) {
        this.id = 0;
        this.idUser=UUID.randomUUID();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getIdUser() {
        return idUser;
    }
}
