<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><tiles:insertAttribute name="title"/></title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="icon" type="image/x-icon" />
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
<!-- jQuery 2.2.0 -->
<script src="${pageContext.request.contextPath}/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.md5.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
  <!--<link rel="stylesheet" href="css/skins/skin-blue.min.css">-->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/skins/_all-skins.min.css">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <style type="text/css">
  .breadcrumb {
  	text-transform: capitalize;
  }
  </style>
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition ${sessionScope.skin!=null?sessionScope.skin:"skin-blue"} sidebar-mini">
<c:set var="path" value="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}"/>

<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="${pageContext.request.contextPath}/" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><tiles:insertAttribute name="logo-mini"/></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><tiles:insertAttribute name="logo-lg"/> </span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <img class="user-image userImage" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs">${userProfile.firstName} ${userProfile.lastName}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img class="img-circle userImage" alt="User Image">

                <p>
                  ${userProfile.firstName} ${userProfile.lastName} <c:if test="${userProfile.position != null && userProfile.position != ''}">- </c:if> ${userProfile.position}
                  <small>Member since <fmt:formatDate value="${userProfile.created}" pattern="MMM yyyy"/> </small>
                </p>
              </li>
              <!-- Menu Body -->
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="${pageContext.request.contextPath}/users/profile" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="${pageContext.request.contextPath}/logout" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img class="img-circle userImage" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${userProfile.firstName} ${userProfile.lastName}</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <tiles:insertAttribute name="nav"/>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <tiles:insertAttribute name="header"/>
        <small><tiles:insertAttribute name="optDescription"/></small>
      </h1>
      <c:set var="crumbs" value="${fn:split(path, '/')}"/>
      <ol class="breadcrumb">
      <c:forEach items="${crumbs}" var="crumb" varStatus="status">
      	<c:if test="${status.first}">
	      <c:set var="hrefprev" value="http://${'localhost'==pageContext.request.serverName ? "localhost:8080":pageContext.request.serverName}/" />
      	</c:if>
      	<c:choose>
      		<c:when test="${status.last}">
      		<li class="active">
      		</c:when>
      		<c:otherwise>
      		<li>
      		</c:otherwise>
      	</c:choose>
      	<c:set var="href" value="${hrefprev}${crumb}/" />
      	<c:set var="hrefprev" value="${href}" />
      	<a href="${href}">
      	<c:choose>
      		<c:when test="${status.first}">
      	<i class="fa fa-dashboard"></i> Home
      		</c:when>
      		<c:otherwise>
      	${crumb}
      		</c:otherwise>
      	</c:choose>
        </a></li>
      </c:forEach>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
	<tiles:insertAttribute name="content"/>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.0.0
    </div>
    <strong>Copyright &copy; 2010-2016 <a href="http://leewardassociates.com">Leeward Associates</a></strong> All rights
    reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<script>
//Skin switcher
  var current_skin = '${sessionScope.skin!=null?sessionScope.skin:"skin-blue"}';
  $(document).ready(function(){
	  
	  $('#layout-skins-list [data-skin]').click(function(e) {
	    e.preventDefault();
	    var skinName = $(this).data('skin');
	    $('body').removeClass(current_skin);
	    $('body').addClass(skinName);
	    current_skin = skinName;
	    $.ajax({
	    	url: '${pageContext.request.contextPath}/changeSkin/'+skinName,
	    	success: function(result){
	    		console.log("Changed " + result.data);
	    	}
	    });
	  });
	  
	  $('.userImage').each(function(){
		 $(this).attr('src','https://www.gravatar.com/avatar/'+getHash('${userProfile.email}')); 
	  });
  });
  function getHash(email) {
	var em = email.trim().toLowerCase();
	return $.md5(em);
  }
</script>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>
