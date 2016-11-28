<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<input type="hidden" name="username" value="<c:out value="${requestScope.student.username}" />" />

<div class="row contact-wrap">

    <h1 class="center wow fadeInDown animated-item" style="margin-top: 50px"> Details of <c:out value="${requestScope.student.name}" /></h1>
    <br>

    <h2 class="center" style="margin-bottom: 30px"> Personal Details </h2>

    <div class="col-sm-5 col-sm-offset-1">
        <div class="form-group">
            <label>Name *</label>
            <input type="text" name="name" value="<c:out value='${requestScope.student.name}' />" class="form-control" required="required">
        </div>
        <div class="form-group">
            <label>Email *</label>
            <input type="email" name="email" value="<c:out value='${requestScope.student.email}' />" class="form-control" >
        </div>

        <fmt:formatDate value='${requestScope.student.dob}' var="formattedDate" type='date' pattern='dd/MM/yyyy'/>

        <div class="form-group">
            <label>Birth Date *</label>
            <input class="form-control" value="<c:out value='${formattedDate}' />" type="text" placeholder="Date of birth" name="dob" id="dob"
                   pattern = "(^(((0[1-9]|1[0-9]|2[0-8])[\/](0[1-9]|1[012]))|((29|30|31)[\/](0[13578]|1[02]))|((29|30)[\/](0[4,6,9]|11)))[\/](19|[2-9][0-9])\d\d$)|(^29[\/]02[\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)" title="Please enter a valid date">
        </div>

        <div class="form-group">
            <label>Gender *</label><br>
            <select name="gender" class="form-control" >
                <option value="Male" >Male</option>
                <option value="Female"  <c:if test="${requestScope.student.gender == 'FEMALE'}"> selected </c:if> >Female</option>
                </select>
            </div>
            <div class="form-group">
                <label>Fields of interest</label>
                <textarea name="interest" id="message" class="form-control" rows="6"><c:out value='${requestScope.student.printFieldsofInterest()}' /></textarea>
            <label style="font-family: verdana;">Note : Please use "," (Without quotes) to separate different fields</label>
        </div>

    </div>
    <div class="col-sm-5">

        <div class="form-group">
            <label>Mobile No. 1</label>
            <input type="text" name="mobile1" value="<c:out value='${requestScope.student.mobileNo1}' />" class="form-control">
        </div>

        <div class="form-group">
            <label>Mobile No. -2</label>
            <input type="number" name="mobile2"  value="<c:out value='${requestScope.student.mobileNo2}' />"  class="form-control">
        </div>

        <div class="form-group">
            <label>Address1 *</label>
            <input type="text" name="address1" value="<c:out value='${requestScope.student.address1}' />" class="form-control" required>
        </div>
        <div class="form-group">
            <label>Address2 </label>
            <input type="text" name="address2" class="form-control" value="<c:out value='${requestScope.student.address2}' />">
        </div>
        <div class="form-group">
            <label>Address3 </label>
            <input type="text" name="address3" value="<c:out value='${requestScope.student.address3}' />" class="form-control" >
        </div>
        <div class="form-group">
            <label>City *</label>
            <input type="text" name="city" value="<c:out value='${requestScope.student.city}' />" class="form-control" required="required">
        </div>

        <div class="form-group">
            <label>Pincode *</label>
            <input type="number" name="pincode" value="<c:out value='${requestScope.student.pincode}' />" class="form-control" required="required">
        </div>
    </div>
</div><!--/.row-->
