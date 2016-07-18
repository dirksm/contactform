<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
$(document).ready(function(){
	$('.profile-user-img').attr('src','https://www.gravatar.com/avatar/'+getHash('${usersForm.email}')); 
});
</script>
<section class="content">
      <div class="row">
        <div class="col-md-3">
		<!-- Profile Image -->
          <div class="box box-primary">
            <div class="box-body box-profile">
              <img class="profile-user-img img-responsive img-circle" alt="User profile picture">
              <p class="text-muted text-center">Image provided by <a href="https://en.gravatar.com">Gravatar</a></p>
			  <br/>
              <h2 class="profile-username text-center">${usersForm.firstName} ${usersForm.lastName}</h2>

              <p class="text-muted text-center">${usersForm.position}</p>
              
              <p class="text-muted text-center">${usersForm.email}</p>
			  <a href="${pageContext.request.contextPath}/users/changePasswd" class="btn btn-primary btn-block"><i class="fa fa-lock"></i>&nbsp;&nbsp;<b>Change Password</b></a><br/>
			  <a href="${pageContext.request.contextPath}/users/editProfile" class="btn btn-primary btn-block"><i class="fa fa-edit"></i>&nbsp;&nbsp;<b>Edit Profile</b></a>
            </div>
        </div>
      </div>
        <div class="col-md-9">
        <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title"><i class="fa fa-user"></i>&nbsp;Edit Profile</h3>&nbsp;&nbsp;<span style="color: red;">${errMsg}</span>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <html:form class="form-horizontal" modelAttribute="usersForm" action="${pageContext.request.contextPath}/users/editProfile" method="post">
                <html:hidden path="username"/>
                  <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">Username:</label>

                    <div class="col-sm-10" style="margin:6px auto;">
                    <em>${usersForm.username}</em>
                     </div>
                  </div>
                  <div class="form-group">
                    <label for="firstName" class="col-sm-2 control-label">First Name:</label>

                    <div class="col-sm-10">
				       <html:input class="form-control" placeholder="First Name" id="firstName" path="firstName" required="required" />
				        <span style="color: red;"><html:errors path="firstName" cssClass="err" /></span>
                     </div>
                  </div>
                  <div class="form-group">
                    <label for="lastName" class="col-sm-2 control-label">Last Name:</label>

                    <div class="col-sm-10">
				       <html:input class="form-control" placeholder="Last Name" id="lastName" path="lastName" required="required" />
				        <span style="color: red;"><html:errors path="lastName" cssClass="err" /></span>
                     </div>
                  </div>
                  <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>

                    <div class="col-sm-10">
						<html:input class="form-control" placeholder="Email" id="email" path="email" required="required" type="email" />
				        <span style="color: red;"><html:errors path="email" cssClass="err" /></span>
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