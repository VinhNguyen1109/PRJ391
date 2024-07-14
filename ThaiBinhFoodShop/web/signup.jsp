<%-- 
    Document   : signup
    Created on : Feb 5, 2024, 1:16:14 PM
    Author     : MTDT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="dal.LocationsDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="image/logo.jpeg" type="image/x-icon">
        <title>Vinh Nông Sản</title>
        <link rel="stylesheet" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        

    </head>
    <body>


        <div class="main">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-img">
                        <img src="image/logo.jpeg" alt="">
                    </div>
                    <div class="signup-form">
                        <form action="signup" method="POST" class="register-form" id="register-form">
                            <h2>Sign Up</h2>
                            <br>
                            <h2>${requestScope.err}</h2>
                            <div id="hello">

                            </div>
                            <p id="demo"></p>
                            
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="country">Country :</label>
                                    <div class="form-select">
                                         <input type="text" name="country" id="country" required/>
                                        
                                        <span class="select-icon"><i class="zmdi zmdi-chevron-down"></i></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="country">Province :</label>
                                    <div class="form-select">
                                         <input type="text" name="province" id="country" required/>
                                        
                                        <span class="select-icon"><i class="zmdi zmdi-chevron-down"></i></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="country">District :</label>
                                    <div class="form-select">
                                         <input type="text" name="district" id="country" required/>
                                        
                                        <span class="select-icon"><i class="zmdi zmdi-chevron-down"></i></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="country">Wards :</label>
                                    <div class="form-select">
                                         <input type="text" name="wards" id="country" required/>
                                        
                                        <span class="select-icon"><i class="zmdi zmdi-chevron-down"></i></span>
                                    </div>
                                </div>


                            </div>
                            
                            
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="username">UserName :</label>
                                    <input type="text" name="username" id="username" required/>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password :</label>
                                    <input type="text" name="password" id="password" required/>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group">
                                    <label for="firstname">First Name :</label>
                                    <input type="text" name="firstname" id="firstname" required/>
                                </div>
                                <div class="form-group">
                                    <label for="lastname">last Name :</label>
                                    <input type="text" name="lastname" id="lastname" required/>
                                </div>
                            </div>

                            <div class="form-radio">
                                <label for="gender" class="radio-label">Gender :</label>
                                <div class="form-radio-item">
                                    <input type="radio" name="gender" id="male" value="1" checked>
                                    <label for="male">Male</label>
                                    <span class="check"></span>
                                </div>
                                <div class="form-radio-item">
                                    <input type="radio" name="gender" id="female" value="2">
                                    <label for="female">Female</label>
                                    <span class="check"></span>
                                </div>                             
                            </div>
                            
                            <div class="form-group">
                                <label for="address">Address :</label>
                                <input type="text" name="address" id="address" required>
                            </div>
                            <div class="form-group">
                                <label for="birth_date">DOB :</label>
                                <input type="date" name="birth_date" id="birth_date" required>
                            </div>
                            <div class="form-group">
                                <label for="pincode">Phone Number :</label>
                                <input type="text" name="phonenumber" id="phonenumber" required>
                            </div>                         
                            <div class="form-submit">
                                <a href="login">Back To login</a>
                                <input type="reset" value="Reset All" class="submit" name="reset" id="reset" />
                                <input type="submit" value="Submit Form" class="submit" name="submit" id="submit" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>



       
    </body>
</html>
