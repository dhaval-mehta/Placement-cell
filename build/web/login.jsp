<%--
    Document   : login
    Created on : 15-Aug-2016, 18:00:19
    Author     : Dhaval
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Login </title>
        <c:import url="head.html" />
    </head><!--/head-->

    <body>
        <c:import url="header.jsp" />

        <section id="login_bg" style="margin-top:50px">
            <div class="container" >
                <div class="row center wow fadeInDown animated-item" >
                    <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
                        <div id="login">
                            <p class="text-center" style="font-size: 20px">
                                Login
                            </p>
                            <hr>
                            <form action="/Authentication" method="post">
                                <div class="form-group">
                                    <input type="text" name="username" class=" form-control " placeholder="Username">
                                    <span class="input-icon"></span>
                                </div>
                                <div class="form-group" style="margin-bottom:5px;">
                                    <input type="password" name="password" class=" form-control" placeholder="Password" style="margin-bottom:5px;">
                                    <span class="input-icon"><i class="icon-lock"></i></span>
                                </div>
                                <div style="height: 20px" ></div>
                                <input type="submit" value="Log in" class="button_fullwidth" style="background-color: #c52d2f" />
                            </form>

                            <c:if test="${param.message != null}">
                                <hr>    <p style="color: red;font-size: 20px;text-align: center;font-family: verdana"> ${param.message} </p>
                                <hr>    <p style="color: red;font-size: 20px;text-align: center;font-family: verdana"> If you forgot your password then contact faculty</p>
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>
        </section> <!-- End login -->
        <c:import url="footer.html" />
    </body>
</html>