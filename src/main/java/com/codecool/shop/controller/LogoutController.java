package com.codecool.shop.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


    @WebServlet("/logout")
    public class LogoutController extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/");
        }

    }
