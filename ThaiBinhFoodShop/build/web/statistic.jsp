<%-- 
    Document   : WishList
    Created on : Mar 2, 2024, 2:25:58 PM
    Author     : MTDT
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {
                'packages': ['geochart'],
            }
            );
            google.charts.setOnLoadCallback(drawRegionsMap);

            function drawRegionsMap() {
                var data = google.visualization.arrayToDataTable([
                    ['Country', 'Popularity'],
                    ['Germany', 200],
                    ['United States', 300],
                    ['Brazil', 400],
                    ['Canada', 500],
                    ['France', 600],
                    ['RU', 700]
                ]);

                var options = {
                }
                ;

                var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

                chart.draw(data, options);
            }
        </script>
    </head>
    <body>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
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

                                <!-- Shopping cart table -->
                                <div class="table-responsive">
                                    <h1 style="color: #ff3333">Tổng thu nhập: ${requestScope.Total} VND</h1>
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="p-2 px-3 text-uppercase" >
                                                        <a href="statistic?stt=1" style="color: black"   onMouseOver="this.style.color = '#FFA500'"
                                                           onMouseOut="this.style.color = '#454545'">Thống kê doanh số bán hàng các tháng</a>
                                                    </div>
                                                </th>

                                            </tr>
                                        </thead>

                                        <thead>
                                            <tr>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="p-2 px-3 text-uppercase">
                                                        <a href="statistic?stt=2" style="color: black"onMouseOver="this.style.color = '#FFA500'"
                                                           onMouseOut="this.style.color = '#454545'">Độ tuổi người dùng web</a>
                                                    </div>
                                                </th>

                                            </tr>
                                        </thead>
                                        <thead>
                                            <tr>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="p-2 px-3 text-uppercase">
                                                        <a href="statistic?stt=3" style="color: black" onMouseOver="this.style.color = '#FFA500'"
                                                           onMouseOut="this.style.color = '#454545'">Doanh số bán hàng của các loại hàng</a>
                                                    </div>
                                                </th>

                                            </tr>
                                        </thead>
                                        <thead>
                                            <tr>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="p-2 px-3 text-uppercase">
                                                        <a href="statistic?stt=4" style="color: black" onMouseOver="this.style.color = '#FFA500'"
                                                           onMouseOut="this.style.color = '#454545'">Chi tiết doanh số các sản phẩm</a>
                                                    </div>
                                                </th>

                                            </tr>
                                        </thead>

                                        <thead>
                                            <tr>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="p-2 px-3 text-uppercase">
                                                        <a href="statistic?stt=5" style="color: black" onMouseOver="this.style.color = '#FFA500'"
                                                           onMouseOut="this.style.color = '#454545'">Thống kê mua sắm tại web của các tỉnh thành</a>
                                                    </div>
                                                </th>

                                            </tr>
                                        </thead>

                                    </table>
                                </div>
                                <div class="row" style="background: white">

                                    <c:if test="${requestScope.t1 != null}">
                                        <div id="myChart" style="width:100%; max-width:1000px; height:700px;"></div>

                                        <script>
                                            google.charts.load('current', {'packages': ['corechart']});
                                            google.charts.setOnLoadCallback(drawChart);

                                            function drawChart() {

                                                // Set Data
                                                const data = google.visualization.arrayToDataTable([
                                                    ['Month', 'Quantity'],
                                                    ['Tháng 1', ${requestScope.t1}],
                                                    ['Tháng 2', ${requestScope.t2}],
                                                    ['Tháng 3', ${requestScope.t3}],
                                                    ['Tháng 4', ${requestScope.t4}],
                                                    ['Tháng 5', ${requestScope.t5}],
                                                    ['Tháng 6', ${requestScope.t6}],
                                                    ['Tháng 7', ${requestScope.t7}],
                                                    ['Tháng 8', ${requestScope.t8}],
                                                    ['Tháng 9', ${requestScope.t9}],
                                                    ['Tháng 10', ${requestScope.t10}],
                                                    ['Tháng 11', ${requestScope.t11}],
                                                    ['Tháng 12', ${requestScope.t12}],
                                                ]);

                                                // Set Options
                                                const options = {
                                                    title: 'Số lượng sản phẩm bán được các tháng trong năm'
                                                };

                                                // Draw
                                                const chart = new google.visualization.BarChart(document.getElementById('myChart'));
                                                chart.draw(data, options);

                                            }
                                        </script>
                                    </c:if>    



                                    <c:if test="${requestScope.young != null}">
                                        <div id="myChart" style="width:100%; max-width:1000px; height:700px;"></div>

                                        <script>
                                            google.charts.load('current', {'packages': ['corechart']});
                                            google.charts.setOnLoadCallback(drawChart);

                                            function drawChart() {


                                                var data = google.visualization.arrayToDataTable([

                                                    ['Độ tuổi', 'Số lượng', {role: 'style'}, {role: 'annotation'}],
                                                    ['0-15', ${requestScope.young}, '#b87333', 'Young Buffalo'],
                                                    ['16-35', ${requestScope.student}, 'silver', 'Student'],
                                                    ['36-59', ${requestScope.middleAge}, 'gold', 'Middle Age'],
                                                    ['60-100', ${requestScope.old}, 'color: #e5e4e2', 'Old']
                                                ]);

                                                // Set Options
                                                const options = {
                                                    title: 'Trung bình độ tuổi mua hàng của người dùng',
                                                    legend: {position: 'none'},
                                                    chart: {orientation: 'vertical'},
                                                    bars: 'vertical'
                                                };

                                                // Draw
                                                const chart = new google.visualization.BarChart(document.getElementById('myChart'));
                                                chart.draw(data, options);

                                            }
                                        </script>
                                    </c:if>    

                                    <c:if test="${requestScope.map != null}">
                                        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                                        <script type="text/javascript">
                                            google.charts.load("current", {packages: ['corechart']});
                                            google.charts.setOnLoadCallback(drawChart);
                                            function drawChart() {
                                                var data = google.visualization.arrayToDataTable([
                                                    ["Element", "Density", {role: "style"}],
                                            <c:forEach items="${requestScope.map}" var="c">
                                                    ['${c.key}', ${c.value}, "silver"],
                                            </c:forEach>


                                                ]);

                                                var view = new google.visualization.DataView(data);
                                                view.setColumns([0, 1,
                                                    {calc: "stringify",
                                                        sourceColumn: 1,
                                                        type: "string",
                                                        role: "annotation"},
                                                    2]);

                                                var options = {
                                                    title: "Số lượng đặt hàng tại các tỉnh thành",
                                                    width: 600,
                                                    height: 400,
                                                    bar: {groupWidth: "95%"},
                                                    legend: {position: "none"},
                                                };
                                                var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
                                                chart.draw(view, options);
                                            }
                                        </script>
                                        <div id="columnchart_values" style="width:100%; max-width:1000px; height:700px;"></div>
                                    </c:if>    


                                    <c:if test="${requestScope.ListAllProduct != null}">
                                        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                                        <script type="text/javascript">
                                            google.charts.load('current', {'packages': ['table']});
                                            google.charts.setOnLoadCallback(drawTable);

                                            function drawTable() {
                                                var data = new google.visualization.DataTable();
                                                data.addColumn('string', 'ProductID');
                                                data.addColumn('string', 'Product Name');
                                                data.addColumn('number', 'Price');
                                                data.addColumn('number', 'Quantity Order');
                                                data.addRows([
                                            <c:forEach items="${requestScope.ListAllProduct}" var="c">
                                                    ['${c.getProductID()}', '${c.getProductName()}', ${c.getPrice()}, ${c.getQuantityOrdered()}],
                                            </c:forEach>
                                                ]);

                                                var table = new google.visualization.Table(document.getElementById('table_div'));

                                                table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
                                            }
                                        </script>
                                        <div id="table_div" style="width:100%; max-width:1000px; height:500px;" ></div>

                                    </c:if> 



                                    <c:if test="${requestScope.mapLocation != null}">
                                        <div
                                            id="myChart" style="width:100%; max-width:1000px; height:500px;">
                                        </div>

                                        <script>
                                            google.charts.load('current', {'packages': ['corechart']});
                                            google.charts.setOnLoadCallback(drawChart);

                                            function drawChart() {
                                                const data = google.visualization.arrayToDataTable([
                                                    ['Contry', 'Mhl'],
                                            <c:forEach items="${requestScope.mapLocation}" var="c">
                                                    ['${c.key}', ${c.value}],
                                            </c:forEach>
                                                ]);

                                                const options = {
                                                    title: 'Thống kê hóa đơn tại các tỉnh thành',
                                                    is3D: true
                                                };

                                                const chart = new google.visualization.PieChart(document.getElementById('myChart'));
                                                chart.draw(data, options);
                                            }
                                        </script>
                                    </c:if> 

                                </div>
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
