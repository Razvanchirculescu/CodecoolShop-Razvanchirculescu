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

        Supplier legoWorld = new Supplier("Lego World", "Lego Supplier");
        supplierDataStore.add(legoWorld);
        Supplier duplo = new Supplier("Duplo", "Lego Supplier");
        supplierDataStore.add(duplo);
        Supplier barbie = new Supplier("Barbie", "Barbie Supplier");
        supplierDataStore.add(barbie);
        Supplier cars = new Supplier("MOONTOY", "Cars");
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
        productDataStore.add(new Product("10 Scale Brushless RC Cars", new BigDecimal("247.62"), "USD",
                "Whether you're steering on sand, concrete, or anything in between, these RC monster trucks for boys and " +
                        "girls are built to withstand rugged surfaces with expert grip, operation, and durability.", car, cars));
        productDataStore.add(new Product("Puzzle Car Tracks Playsets City Rescue", new BigDecimal("19.99"), "USD",
                "Fun manual operations toys set. Parent-child educational toys. Imaginative and creative design. Safe and " +
                        "environmentally car toys set. Perfect gift for kids.", car, cars));
        productDataStore.add(new Product("Cars Set - Bull Truck, Leopard Truck & Crocodile Trucks", new BigDecimal("19.99"), "USD",
                "MOONTOY monster trucks set include 3 unique wild animals toy car figures model: bull, leopard, and crocodile." +
                        " No battery needed energy-saving and environmentally friendly.", car, cars));
        productDataStore.add(new Product("Paw Patrol, Chase’s Movie Transforming Toy Car", new BigDecimal("10.89"), "USD",
                "TRANSFORMING TOY CAR: Push the spoiler to armor up Chase’s Deluxe Vehicle, revealing a projectile launcher in " +
                        "the back! All of the PAW Patrol toy cars have their own transformation!", car, cars));
        productDataStore.add(new Product("Deejoy RC Stunt Car, Remote Control Gesture Sensor Toy Cars", new BigDecimal("38.99"), "USD",
                "Smart remote control mode. Off-Road Multi-Roller Tires. Crazy stunts and cool features. Included Safe Rechargeable " +
                        "Battery. Best Gift Ideas.", car, cars));
        productDataStore.add(new Product("Barbie Camper, Doll Playset with 50 Accessories", new BigDecimal("99.00"), "USD",
                "Transform the colorful Barbie DreamCamper into a campsite play set with the push of a button -the top pops up, the " +
                        "side pops out and the back expands to create a play space more than 2 feet long.", doll, barbie));
        productDataStore.add(new Product("Barbie Doll and Pet Boutique Playset with 4 Pets", new BigDecimal("33.77"), "USD",
                "The Barbie pet boutique playset is an animal lover's dream with 4 pets, a grooming station, checkout counter and over " +
                        "20 themed play pieces!", doll, barbie));
        productDataStore.add(new Product("Barbie Closet Playset with 35+ Accessories", new BigDecimal("80.99"), "USD",
                "The Barbie Dream Closet inspires kids to create endless looks and unlimited story possibilities! This modern pink closet " +
                        "opens 2+ feet wide, has 15+ storage spaces including a pop-up hanging area", doll, barbie));
        productDataStore.add(new Product("LEGO DUPLO Town Space Shuttle Mission", new BigDecimal("24.49"), "USD",
                "Treat a toddler to the LEGO DUPLO Town Space Shuttle Mission (10944) and watch their imaginations and developmental skills " +
                        "skyrocket. This toddler-friendly space toy has got it all.", lego, duplo));
        productDataStore.add(new Product("LEGO DUPLO Marvel Spider-Man & Friends", new BigDecimal("25.29"), "USD",
                "Super Heroes aged 2 and up will love to explore hands-on activities and creative role play with LEGO DUPLO Marvel Spider-Man & " +
                        "Friends: Funfair Adventure. LEGO DUPLO sets are expertly designed with fun.", lego, duplo));
        productDataStore.add(new Product("LEGO DUPLO Wild Animals of The Ocean Set", new BigDecimal("25.29"), "USD",
                "Take animal-loving toddlers on an imaginative journey to the bottom of the sea with LEGO DUPLO Wild Animals of the Ocean (10972) " +
                        "for ages 2+. LEGO DUPLO sets are expertly designed with fun.", lego, duplo));
        productDataStore.add(new Product("LEGO DUPLO Princess TM Belle's Ballroom", new BigDecimal("15.00"), "USD",
                "Iconic dancing scene – Toddlers recreate magical movie moments from Disney’s Beauty and the Beast with LEGO DUPLO Disney Belle's " +
                        "Ballroom. LEGO DUPLO sets are expertly designed with fun.", lego, duplo));
    }
}
