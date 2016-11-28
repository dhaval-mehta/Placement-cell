<%--
    Document   : updateDetails
    Created on : 15-Aug-2016, 22:07:30
    Author     : Dhaval
--%>

<%@taglib prefix="my" uri="/WEB-INF/tlds/myTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title> Update Details </title>
        <c:import url="/head.html" />
        <link rel="stylesheet" href="/css/datepicker.css">
        <script src="/js/jquery-1.9.1.min.js"></script>
        <script src="/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript">
            // When the document is ready
            $(document).ready(function () {

                $('#dob').datepicker({
                    format: "dd/mm/yyyy"
                });

            });
        </script>

    </head><!--/head-->

    <body>

        <c:import url="/header.jsp" />

        <section id="contact-page">

            <div class="row contact-wrap">
                <form method="post" action="">
                    <div class="col-sm-5 col-sm-offset-1">
                        <div class="form-group">
                            <label>Username of Student *</label>
                            <input type="text" name="username" class="form-control" required="required" value='<c:out value="${param.username}" />'>
                            <input type="hidden" name="action" value="fetch" />
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <div class="form-group center" style="padding-top: 10px">
                            <input type="submit" name="submit" class="btn btn-primary btn-lg" value="Submit">
                        </div>
                    </div>
                </form>
            </div>

            <c:if test = "${!empty(param.action)}" >

                <hr>

                <c:if test = '${param.action == "update"}'>
                    <c:import url="UpdateStudentDetails" />
                    <h2 class="center"> <c:out value="${requestScope.message}" /> </h2> <hr>
                </c:if>

                <c:if test = '${param.action == "fetch"}'>
                    <c:import url="FetchStudentDetails" />
                </c:if>

            </c:if>
        </section><!--/#details-page-->

        <c:import url="/footer.html" />
    </body>
</html>

