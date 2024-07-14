/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MTDT
 */
public class Order {

    private String OrderID, CustomerID, OrderDate, ShipCountry, ShipProvince, ShipDistrict, ShipWards, ShipAddress;

    public Order(String OrderID, String CustomerID, String OrderDate, String ShipCountry, String ShipProvince, String ShipDistrict, String ShipWards, String ShipAddress) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.OrderDate = OrderDate;
        this.ShipCountry = ShipCountry;
        this.ShipProvince = ShipProvince;
        this.ShipDistrict = ShipDistrict;
        this.ShipWards = ShipWards;
        this.ShipAddress = ShipAddress;
    }

    public Order() {
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getShipCountry() {
        return ShipCountry;
    }

    public void setShipCountry(String ShipCountry) {
        this.ShipCountry = ShipCountry;
    }

    public String getShipProvince() {
        return ShipProvince;
    }

    public void setShipProvince(String ShipProvince) {
        this.ShipProvince = ShipProvince;
    }

    public String getShipDistrict() {
        return ShipDistrict;
    }

    public void setShipDistrict(String ShipDistrict) {
        this.ShipDistrict = ShipDistrict;
    }

    public String getShipWards() {
        return ShipWards;
    }

    public void setShipWards(String ShipWards) {
        this.ShipWards = ShipWards;
    }

    public String getShipAddress() {
        return ShipAddress;
    }

    public void setShipAddress(String ShipAddress) {
        this.ShipAddress = ShipAddress;
    }

}
