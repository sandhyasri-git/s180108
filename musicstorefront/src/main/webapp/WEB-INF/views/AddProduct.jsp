<%@include file="AdminHome.jsp"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Add product</title>
<style>
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
		<form:form method="POST" action="addprod" commandName="product"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td><h2>Add Product</h2></td>
				</tr>
				<c:if test="${!empty product.prodname}">
					<tr>
						<td><form:label path="prodid">ID</form:label></td>
						<td><form:input path="prodid" readonly="true" size="8"
								disabled="true" /> <form:hidden path="prodid" /></td>
					</tr>
				</c:if>

				<tr>
					<td><form:label path="prodname">Product Name</form:label></td>
					<td><form:input path="prodname" /></td>
					<td><span><form:errors path="prodname" cssStyle="color: #ff0000;"/></span></td>
				</tr>

				<tr>
					<td><form:label path="price">Price</form:label></td>
					<td><form:input path="price" /></td>
					<td><span><form:errors path="price" cssStyle="color: #ff0000;"/></span></td>
				</tr>

				<tr>
					<td><form:label path="quantity">Quantity</form:label></td>
					<td><form:input path="quantity" /></td>
					<td><span><form:errors path="quantity" cssStyle="color: #ff0000;"/></span></td>
				</tr>


				<!--  select:dropdown, items:collection ,itemValue: name to item-->
				<tr>
					<td>Category</td>
					<td><form:select path="category.catid" items="${categoryList}"
							itemValue="category.catid" itemLabel="category.catid">
						</form:select></td>
				</tr>

				<tr>
					<td>Supplier</td>
					<td><form:select path="supplierid" items="${supplierList}"
							itemValue="supplierid" itemLabel="supplierid">
						</form:select></td>
				</tr>

				<tr>
					<td><form:label path="img">Select Image:</form:label></td>

					<td><form:input type="file" path="img" /></td>
				</tr>
				<tr>
					<c:if test="${empty product.prodname}">
						<td style="text-align: center;"><input type="submit"
							value="Submit" style="color: green; font-size: 15pt;" /></td>
						<td><input type="reset" value="Cancel"
							style="color: red; font-size: 15pt" /></td>
					</c:if>

					<c:if test="${!empty product.prodname}">
						<td><input type="submit" value="Edit Product" /></td>
					</c:if>

				</tr>
			</table>
		</form:form>

		<h2>Product List</h2>

		<!-- core tags ,if or choose  ,$-expression language -->

		<c:if test="${!empty productList}">
			<table class="tg">
				<tr>
					<th>Product Id</th>
					<th>ProductName</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Category Id</th>
					<th>Supplier Id</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>

				<c:forEach items="${productList}" var="product">
					<tr>
						<td>${product.prodid}</td>
						<td>${product.prodname}</td>
						<td>${product.price}</td>
						<td>${product.quantity}</td>
						<td>${product.category.catid}</td>
						<td>${product.supplier.supplierid}</td>
						<td><a
							href="<c:url value='/editproducts${product.prodid}'/>">Edit</a></td>
						<td><a
							href="<c:url value='/deleteproduct${product.prodid}'/>">Delete</a></td>
					</tr>
				</c:forEach>


			</table>
		</c:if>
		<a href="adminhome">Back</a>
</body>
</html>