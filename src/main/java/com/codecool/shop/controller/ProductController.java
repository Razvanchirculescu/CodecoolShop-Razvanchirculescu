package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.CartDaoJdbc;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        com.codecool.shop.dao.ProductDao productDataStore = ProductDaoJdbc.getInstance();
        com.codecool.shop.dao.ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
        com.codecool.shop.dao.SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);
        com.codecool.shop.dao.CartDao cartDataStore = CartDaoJdbc.getInstance();
        String categoryParam = req.getParameter("category");
        String supplierParam = req.getParameter("supplier");
        String productParam = req.getParameter("product");

        if(productParam != null) {
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
