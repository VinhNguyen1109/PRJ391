<%-- 
    Document   : listA
    Created on : Mar 9, 2024, 9:53:29 PM
    Author     : FPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Nen Shop</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            body {
                color: #404E67;
                background: #F5F7FA;
                font-family: 'Open Sans', sans-serif;
            }
            .table-wrapper {
                width: 1500px;
                margin: 30px auto;
                background: #fff;
                padding: 20px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 10px;
                margin: 0 0 10px;
            }
            .table-title h2 {
                margin: 6px 0 0;
                font-size: 22px;
            }
            .table-title .add-new {
                float: right;
                height: 30px;
                font-weight: bold;
                font-size: 12px;
                text-shadow: none;
                min-width: 100px;
                border-radius: 50px;
                line-height: 13px;
            }
            .table-title .add-new i {
                margin-right: 4px;
            }
            .table-title .add-new a{
                text-decoration: none;
            }
            table.table {
                table-layout: fixed;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table th:last-child {
                width: 100px;
            }
            table.table td a {
                cursor: pointer;
                display: inline-block;
                margin: 0 5px;
                min-width: 24px;
            }
            table.table td a.add {
                color: #fff;
            }
            table.table td a.edit {
                color: #FFC107;
            }
            table.table td a.delete {
                color: #E34724;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table td a.add i {
                font-size: 30px;
                margin-right: -1px;
                position: relative;
                top: 3px;
            }
            table.table .form-control {
                height: 32px;
                line-height: 32px;
                box-shadow: none;
                border-radius: 2px;
            }
            table.table .form-control.error {
                border-color: #f50000;
            }
            table.table td .add {
                display: none;
            }
        </style>
        <script>
            function doDelete(user) {
                if (confirm("Are you sure to delete account with username = " + user)) {
                    window.location = "check?action=delete&username=" + user;
                }

            }
        </script>
    </head>
    <body>

        <div class="container-lg">
            <h3><a href="home">Back</a></h3>
            <a href="listA" class="nav-item nav-link">List Accounts</a>
            <a href="listp" class="nav-item nav-link">List Products</a>
            <a href="listc" class="nav-item nav-link">List Categories</a>

            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-8"><h2>Account <b>Details</b></h2></div>
                            <div class="col-sm-4">
                                <button class="btn btn-info add-new"><i class="fa fa-plus"></i><a href="check?action=add"> Add New</a></button>
                            </div>
                        </div>
                    </div>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th style="width: 45px">ID</th>
                                <th style="width: 100px">Name</th>
                                <th style="width: 100px">Description</th>
                                <th style="width: 100px">Actions</th>
                            </tr>
                        </thead>
                        <c:forEach items="${requestScope.ListAll}" var="c">
                            <tbody>
                                <tr>
                                    <td>${c.getCategoryID()}</td>
                                    <td>${c.getCategoryName()}</td>
                                    <td>${c.getDescription()}</td>

                                    <td>
                                        <a class="add" title="Add" data-toggle="tooltip" href="check?action=add"><i class="material-icons">&#xE03B;</i></a>
                                        <a class="edit" title="Edit" data-toggle="tooltip" href="check?action=update&username=${user}"><i class="material-icons">&#xE254;</i></a>
                                        <a class="delete" title="Delete" data-toggle="tooltip" href="#" onclick="doDelete('${user}')"}><i class="material-icons">&#xE872;</i></a>
                                    </td>
                                </tr>

                            </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>     
    </body>
</html>