package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Cart;

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

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        Supplier legoWorld = new Supplier("Lego World", "Lego Supplier");
        supplierDataStore.add(legoWorld);
        Supplier duplo = new Supplier("Duplo", "Lego Supplier");
        supplierDataStore.add(duplo);
        System.out.println(duplo);
        Supplier barbie = new Supplier("Barbie", "Barbie Supplier");
        supplierDataStore.add(barbie);
        Supplier cars = new Supplier("Cars", "Cars");
        supplierDataStore.add(cars);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);

        //setting up products and printing it
        Product product1 = new Product("Amazon Fire", new BigDecimal("0.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        Product product2 = new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);
        Product product3 = new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon);

//        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
//        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
//        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(product1);
        productDataStore.add(product2);
        productDataStore.add(product3);



        Cart cart1 = new Cart("cart1");
        cart1.addtoCart(product1);
        cart1.addtoCart(product2);
        cart1.addtoCart(product3);

        CartDaoMem cartDao = CartDaoMem.getInstance();

        cartDao.add(product1);
        cartDao.add(product2);
        cartDao.add(product3);
        cartDao.add(product1);
        cartDao.add(product1);

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
