<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script>
$(document).ready(function(){
	$('button.btn.btn-box-tool').click(function(){
		var title = $(this).attr('data-original-title');
		var id = $(this).attr('id');
		if ("Edit" === title) {
			location.href = '${pageContext.request.contextPath}/contacts/edit/'+id;
		} else {
			if(confirm('Are you sure you want to delete this contact?')){
				location.href = '${pageContext.request.contextPath}/contacts/delete/'+id;
			}
		}

	});
});
</script>
<style>
	a.box {
		color: inherit;
	}
</style>
<section class="invoice">
<div class="row">
<div class="col-xs-12">
  <h2 class="page-header">
    <i class="fa fa-globe"></i> ${customer.company} - <small style="display:inline;"><a href="${pageContext.request.contextPath}/customers/edit/${customer.id}">Edit Customer</a> | <a href="${pageContext.request.contextPath}/addresses/create/${customer.id}">Change Address</a></small>
    <small class="pull-right">Date: <fmt:formatDate value="${customer.initDate}" pattern="MM/dd/yyyy"/> </small>
  </h2>
</div>
  <!-- /.col -->
</div>
<div class="row invoice-info">
	<div class="col-sm-4 invoice-col">
		<p class="lead">Primary Contact:</p>
		<address>
		<strong>${customer.firstName} ${customer.lastName}</strong><br>
		${customer.currentAddress.address1}
		<c:if test="${customer.currentAddress.address2!=null && customer.currentAddress.address2!=''}">
		<br/>${customer.currentAddress.address2}
		</c:if>
		<br/>
		${customer.currentAddress.city}${customer.currentAddress.city!=null&&customer.currentAddress.city!=''?", ":""} ${customer.currentAddress.longState}  ${customer.currentAddress.zip}
		</address>
		
	</div>
	<div class="col-sm-4 invoice-col"></div>
	<div class="col-sm-4 invoice-col"></div>
</div>
<div class="row">
	<div class="col-xs-12">
		<h2 class="page-header"><i class="fa fa-users"></i> Contacts  - <small style="display:inline;"><a href="${pageContext.request.contextPath}/contacts/create/${customer.id}">Create New Contact</a></small></h2>
	</div>
	<div class="col-xs-12">
	<c:forEach items="${customer.contacts}" var="contact" varStatus="status">
	<div class="col-sm-4 invoice-col">
		<div class="box box-solid box-default">
		  <div class="box-header with-border">
		    <h3 class="box-title">${contact.status}</h3>
			  <div class="box-tools pull-right">
	            <button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" data-original-title="Edit" id="${contact.id}">
	              <i class="fa fa-edit"></i></button>
	            <button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" data-original-title="Remove" id="${contact.id}">
	              <i class="fa fa-times"></i></button>
	          </div>
		  </div><!-- /.box-header -->
		  <div class="box-body">
		    ${contact.contactName} - ${contact.title}<br/>
							${contact.email}<br/>
							${contact.website}<br/><br/>
							
			Dept: ${contact.dept}<br/>
			Work: ${contact.workPhone}<br/>
			Cell: ${contact.cellPhone}<br/>
			Fax: ${contact.fax}<br/>
		  </div><!-- /.box-body -->
		</div><!-- /.box -->
	</div>
	<c:set var="lastcount" value="${status.count}"/>
	</c:forEach>
	<c:if test="${lastcount%3!=0}">
	<c:forEach begin="1" end="${(3-lastcount%3)}" varStatus="status">
	<div class="col-sm-4 invoice-col"></div>
	</c:forEach>
	</c:if>
	</div>
</div>
<c:if test="${fn:length(customer.previousAddress) > 0}">
<div class="row">
	<div class="col-xs-12">
		<h2 class="page-header"><i class="fa fa-users"></i> Address History</h2>
	</div>
	<div class="col-xs-12">
	<c:forEach items="${customer.previousAddress}" var="address" varStatus="status">
	<div class="col-sm-4 invoice-col">
		<div class="box box-solid box-default">
		  <div class="box-header with-border">
		    <h3 class="box-title">From <fmt:formatDate value="${address.dateAddressFrom}" pattern="MM/dd/yyyy"/> to <fmt:formatDate value="${address.dateAddressTo}" pattern="MM/dd/yyyy"/></h3>
		  </div><!-- /.box-header -->
		  <div class="box-body">
			<address>
			${address.address1}
			<c:if test="${address.address2!=null && address.address2!=''}">
			<br/>${address.address2}
			</c:if>
			<br/>
			${address.city}${address.city!=null&&address.city!=''?", ":""} ${address.longState}  ${address.zip}
			</address>
		  </div><!-- /.box-body -->
		</div><!-- /.box -->
	</div>
	<c:set var="lastcount" value="${status.count}"/>
	</c:forEach>
	<c:if test="${lastcount%3!=0}">
	<c:forEach begin="1" end="${(3-lastcount%3)}" varStatus="status">
	<div class="col-sm-4 invoice-col"></div>
	</c:forEach>
	</c:if>
	</div>
</div>
</c:if>
</section>
