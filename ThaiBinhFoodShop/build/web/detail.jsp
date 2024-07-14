<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                                            <input type="number" name="PriceFrom" required placeholder="From">
                                            <input type="number" name="PriceTo" required placeholder="To">
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
                    <div class="container">
                        <div class="row" id="ProductList">
                            <div class="card">
                                <div class="row">
                                    <aside class="col-sm-5 border-right">
                                        <article class="gallery-wrap"> 
                                            <div class="img-big-wrap">
                                                <div> <a href="#"><img style="width: 340px" src="${sessionScope.productCart.getImage()}"></a></div>
                                            </div> <!-- slider-product.// -->
                                            <div class="img-small-wrap">

                                            </div> <!-- slider-nav.// -->
                                        </article> <!-- gallery-wrap .end// -->
                                    </aside>
                                    <aside class="col-sm-">
                                        <article class="card-body p-5">
                                            <h3 class="title mb-3">${sessionScope.productCart.getProductName()}</h3>

                                            <p class="price-detail-wrap"> 
                                                <span class="price h3 text-warning"> 
                                                    <span class="currency"></span><span class="num">${sessionScope.productCart.getPrice()} VND</span>
                                                </span> 
                                                <!--<span>/per kg</span>--> 
                                            </p> <!-- price-detail-wrap .// -->
                                            <dl class="item-property">
                                                <dt>Description</dt>
                                                <dd><p>${sessionScope.productCart.getDescription()} </p></dd>
                                            </dl>


                                            <hr>
                                            <form action="buy" method="post">
                                                <div class="row">
                                                    <c:if test="${sessionScope.TypeOfUser == 2}">
                                                        <div class="col-sm-5">
                                                            <dl class="param param-inline">
                                                                <dt>Quantity: </dt>
                                                                <dd>
                                                                    <input type="hidden" name="ProductID" value="${sessionScope.productCart.getProductID()}">
                                                                    <input type="number" id="quantity" name="quantity" min="1" >
                                                                </dd>
                                                            </dl>  <!-- item-property .// -->
                                                        </div> <!-- col.// -->
                                                    </c:if>
                                                </div> <!-- row.// -->
                                                <hr>

                                                <c:if test="${sessionScope.TypeOfUser == 2}">
                                                    <c:if test="${sessionScope.productCart.getQuantityRemaining() > 0}">
                                                        <input type="submit" value="BUY NOW" class="btn btn-lg btn-primary text-uppercase">

                                                        <button  class="btn btn-lg btn-outline-primary text-uppercase" type="submit">Add to cart</button>
                                                    </c:if>

                                                </c:if>
                                            </form>
                                            <c:if test="${sessionScope.TypeOfUser == 1}">
                                                <a href="#" onclick="doDelete('${requestScope.product.getProductID()}')" class="btn btn-lg btn-primary text-uppercase"> Delete Product </a>
                                                <a href="adminmanager?ProductIDEdit=${requestScope.product.getProductID()}" class="btn btn-lg btn-outline-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Edit Product </a>
                                            </c:if>
                                        </article> <!-- card-body.// -->
                                    </aside> <!-- col.// -->
                                </div> <!-- row.// -->
                            </div> <!-- card.// -->
                        </div>

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

