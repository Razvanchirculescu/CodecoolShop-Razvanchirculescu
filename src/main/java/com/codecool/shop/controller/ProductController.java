package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.database.DatabaseManager;
import com.codecool.shop.dao.implementation.memory.CartDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
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

        DatabaseManager dbManager = new DatabaseManager();

        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        com.codecool.shop.dao.ProductDao productDataStore = ProductDaoMem.getInstance();
        com.codecool.shop.dao.ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        com.codecool.shop.dao.SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);
        com.codecool.shop.dao.CartDao cartDataStore = CartDaoMem.getInstance();
        String categoryParam = req.getParameter("category");
        String supplierParam = req.getParameter("supplier");
        String productParam = req.getParameter("product");

        if (productParam != null) {
            System.out.println(productParam);
            cartDataStore.add(productDataStore.find(Integer.parseInt(productParam)));
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

        context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("allProducts", productsToShow);
        context.setVariable("categories", productService.getAllCategories());
        context.setVariable("suppliers", productService.getAllSuppliers());
        engine.process("product/index.html", context, resp.getWriter());
    }

}
