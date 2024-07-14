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
import java.util.List;
import model.*;

/**
 *
 * @author MTDT
 */
public class AdminManager extends HttpServlet {

    private String ThisPageMove;
    private String CategoryIDToMove;

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
            out.println("<title>Servlet AdminManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManager at " + request.getContextPath() + "</h1>");
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
        ProductTypeDAO ProductTypeDao = new ProductTypeDAO();
        ProductDAO ProductDao = new ProductDAO();
        CategoryDAO CategoryDao = new CategoryDAO();
        List<Category> ListCategories = CategoryDao.getAll();
        request.setAttribute("ListCategories", ListCategories);

        //EditDoget(request, response, ProductDao);
        //getNewCategory(request, response, session);
        //EditCategoryDoget(request, response, CategoryDao, session);
        String ProductIDEdit = request.getParameter("ProductIDEdit");
        if (ProductIDEdit != null) {
            Product ProductEdit = ProductDao.getProductID(ProductIDEdit);

            session.setAttribute("ProductEdit", ProductEdit);
            NextPageDoGet(request, response, ProductDao);
            request.getRequestDispatcher("Admin.jsp").forward(request, response);
        } else {
            session.setAttribute("ProductEdit", null);
            String CategoryIDEdit = request.getParameter("CategoryIDEdit");
            if (CategoryIDEdit != null) {
                Category CategoryEdit = CategoryDao.getCategoryByID(CategoryIDEdit);
                session.setAttribute("CategoryEdit", CategoryEdit);
                request.getRequestDispatcher("Admin.jsp").forward(request, response);
            } else {
                session.setAttribute("CategoryEdit", null);
                String newCategory = request.getParameter("newCategory");
                if (newCategory != null) {
                    session.setAttribute("newCate", "newCate");
                    request.getRequestDispatcher("Admin.jsp").forward(request, response);
                } else {
                     session.setAttribute("newCate", null);
                    String idDeleteCategory = request.getParameter("idDeleteCategory");
                    if (idDeleteCategory != null) {
                        getDeleteCategory(request, response, CategoryDao, session, idDeleteCategory, ProductTypeDao);

                    } else {
                        String idDelete = request.getParameter("idDelete");
                        if (idDelete != null) {
                            ProductTypeDao.Delete(idDelete);
                            ProductDao.Delete(idDelete);
                            NextPage(response);
                        } else {
                            request.getRequestDispatcher("Admin.jsp").forward(request, response);
                        }
                    }
                }
            }
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
        CategoryDAO CategoryDao = new CategoryDAO();
        ProductDAO ProductDao = new ProductDAO();
        ProductTypeDAO ProductTypeDao = new ProductTypeDAO();
        List<Category> ListCategories = CategoryDao.getAll();
        request.setAttribute("ListCategories", ListCategories);
        DoForCategory(request, response, CategoryDao, ListCategories, session);
        DoForProduct(request, response, CategoryDao, ProductDao, ProductTypeDao, session);
    }

    private void DoForCategory(HttpServletRequest request,
            HttpServletResponse response,
            CategoryDAO CategoryDao,
            List<Category> ListCategories,
            HttpSession session) throws ServletException, IOException {
        String CategoryID = request.getParameter("CategoryID");
        String CategoryName = request.getParameter("CategoryName");
        String Description = request.getParameter("Description");
        String CategoryChange = request.getParameter("categorychange");
        if (CategoryID != null && CategoryName != null && Description != null) {
            if (CategoryID.trim().isBlank() || CategoryName.trim().isBlank() || Description.trim().isBlank()) {
                request.setAttribute("Err", "You input only blank!");
                request.getRequestDispatcher("Admin.jsp").forward(request, response);
            } else {
                Category category = new Category(CategoryID, CategoryName, Description);
                if (CategoryChange.equals("Edit")) {
                    EditCategoryDoPost(CategoryDao, session, category, response);
                } else {
                    DeleteCategoryDoPost(ListCategories, CategoryID, category, CategoryDao, session, request, response);
                }
                response.sendRedirect("home");
            }
        }

    }

    private void DoForProduct(HttpServletRequest request,
            HttpServletResponse response,
            CategoryDAO CategoryDao,
            ProductDAO ProductDao,
            ProductTypeDAO ProductTypeDao,
            HttpSession session) throws ServletException, IOException {
        String ProductID = request.getParameter("ProductID");
        String ProductName = request.getParameter("ProductName");
        String QuantityRemaining_raw = request.getParameter("QuantityRemaining");
        String NOReorder_raw = request.getParameter("NOReorder");
        String QuantityOrdered_raw = request.getParameter("QuantityOrdered");
        String Description = request.getParameter("Description");
        String Image = request.getParameter("Image");
        String Price_raw = request.getParameter("Price");
        String TypeOFChange = request.getParameter("TypeOfChange");

        if (ProductName != null && QuantityOrdered_raw != null && NOReorder_raw != null && QuantityOrdered_raw != null && Image != null && Price_raw != null) {
            if (ProductName.trim().isBlank()
                    || QuantityRemaining_raw.trim().isBlank()
                    || NOReorder_raw.trim().isBlank()
                    || QuantityOrdered_raw.trim().isBlank()
                    || Description.trim().isBlank()
                    || Image.trim().isBlank()
                    || Price_raw.trim().isBlank()) {
                request.setAttribute("Err", "You input only blank!");
                request.getRequestDispatcher("Admin.jsp").forward(request, response);
            } else {
                try {
                    int QuantityRemaining = Integer.parseInt(QuantityRemaining_raw);
                    int NOReorder = Integer.parseInt(NOReorder_raw);
                    int QuantityOrdered = Integer.parseInt(QuantityOrdered_raw);
                    float Price = Float.parseFloat(Price_raw);
                    Product product = new Product(ProductID, Image.trim(), QuantityRemaining, NOReorder, QuantityOrdered, ProductName.trim(), Description.trim(), Price);

                    String[] CategoryType = request.getParameterValues("CategoryType");
                    if (CategoryType == null) {
                        request.setAttribute("Err", "You not choose the type of Product");
                        request.getRequestDispatcher("Admin.jsp").forward(request, response);
                    } else {
                        if (TypeOFChange.equals("Edit")) {
                            EditDoPost(CategoryDao, ProductDao, ProductTypeDao, product, session, ProductID, CategoryType);
                        } else {
                            InsertDoPost(request, response, CategoryDao, ProductDao, ProductTypeDao, product, ProductID, CategoryType);
                        }
                        NextPage(response);
                    }

                } catch (NumberFormatException e) {
                    request.setAttribute("Err", "You need input true type with each attribute");
                    request.getRequestDispatcher("Admin.jsp").forward(request, response);
                }

            }
        }

    }

    private void EditDoPost(CategoryDAO CategoryDao, ProductDAO ProductDao, ProductTypeDAO ProductTypeDao, Product product, HttpSession session, String ProductID, String[] CategoryType) {
        ProductDao.Update(product);
        session.removeAttribute("ProductEdit");
        ProductTypeDao.Delete(ProductID);
        for (String CategoryType1 : CategoryType) {
            Category category = CategoryDao.getCategoryBy(CategoryType1);
            ProductTypeDao.Insert(category.getCategoryID(), ProductID);
        }
    }

    private void InsertDoPost(HttpServletRequest request,
            HttpServletResponse response,
            CategoryDAO CategoryDao,
            ProductDAO ProductDao,
            ProductTypeDAO ProductTypeDao,
            Product product,
            String ProductID,
            String[] CategoryType) throws ServletException, IOException {
        List<Product> list = ProductDao.getAll();
        for (Product product1 : list) {
            if (product1.getProductID().equals(ProductID.trim())) {
                request.setAttribute("Err", "This ProductID is Exit!");
                request.getRequestDispatcher("Admin.jsp").forward(request, response);
            }
        }
        ProductDao.Insert(product);
        for (String CategoryType1 : CategoryType) {
            Category category = CategoryDao.getCategoryBy(CategoryType1);
            ProductTypeDao.Insert(category.getCategoryID(), ProductID);
        }
    }

    /**
     * This function to get id of product want to edit and put into Admin.jsp to
     * show
     *
     * @param request
     * @param response
     * @param ProductDao
     * @throws ServletException
     * @throws IOException
     */
    private void EditDoget(HttpServletRequest request, HttpServletResponse response, ProductDAO ProductDao) throws ServletException, IOException {
        String ProductIDEdit = request.getParameter("ProductIDEdit");
        if (ProductIDEdit != null) {
            Product ProductEdit = ProductDao.getProductID(ProductIDEdit);
            HttpSession session = request.getSession();
            session.setAttribute("ProductEdit", ProductEdit);
            //request.getRequestDispatcher("Admin.jsp").forward(request, response);
        }
    }

    /**
     * This function to choose the next page will move to and put value to
     * global variable CategoryID and ThisPage
     *
     * @param request
     * @param response
     * @param ProductDao
     * @throws ServletException
     * @throws IOException
     */
    private void NextPageDoGet(HttpServletRequest request, HttpServletResponse response, ProductDAO ProductDao) throws ServletException, IOException {
        String CategoryID = request.getParameter("categoryid");
        String ThisPage = request.getParameter("thispage");
        if (CategoryID != null && ThisPage != null) {
            CategoryIDToMove = CategoryID;
            ThisPageMove = ThisPage;
        } else {
            CategoryIDToMove = null;
            ThisPageMove = null;
        }
    }

    /**
     * This function to choose the next page will move to
     *
     * @param response
     * @throws IOException
     */
    private void NextPage(HttpServletResponse response) throws IOException {
        if (ThisPageMove != null && CategoryIDToMove != null) {
            if (ThisPageMove.equals("category")) {
                response.sendRedirect("categoryhome?CategoryID=" + CategoryIDToMove);
            }
        } else {
            response.sendRedirect("home");
        }
    }

    private void getNewCategory(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String newCategory = request.getParameter("newCategory");
        if (newCategory != null) {
            session.setAttribute("newCate", "newCate");
            request.getRequestDispatcher("Admin.jsp").forward(request, response);
        }
    }

    private void getDeleteCategory(HttpServletRequest request, HttpServletResponse response, CategoryDAO CategoryDao, HttpSession session, String idDeleteCategory, ProductTypeDAO ProductTypeDao) throws ServletException, IOException {
        if (idDeleteCategory != null) {
            ProductTypeDao.DeleteByCategoryID(idDeleteCategory);
            CategoryDao.Delete(idDeleteCategory);
            response.sendRedirect("home");
        }
    }

    private void EditCategoryDoget(HttpServletRequest request, HttpServletResponse response, CategoryDAO CategoryDao, HttpSession session) throws ServletException, IOException {
        String CategoryIDEdit = request.getParameter("CategoryIDEdit");
        if (CategoryIDEdit != null) {
            Category CategoryEdit = CategoryDao.getCategoryByID(CategoryIDEdit);
            session.setAttribute("CategoryEdit", CategoryEdit);
            request.getRequestDispatcher("Admin.jsp").forward(request, response);
        }
    }

    private void EditCategoryDoPost(CategoryDAO CategoryDao, HttpSession session, Category category, HttpServletResponse response) throws IOException {
        CategoryDao.Update(category);
        session.removeAttribute("CategoryEdit");
    }

    private void DeleteCategoryDoPost(List<Category> ListCategories, String CategoryID, Category category, CategoryDAO CategoryDao, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for (Category ListCategory : ListCategories) {
            if (ListCategory.getCategoryID().equals(CategoryID)) {
                request.setAttribute("Err", "This Category Id is exits!");
                request.getRequestDispatcher("Admin.jsp").forward(request, response);
            }
        }
        CategoryDao.Insert(category);
        session.removeAttribute("newCate");
    }
}
