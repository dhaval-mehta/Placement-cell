<%--
    Document   : Get_Eligible_Students_List
    Created on : 09-Nov-2016, 00:29:50
    Author     : Dhaval
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <c:import url="/head.html" />
    </head>
    <body>
        <c:import url="/header.jsp" />
        <section id="contact-page">
            <div class="container">


                <div class="row contact-wrap">
                    <form action="FetchEligibleStudents" method="post">
                        <c:import url="insertBTechCriteria.jsp" />

                        <div class="form-group center" style="padding-top: 10px">
                            <input type="submit" name="submit" class="btn btn-primary btn-lg" value="Submit">
                        </div>

                    </form>
                </div>
            </div>
        </section>
        <c:import url="/footer.html" />
    </body>
</html>
