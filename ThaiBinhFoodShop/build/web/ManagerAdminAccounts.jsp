<%-- 
    Document   : WishList
    Created on : Mar 2, 2024, 2:25:58 PM
    Author     : MTDT
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="dal.*" %>
<%@page import="java.util.List"%>
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
        <link href="css/style_1.css" rel="stylesheet" type="text/css"/>
        <style>
            body{
                font-size: 13px;
                line-height: 1.8;
                color: #222;
                font-weight: 400;
                background-color: #2f801b;
                padding: 90px 0;
            }

        </style>
        <script>
            function doDelete(id) {
                if (confirm("Do you want delete Account with UserName = " + id + "?")) {
                    window.location = "manageraccounts?idDelete=" + id;
                }
            }

        </script>
    </head>
    <body>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark" >
            <div class="container" style="background-color:  white">
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
                                <c:if test="${sessionScope.TypeOfUser == 2}">
                                <li class="breadcrumb-item active"><a href="showallorder">All Orders</a></li>
                                </c:if> 
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
        <div class="shopping-cart">
            <div class="px-4 px-lg-0">

                <div class="pb-5">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                                <center>
                                    <c:if test="${sessionScope.UserName eq 'sa'}">
                                        <h2 style="color: chocolate"><a  href="signup">Create Account Admin</a></h2>
                                    </c:if>
                                </center>

                                <c:if test="${requestScope.Account!= null}">
                                    <center>
                                        <h1>Edit Account Admin</h1>
                                        <form action="manageraccounts" method="post">                        
                                            <table>
                                                <tr>
                                                    <td>User Name</td>
                                                    <td> <input type="text" name="UseName" readonly value="${requestScope.UserName}" required/> </td>
                                                </tr>  
                                                <tr>
                                                    <td>PassWord</td>
                                                    <td> <input type="text" name="pass" value="${requestScope.Pass}" required/> </td>
                                                </tr>  
                                                <tr>
                                                    <td>Type</td>
                                                    <td>

                                                        <c:if test="${requestScope.type == 1}">
                                                            Admin<input type="radio" name="Type" value="1" checked=""><br>
                                                            User<input type="radio" name="Type" value="2">

                                                        </c:if>
                                                        <c:if test="${requestScope.type == 2}">
                                                            Admin<input type="radio" name="Type" value="1" ><br>
                                                            User<input type="radio" name="Type" value="2" checked="">
                                                        </c:if>
                                                    </td>
                                                </tr>                   
                                            </table></br>
                                            <div class="submit">
                                                <input type="submit" value="Edit"/>
                                                <input type="reset" value="Reset"/>
                                            </div>
                                        </form>
                                    </center>
                                </c:if>

                                <!-- Shopping cart table -->
                                <c:if test="${requestScope.listAccountAdmin != null}">

                                    <center>
                                        <div class="table-responsive">

                                            <table border="1px">
                                                <tr>
                                                    <th>UserName</th>
                                                    <th>Name</th>
                                                    <th>Phone Number</th>

                                                    <c:if test="${sessionScope.UserName eq 'sa'}">
                                                        <th colspan="2">Change Type</th>
                                                        </c:if>
                                                </tr>

                                                <c:forEach items="${requestScope.listAccountAdmin }" var="c">
                                                    <tr>
                                                        <td>${c.getCustomerID()}</td>
                                                        <td>${c.getFirstName()} ${c.getLastName()}</td>
                                                        <td>${c.getPhonenumber()}</td>   
                                                        <c:if test="${sessionScope.UserName eq 'sa'}">
                                                            <c:if test="${c.getCustomerID() ne 'sa'}">
                                                                <td><a href="manageraccounts?idEdit=${c.getCustomerID()}">Edit</a></td>
                                                                <td><a onclick="doDelete('${c.getCustomerID()}')" href="###">Delete</a></td>
                                                            </c:if>


                                                        </c:if>
                                                    </tr>

                                                </c:forEach>
                                            </table>
                                            <br>


                                        </div>
                                    </center>
                                </c:if>
                                <!-- End -->
                            </div>
                        </div>



                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

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
