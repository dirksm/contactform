<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<div class="col-md-12 text-center">
		<h1><a role="button" class="btn btn-md btn-primary"
				href='${pageContext.request.contextPath}/contacts/create'>Create New Contact</a>
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
					<th>Customer</th>
					<th>Status</th>
					<th>Contact</th>
					<th>Dept</th>
					<th>Phone Numbers</th>
					<th>Notes</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${contacts}" var="contact">
				<tr>
					<td><a role="button" class="btn btn-sm btn-primary"
						href="${pageContext.request.contextPath}/contacts/edit/${contact.id}">Edit</a></td>
					<td><a role="button" class="btn btn-sm btn-danger"
						href="${pageContext.request.contextPath}/contacts/delete/${contact.id}">Delete</a></td>
					<td>${contact.id}</td>
					<td>${contact.companyName}</td>
					<td>${contact.status}</td>
					<td>
							${contact.contactName} - ${contact.title}<br/>
							${contact.email}<br/>
							${contact.website}<br/>
					</td>
					<td>${contact.dept}</td>
					<td>
							Work: ${contact.workPhone}<br/>
							Cell: ${contact.cellPhone}<br/>
							Fax: ${contact.fax}<br/>
					</td>
					<td>${contact.notes}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

</div>

