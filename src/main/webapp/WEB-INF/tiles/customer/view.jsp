<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<section class="invoice">
<div class="row">
<div class="col-xs-12">
  <h2 class="page-header">
    <i class="fa fa-globe"></i> ${customer.company}
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
		${customer.currentAddress.city}, ${customer.currentAddress.longState}  ${customer.currentAddress.zip}
		</address>
		
	</div>
	<div class="col-sm-4 invoice-col"></div>
	<div class="col-sm-4 invoice-col"></div>
</div>
<div class="row">
	<div class="col-xs-12">
		<h2 class="page-header"><i class="fa fa-users"></i> Contacts</h2>
	</div>
</div>

</section>
