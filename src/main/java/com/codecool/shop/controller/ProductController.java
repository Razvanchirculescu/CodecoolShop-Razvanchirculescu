package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.database.DatabaseManager;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.user.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DatabaseManager dbManager = DatabaseManager.getInstance(new User("Guest"));

        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        com.codecool.shop.dao.ProductDao productDataStore = dbManager.getProductDao();
        com.codecool.shop.dao.ProductCategoryDao productCategoryDataStore = dbManager.getProductCategoryDao();
        com.codecool.shop.dao.SupplierDao supplierDataStore = dbManager.getSupplierDao();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);

//        for (Product product: productService.getAll()) {
//            System.out.println(product);
//            productDataStore.add(product);
//        }
//        System.out.println(productDataStore);

        com.codecool.shop.dao.CartDao cartDataStore = dbManager.getCartDao();

        String categoryParam = req.getParameter("category");
        String supplierParam = req.getParameter("supplier");
        String productParam = req.getParameter("product");

        if(productParam != null) {
            System.out.println(productParam);
            cartDataStore.add(productDataStore.find(Integer.parseInt(productParam)));
            System.out.println(cartDataStore);
        }
        List<Product> productsToShow = new ArrayList<>();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (categoryParam == null && supplierParam == null) {
            productsToShow = productService.getAll();
        } else if (supplierParam == null) {
            productsToShow = productService.getProductsForCategory(Integer.parseInt(categoryParam));
        } else if (categoryParam == null) {
            productsToShow = productService.getProductsForSupplier(Integer.parseInt(supplierParam));
        }

        context.setVariable("allProducts", productsToShow);
        context.setVariable("categories", dbManager.getAllCategories());
        context.setVariable("suppliers", dbManager.getAllSuppliers());
        engine.process("product/index.html", context, resp.getWriter());
    }

}
