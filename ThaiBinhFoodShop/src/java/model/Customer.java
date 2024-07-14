/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MTDT
 */
public class Customer {
//    CREATE TABLE [dbo].[Customers](
//	[CustomerID] [varchar](255) NOT NULL,
//	[LastName] [nvarchar](255) NOT NULL,
//	[FirstName] [nvarchar](255) NOT NULL,
//	[Gender] [int] NOT NULL,
//	[DOB] [date] NULL,
//	[PhoneNumber] [varchar](12) NULL,
//	[Country] [nvarchar](100) NOT NULL,
//	[Province] [nvarchar](100) NOT NULL,
//	[District] [nvarchar](100) NOT NULL,
//	[Wards] [nvarchar](100) NOT NULL,
//	[Address] [nvarchar](200) NOT NULL,
    
    private String CustomerID, LastName, FirstName, Phonenumber, Country, Province, District, Wards, Address, date;
    private int Gender;

    public Customer(String CustomerID, String LastName, String FirstName, String Phonenumber, String Country, String Province, String District, String Wards, String Address, String date, int Gender) {
        this.CustomerID = CustomerID;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.Phonenumber = Phonenumber;
        this.Country = Country;
        this.Province = Province;
        this.District = District;
        this.Wards = Wards;
        this.Address = Address;
        this.date = date;
        this.Gender = Gender;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public String getLastName() {
        return LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public String getCountry() {
        return Country;
    }

    public String getProvince() {
        return Province;
    }

    public String getDistrict() {
        return District;
    }

    public String getWards() {
        return Wards;
    }

    public String getAddress() {
        return Address;
    }

    public String getDate() {
        return date;
    }

    public int getGender() {
        return Gender;
    }
    
    
}
