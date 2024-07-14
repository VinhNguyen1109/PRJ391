
CREATE TABLE Account(
    [Type] int NOT NULL,
    UserName varchar(255) NOT NULL,
    [PassWord] varchar(255) NOT NULL
	PRIMARY KEY (UserName)
);
CREATE TABLE Customers(
    CustomerID varchar(255) NOT NULL,
    LastName nvarchar(255) NOT NULL,
    FirstName nvarchar(255) NOT NULL,
	Gender int NOT NULL,
	DOB DATE,
	PhoneNumber varchar(12),
	Country nvarchar(100) NOT NULL,
	Province nvarchar(100) NOT NULL,
	District nvarchar(100) NOT NULL,
	Wards nvarchar(100) NOT NULL,
	[Address] nvarchar(200) NOT NULL,
	PRIMARY KEY (CustomerID),
	FOREIGN KEY (CustomerID) REFERENCES Account(UserName)
);
CREATE TABLE Categories(
    CategoryID varchar(50) NOT NULL,
    CategoryName varchar(255) NOT NULL,
    [Description] varchar(max),
	
	PRIMARY KEY (CategoryID)
);
CREATE TABLE Products(
    ProductID varchar(50) NOT NULL,	
    ProductName varchar(255) NOT NULL,
    Price money NOT NULL,
	[Image] nvarchar(max) NOT NULL,
	QuantityRemaining int NOT NULL,
	NOReorder int NOT NULL,
	[Description] varchar(max),
	QuantityOrdered int NOT NULL,

	PRIMARY KEY (ProductID),
);

CREATE TABLE ProductType(
	CategoryID varchar(50) NOT NULL,
	ProductID varchar(50) NOT NULL,	
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID),
	FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
	PRIMARY KEY (ProductID, CategoryID)
);


CREATE TABLE Orders(
     OrderID varchar(50) NOT NULL,
	 CustomerID varchar(255) NOT NULL,
	 OrderDate date NOT NULL,
	 ShipCountry nvarchar(100) NOT NULL,
	 ShipProvince nvarchar(100) NOT NULL,
	 ShipDistrict nvarchar(100) NOT NULL,
	 ShipWards nvarchar(100) NOT NULL,
	 ShipAddress nvarchar(200) NOT NULL,
	 PRIMARY KEY (OrderID),
	 FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE OrderDetails(
     OrderID varchar(50) NOT NULL,
	 ProductID varchar(50) NOT NULL,
	 Quantity int NOT NULL,
	 Discount float
	 PRIMARY KEY (OrderID,ProductID),
	 FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
	 FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
);



INSERT INTO  ProductType VALUES ('5', '43');
select * from Account
select * from Customers
INSERT INTO  Account VALUES (1, 'admin', '123');
