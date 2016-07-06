<script>
$(document).ready(function(){
	$('.profile-user-img').attr('src','https://www.gravatar.com/avatar/'+getHash('${userProfile.email}')); 
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
              <h2 class="profile-username text-center">${user.firstName} ${user.lastName}</h2>

              <p class="text-muted text-center">${user.position}</p>
              
              <p class="text-muted text-center">${user.email}</p>
			  <a href="#" class="btn btn-primary btn-block"><i class="fa fa-lock"></i>&nbsp;&nbsp;<b>Change Password</b></a><br/>
			  <a href="${pageContext.request.contextPath}/users/editProfile" class="btn btn-primary btn-block"><i class="fa fa-edit"></i>&nbsp;&nbsp;<b>Edit Profile</b></a>
            </div>
        </div>
      </div>
        <div class="col-md-9">
        </div>
      </div>
</section>