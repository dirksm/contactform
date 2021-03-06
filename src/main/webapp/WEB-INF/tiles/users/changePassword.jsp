<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/pwstrength/pwstrength-bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	$('.profile-user-img').attr('src','https://www.gravatar.com/avatar/'+getHash('${usersForm.email}')); 
	$('#password').pwstrength();
	$('#oldPassword').focus();
});
</script>
<section class="content">
      <div class="row">
        <div class="col-md-3">
        <jsp:include page="/WEB-INF/tiles/users/userInfo.jsp"></jsp:include>
      </div>
        <div class="col-md-9">
        <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title"><i class="fa fa-user"></i>&nbsp;Edit Profile</h3>&nbsp;&nbsp;<span style="color: red;">${errMsg}</span>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <html:form class="form-horizontal" modelAttribute="usersForm" action="${pageContext.request.contextPath}/users/changePasswd" method="post">
                <html:hidden path="username"/>
                  <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">Username:</label>

                    <div class="col-sm-10" style="margin:6px auto;">
                    <em>${usersForm.username}</em>
                     </div>
                  </div>
                  <div class="form-group">
                    <label for="oldPassword" class="col-sm-2 control-label">Current Password:</label>

                    <div class="col-sm-10">
				       <html:password class="form-control" placeholder="Current Password" id="oldPassword" path="oldPassword" required="required" />
				        <span style="color: red;"><html:errors path="oldPassword" cssClass="err" /></span>
                     </div>
                  </div>
                  <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">New Password:</label>

                    <div class="col-sm-10">
				       <html:password class="form-control" placeholder="New Password" id="password" path="password" required="required" />
				        <span style="color: red;"><html:errors path="password" cssClass="err" /></span>
                     </div>
                  </div>
                  <div class="form-group">
                    <label for="password2" class="col-sm-2 control-label">New Password (again):</label>

                    <div class="col-sm-10">
				       <html:password class="form-control" placeholder="New Password" id="password2" path="password2" required="required" />
				        <span style="color: red;"><html:errors path="password2" cssClass="err" /></span>
                     </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                      <html:button type="submit" class="btn btn-primary">Submit</html:button>
                    </div>
                  </div>
                </html:form>
            </div>
        </div>
        </div>
      </div>
</section>