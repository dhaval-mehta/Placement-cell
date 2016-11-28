<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="academic row wow fadeInDown animated-item contact-wrap">
    <div><h2 class="center" style="margin-bottom: 30px"> Academical Details </h2> </div>

    <div class="col-sm-5 col-sm-offset-1">
        <div class="form-group">
            <label>SSC Percentage*</label>
            <input type="text" name="ssc_percentege" value="<c:out value='${requestScope.academic_Info.ssc_percentage}' />" class="form-control" >
        </div>
    </div>
    <div class="col-sm-5 col-sm-offset">
        <div class="form-group">
            <label>SSC board*</label>
            <input type="text" name="ssc_board" value="<c:out value='${requestScope.academic_Info.ssc_board}' />" class="form-control" >
        </div>
    </div>
    <div class="col-sm-5 col-sm-offset-1">
        <div class="form-group">
            <label>D2d Student*</label><br>
            <select name="is_d2d" class="form-control" >
                <option value="true" >Yes</option>
                <option value="false"  <c:if test="${!requestScope.academic_Info.d2d}"> selected </c:if> >No</option>
                </select>
            </div>
        </div>
        <div class="col-sm-5">

            <div class="form-group">
                <label>Diploma CPI*</label>
                <input type="text" name="diploma_cpi" value="<c:out value='${requestScope.academic_Info.diploma_cpi}' />" class="form-control" >
        </div>
    </div>
    <div class="col-sm-5 col-sm-offset-1">
        <div class="form-group">
            <label>HSC Percentage*</label>
            <input type="text" name="hsc_percentage" value="<c:out value='${requestScope.academic_Info.hsc_percentage}' />" class="form-control" >
        </div>
    </div>
    <div class="col-sm-5 col-sm-offset">
        <div class="form-group">
            <label>HSC Board*</label>
            <input type="text" name="hsc_board" value="<c:out value='${requestScope.academic_Info.hsc_board}' />" class="form-control" >
        </div>
    </div>
</div>


<div class="row wow fadeInDown animated-item contact-wrap">

    <div class="col-sm-5 col-sm-offset-1">
        <div class="form-group">
            <label>sem-1*</label>
            <input type="text" name="btech_sem1_spi" value="<c:out value='${requestScope.academic_Info.btech_sem1_spi}' />" class="form-control" >
        </div>
        <div class="form-group">
            <label>sem-2*</label>
            <input type="text" name="btech_sem2_spi" value="<c:out value='${requestScope.academic_Info.btech_sem2_spi}' />" class="form-control" >
        </div>
        <div class="form-group">
            <label>sem-3*</label>
            <input type="text" name="btech_sem3_spi" value="<c:out value='${requestScope.academic_Info.btech_sem3_spi}' />" class="form-control" >
        </div>
        <div class="form-group">
            <label>sem-4*</label>
            <input type="text" name="btech_sem4_spi" value="<c:out value='${requestScope.academic_Info.btech_sem4_spi}' />" class="form-control" >
        </div>
    </div>
    <div class="col-sm-5">
        <div class="form-group">
            <label>sem-5*</label>
            <input type="text" name="btech_sem5_spi" value="<c:out value='${requestScope.academic_Info.btech_sem5_spi}' />" class="form-control" >
        </div>
        <div class="form-group">
            <label>sem-6*</label>
            <input type="text" name="btech_sem6_spi" value="<c:out value='${requestScope.academic_Info.btech_sem6_spi}' />" class="form-control" >
        </div>
        <div class="form-group">
            <label>sem-7*</label>
            <input type="text" name="btech_sem7_spi" value="<c:out value='${requestScope.academic_Info.btech_sem7_spi}' />" class="form-control" >
        </div>
        <div class="form-group">
            <label>sem-8*</label>
            <input type="text" name="btech_sem8_spi"  value="<c:out value='${requestScope.academic_Info.btech_sem8_spi}' />" class="form-control" >
        </div>
    </div>
</div>


<c:if test="${sessionScope.userType = 'student'}">
    <script>
        $('#academic').find('input, textarea, button, select').attr('disabled', 'disabled');
    </script>
</c:if>
