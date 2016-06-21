<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<div class="row">
    <div class="col-md-3 col-sm-6 col-xs-12">
    <a href="${pageContext.request.contextPath}/customers/" title="View Customer Information">
		<div class="info-box">
		  <!-- Apply any bg-* class to to the icon to color it -->
		  <span class="info-box-icon bg-blue"><i class="fa fa-users"></i></span>
		  <div class="info-box-content">
		    <span class="info-box-text">Customers</span>
		    <span class="info-box-number"><fmt:formatNumber value="${customerCount}"/></span>
		  </div><!-- /.info-box-content -->
		</div><!-- /.info-box -->
	</a>
    </div><!-- /.col -->
    <div class="col-md-3 col-sm-6 col-xs-12">
    <a href="${pageContext.request.contextPath}/contacts/" title="View Contact Information">
      <div class="info-box">
        <span class="info-box-icon bg-green"><i class="fa fa-users"></i></span>
        <div class="info-box-content">
          <span class="info-box-text">Contacts</span>
          <span class="info-box-number"><fmt:formatNumber value="${contactCount}"/></span>
        </div><!-- /.info-box-content -->
      </div><!-- /.info-box -->
    </a>
    </div><!-- /.col -->
    <div class="col-md-3 col-sm-6 col-xs-12">
    <a href="${pageContext.request.contextPath}/addresses/" title="View Address Information">
      <div class="info-box">
        <span class="info-box-icon bg-yellow"><i class="fa fa-envelope"></i></span>
        <div class="info-box-content">
          <span class="info-box-text">Addresses</span>
          <span class="info-box-number"><fmt:formatNumber value="${addressCount}"/></span>
        </div><!-- /.info-box-content -->
      </div><!-- /.info-box -->
    </a>
    </div><!-- /.col -->
  </div>