<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<!-- Profile Image -->
		<!-- Included page -->
          <div class="box box-primary">
            <div class="box-body box-profile">
              <img class="profile-user-img img-responsive img-circle" alt="User profile picture">
              <p class="text-muted text-center">Image provided by <a href="https://en.gravatar.com">Gravatar</a></p>
			  <br/>
              <h2 class="profile-username text-center">${user.firstName} ${user.lastName}</h2>

              <p class="text-muted text-center">${user.position}<br/>
              ${user.email}<br/>
              </p>
              <p class="text-muted text-center">Last Logged In: <fmt:formatDate value="${user.lastLogin}" pattern="MM/dd/yyyy hh:mm a"/> </p>

			  <a href="${pageContext.request.contextPath}/users/changePasswd" class="btn btn-primary btn-block"><i class="fa fa-lock"></i>&nbsp;&nbsp;<b>Change Password</b></a><br/>
			  <a href="${pageContext.request.contextPath}/users/editProfile" class="btn btn-primary btn-block"><i class="fa fa-edit"></i>&nbsp;&nbsp;<b>Edit Profile</b></a>
            </div>
        </div>
