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
import dal.ProductDAO;
import java.util.List;
import model.Product;

/**
 *
 * @author MTDT
 */
public class Search extends HttpServlet {

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
            out.println("<title>Servlet Search</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet dcmm Search at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        String CategoryID = request.getParameter("TypeSearch");
        String PriceFrom_raw = request.getParameter("PriceFrom");
        String PriceTo_raw = request.getParameter("PriceTo");

        try {
            float PriceFrom = Float.parseFloat(PriceFrom_raw);
            float PriceTo = Float.parseFloat(PriceTo_raw);
            List<Product> listByFilter = ProductDao.getByFilter(CategoryID, PriceFrom, PriceTo);

            int page, numberPerPage = 6;
            int sizeList = listByFilter.size();
            int numberOfPage = (sizeList % 6 == 0 ? (sizeList / 6) : ((sizeList / 6)) + 1);
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);

            }
            int start, end;
            start = (page - 1) * numberPerPage;
            end = Math.min(page * numberPerPage, sizeList);
            List<Product> ProductListPage = ProductDao.getListByPage(listByFilter, start, end);

            request.setAttribute("NumberOfPage", numberOfPage);
            request.setAttribute("page", page);
            request.setAttribute("TypeSearch", CategoryID);
            request.setAttribute("PriceFrom", PriceFrom);
            request.setAttribute("PriceTo", PriceTo);
            request.setAttribute("list", ProductListPage);
            request.getRequestDispatcher("search.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e);
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
        ProductDAO ProductDao = new ProductDAO();

        String search = request.getParameter("search");

        List<Product> list = ProductDao.getBySearch(search);
        
              
        
        request.setAttribute("list", list);
        request.getRequestDispatcher("search.jsp").forward(request, response);

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
