<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<div class="col-md-12 text-center">
		<h1>
			<a role="button" class="btn btn-md btn-primary" href='${pageContext.request.contextPath}/addresses/create'>Create
				New Address</a>
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
					<th>id</th>
					<th>Address</th>
					<th>Address 2</th>
					<th>City</th>
					<th>State</th>
					<th>Zip</th>
					<th>Notes</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${addresses}" var="address">
				<tr>
					<td><a role="button" class="btn btn-sm btn-primary"
						href="${pageContext.request.contextPath}/addresses/edit/${address.id}">Edit</a></td>
					<td><a role="button" class="btn btn-sm btn-danger"
						href="${pageContext.request.contextPath}/addresses/delete/${address.id}">Delete</a></td>
					<td>${address.id}</td>
					<td>${address.address1}</td>
					<td>${address.address2}</td>
					<td>${address.city}</td>
					<td>${address.shortState}</td>
					<td>${address.zip}</td>
					<td>${address.notes}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

</div>
