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
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author MTDT
 */
public class OrderDetailDAO extends DBContext {

    public List<OrderDetail> getAll() {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetail orderdetail = new OrderDetail(rs.getString("OrderID"),
                        rs.getString("ProductID"),
                        rs.getInt("Quantity"));

                list.add(orderdetail);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public void Insert(OrderDetail orderdetail) {
        String sql = "INSERT INTO OrderDetails VALUES(?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderdetail.getOrderID());
            st.setString(2, orderdetail.getProductID());
            st.setInt(3, orderdetail.getQuantity());

            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void Delete(String OrderID) {
        String sql = "DELETE FROM OrderDetails WHERE OrderID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, OrderID);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getQuantityInOrder(String CustomerID, String OrderId, String ProductId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT  OD.Quantity FROM Orders O FULL JOIN OrderDetails OD ON O.OrderID = OD.OrderID\n"
                + "                				   FULL JOIN Products P ON P.ProductID = OD.ProductID WHERE O.CustomerID = ? AND OD.OrderID = ? AND P.ProductID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CustomerID);
            st.setString(2, OrderId);
            st.setString(3, ProductId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int quantity = rs.getInt("Quantity");
                return quantity;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void main(String[] args) {
        OrderDetailDAO dao = new OrderDetailDAO();
//           OrderDetail o = new OrderDetail("1", "1", 1);
//           dao.Insert(o);
        dao.Delete("28_31_1_63_2024_oke123");
    }
}
