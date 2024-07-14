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
import dal.*;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.*;
import java.util.List;

/**
 *
 * @author MTDT
 */
public class AddProductForCategory extends HttpServlet {

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
            out.println("<title>Servlet AddProductForCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductForCategory at " + request.getContextPath() + "</h1>");
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
        CategoryDAO CategoryDao = new CategoryDAO();
        ProductDAO ProductDao = new ProductDAO();

        String CategoryIDGet = request.getParameter("CategoryID");
        session.setAttribute("CategoryID", CategoryIDGet);
        if (CategoryIDGet != null) {
            List<Category> CategoryList = CategoryDao.getAll();
            List<Product> listAllProduct = ProductDao.getAll();
            List<Product> listAllProductHaveCategoryID = ProductDao.getProductByCategoryID(CategoryIDGet);
            List<Product> listAllProductNotHaveCategoryID =  ProductDao.getProductnNotHaveCategoryID(CategoryIDGet);           
            request.setAttribute("CategoryList", CategoryList);
            if (listAllProductHaveCategoryID.get(0).getProductName() == null) {
                request.setAttribute("listAll", listAllProduct);
            }
            request.setAttribute("listAllProductHaveCategoryID", listAllProductHaveCategoryID);
            request.setAttribute("listAllProductNotHaveCategoryID", listAllProductNotHaveCategoryID);

            request.getRequestDispatcher("AddProductToCategory.jsp").forward(request, response);
        }
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
        ProductTypeDAO ProductTypeDao = new ProductTypeDAO();
        PrintWriter out = response.getWriter();
        String[] ProductID = request.getParameterValues("ProductAdd");
        String CaegoryID = (String) session.getAttribute("CategoryID");
        //Delete all product with categoryID       
        ProductTypeDao.DeleteByCategoryID(CaegoryID);
        for (String id : ProductID) {
            ProductTypeDao.Insert(CaegoryID, id);
        }
        session.removeAttribute("CategoryID");
        response.sendRedirect("categoryhome?CategoryID=" + CaegoryID);
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
