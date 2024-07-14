/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.*;

/**
 *
 * @author MTDT
 */
public class ProductTypeDAO extends DBContext {

    public  void Insert(String CategoryID, String ProductID ){
        String sql = "Insert INTO ProductType values (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CategoryID);
            st.setString(2, ProductID);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void Delete( String ProductID) {
        String sql = "DELETE FROM ProductType WHERE ProductID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ProductID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
   
    public void DeleteByCategoryID( String CategoryID) {
        String sql = "DELETE FROM ProductType WHERE CategoryID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CategoryID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        ProductTypeDAO dao = new ProductTypeDAO();
        //dao.DeleteByCategoryID("9");
        dao.Insert("9", "1");
        dao.Insert("9", "2");
        dao.Insert("9", "3");
        dao.Insert("9", "4");
        dao.Insert("9", "5");
    }
}
