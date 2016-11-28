<%--
    Document   : updateCompanyDetails
    Created on : 28 Aug, 2016, 1:25:48 PM
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

                <div class="row contact-wrap">

                    <form method="post" action="">
                        <input type="hidden" name="action" value="update" />
                        <div class="col-sm-5 col-sm-offset-1">
                            <div class="form-group">
                                <label>Company ID(cid) *</label>
                                <input type="number" name="cid" class="form-control" required="required" value='<c:out value="${param.cid}" />'>
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
                <my:ManageCompany />
                <c:if test="${empty(param.name)}">
                    <hr>
                    <h1 style="text-align: center; padding-top: 50px; margin-bottom: 30px">Update Company Details</h1>
                    <div class="form-group center" hidden="${empty(requestScope.errorMessage)}">
                        <p style="text-align: center; padding: 20px 20px; color: red; font-size: x-large">${requestScope.errorMessage}</p>
                    </div>
                    <form method="post" action="">
                        <input type="hidden" name="action" value="update" />
                        <c:import url="CompanyDetails.jsp" />
                        <div class="form-group center">
                            <input type="submit" name="submit" class="btn btn-primary btn-lg" value="Update">
                        </div>
                    </form>
                </c:if>

            </div>
        </section>
        <c:import url="/footer.html" />
    </body>
</html>
