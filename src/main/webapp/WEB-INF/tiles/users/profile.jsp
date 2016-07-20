<script>
$(document).ready(function(){
	$('.profile-user-img').attr('src','https://www.gravatar.com/avatar/'+getHash('${userProfile.email}')); 
});
</script>
<section class="content">
      <div class="row">
        <div class="col-md-3">
        <jsp:include page="/WEB-INF/tiles/users/userInfo.jsp"></jsp:include>
      </div>
        <div class="col-md-9">
        </div>
      </div>
</section>