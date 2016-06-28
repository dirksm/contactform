<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css"/>
<script>
$(document).ready(function(){
	$('.date').datepicker({
		format: 'mm/dd/yyyy'
	});
});
</script>
<div class="row">
	<div class="col-md-12 text-center">
		<h1>Create New Customer</h1>
	</div>
</div>

<div class="row clearfix"></div>

<div class="row">
	<div class="col-md-12">

		<html:form class="form-horizontal" action='${pageContext.request.contextPath}/customers/create' method='POST' modelAttribute="customerForm">

			<div class="form-group">
				<label class="col-md-4 control-label" for="first_name">First Name:</label>
				<div class="col-md-4">
					<html:input id="first_name" path="firstName" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="last_name">Last Name:</label>
				<div class="col-md-4">
					<html:input id="last_name" path="lastName" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="company">Company:</label>
				<div class="col-md-4">
					<html:input id="company" path="company" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="init_date">Init Date:</label>
				<div class="col-md-4">
					<html:input id="init_date" path="initDate" type="text"
						class="form-control input-md date"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="notes">Notes</label>
				<div class="col-md-4">
					<html:textarea path="notes" class="form-control input-md" id="notes"/>
				</div>
			</div>
			<div class="form-group">
			 <div class="col-md-4"></div>
			  <div class="col-md-4">
			<button role="button" class="btn btn-primary" type='submit'>Add
				Customer</button>
				</div>
			</div>
		</html:form>

	</div>
</div>
