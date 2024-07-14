/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import dal.LoginDAO;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Customer;

/**
 *
 * @author MTDT
 */
public class SignUpServlet extends HttpServlet {

    private String globalProvince;
    private String globalDistrict;

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
            out.println("<title>Servlet SignUpServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpServlet at " + request.getContextPath() + "</h1>");
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

        request.getRequestDispatcher("signup.jsp").forward(request, response);

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
        String UserName_raw = request.getParameter("username");
        String PassWord_raw = request.getParameter("password");
        String FirstName_raw = request.getParameter("firstname");
        String lastName_raw = request.getParameter("lastname");
        String Gender_raw = request.getParameter("gender");
        String Country = request.getParameter("country");
        String Province = request.getParameter("province");
        String District = request.getParameter("district");
        String Wards = request.getParameter("wards");
        String Address_raw = request.getParameter("address");
        String DOB = request.getParameter("birth_date");
        String PhoneNumber_raw = request.getParameter("phonenumber");

        String UserName = UserName_raw.trim();
        String PassWord = PassWord_raw.trim();
        String FirstName = FirstName_raw.trim();
        String LastName = lastName_raw.trim();
        String PhoneNumber = PhoneNumber_raw.trim();
        String Address = Address_raw.trim();
        String err = "";
        if (UserName.isBlank() || PassWord.isBlank() || FirstName.isBlank()
                || LastName.isBlank() || PhoneNumber.isBlank() || Address.isBlank()) {
            err = "You only input blank!";
            request.setAttribute("err", "You only input blank!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }

        try {
            int gender = Integer.parseInt(Gender_raw);
            CustomerDAO customerDAO = new CustomerDAO();
            LoginDAO loginDao = new LoginDAO();
            List<Customer> listCustomer = customerDAO.getAll();
            for (Customer customer : listCustomer) {
                if (customer.getCustomerID().equalsIgnoreCase(UserName)) {
                    err = "UserName is Exit!";
                    request.setAttribute("err", "UserName is Exit!");
                    //request.getRequestDispatcher("tempt").forward(request, response);
                }
            }
            char[] charPhoneNumber = PhoneNumber.toCharArray();
            if (charPhoneNumber.length != 10) {
                err = "The phone number is not in the correct format!";
                request.setAttribute("err", "The phone number is not in the correct format!");

            } else {
                for (char c : charPhoneNumber) {
                    if (c <= 90 && c >= 65) {
                        err = "The phone number is not in the correct format!";
                        request.setAttribute("err", "The phone number is not in the correct format!");

                    }
                }
            }

            if (err == "") {
                request.getRequestDispatcher("login").forward(request, response);
                Customer customer = new Customer(UserName, LastName, FirstName, PhoneNumber, Country, Province, District, Wards, Address, DOB, gender);

                Account account;
                if (session.getAttribute("TypeOfUser") != null) {
                    int type = (int) session.getAttribute("TypeOfUser");
                    if (type == 1) {
                        account = new Account(1, UserName, PassWord);
                    } else {
                        account = new Account(2, UserName, PassWord);
                    }
                    loginDao.insert(account);
                    customerDAO.Insert(customer);
                } else {
                    account = new Account(2, UserName, PassWord);
                    loginDao.insert(account);
                    customerDAO.Insert(customer);
                }

            } else {
                doGet(request, response);
            }

        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

}
