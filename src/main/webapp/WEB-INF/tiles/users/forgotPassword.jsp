<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
$(document).ready(function(){
	$('#email').focus();
});
</script>
  <div class="register-box-body">
    <p class="login-box-msg">Forgot password</p>
    <span style="color: red;">${errMsg}</span>
    <html:form action="${pageContext.request.contextPath}/users/forgotPassword" method="post" modelAttribute="usersForm">

      <div class="form-group has-feedback">
        <html:input class="form-control" placeholder="Email" path="email" required="required" type="email" />
        <span style="color: red;"><html:errors path="email" cssClass="err" /></span>
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
		<html:button type="submit" class="btn btn-primary btn-block btn-flat">Get a new password</html:button>
      </div>
      
      
    </html:form>

  </div>
    