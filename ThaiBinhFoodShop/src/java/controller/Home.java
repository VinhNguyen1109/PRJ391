/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import dal.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.Cookie;
import java.util.List;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author MTDT
 */
public class Home extends HttpServlet {

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
            out.println("<title>Servlet Home</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Home at " + request.getContextPath() + "</h1>");
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
        CategoryDAO CategoryDao = new CategoryDAO();
        CustomerDAO CustomerDao = new CustomerDAO();
        List<Product> Top3Selling = ProductDao.getTop3Selling();
        List<String> ListDesciption = ProductDao.getDescription();
        List<Product> ProductList = ProductDao.getAll();
        int page, numberPerPage = 6;
        int sizeList = ProductList.size();
        int numberOfPage = (sizeList%6 == 0?(sizeList/6):((sizeList/6)) + 1);
        String xpage = request.getParameter("page");
        if(xpage == null){
            page = 1;
        }else{
            page = Integer.parseInt(xpage);
            
        }       
        int start, end;
        start = (page -1) * numberPerPage;
        end = Math.min(page * numberPerPage, sizeList);
        List<Product> ProductListPage = ProductDao.getListByPage(ProductList, start, end);
        
        
        
        List<Category> CategoryList = CategoryDao.getAll();
        request.setAttribute("NumberOfPage", numberOfPage);
        request.setAttribute("page", page);
        session.setAttribute("ListDesciption", ListDesciption);
        session.setAttribute("top3", Top3Selling);
        request.setAttribute("ProductList", ProductListPage);
        session.setAttribute("CategoryListToShow", CategoryList);
        //request.setAttribute("CategoryList", CategoryList);

       

        String profile = request.getParameter("profile");
        if (profile != null) {
            ShowProfile(request, response, session, CustomerDao);
        } else {
            request.getRequestDispatcher("home.jsp").forward(request, response);
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
        processRequest(request, response);
    }

    private void ShowProfile(HttpServletRequest request, HttpServletResponse response, HttpSession session, CustomerDAO CustomerDao) throws ServletException, IOException {
        String UserNameProfile = (String) session.getAttribute("UserName");
        Customer customer = CustomerDao.getCustomerByID(UserNameProfile);
        String profile = request.getParameter("profile");
        if (profile != null) {
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }

    }

}
