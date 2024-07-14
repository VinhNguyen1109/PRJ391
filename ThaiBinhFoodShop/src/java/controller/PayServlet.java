/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import model.Item;
import model.*;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.CustomerDAO;
import dal.ProductDAO;
import jakarta.servlet.http.Cookie;
import java.time.LocalDate;

/**
 *
 * @author MTDT
 */
public class PayServlet extends HttpServlet {

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
            out.println("<title>Servlet PayServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PayServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        PrintWriter out = response.getWriter();
        List<Item> list = (List<Item>) session.getAttribute("ListItem");

        LocalDateTime currentTime = LocalDateTime.now();
        int minute = currentTime.getMinute();
        int second = currentTime.getSecond();
        int hour = currentTime.getHour();
        int day = currentTime.getDayOfYear();
        int year = currentTime.getYear();
        String OrderKey = second + "_" + minute + "_" + hour + "_" + day + "_" + year + "_";

        OrderDAO OrderDao = new OrderDAO();
        OrderDetailDAO OrderDetailDao = new OrderDetailDAO();
        CustomerDAO CustomerDao = new CustomerDAO();
        String CustomerId = (String) session.getAttribute("UserName");
        if (CustomerId != null) {
            OrderKey += CustomerId;
            LocalDate curentDate = LocalDate.now();
            String date = curentDate.toString();
            Customer customer = CustomerDao.getCustomerByID(CustomerId);
            Order order = new Order(OrderKey, CustomerId, date, customer.getCountry(), customer.getProvince(), customer.getDistrict(), customer.getWards(), customer.getAddress());
            OrderDao.Insert(order);
            session.setAttribute("orderId", OrderKey);
            ProductDAO productDao = new ProductDAO();

            for (Item item : list) {
                Product product = item.getProduct();
                int QuantityRemaining = product.getQuantityRemaining();
                int QuantityOrderd = product.getQuantityOrdered();
                int newQuantityRemaining = QuantityRemaining - item.getQuantity();
                int NewQuantityOrderd = QuantityOrderd + item.getQuantity();
                Product UpdateProduct = new Product(product.getProductID(), product.getImage(), newQuantityRemaining, product.getNOReorder(), NewQuantityOrderd, product.getProductName(), product.getDescription(), product.getPrice());
                productDao.Update(UpdateProduct);
                OrderDetail orderDetail = new OrderDetail(OrderKey, item.getProduct().getProductID(), item.getQuantity());
                OrderDetailDao.Insert(orderDetail);
            }
        }

        ProductDAO ProductDao = new ProductDAO();
        List<Product> ListAllProduct = ProductDao.getAll();
        Cookie[] Array = request.getCookies();
        String text = "";

        if (Array != null) {
            for (Cookie cookie : Array) {
                if (cookie.getName().equals("cart")) {
                    text += cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        String CustomerID = (String) session.getAttribute("UserName");;
        Cart cart = new Cart(text, ListAllProduct, CustomerID);

        for (Item item : list) {
            cart.removeItem(item.getProduct().getProductID());
        }

        String text1 = "";
        String[] TemptItem = text.split("/");

        if (!TemptItem[0].contains(CustomerID)) {
            text1 += TemptItem[0];
        }
        for (int i = 1; i < TemptItem.length; i++) {
            if (!TemptItem[i].contains(CustomerID)) {
                text1 += "/" + TemptItem[i];
            }
        }

        List<Item> items = cart.getItems();
        text = "";
        if (items.size() > 0) {
            if (text1.isEmpty()) {
                text1 = CustomerID + ":" + items.get(0).getProduct().getProductID() + ":" + items.get(0).getQuantity();
                for (int i = 1; i < items.size(); i++) {
                    text1 += "/" + CustomerID + ":" + items.get(i).getProduct().getProductID() + ":" + items.get(i).getQuantity();
                }
            } else {
                for (int i = 0; i < items.size(); i++) {
                    text1 += "/" + CustomerID + ":" + items.get(i).getProduct().getProductID() + ":" + items.get(i).getQuantity();
                }
            }

        }
        Cookie c = new Cookie("cart", text1);
        c.setMaxAge(2 * 60 * 60 * 24);
        response.addCookie(c);
        session.setAttribute("size", 0);

        request.getRequestDispatcher("final.jsp").forward(request, response);
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
