<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
	<div class="col-md-12 text-center">
		<h1>New Address</h1>
	</div>
</div>

<div class="row clearfix"></div>

<div class="row">
	<div class="col-md-12">

		<html:form class="form-horizontal" action='${pageContext.request.contextPath}/addresses/create' method='POST' modelAttribute="addressForm">
		<html:hidden path="customerId"/>
			<div class="form-group">
				<label class="col-md-4 control-label" for="address_1">address_1</label>
				<div class="col-md-4">
					<html:input id="address_1" path="address1" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="address_2">address_2</label>
				<div class="col-md-4">
					<html:input id="address_2" path="address2" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="city">city</label>
				<div class="col-md-4">
					<html:input id="city" path="city" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="state">state</label>
				<div class="col-md-4">
					<html:select path="state" id="state" class="form-control input-md">
					<html:option value="">--- Select State ---</html:option>
					<c:forEach items="${stateList}" var="state">
					<option value="${state.id}">${state.abbrev} - ${state.name}</option>
					</c:forEach>
					</html:select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="zip">zip</label>
				<div class="col-md-4">
					<html:input id="zip" path="zip" type="text"
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
				Address Record</button>
		</html:form>

	</div>
</div>