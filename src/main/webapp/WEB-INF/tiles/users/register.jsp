<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <div class="register-box-body">
    <p class="login-box-msg">Register a new membership</p>
    <span style="color: red;">${errMsg}</span>

    <html:form action="${pageContext.request.contextPath}/users/register" method="post" modelAttribute="usersForm">
      <div class="form-group has-feedback">
        <html:input class="form-control" placeholder="Username" path="username" autofocus="autofocus" required="required"/>
        <span style="color: red;"><html:errors path="username" cssClass="err" /></span>
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <html:input class="form-control" placeholder="First Name" path="firstName" required="required" />
        <span style="color: red;"><html:errors path="firstName" cssClass="err" /></span>
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <html:input class="form-control" placeholder="Last Name" path="lastName" required="required" />
        <span style="color: red;"><html:errors path="lastName" cssClass="err" /></span>
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>

      <div class="form-group has-feedback">
        <html:input class="form-control" placeholder="Email" path="email" required="required" type="email" />
        <span style="color: red;"><html:errors path="email" cssClass="err" /></span>
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <html:password class="form-control" placeholder="Password" path="password"  required="required"/>
        <span style="color: red;"><html:errors path="password" cssClass="err" /></span>
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <html:password class="form-control" placeholder="Retype password" path="password2"  required="required"/>
        <span style="color: red;"><html:errors path="password2" cssClass="err" /></span>
        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-8">
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <html:button type="submit" class="btn btn-primary btn-block btn-flat">Register</html:button>
        </div>
        <!-- /.col -->
      </div>
    </html:form>

    <a href="${pageContext.request.contextPath}/" class="text-center">I already have a membership</a>
  </div>
  <!-- /.form-box -->
