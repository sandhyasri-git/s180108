<%@include file="Header.jsp"%> 

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="col-sm-6">
				<div class="thumbnail">
					<img src="/musicstorefront/pics/${user.username}.jpg" alt="User image">

				</div>

			</div>
			<div class="col-sm-6">
				<table class="table table-responsive">
					<tr>
					<td>First Name</td>
					<td>${user.firstname}</td>
					
					</tr>
					<tr>
					<td>Email Id</td>
					<td>${user.emailid}</td>
					</tr>
					<tr>
					<td>Phone</td>
					<td>${user.phone}</td>
					</tr>

	<tr>
	<td><a href="Welcome"><input type ="submit" value="Back"/></a></td>
	</tr>
		</table>

		</div>
		</div>
	</div>
	<div class="col-sm-3"></div>

</div>
<%@ include file="Footer.jsp"%>

