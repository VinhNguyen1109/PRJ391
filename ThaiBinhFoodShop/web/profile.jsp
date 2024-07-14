<%-- 
    Document   : profile
    Created on : Feb 24, 2024, 12:10:26 AM
    Author     : MTDT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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
            table{
                font-size: 20px
            }

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
    <body>
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

                <div class="col-sm-9" id="ProductList">
                    <center>
                        <div class="row" >
                            <h1 >My Profile</h1>

                        </div>

                        <div class="row">

                            <table style="margin: 20px 0px 0px 30px" >
                                <tr>
                                    <td>Customer ID</td>
                                    <td>${requestScope.customer.getCustomerID()}</td>
                                </tr>
                                <tr>
                                    <td>Name</td>
                                    <td>${requestScope.customer.getFirstName()} ${requestScope.customer.getLastName()}</td>
                                </tr>
                                <tr>
                                    <td>Gender</td>
                                    <c:if test="${requestScope.customer.getGender() == 1}">
                                        <td>Male</td>
                                    </c:if>
                                    <c:if test="${requestScope.customer.getGender() == 2}">
                                        <td>FeMale</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td>Day Of Birth</td>
                                    <td>${requestScope.customer.getDate()}</td>
                                </tr>
                                <tr>
                                    <td>Phone Number</td>
                                    <td>${requestScope.customer.getPhonenumber()}</td>
                                </tr>
                                <tr>
                                    <td>Location</td>
                                    <td>
                                        ${requestScope.customer.getAddress()},
                                        ${requestScope.customer.getWards()},
                                        ${requestScope.customer.getDistrict()},
                                        ${requestScope.customer.getProvince()},
                                        ${requestScope.customer.getCountry()}                           
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </center>

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
