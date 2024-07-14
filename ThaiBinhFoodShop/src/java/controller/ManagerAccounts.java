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
import model.*;
import java.util.List;

/**
 *
 * @author MTDT
 */
public class ManagerAccounts extends HttpServlet {

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
            out.println("<title>Servlet ManagerAccounts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerAccounts at " + request.getContextPath() + "</h1>");
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
        LoginDAO LoginDao = new LoginDAO();
        CustomerDAO customerDao = new CustomerDAO();
        
        

        String UserName = request.getParameter("idEdit");
        String UsernameDelete = request.getParameter("idDelete");
        if (UserName != null) {
            Account account = LoginDao.GetById(UserName);
            int type = account.getType();
            String username = account.getUserName();
            String PassWord = account.getPassWord();
            request.setAttribute("Account", account);
            request.setAttribute("type", type);
            request.setAttribute("UserName", UserName);
            request.setAttribute("Pass", PassWord);
        }

        if(UsernameDelete != null){
            customerDao.Delete(UsernameDelete);
            LoginDao.Delete(UsernameDelete);
        }
        List<Customer> listAccountAdmin = LoginDao.getAllAdmin();
        request.setAttribute("listAccountAdmin", listAccountAdmin);
        request.getRequestDispatcher("ManagerAdminAccounts.jsp").forward(request, response);
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
        LoginDAO loginDao = new LoginDAO();
        String UserName = request.getParameter("UseName");
        String PassWord = request.getParameter("pass");
        String Type = request.getParameter("Type");

        try {
            int TypeNum = Integer.parseInt(Type);
            Account account = new Account(TypeNum, UserName, PassWord);

            loginDao.Update(account);
        } catch (Exception e) {
        }
        List<Customer> listAccountAdmin = loginDao.getAllAdmin();
        request.setAttribute("listAccountAdmin", listAccountAdmin);
        request.getRequestDispatcher("ManagerAdminAccounts.jsp").forward(request, response);
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
