<%-- 
    Document   : Admin
    Created on : Feb 5, 2024, 3:08:42 PM
    Author     : MTDT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> 
    <head>
    <head>
        <link rel="icon" href="image/logo.jpeg" type="image/x-icon">
        <title>Vinh Nông Sản</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .category_block li:hover {
                background-color: #007bff;
            }
            .category_block li:hover a {
                color: #ffffff;
            }
            .category_block li a {
                color: #343a40;
            }

        </style>
        <script>
            function doDelete(id) {
                if (confirm("Do you want delete Product With id= " + id + "?")) {
                    window.location = "adminmanager?idDelete=" + id;
                }
            }
            function doDeleteCategory(id) {
                if (confirm("Do you want delete Category With id= " + id + "?")) {
                    window.location = "adminmanager?idDeleteCategory=" + id;
                }
            }
        </script>
    </head>
</head>
<body>
    <!--begin of menu-->
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="home" style="color: black">Nông Sản Thái Bình</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <ul class="navbar-nav m-auto">
                        <c:if test="${sessionScope.UserName == null}">
                            <li class="nav-item">
                                <a class="nav-link"style="color: black" href="login">Login</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.UserName != null}">
                            <li class="nav-item">
                                <a class="nav-link"style="color: black" href="login">Logout</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"style="color: black" href="home?profile=1">My Profile</a>
                            </li>
                        </c:if>   
                        <c:if test="${sessionScope.TypeOfUser == 1}">
                            <li class="nav-item">
                                <a class="nav-link"style="color: black" href="manageraccounts">Manage Admin Accounts</a>
                            </li>
                        </c:if>  

                        <c:if test="${sessionScope.TypeOfUser == 1}">
                            <li class="nav-item">
                                <a class="nav-link"style="color: black" href="statistic">Statistics</a>
                            </li>
                        </c:if>  
                    </ul>

                    <form action="search" method="post" class="form-inline my-2 my-lg-0">
                        <div class="input-group input-group-sm">
                            <input name="search" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-secondary btn-number">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                        <a class="btn btn-success btn-sm ml-3" href="show">
                            <i class="fa fa-shopping-cart"></i> Cart
                            <span class="badge badge-light">${sessionScope.size}</span>
                        </a>
                    </form>
                </div>
            </div>
        </nav>
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100 " style="max-height: 700px" src="image/signup.jpg"  alt="First slide">
                </div>
                
            </div>
            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    <!--end of menu-->
    <div class="container">
        <div class="row">
            <div class="col">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>                           
                        <li class="breadcrumb-item active"><a href="bestselling">Best - Selling Product</a></li>
                            <c:if test="${sessionScope.TypeOfUser == 1}">
                            <li class="breadcrumb-item active"><a href="adminmanager">Insert Product</a></li>
                            </c:if>  
                            <c:if test="${sessionScope.TypeOfUser == 1}">
                            <li class="breadcrumb-item active"><a href="adminmanager?newCategory=newCategory">New Category</a></li>
                            </c:if>                               
                    </ol>
                </nav>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="card bg-light mb-3">
                    <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                    <ul class="list-group category_block">
                        <c:forEach items="${sessionScope.CategoryListToShow}" var="c">                               
                            <li class="list-group-item text-white"><a href="categoryhome?CategoryID=${c.getCategoryID()}">${c.getCategoryName()}</a>
                                <c:if test="${sessionScope.TypeOfUser == 1}">
                                    <a href="#" onclick="doDeleteCategory('${c.getCategoryID()}')" style="color: red">Delete</a>
                                    <a href="adminmanager?CategoryIDEdit=${c.getCategoryID()}" style="color: red">Edit</a>
                                </c:if>

                            </li>
                        </c:forEach>

                    </ul>
                </div>
                <div class="card bg-light mb-3">
                    <div class="card-header bg-success text-white text-uppercase">Search Product</div>
                    <div class="card-body">
                        <form action="search" method="get">
                            <table>
                                <tr>
                                    <td>Type Of Product</td>
                                    <td>
                                        <select name="TypeSearch" required>
                                            <c:forEach items="${sessionScope.CategoryListToShow}" var="c">
                                                <option value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Price</td>
                                    <td>
                                        <label for="PriceFrom">From</label>
                                        <input type="number" name="PriceFrom" required>
                                        <label for="PriceFrom">To</label>
                                        <input type="number" name="PriceTo" required>
                                    </td>
                                </tr>
                                <tr> 
                                <input type="submit" value="SEARCH">
                                </tr>

                            </table>
                        </form>

                    </div>
                </div>
                <div class="card bg-light mb-3">
                    <div class="card-header bg-success text-white text-uppercase">Top 3 Best Selling Product</div>
                    <div class="card-body">
                        <c:forEach items="${sessionScope.top3}" var="c">
                            <img class="img-fluid" src="${c.getImage()}" />
                            <h5 class="card-title">${c.getProductName()}</h5>
                            <p class="card-text">${c.getDescription()}</p>
                            <p class="bloc_left_price">${c.getPrice()} $</p>

                        </c:forEach>

                    </div>
                </div>
            </div>

            <div class="col-sm-9">
                <div class="row">
                    <div class="card">
                        <c:if test="${sessionScope.ProductEdit!= null}">
                            <img class="card-img-top" src="${sessionScope.ProductEdit.getImage()}" alt="Card image cap">
                        </c:if>

                        <div class="card-body">

                            <h1 style="color: #ff3333">${requestScope.Err}</h1>

                            <form action="adminmanager" method="post">
                                <c:if test="${sessionScope.CategoryEdit == null}">
                                    <c:if test="${sessionScope.newCate == null}">
                                        <c:if test="${sessionScope.ProductEdit!= null}">
                                            ProductID: <input type="text" name="ProductID" value="${sessionScope.ProductEdit.getProductID()}" readonly>  
                                        </c:if>
                                        <c:if test="${sessionScope.ProductEdit== null}">
                                            ProductID: <input type="text" name="ProductID" value="${sessionScope.ProductEdit.getProductID()}"> 
                                        </c:if>

                                        Product Name:<input type="text" name="ProductName" value="${sessionScope.ProductEdit.getProductName()}" required>
                                        Quantity Remaining:<input type="text" name="QuantityRemaining" value="${sessionScope.ProductEdit.getQuantityRemaining()}" required>
                                        Number Of Reorder:<input type="text" name="NOReorder" value="${sessionScope.ProductEdit.getNOReorder()}" required>
                                        Quantity Ordered:<input type="text" name="QuantityOrdered" value="${sessionScope.ProductEdit.getQuantityOrdered()}" required>                         
                                        Description:<input type="text" name="Description" value="${sessionScope.ProductEdit.getDescription()}" required>
                                        URL image:<input type="text" name="Image" value="${sessionScope.ProductEdit.getImage()}" required>
                                        Price:<input type="text" name="Price" value="${sessionScope.ProductEdit.getPrice()}" required>

                                        <c:forEach items="${requestScope.ListCategories}" var="c">
                                            ${c.getCategoryName()}<input type="checkbox" value="${c.getCategoryName()}" name="CategoryType">
                                        </c:forEach>   
                                        <c:if test="${sessionScope.ProductEdit!= null}">
                                            <input type="submit" name="TypeOfChange" value="Edit"> 
                                        </c:if>
                                        <c:if test="${sessionScope.ProductEdit== null}">
                                            <input type  ="submit" name="TypeOfChange" value="Insert"> 
                                        </c:if>
                                    </c:if>  
                                </c:if> 
                                <c:if test="${sessionScope.CategoryEdit == null}">
                                    <c:if test="${sessionScope.newCate != null}">
                                        CategoryID:<input type="text" name="CategoryID" required>
                                        Category Name:<input type="text" name="CategoryName" required>
                                        Description:<input type="text" name="Description" required>
                                        <input type="submit" name="categorychange" value="Insert">
                                    </c:if>  
                                </c:if> 
                                <c:if test="${sessionScope.CategoryEdit != null}">
                                    CategoryID:<input type="text" name="CategoryID" value="${sessionScope.CategoryEdit.getCategoryID()}" readonly>
                                    Category Name:<input type="text" name="CategoryName" value="${sessionScope.CategoryEdit.getCategoryName()}" required>
                                    Description:<input type="text" name="Description" value="${sessionScope.CategoryEdit.getDescription()}" required>
                                    <input type="submit" name="categorychange" value="Edit">
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
            <!-- Footer -->
            <footer class="page-footer font-small blue">

                <!-- Copyright -->
                <div class="footer-copyright text-center py-3" style="color: wheat"> 0981868703 - MB Bank

                </div>
                <!-- Copyright -->

            </footer>
            <!-- Footer -->
            </body>
            </html>
