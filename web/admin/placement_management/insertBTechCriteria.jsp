<%--
    Document   : insertBTechCriteria
    Created on : 08-Nov-2016, 01:48:08
    Author     : Dhaval
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 style="text-align: center;margin-bottom: 20px;font-size: 30px">Enter selection Criteria</h1>

<div class="row contact-wrap" style="padding-top: 20px">

    <div class="col-sm-5 col-sm-offset-1">
        <div class="form-group">
            <label>10th Percentage *</label>
            <input type="number" name="ssc_percentage" step="0.01" class="form-control" required="required" min="0" max="100" value='<c:out value="${requestScope.btechCriteria.cid}" />'>
        </div>
        <div class="form-group">
            <label>12th Percentage *</label>
            <input type="number" name="hsc_percentage" step="0.01" class="form-control" required="required" min="0" max="100" value='<c:out value="${requestScope.btechCriteria.cid}" />'>
        </div>

    </div>
    <div class="col-sm-5 col-sm-offset">

        <div class="form-group">
            <label> Diploma CPI *</label>
            <input type="number" name="diploma_cpi" step="0.01" class="form-control" required="required" min="0" max="10" value='<c:out value="${requestScope.btechCriteria.cid}" />'>
        </div>

        <div class="form-group">
            <label>BTech CPI</label>
            <input type="number" name="btech_cpi" step="0.01" class="form-control" required="required" min="0" max="10" value='<c:out value="${requestScope.btechCriteria.cid}" />'>
        </div>
    </div>
</div>