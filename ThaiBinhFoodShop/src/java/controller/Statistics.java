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
import model.*;
import dal.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author MTDT
 */
public class Statistics extends HttpServlet {

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
            out.println("<title>Servlet Statistics</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Statistics at " + request.getContextPath() + "</h1>");
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
        ProductDAO ProductDao = new ProductDAO();
        List<Product> ListAllProduct = ProductDao.getAll();

        int TotalMoneyNumber = 0;
        for (Product product : ListAllProduct) {
            TotalMoneyNumber += product.getPrice() * product.getQuantityOrdered();
        }

        String stt = request.getParameter("stt");

        if (stt != null) {
            if (stt.equals("1")) {
                OrderDAO OrderDao = new OrderDAO();
                int t1 = OrderDao.getOrderinMonth(1);
                int t2 = OrderDao.getOrderinMonth(2);
                int t3 = OrderDao.getOrderinMonth(3);
                int t4 = OrderDao.getOrderinMonth(4);
                int t5 = OrderDao.getOrderinMonth(5);
                int t6 = OrderDao.getOrderinMonth(6);
                int t7 = OrderDao.getOrderinMonth(7);
                int t8 = OrderDao.getOrderinMonth(8);
                int t9 = OrderDao.getOrderinMonth(9);
                int t10 = OrderDao.getOrderinMonth(10);
                int t11 = OrderDao.getOrderinMonth(11);
                int t12 = OrderDao.getOrderinMonth(12);

                request.setAttribute("t1", t1);
                request.setAttribute("t2", t2);
                request.setAttribute("t3", t3);
                request.setAttribute("t4", t4);
                request.setAttribute("t5", t5);
                request.setAttribute("t6", t6);
                request.setAttribute("t7", t7);
                request.setAttribute("t8", t8);
                request.setAttribute("t9", t9);
                request.setAttribute("t10", t10);
                request.setAttribute("t11", t11);
                request.setAttribute("t12", t12);
            }

            if (stt.equals("2")) {
                CustomerDAO customerDao = new CustomerDAO();
                int young = customerDao.getQuantityCustomerByAge(0, 15);

                int student = customerDao.getQuantityCustomerByAge(16, 35);
                int middleAge = customerDao.getQuantityCustomerByAge(36, 59);
                int old = customerDao.getQuantityCustomerByAge(60, 100);

                request.setAttribute("young", young);

                request.setAttribute("student", student);
                request.setAttribute("middleAge", middleAge);
                request.setAttribute("old", old);

            }

            if (stt.equals("3")) {
                HashMap<String, Integer> map = new HashMap();
                CategoryDAO CategoryDao = new CategoryDAO();
                List<Category> ListCategory = CategoryDao.getAll();
                
                for (Category category : ListCategory) {
                    int num = CategoryDao.getQuantityEachCategory(category.getCategoryID());
                    map.put(category.getCategoryName(), num);
                }
                request.setAttribute("map", map);

            }

            if (stt.equals("4")) {
                request.setAttribute("ListAllProduct", ListAllProduct);
            }
            if (stt.equals("5")) {
                LocationsDAO locationDao = new LocationsDAO();
                OrderDAO Orderdao = new OrderDAO();
                HashMap<String, Integer> mapLocation = new HashMap();
                List<String> province = locationDao.getProvince();
                for (String string : province) {
                    int number = Orderdao.CountByProvince(string);
                    mapLocation.put(string, number);
                }
                request.setAttribute("mapLocation", mapLocation);
            }
        }

        request.setAttribute("Total", TotalMoneyNumber);
        request.getRequestDispatcher("statistic.jsp").forward(request, response);
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
        processRequest(request, response);
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
