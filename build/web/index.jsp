<%--
    Document   : index
    Created on : 08-Nov-2016, 22:44:35
    Author     : Dhaval
--%>

<%

    String userType = (String) request.getSession().getAttribute("userType");

    switch (userType)
    {
        case "student":
            response.sendRedirect("/student/index.jsp");
        case "administrator":
            response.sendRedirect("/admin/index.jsp");
        default:
            response.sendRedirect("/login.jsp");
    }
%>