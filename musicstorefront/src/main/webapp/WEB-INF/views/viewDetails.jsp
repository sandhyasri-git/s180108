<%@include file="Header.jsp"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="col-sm-6">
				<div class="thumbnail">
					<img src="/musicstorefront/pics/${product.prodname}.jpg" alt="product image">

				</div>

			</div>
			<div class="col-sm-6">
				<table class="table table-responsive">
					<tr>
					<td>Product Name</td>
					<td>${product.prodname}</td>
					
					</tr>
					<tr>
					<td>Product Price</td>
					<td>${product.price}</td>
					</tr>
					<tr>
					<td>Product quantity</td>
					<td>${product.qty}</td>
					</tr>

	<tr>
	<td><a href="Cart"><input type ="submit" value="AddtoCart"/></a></td>
	</tr>
		</table>

		</div>
		</div>
	</div>
	<div class="col-sm-3"></div>

</div>

<%@include file="Footer.jsp"%>