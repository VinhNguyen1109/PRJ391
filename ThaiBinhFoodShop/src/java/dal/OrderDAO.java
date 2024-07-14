/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Order;

/**
 *
 * @author MTDT
 */
public class OrderDAO extends DBContext {

    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getString("OrderID"),
                        rs.getString("CustomerID"),
                        rs.getString("OrderDate"),
                        rs.getString("ShipCountry"),
                        rs.getString("ShipProvince"),
                        rs.getString("ShipDistrict"),
                        rs.getString("ShipWards"),
                        rs.getString("ShipAddress"));

                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public void Insert(Order order) {
        String sql = "INSERT INTO Orders VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, order.getOrderID());
            st.setString(2, order.getCustomerID());
            st.setString(3, order.getOrderDate());
            st.setString(4, order.getShipCountry());
            st.setString(5, order.getShipProvince());
            st.setString(6, order.getShipDistrict());
            st.setString(7, order.getShipWards());
            st.setString(8, order.getShipAddress());
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void Delete(String OrderID) {
        String sql = "DELETE FROM Orders WHERE OrderID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, OrderID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Order getOrderByID(String id) {
        String sql = "SELECT * FROM Orders WHERE OrderID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Order order = new Order(rs.getString("OrderID"),
                        rs.getString("CustomerID"),
                        rs.getString("OrderDate"),
                        rs.getString("ShipCountry"),
                        rs.getString("ShipProvince"),
                        rs.getString("ShipDistrict"),
                        rs.getString("ShipWards"),
                        rs.getString("ShipAddress"));

                return order;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Order> getOrderByCustomerID(String CustomerID) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE CustomerID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CustomerID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getString("OrderID"),
                        rs.getString("CustomerID"),
                        rs.getString("OrderDate"),
                        rs.getString("ShipCountry"),
                        rs.getString("ShipProvince"),
                        rs.getString("ShipDistrict"),
                        rs.getString("ShipWards"),
                        rs.getString("ShipAddress"));

                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int getOrderinMonth(int month) {
        String sql = "SELECT SUM(OD.Quantity) num FROM Orders O FULL JOIN OrderDetails OD ON O.OrderID = OD.OrderID WHERE MONTH(O.OrderDate) = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, month);
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

    public int CountByProvince(String province) {
        String sql = "SELECT COUNT(*) num FROM Orders WHERE ShipProvince = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, province);
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

//        LocalDateTime currentTime = LocalDateTime.now();
//        int minute = currentTime.getMinute();
//        int second = currentTime.getSecond();
//        int hour = currentTime.getHour();
//        int day = currentTime.getDayOfYear();
//        int year = currentTime.getYear();
//        System.out.println("Minute: " + minute);
//        System.out.println("Second: " + second);
//        System.out.println("hour: " + hour);
//        System.out.println("day: " + day);
//        System.out.println("year: " + year);
//        OrderDAO dao = new OrderDAO();
//        List<Order> list = dao.getAll();
//        if(list == null){
//            System.out.println("null");
//        }else{
//            System.out.println("klasdnc");
//        }
        OrderDAO dao = new OrderDAO();
       
    }

}
