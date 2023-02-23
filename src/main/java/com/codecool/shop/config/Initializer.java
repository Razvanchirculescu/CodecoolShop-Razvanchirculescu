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

        Supplier legoWorld = new Supplier("Lego World", "Lego Supplier");
        supplierDataStore.add(legoWorld);
        Supplier duplo = new Supplier("Duplo", "Lego Supplier");
        supplierDataStore.add(duplo);
        System.out.println(duplo);
        Supplier barbie = new Supplier("Barbie", "Barbie Supplier");
        supplierDataStore.add(barbie);
        Supplier cars = new Supplier("Cars", "Cars");
        supplierDataStore.add(cars);


        ProductCategory lego = new ProductCategory("Lego", "Toys", "A line of plastic construction toys");
        productCategoryDataStore.add(lego);
        ProductCategory doll = new ProductCategory("Doll", "Toys", "Toy for girls");
        productCategoryDataStore.add(doll);
        ProductCategory car = new ProductCategory("Car", "Toy","Toys for boy");
        productCategoryDataStore.add(car);

        productDataStore.add(new Product("LEGO Star Wars - Dark Trooper Attack", new BigDecimal("68.99"), "USD",
                "set for Luke Skywalker vs. Dark Troopers battles – Fans can relive Luke Skywalker’s return.", lego, legoWorld));
        productDataStore.add(new Product("LEGO Harry Potter Hogwarts Magical Trunk", new BigDecimal("51.99"), "USD",
                "Personalized and portable, the LEGO Harry Potter Hogwarts Magical Trunk for ages 8+.", lego, legoWorld));
        productDataStore.add(new Product("LEGO DUPLO My First Alphabet Truck", new BigDecimal("42.40"), "USD",
                "With the LEGO DUPLO Alphabet Truck, preschoolers can combine creative building.", lego, duplo));
        productDataStore.add(new Product("Disney Princess Magic Travel Toy", new BigDecimal("35.01") , "USD",
                "With Cinderella, Jasmine, Rapunzel Mini Dolls, Toy Horse & Carriage, Flying Rug, Hot Air Balloon for Girls and Boys.", lego, barbie));
        productDataStore.add(new Product("Barbie Travel Doll", new BigDecimal("30.00"), "USD",
                "Send curious minds around the world with Barbie doll and a travel-themed set inspired by " +
                        "Barbie Dream-house Adventures that comes with a puppy for a travel companion!", doll, barbie));
        productDataStore.add(new Product("LEGO Friends Aliya's Room", new BigDecimal("18.00"), "USD",
                "Children aged 6 and up can host a fun sleepover party in LEGO Friends Aliya's rooms. This fun mini " +
                        "doll play set stimulates imagination and inspires creative stories with the figure.", lego, legoWorld));
        productDataStore.add(new Product("Barbie GBK12 Portable Wardrobe", new BigDecimal("29.08"), "USD",
                "Barbie's dream wardrobe testifies style inside and outside with the included Barbie doll, fashion " +
                        "and accessories. The pink wardrobe has two clear cabinet doors.", doll, barbie));
        productDataStore.add(new Product("20 Metal Pull Back Mini Toy Cars", new BigDecimal("29.99"), "USD",
                "SUPER PACK. Our pull-back model model toy cars from model building includes a total of 20 toy cars: " +
                        "police cars, fire engines, bulldozers, dump trucks, mountain cars and other.", car, cars));
    }
}
