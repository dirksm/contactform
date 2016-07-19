<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="${pageContext.request.contextPath}/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/pwstrength/pwstrength-bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	$('#password').pwstrength();
});
</script>

  <div class="register-box-body">
    <p class="login-box-msg">Reset Password</p>
    <span style="color: red;">${errMsg}</span>

    <html:form action="${pageContext.request.contextPath}/users/resetPassword" method="post" modelAttribute="prmForm">
	<html:hidden path="id"/>
	<html:hidden path="key"/>
      <div class="form-group has-feedback">
        <html:password class="form-control" placeholder="New Password" path="password" id="password"  required="required"/>
        <span style="color: red;"><html:errors path="password" cssClass="err" /></span>
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <html:password class="form-control" placeholder="Retype password" path="password2"  required="required"/>
        <span style="color: red;"><html:errors path="password2" cssClass="err" /></span>
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <html:button type="submit" class="btn btn-primary btn-block btn-flat">Reset Password</html:button>

    </html:form>

  </div>