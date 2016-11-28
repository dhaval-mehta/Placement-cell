<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<fmt:formatDate value="${requestScope.company.placementDate}" var="placementDate" type='date' pattern='dd/MM/yyyy'/>
<fmt:formatDate value="${requestScope.company.lastDate}" var="lastDate" type='date' pattern='dd/MM/yyyy'/>

<div class="row contact-wrap" style="padding-top: 20px">
    <div class="col-sm-5 col-sm-offset-1">

        <c:if test="${!empty(requestScope.company.cid)}" >
            <div class="form-group">
                <label>ID *</label>
                <input type="number" name="cid" class="form-control" required="required" value='<c:out value="${requestScope.company.cid}" />' readonly>
            </div>
        </c:if>

        <div class="form-group">
            <label>Name *</label>
            <input type="text" name="name" class="form-control" required="required" value='<c:out value="${requestScope.company.name}" />'>
        </div>

        <div class="form-group">
            <label>Description</label>
            <textarea name="description" id="message" class="form-control" rows="8"><c:out value="${requestScope.company.description}" /></textarea>
        </div>

    </div>
    <div class="col-sm-5 col-sm-offset">

        <div class="form-group">
            <label>Date of placement*</label>
            <input class="form-control" value='<c:out value="${placementDate}"/>' type="text" name="event_date" id="date" required="required">
        </div>

        <div class="form-group">
            <label>Last Date for Registration *</label>
            <input class="form-control" value="<c:out value='${lastDate}' />" type="text" name="last_date" id="last_date" required="required">
        </div>

        <div class="form-group">
            <label>Venue *</label>
            <input type="text" name="venue" class="form-control" required="required" value='<c:out value="${requestScope.company.venue}" />'>
        </div>

        <div class="form-group">
            <label>Minimum package *</label>
            <input type="text" name="minPackage" class="form-control" required="required" value='<c:out value="${requestScope.company.minPackage}" />'>
        </div>

        <div class="form-group">
            <label>Maximum package *</label>
            <input type="text" name="maxPackage" class="form-control" required="required" value='<c:out value="${requestScope.company.maxPackage}" />'>
        </div>
    </div>
</div>