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
import model.Account;
import model.Customer;

/**
 *
 * @author MTDT
 */
public class LoginDAO extends DBContext {

    public Account check(String username, String password) {
        String sql = "SELECT * FROM Account WHERE UserName = ? AND PassWord = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getInt("type"),
                        rs.getString("UserName"),
                        rs.getString("PassWord"));
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Account";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getInt("type"),
                        rs.getString("UserName"),
                        rs.getString("PassWord"));
                list.add(account);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void insert(Account account) {
        String sql = "INSERT INTO Account VALUES(?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, account.getType());
            st.setString(2, account.getUserName());
            st.setString(3, account.getPassWord());

            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Customer> getAllAdmin() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT C.CustomerID, C.LastName, C.FirstName, C.Gender, C.DOB, C.PhoneNumber, C.Country, C.Province, C.District, C.Wards, C.Address\n"
                + "FROM Customers C FULL JOIN Account A ON C.CustomerID = A.UserName WHERE A.Type = 1";
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

    public Account GetById(String username) {
        String sql = "SELECT * FROM Account WHERE UserName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getInt("type"),
                        rs.getString("UserName"),
                        rs.getString("PassWord"));
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void Update(Account account) {
        String sql = "UPDATE Account SET UserName = ?, PassWord = ?, Type = ? WHERE UserName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, account.getUserName());
            st.setString(2, account.getPassWord());
            st.setInt(3, account.getType());
            st.setString(4, account.getUserName());
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void Delete(String UserName) {
        String sql = "DELETE FROM Account WHERE UserName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, UserName);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        LoginDAO dao = new LoginDAO();
        Account account = new Account(1, "liuliu123", "123");
        dao.Update(account);
    }
}
