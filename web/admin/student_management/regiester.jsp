<%--
    Document   : register.jsp
    Created on : 13-Aug-2016, 21:31:48
    Author     : Dhaval
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Student Registration</title>
        <c:import url="/head.html" />
    </head>
    <body class="homepage">
        <c:import url="/header.jsp" />

        <div class="container">

            <c:if test="${!empty(requestScope.message)}">
                <div>
                    <h1 style="font-size: x-large; color: black; text-align: center">${requestScope.message}</h1>
                </div>
            </c:if>
            <div class="col-lg-3" ></div>

            <div class="centered center wow fadeInDown animated-item col-lg-5" style='margin-left: auto;margin-right: auto'>

                <h1 style="color: black">Student Registration</h1><br>
                <form method="post" action="UploadServlet" enctype="multipart/form-data">

                    <label> Department : </label>
                    <select name="dept" class="form-control" >
                        <option value='1' >CE</option>
                    </select>

                    </br>

                    <label> Student Type : </label>
                    <select name="dept" class="form-control" >
                        <option value='1' >Under Graduate (B Tech)</option>
                    </select>

                    </br>

                    <label> Upload File </label>
                    <input type="file" name="file" accept="application/vnd.ms-excel" class="form-control" />

                    <div class="form-group center">
                        <input type="submit" name="submit" class="btn btn-primary btn-lg" value="Create" style="margin-top: 20px;">
                    </div>
                </form>
            </div>
        </div>
        <c:import url="/footer.html" />
    </body>
</html>
