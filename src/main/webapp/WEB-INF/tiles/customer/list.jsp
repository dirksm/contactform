<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script>
	var btnClicked = false;
	$(document).ready(function(){
		$('.table tbody tr').click(function(evt){
			if($(this).attr('id')!=null && $.isNumeric($(this).attr('id'))) {
				if (btnClicked) {
					btnClicked = false;
				} else {
					location.href = '${pageContext.request.contextPath}/customers/view/'+$(this).attr('id');
				}
			}
		});
	});
	
	function deleteRow(id) {
		btnClicked = true;
		if(confirm('Are you sure you want to delete?')){
			location.href= '${pageContext.request.contextPath}/customers/delete/'+id;
		}
	}
</script>
<style>
.table tbody tr {
	cursor:pointer;
}
</style>
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

		<table class="table table-condensed table-hover">
			<thead>
				<tr>
					<th></th>
					<th>Company</th>
					<th>Name</th>
					<th>Start Date</th>
				</tr>
			</thead>
			<tbody>
<c:forEach items="${customers}" var="customer">
				<tr id="${customer.id}">
					<td><a href="${pageContext.request.contextPath}/customers/edit/${customer.id}" title="Edit Customer" class=" btn btn-xs btn-default text-blue"><i class="fa fa-edit"></i></a>&nbsp;
					<a href="javascript:void(0);" onclick="javascript:deleteRow(${customer.id});" title="Delete Customer" class=" btn btn-xs btn-default text-red"><i class="fa fa-remove"></i></a>
					</td>
					<td>${customer.company}</td>
					<td>${customer.firstName} ${customer.lastName}</td>
					<td><fmt:formatDate value="${customer.initDate}" pattern="MM/dd/yyyy"/> </td>
				</tr>
</c:forEach>			
			</tbody>
		</table>
	</div>

</div>

