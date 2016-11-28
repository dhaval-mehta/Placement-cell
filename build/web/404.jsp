<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title> Error </title>
        <c:import url="/head.html" />
    </head>
    <body>
        <c:import url="/header.jsp" />
        
    <section id="error" class="container text-center">
        <h1>404, Page not found</h1>
        <p>The Page you are looking for doesn't exist or an other error occurred.</p>
        <a class="btn btn-primary" href="index.html">GO BACK TO THE HOMEPAGE</a>
    </section><!--/#error-->


   <c:import url="footer.html" />
</body>
</html>