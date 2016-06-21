<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="row">
	<div class="col-md-12 text-center">
		<h1><a role="button" class="btn btn-md btn-primary"
				href='${pageContext.request.contextPath}/customers/create'>Create
				New Customer</a>
		</h1>
	</div>
</div>

<div class="row clearfix"></div>

<div class="row">
	<div class="col-md-12 table-responsive">

		<table class="table table-condensed">
			<thead>
				<tr>
					<th></th>
					<th></th>
					<th>Id</th>
					<th>Company</th>
					<th>Name</th>
					<th>Start Date</th>
				</tr>
			</thead>
			<tbody>
<c:forEach items="${customers}" var="customer">
				<tr>
					<td><a role="button" class="btn btn-sm btn-primary"
						href="${pageContext.request.contextPath}/customers/edit/${customer.id}">Edit</a></td>
					<td><a role="button" class="btn btn-sm btn-danger"
						href="${pageContext.request.contextPath}/customers/delete/${customer.id}">Delete</a></td>
					<td>${customer.id}</td>
					<td>${customer.company}</td>
					<td>${customer.firstName} ${customer.lastName}</td>
					<td><fmt:formatDate value="${customer.initDate}" pattern="MM/dd/yyyy"/> </td>
				</tr>
</c:forEach>			
			</tbody>
		</table>
	</div>

</div>

