package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CartDaoMem cartDao = CartDaoMem.getInstance();
        CartService cartService = new CartService(cartDao);


        String delProductID = req.getParameter("delProductID");

        if(delProductID != null) {
            for(Product product : cartDao.getAll().keySet()) {
                if (product.getId() ==  Integer.parseInt(delProductID)) {
                    cartDao.removeOne(product);
                    resp.sendRedirect("/cart");
                    break;
                }
            }
        }

        String addProductID = req.getParameter("addProductID");

        if(addProductID != null) {
            for(Product product : cartDao.getAll().keySet()) {
                if (product.getId() ==  Integer.parseInt(addProductID)) {
                    cartDao.add(product);
                    resp.sendRedirect("/cart");
                    break;
                }
            }
        }

        String delAllProductIDProductID = req.getParameter("delAllProductID");

        if(delAllProductIDProductID != null) {
            for(Product product : cartDao.getAll().keySet()) {
                if (product.getId() ==  Integer.parseInt(delAllProductIDProductID)) {
                    cartDao.delAllProductID(product);
                    resp.sendRedirect("/cart");
                    break;
                }
            }
        }



        String promoCode = req.getParameter("promoCode");
        if(promoCode != null) {
            cartDao.setDiscount(Integer.parseInt(promoCode));
            resp.sendRedirect("/cart");
        }




//        String cartQuantityProduct = req.getParameter("getCartQuantityProduct");
//
//        if(cartQuantityProduct != null) {
//            String[] parts = cartQuantityProduct.split("/");
//            int quantity = Integer.parseInt(parts[0]);
//            String idProduct = parts[1];
//            for(Product product : cartDao.getAll().keySet()) {
//                if (product.getId() ==  Integer.parseInt(idProduct)) {
//                    cartDao.addQuantity(product, quantity);
//                    resp.sendRedirect("/cart");
//                    break;
//                }
//            }
//        }




        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("cartProduct", cartService.getCartProducts());

        engine.process("cart/cart.html", context, resp.getWriter());


    }
}
