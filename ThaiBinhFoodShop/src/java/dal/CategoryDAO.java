/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Category;
import model.Product;

/**
 *
 * @author MTDT
 */
public class CategoryDAO extends DBContext {

    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM Categories";
        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getString("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Description"));
                list.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Category getCategoryBy(String CategoryName) {
        String sql = "SELECT * FROM Categories WHERE CategoryName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CategoryName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getString("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Description"));
                return category;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void Insert(Category category) {
        String sql = "INSERT INTO Categories VALUES(?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, category.getCategoryID());
            st.setString(2, category.getCategoryName());
            st.setString(3, category.getDescription());

            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void Delete(String CategoryID) {
        String sql = "DELETE FROM Categories WHERE CategoryID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CategoryID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Category getCategoryByID(String id) {
        String sql = "SELECT * FROM Categories WHERE CategoryID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category category = new Category(rs.getString("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Description"));

                return category;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void Update(Category category) {
        String sql = "UPDATE   Categories SET CategoryID = ?, CategoryName = ?, Description = ? WHERE CategoryID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, category.getCategoryID());
            st.setString(2, category.getCategoryName());
            st.setString(3, category.getDescription());
            st.setString(4, category.getCategoryID());
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getQuantityEachCategory(String CategoryID) {
        String sql = "SELECT C.CategoryID, COUNT(C.CategoryID) num FROM Categories C FULL JOIN ProductType PT ON C.CategoryID = PT.CategoryID							\n"
                + "							FULL JOIN OrderDetails OD ON OD.ProductID = PT.ProductID\n"
                + "							WHERE OD.OrderID IS NOT NULL AND C.CategoryID = ?\n"
                + "							GROUP BY C.CategoryID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CategoryID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int number = rs.getInt("num");

                return number;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
        System.out.println(dao.getQuantityEachCategory("9"));
    }
}
