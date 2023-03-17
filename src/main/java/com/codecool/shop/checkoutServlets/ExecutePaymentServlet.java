package com.codecool.shop.checkoutServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;

import com.codecool.shop.payment.PaymentServices;

@WebServlet("PaypalTest/execute_payment")
public class ExecutePaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ExecutePaymentServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");

        try {
            PaymentServices paymentServices = new PaymentServices();
            Payment payment = paymentServices.executePayment(paymentId, payerId);

            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
            System.out.println(transaction.getAmount());
            System.out.println(transaction.getItemList());


            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);

            request.getRequestDispatcher("../WEB-INF/JSP/receipt.jsp").forward(request, response);
        } catch (PayPalRESTException ex) {
            request.setAttribute("../WEB-INF/JSP/errorMessage", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("../WEB-INF/JSP/error.jsp").forward(request, response);
        }
    }
}
