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
import dal.OrderDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.OrderDetailDAO;
import dal.ProductDAO;
import java.util.List;
import model.Item;
import model.Product;

/**
 *
 * @author MTDT
 */
public class FinalServlet extends HttpServlet {

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
            out.println("<title>Servlet FinalServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FinalServlet at " + request.getContextPath() + "</h1>");
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
        ProductDAO productDao = new ProductDAO();
        HttpSession session = request.getSession();
        String OrderId = (String) session.getAttribute("orderId");
        String CustomerId = (String) session.getAttribute("UserName");
        List<Item> ListItem =  (List<Item>) session.getAttribute("ListItem");
        PrintWriter out = response.getWriter();
        
        OrderDAO OrderDao = new OrderDAO();
        OrderDetailDAO OrderDetailDao = new OrderDetailDAO();

        if(ListItem != null){
            for (Item item : ListItem) {
                out.println(item.getQuantity());
                Product product = item.getProduct();
                out.print(product.getQuantityRemaining());
                
                Product newProduct = new Product(product.getProductID(), product.getImage(), product.getQuantityRemaining(), product.getNOReorder(), product.getQuantityOrdered(), product.getProductName(), product.getDescription(), product.getPrice());
                productDao.Update(newProduct);
            }
        }
        
        if (OrderId != null && CustomerId != null) {
            OrderDetailDao.Delete(OrderId);
            OrderDao.Delete(OrderId);

            session.removeAttribute("TotalMoney");
            session.removeAttribute("ListItem");
            session.removeAttribute("orderId");
            request.getRequestDispatcher("home").forward(request, response);
        } else {

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
