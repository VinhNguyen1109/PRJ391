/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.lang.invoke.VarHandle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author MTDT
 */
public class CustomerDAO extends DBContext {

    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM Customers";
        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(rs.getString("CustomerID"),
                        rs.getString("LastName"),
                        rs.getString("FirstName"),
                        rs.getString("Phonenumber"),
                        rs.getString("Country"),
                        rs.getString("Province"),
                        rs.getString("District"),
                        rs.getString("Wards"),
                        rs.getString("Address"),
                        rs.getString("DOB"),
                        rs.getInt("Gender"));
                list.add(customer);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Customer> getAllAdmin(int type) {
        List<Customer> list = new ArrayList<>();
        String sql = "select C.CustomerID, C.LastName, C.FirstName, C.PhoneNumber, C.Country, C.Province, C.District, C.Wards, C.Address, C.DOB, C.Gender\n"
                + "from Customers C full join Account A on A.UserName = C.CustomerID WHERE A.Type = ?";

        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, type);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(rs.getString("CustomerID"),
                        rs.getString("LastName"),
                        rs.getString("FirstName"),
                        rs.getString("Phonenumber"),
                        rs.getString("Country"),
                        rs.getString("Province"),
                        rs.getString("District"),
                        rs.getString("Wards"),
                        rs.getString("Address"),
                        rs.getString("DOB"),
                        rs.getInt("Gender"));
                list.add(customer);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public void Insert(Customer customer) {
        String sql = "INSERT INTO Customers VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, customer.getCustomerID());
            st.setString(2, customer.getLastName());
            st.setString(3, customer.getFirstName());
            st.setInt(4, customer.getGender());
            st.setString(5, customer.getDate());
            st.setString(6, customer.getPhonenumber());
            st.setString(7, customer.getCountry());
            st.setString(8, customer.getProvince());
            st.setString(9, customer.getDistrict());
            st.setString(10, customer.getWards());
            st.setString(11, customer.getAddress());
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void Delete(String CustomerID) {
        String sql = "DELETE FROM Customers WHERE CustomerID  = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CustomerID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void Update(Customer customer) {
        String sql = "UPDATE Customers SET CustomerID = ?, LastName = ?,"
                + "FirstName = ?"
                + "Gender = ?"
                + "DOB = ?"
                + "PhoneNumber = ?"
                + "Country = ?"
                + "Province = ?"
                + "District = ?"
                + "Wards = ?"
                + "Address = ?"
                + " WHERE CustomerID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, customer.getCustomerID());
            st.setString(2, customer.getLastName());
            st.setString(3, customer.getFirstName());
            st.setInt(4, customer.getGender());
            st.setString(5, customer.getDate());
            st.setString(6, customer.getPhonenumber());
            st.setString(7, customer.getCountry());
            st.setString(1, customer.getProvince());
            st.setString(1, customer.getDistrict());
            st.setString(1, customer.getWards());
            st.setString(1, customer.getAddress());
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Customer getCustomerByID(String id) {
        String sql = "SELECT * FROM Customers WHERE CustomerID  = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer(rs.getString("CustomerID"),
                        rs.getString("LastName"),
                        rs.getString("FirstName"),
                        rs.getString("Phonenumber"),
                        rs.getString("Country"),
                        rs.getString("Province"),
                        rs.getString("District"),
                        rs.getString("Wards"),
                        rs.getString("Address"),
                        rs.getString("DOB"),
                        rs.getInt("Gender"));
                return customer;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int getQuantityCustomerByAge(int month1, int month2) {
        String sql = "SELECT COUNT(*) num FROM Customers WHERE (YEAR(GETDATE()) - YEAR(DOB)) BETWEEN ? AND ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, month1);
            st.setInt(2, month2);
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
        CustomerDAO dao = new CustomerDAO();
        int num = dao.getQuantityCustomerByAge(16, 20);
        System.out.println(num);
    }
}
