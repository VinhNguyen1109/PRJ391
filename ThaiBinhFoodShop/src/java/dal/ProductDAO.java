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
import model.Product;

/**
 *
 * @author MTDT
 */
public class ProductDAO extends DBContext {

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products ORDER BY price";
        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("ProductID"),
                        rs.getString("Image"),
                        rs.getInt("QuantityRemaining"),
                        rs.getInt("NOReorder"),
                        rs.getInt("QuantityOrdered"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getFloat("price"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public void Insert(Product product) {
        String sql = "INSERT INTO products VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, product.getProductID());
            st.setString(2, product.getImage());
            st.setInt(3, product.getQuantityRemaining());
            st.setInt(4, product.getNOReorder());
            st.setInt(5, product.getQuantityOrdered());
            st.setString(6, product.getProductName());
            st.setString(7, product.getDescription());
            st.setFloat(8, product.getPrice());

            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void Delete(String ProductID) {
        String sql = "DELETE FROM products WHERE ProductID  = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ProductID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void Update(Product product) {
        String sql = "UPDATE products SET ProductID = ?, Image = ?,"
                + "QuantityRemaining = ?,"
                + "NOReorder = ?,"
                + "QuantityOrdered = ?,"
                + "ProductName = ?,"
                + "Description = ?,"
                + "price = ? WHERE ProductID = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, product.getProductID());
            st.setString(2, product.getImage());
            st.setInt(3, product.getQuantityRemaining());
            st.setInt(4, product.getNOReorder());
            st.setInt(5, product.getQuantityOrdered());
            st.setString(6, product.getProductName());
            st.setString(7, product.getDescription());
            st.setFloat(8, product.getPrice());
            st.setString(9, product.getProductID());
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Product getProductID(String id) {
        String sql = "SELECT * FROM products WHERE ProductID  = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product product = new Product(rs.getString("ProductID"),
                        rs.getString("Image"),
                        rs.getInt("QuantityRemaining"),
                        rs.getInt("NOReorder"),
                        rs.getInt("QuantityOrdered"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getFloat("price"));
                return product;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getProductByCategoryID(String CategoryID) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products P FULL JOIN ProductType PT ON P.ProductID = PT.ProductID\n"
                + "						FULL JOIN Categories C  ON C.CategoryID = PT.CategoryID \n"
                + "						WHERE C.CategoryID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CategoryID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("ProductID"),
                        rs.getString("Image"),
                        rs.getInt("QuantityRemaining"),
                        rs.getInt("NOReorder"),
                        rs.getInt("QuantityOrdered"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getFloat("price"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductnNotHaveCategoryID(String CategoryID) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT  * FROM Products WHERE ProductID Not IN (SELECT DISTINCT  P.ProductID FROM Products P FULL JOIN ProductType PT ON P.ProductID = PT.ProductID\n"
                + "						FULL JOIN Categories C ON C.CategoryID = PT.CategoryID\n"
                + "						WHERE C.CategoryID = ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CategoryID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("ProductID"),
                        rs.getString("Image"),
                        rs.getInt("QuantityRemaining"),
                        rs.getInt("NOReorder"),
                        rs.getInt("QuantityOrdered"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getFloat("price"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getTop3Selling() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 3 * from Products where QuantityOrdered > 0";
        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("ProductID"),
                        rs.getString("Image"),
                        rs.getInt("QuantityRemaining"),
                        rs.getInt("NOReorder"),
                        rs.getInt("QuantityOrdered"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getFloat("price"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Product> getBestSelling() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 10 * FROM Products ORDER BY QuantityOrdered DESC";
        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("ProductID"),
                        rs.getString("Image"),
                        rs.getInt("QuantityRemaining"),
                        rs.getInt("NOReorder"),
                        rs.getInt("QuantityOrdered"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getFloat("price"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<String> getDescription() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT Description FROM Products";
        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String description = rs.getString("Description");
                list.add(description);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Product> getByFilter(String CategoryID, float PriceFrom, float PriceTo) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT P.ProductID, P.Image, P.QuantityRemaining, P.NOReorder, P.QuantityOrdered, P.ProductName, P.Description, P.price FROM Products P FULL JOIN ProductType PT ON P.ProductID = PT.ProductID\n"
                + "						FULL JOIN Categories C ON C.CategoryID = PT.CategoryID\n"
                + "						WHERE C.CategoryID = ? AND P.price >= ? AND P.price <= ? ";

        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, CategoryID);
            st.setFloat(2, PriceFrom);
            st.setFloat(3, PriceTo);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("ProductID"),
                        rs.getString("Image"),
                        rs.getInt("QuantityRemaining"),
                        rs.getInt("NOReorder"),
                        rs.getInt("QuantityOrdered"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getFloat("price"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Product> getBySearch(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE ProductName LIKE ? OR Description LIKE ?";
        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + keyword + "%");
            st.setString(2, "%" + keyword + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("ProductID"),
                        rs.getString("Image"),
                        rs.getInt("QuantityRemaining"),
                        rs.getInt("NOReorder"),
                        rs.getInt("QuantityOrdered"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getFloat("price"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Product> getProductInOrder(String CustomerID, String OrderId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products	WHERE ProductID IN \n" +
"                (SELECT  P.ProductID FROM Orders O FULL JOIN OrderDetails OD ON O.OrderID = OD.OrderID\n" +
"                				   FULL JOIN Products P ON P.ProductID = OD.ProductID WHERE O.CustomerID = ? AND OD.OrderID = ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CustomerID);
            st.setString(2, OrderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("ProductID"),
                        rs.getString("Image"),
                        rs.getInt("QuantityRemaining"),
                        rs.getInt("NOReorder"),
                        rs.getInt("QuantityOrdered"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getFloat("price"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public  List<Product> getListByPage(List<Product> list, int start, int end){
        List<Product> array = new ArrayList<>();      
        for (int i = start; i < end; i++) {
            array.add(list.get(i));
        }
        return array;
    }
 
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        System.out.println("sa");
        //List<Product> list = dao.getByFilter("", 0, 0, Description)
//        for (Product product : list) {
//            System.out.println(product.getProductName());
//        }
    }
}
