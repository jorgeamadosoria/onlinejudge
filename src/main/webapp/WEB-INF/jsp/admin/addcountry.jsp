<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>

<div class="row">
    <div class="col-xs-10">
        <form:form method="post" enctype="multipart/form-data"
                   modelAttribute="country" cssClass="form-horizontal">

            <!-- NAME OF VIEW -->
            <legend>
               <h2> <spring:message code="page.general.admin.header"/>: <spring:message code="page.addcountry.header"/></h2>
            </legend>

            <!-- NAME OF COUNTRY -->
            <div sec:authorize="hasAnyRole('ROLE_USER','ROLE_TEAM')">
                <div class="form-group">
                    <label class="control-label col-xs-4">
                        <spring:message code="page.addcountry.name"/>
                    </label>

                    <div class="col-xs-7">
                        <form:input cssClass="form-control" path="name" size="30"
                                    maxlength="70"/>
                    </div>
                    <div class="error col-xs-7 col-xs-offset-4">
                        <span class="label label-danger"><form:errors path="name"/></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </div>

            <!-- ACRONYM OF COUNTRY -->
            <div sec:authorize="hasAnyRole('ROLE_USER','ROLE_TEAM')">
                <div class="form-group">
                    <label class="control-label col-xs-4">
                        <spring:message code="page.addcountry.zip"/>
                    </label>

                    <div class="col-xs-7">
                        <form:input cssClass="form-control" path="zip" size="30"
                                    maxlength="3"/>
                    </div>
                    <div class="error col-xs-7 col-xs-offset-4">
                        <span class="label label-danger"><form:errors path="zip"/></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </div>


            <!-- TWO LETTER OF COUNTRY -->
            <div sec:authorize="hasAnyRole('ROLE_USER','ROLE_TEAM')">
                <div class="form-group">
                    <label class="control-label col-xs-4">
                        <spring:message code="page.addcountry.twozip"/>
                    </label>

                    <div class="col-xs-7">
                        <form:input cssClass="form-control" path="zip_two" size="30"
                                    maxlength="2"/>
                    </div>
                    <div class="error col-xs-7 col-xs-offset-4">
                        <span class="label label-danger"><form:errors path="zip_two"/></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </div>

            <!-- WEB OF COUNTRY -->
            <div sec:authorize="hasAnyRole('ROLE_USER','ROLE_TEAM')">
                <div class="form-group">
                    <label class="control-label col-xs-4">
                        <spring:message code="page.addcountry.website"/>
                    </label>

                    <div class="col-xs-7">
                        <form:input cssClass="form-control" path="website" size="30"
                                    maxlength="70" placeholder="http://"/>
                    </div>
                    <div class="error col-xs-7 col-xs-offset-4">
                        <span class="label label-danger"><form:errors path="website"/></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </div>

            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="judge.register.submit.value"/>"/> <input
                    class="btn btn-primary" type="reset" name="reset" id="reset"
                    value="<spring:message code="judge.register.reset.value"/>"/>
                <a class="btn btn-primary" href="<c:url value="/admin/managecountries.xhtml"/>"><spring:message
                        code="button.close"/></a>
            </div>
        </form:form>
    </div>
</div>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>