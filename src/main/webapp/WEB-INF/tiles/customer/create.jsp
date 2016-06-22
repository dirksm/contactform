<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
				<label class="col-md-4 control-label" for="first_name">first_name</label>
				<div class="col-md-4">
					<html:input id="first_name" path="firstName" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="last_name">last_name</label>
				<div class="col-md-4">
					<html:input id="last_name" path="lastName" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="company">company</label>
				<div class="col-md-4">
					<html:input id="company" path="company" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="init_date">init_date</label>
				<div class="col-md-4">
					<html:input id="init_date" path="initDate" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="notes">notes</label>
				<div class="col-md-4">
					<html:input id="notes" path="notes" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<button role="button" class="btn btn-primary" type='submit'>Add
				Customer</button>
		</html:form>

	</div>
</div>
