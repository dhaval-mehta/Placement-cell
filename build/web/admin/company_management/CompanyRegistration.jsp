<%--
    Document   : CompanyRegistration
    Created on : 27-Aug-2016, 19:01:25
    Author     : Dhaval
--%>
<%@taglib prefix="my" uri="/WEB-INF/tlds/myTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title> Company Registration </title>

        <c:import url="/head.html" />

        <link rel="stylesheet" href="/css/datepicker.css">
        <script src="/js/jquery-1.9.1.min.js"></script>
        <script src="/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript">
            // When the document is ready
            $(document).ready(function () {

                $('#date').datepicker({
                    format: "dd/mm/yyyy"
                });

            });

            $(document).ready(function () {

                $('#last_date').datepicker({
                    format: "dd/mm/yyyy"
                });

            });

        </script>
    </head><!--/head-->

    <body>
        <c:import url="/header.jsp" />

        <section id="contact-page">
            <div class="container">

                <c:choose>
                    <c:when test="${!empty(param.name)}">
                        <p style="text-align: center; margin-top: 100px; font-size: xx-large"><my:ManageCompany /></p>
                    </c:when>
                    <c:otherwise>
                        <h1 style="text-align: center;margin-bottom: 30px">Company Registration</h1>
                        <hr>
                        <form method="post" action="">
                            <c:import url="CompanyDetails.jsp" />
                            <div class="form-group center">
                                <input type="submit" name="submit" class="btn btn-primary btn-lg" value="Create">
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
        <c:import url="/footer.html" />
    </body>
</html>
