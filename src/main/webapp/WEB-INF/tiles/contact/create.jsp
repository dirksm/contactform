<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
	<div class="col-md-12 text-center">
		<h1>New Contact</h1>
	</div>
</div>

<div class="row clearfix"></div>

<div class="row">
	<div class="col-md-12">

		<html:form class="form-horizontal" action='${pageContext.request.contextPath}/contacts/create' method='POST' modelAttribute="contactForm">

			<div class="form-group">
				<label class="col-md-4 control-label" for="customer_id">Customer:</label>
				<div class="col-md-4">
					<html:select path="customerId" id="customer_id" class="form-control input-md">
					<html:option value="">--- Select Customer ---</html:option>
					<c:forEach items="${customers}" var="customer">
					<option value="${customer.id}" ${(contactForm.customerId == customer.id)?"selected=\"selected\"":""}>${customer.company} - ${customer.firstName} ${customer.lastName}</option>
					</c:forEach>
					</html:select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="status_cd">Status:</label>
				<div class="col-md-4">
					<html:select id="status_cd" path="statusCd" class="form-control input-md">
					<html:option value="">--- Select Status ---</html:option>
					<c:forEach items="${statuses}" var="status">
					<option value="${status.id}" ${(contactForm.statusCd == status.id)?"selected=\"selected\"":""}>${status.description}</option>
					</c:forEach>
					</html:select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">email</label>
				<div class="col-md-4">
					<html:input id="email" path="email" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="website">website</label>
				<div class="col-md-4">
					<html:input id="website" path="website" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="salutation">salutation</label>
				<div class="col-md-4">
					<html:input id="salutation" path="salutation" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="contact_name">contact_name</label>
				<div class="col-md-4">
					<html:input id="contact_name" path="contactName" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="title">title</label>
				<div class="col-md-4">
					<html:input id="title" path="title" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="dept">dept</label>
				<div class="col-md-4">
					<html:input id="dept" path="dept" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="work_phone">work_phone</label>
				<div class="col-md-4">
					<html:input id="work_phone" path="workPhone" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="cell_phone">cell_phone</label>
				<div class="col-md-4">
					<html:input id="cell_phone" path="cellPhone" type="text"
						class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="fax">fax</label>
				<div class="col-md-4">
					<html:input id="fax" path="fax" type="text"
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
				Contact Record</button>
		</html:form>

	</div>
</div>
