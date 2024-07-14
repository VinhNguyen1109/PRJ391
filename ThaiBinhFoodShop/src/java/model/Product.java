/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MTDT
 */
public class Product {

    private String ProductID;
    private String Image;
    private int QuantityRemaining;
    private int NOReorder;
    private int QuantityOrdered;
    private String ProductName;
    private String Description;
    private float Price;

    public Product(String ProductID, String Image, int QuantityRemaining, int NOReorder, int QuantityOrdered, String ProductName, String Description, float Price) {
        this.ProductID = ProductID;
        this.Image = Image;
        this.QuantityRemaining = QuantityRemaining;
        this.NOReorder = NOReorder;
        this.QuantityOrdered = QuantityOrdered;
        this.ProductName = ProductName;
        this.Description = Description;
        this.Price = Price;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getQuantityRemaining() {
        return QuantityRemaining;
    }

    public void setQuantityRemaining(int QuantityRemaining) {
        this.QuantityRemaining = QuantityRemaining;
    }

    public int getNOReorder() {
        return NOReorder;
    }

    public void setNOReorder(int NOReorder) {
        this.NOReorder = NOReorder;
    }

    public int getQuantityOrdered() {
        return QuantityOrdered;
    }

    public void setQuantityOrdered(int QuantityOrdered) {
        this.QuantityOrdered = QuantityOrdered;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }
    
}
