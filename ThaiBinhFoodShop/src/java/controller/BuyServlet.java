/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author MTDT
 */
public class BuyServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDAO ProductDao = new ProductDAO();
        List<Product> ListAllProduct = ProductDao.getAll();
        Cookie[] Array = request.getCookies();
        String text = "";

        if (Array != null) {
            for (Cookie cookie : Array) {
                if (cookie.getName().equals("cart")) {
                    text += cookie.getValue();
                    cookie.setMaxAge(0);
                    //response.addCookie(cookie);
                }
            }
        }
        String ProductID = request.getParameter("ProductID");
        String CustomerID = (String) session.getAttribute("UserName");
        if (text.isEmpty()) {
            text = CustomerID + ":" + ProductID + ":" + 1;
        } else {
            text += "/" + CustomerID + ":" + ProductID + ":" + 1;
        }

        Cookie c = new Cookie("cart", text);
        c.setMaxAge(60 * 60 * 24 * 2);
        response.addCookie(c);

        Cart cart = new Cart(text, ListAllProduct, CustomerID);
        List<Item> ListItem = cart.getItems();
        int n;
        if (ListItem != null) {
            n = ListItem.size();
        } else {
            n = 0;
        }

        session.setAttribute("size", n);
        response.sendRedirect("home");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDAO ProductDao = new ProductDAO();
        List<Product> ListAllProduct = ProductDao.getAll();
        Cookie[] Array = request.getCookies();
        String text = "";
        String CustomerID = (String) session.getAttribute("UserName");
        String ProductID = request.getParameter("ProductID");
        if (Array != null) {
            for (Cookie cookie : Array) {
                if (cookie.getName().equals("cart")) {
                    text += cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        if(request.getParameter("quantity") != null){
           
            if (text.isEmpty()) {
                text = CustomerID + ":" + ProductID + ":" + 0;
            } else {
                text += "/" + CustomerID + ":" + ProductID + ":" + 0;
            }
        }


        Cart cart = new Cart(text, ListAllProduct, CustomerID);
        String quantity_raw = request.getParameter("quantity");
        
        int number = 0;
        try {
            number = Integer.parseInt(quantity_raw);
            Product product = ProductDao.getProductID(ProductID);
            int numberStore = product.getQuantityRemaining();

            if (cart.getQuantityByID(ProductID) >= numberStore) {
                number = 0;
            } else {
                
                float price = product.getPrice();
                Item item = new Item(product, number, price);
                cart.addItem (item);
            }
        } catch (NumberFormatException e) {

        }
//        String text1 = "";
//        String[] TemptItem = text.split("/");
//        PrintWriter out = response.getWriter();
//        if (!TemptItem[0].contains(CustomerID)) {
//            text1 += TemptItem[0];
//        }
//        for (int i = 1; i < TemptItem.length;    i++) {
//            if (!TemptItem[i].contains(CustomerID)) {
//                text1 += "/" + TemptItem[i];
//            }
//        }

        List<Item> items = cart.getItems();
        text = "";
        if (items.size() > 0) {
            if (text.isEmpty()) {
                text = CustomerID + ":" + items.get(0).getProduct().getProductID() + ":" + (items.get(0).getQuantity());
                for (int i = 1; i < items.size(); i++) {
                    text += "/" + CustomerID + ":" + items.get(i).getProduct().getProductID() + ":" + (items.get(i).getQuantity());
                }
            } else {
                for (int i = 0; i < items.size(); i++) {
                    text += "/" + CustomerID + ":" + items.get(i).getProduct().getProductID() + ":" + (items.get(i).getQuantity());
                }
            }

        }
        Cookie c = new Cookie("cart", text);
        c.setMaxAge(2 * 60 * 60 * 24);
        response.addCookie(c);

        response.sendRedirect("show");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
