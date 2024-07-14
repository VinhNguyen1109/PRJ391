/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LoginDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;
import org.apache.coyote.http11.Http11InputBuffer;

/**
 *
 * @author MTDT
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        session.setAttribute("TypeOfUser", null);
        session.setAttribute("UserName", null);
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String UserName = request.getParameter("username");
        String PassWord = request.getParameter("password");
        String Remember = request.getParameter("remember");

        Cookie CookieUserName = new Cookie("CookieUserName", UserName);
        Cookie CookiePassword = new Cookie("CookiePassword", PassWord);
        Cookie CookieRemember = new Cookie("CookieRemember", Remember);

        if (Remember != null) {
            CookieUserName.setMaxAge(60 * 60);
            CookiePassword.setMaxAge(60 * 60);
            CookieRemember.setMaxAge(60 * 60);
        } else {
            CookieUserName.setMaxAge(0);
            CookiePassword.setMaxAge(0);
            CookieRemember.setMaxAge(0);
        }
        response.addCookie(CookieUserName);
        response.addCookie(CookiePassword);
        response.addCookie(CookieRemember);

        LoginDAO DAO = new LoginDAO();
        Account account = DAO.check(UserName, PassWord);
        if (account != null) {
            HttpSession session = request.getSession();
            session.setAttribute("UserName", UserName);
            ProductDAO ProductDao = new ProductDAO();
            List<Product> ListAllProduct = ProductDao.getAll();
            Cookie[] Array = request.getCookies();
            String text = "";
            if (Array != null) {

                for (Cookie cookie : Array) {
                    if (cookie.getName().equals("cart")) {
                        text += cookie.getValue();
                    }
                }
            }
            int n = 0;
            if (text.isEmpty()) {
                n = 0;
            } else {
                String CustomerID = (String) session.getAttribute("UserName");
                Cart cart = new Cart(text, ListAllProduct, CustomerID);
                List<Item> ListItem = cart.getItems();
                n = ListItem.size();
            }
            session.setAttribute("size", n);

            if (account.getType() == 2) {
                session.setAttribute("TypeOfUser", 2);
            } else {
                session.setAttribute("admin", "admin");
                session.setAttribute("TypeOfUser", 1);
            }
            response.sendRedirect("home");
        } else {
            request.setAttribute("ErrLogin", "Username of Password invalid. Input again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
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
