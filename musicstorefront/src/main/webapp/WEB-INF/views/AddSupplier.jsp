<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="AdminHome.jsp"%>
<html>
<head>
<title>supplier Page</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: blue;
	background-color: red;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>
</head>
<body>
<center>
	<h2>Add a supplier</h2>

	<form:form action="addsup" commandName="supplier">
		<table>
			<c:if test="${!empty supplier.brand}">
				<tr>
					<td><form:label path="supplierid">
				ID
			</form:label></td>
					<td><form:input path="supplierid" readonly="true" size="8"
							disabled="true" /> <form:hidden path="supplierid" /></td>
				</tr>
			</c:if>
			<tr>
				<td><form:label path="brand">
				Brand
			</form:label></td>
				<td><form:input path="brand" /></td>
			</tr>
			<tr>
				<td><form:label path="address">
			Supplier_Address
			</form:label></td>
				<td><form:input path="address" /></td>
			</tr>
			<tr>
				<td><form:label path="email">
			Supplier_Email
			</form:label></td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td><form:label path="phone">
			Supplier_Phone
			</form:label></td>
				<td><form:input path="phone" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:if test="${!empty supplier.brand}">
						<input type="submit" value="Edit supplier" />
					</c:if> <c:if test="${empty supplier.brand}">
						<input type="submit" value="Add supplier" />
					</c:if></td>
			</tr>
		</table>
	</form:form>
	<br>
	<h3>suppliers List</h3>
	<c:if test="${!empty supplierList}">
		<table class="tg">
			<tr>
				<th width="80">supplier ID</th>
				<th width="120">Brand Name</th>
				<th width="120">supplier Address</th>
				<th width="120">supplier Email</th>
				<th width="120">supplier Phone</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${supplierList}" var="supplier">
				<tr>
					<td>${supplier.supplierid}</td>
					<td>${supplier.brand}</td>
					<td>${supplier.address}</td>
					<td>${supplier.email}</td>
					<td>${supplier.phone}</td>
					<td><a
						href="<c:url value='/editsupplier${supplier.supplierid}' />">Edit</a></td>
					<td><a
						href="<c:url value='/deletesupplier/${supplier.supplierid}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>


