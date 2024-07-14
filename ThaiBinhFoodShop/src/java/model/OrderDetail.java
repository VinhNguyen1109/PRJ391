/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MTDT
 */
public class OrderDetail {
    private  String OrderID, ProductID;
    private int Quantity;
    

    public OrderDetail() {
    }

    public OrderDetail(String OrderID, String ProductID, int Quantity) {
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
        
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }


    
}
