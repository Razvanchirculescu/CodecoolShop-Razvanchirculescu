package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Supplier legoStarWars = new Supplier("StarWars", "Lego Star Wars");
        supplierDataStore.add(legoStarWars);
        Supplier legoHarryPotter = new Supplier("HarryPotter", "Lego Harry Potter");
        supplierDataStore.add(legoHarryPotter);
        Supplier legoAlphabetTruck = new Supplier("AlphabetTruck", "Lego Alphabet Truck");
        supplierDataStore.add(legoAlphabetTruck);
        Supplier legoBarbie = new Supplier("DisneyPrincess", "Disney Princess");
        supplierDataStore.add(legoBarbie);

        Supplier travelDoll = new Supplier("TravelDoll", "Travel Doll");
        supplierDataStore.add(travelDoll);

        ProductCategory lego = new ProductCategory("Lego", "Toys", "A line of plastic construction toys");
        productCategoryDataStore.add(lego);
        ProductCategory doll = new ProductCategory("Doll", "Toys", "Toy for girls");

        productDataStore.add(new Product("LEGO Star Wars - Dark Trooper Attack", new BigDecimal("68.99"), "USD",
                "set for Luke Skywalker vs. Dark Troopers battles – Fans can relive Luke Skywalker’s return.", lego, legoStarWars));
        productDataStore.add(new Product("LEGO Harry Potter Hogwarts Magical Trunk", new BigDecimal("51.99"), "USD",
                "Personalized and portable, the LEGO Harry Potter Hogwarts Magical Trunk for ages 8+.", lego, legoHarryPotter));
        productDataStore.add(new Product("LEGO DUPLO My First Alphabet Truck", new BigDecimal("42.40"), "USD",
                "With the LEGO DUPLO Alphabet Truck, preschoolers can combine creative building.", lego, legoAlphabetTruck));
        productDataStore.add(new Product("Barbie Travel Doll", new BigDecimal("30.00"), "USD",
                "Send curious minds around the world with Barbie doll and a travel-themed set inspired by " +
                        "Barbie Dream-house Adventures that comes with a puppy for a travel companion!", doll, travelDoll));
        productDataStore.add(new Product("Disney Princess Magic Travel Toy", new BigDecimal("35.01") , "USD",
                "With Cinderella, Jasmine, Rapunzel Mini Dolls, Toy Horse & Carriage, Flying Rug, Hot Air Balloon for Girls and Boys.", lego, legoBarbie));
    }
}
