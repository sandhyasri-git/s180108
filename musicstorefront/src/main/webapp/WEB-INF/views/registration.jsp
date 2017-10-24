<%@include file="Header.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<%-- <form action="Register" method="post">
		Enter User Name: <input type="text" name="txtUser" required><br>
		Enter Password: <input type="password" name="txtPass" required><br>
		Enter Email Id <input type="email" name="txtEmail" required><br>
		Enter Phone Number: <input type="number" name="txtPh" required><br>
		<input type="submit" value="Register"> <input type="reset"
			value="Cancel">

	</form> --%>
	
	<body>
	<center>
		<form:form method="POST" action="addus" commandName="user"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td><h2>Register Here</h2></td>
				</tr>
				<tr>
						<td><form:label path="userid">ID</form:label></td>
						<td><form:input path="userid" readonly="true" size="8"
								disabled="true" /> <form:hidden path="userid" /></td>
					</tr>
				<tr>
					<td><form:label path="username">User Name</form:label></td>
					<td><form:input path="username" /></td>
					<td><span><form:errors path="username" cssStyle="color: #ff0000;"/></span></td>
				</tr>

				<tr>
					<td><form:label path="firstname">First Name</form:label></td>
					<td><form:input path="firstname" /></td>
				</tr>

				<tr>
					<td><form:label path="lastname">Last Name</form:label></td>
					<td><form:input path="lastname" /></td>
				</tr>

				<tr>
					<td><form:label path="emailid">Email Id</form:label></td>
					<td><form:input path="emailid" /></td>
					<td><span><form:errors path="emailid" cssStyle="color: #ff0000;"/></span></td>
				</tr>
				<tr>
					<td><form:label path="password">Password</form:label></td>
					<td><form:input path="password" type="password"/></td>
					<td><span><form:errors path="password" cssStyle="color: #ff0000;"/></span>
				</tr>
				<tr>
					<td><form:label path="cpassword">Confirm Password</form:label></td>
					<td><form:input path="cpassword" type="password" /></td>
					<td><span><form:errors path="cpassword" cssStyle="color: #ff0000;"/></span>
				</tr>
				<tr>
					<td><form:label path="phone">Phone </form:label></td>
					<td><form:input path="phone" /></td>
					<td><span><form:errors path="phone" cssStyle="color: #ff0000;"/></span>
				</tr>
				<tr>
					<td><form:label path="img">Select Image:</form:label></td>

					<td><form:input type="file" path="img" /></td>
				</tr>
				<tr>
					<td style="text-align: center;"><input type="submit"
							value="Submit" style="font-size: 15pt;" /></td>
						<td><input type="reset" value="Cancel"
							style="font-size: 15pt" /></td>
				</tr>
				</table>
				<form:errors path="cpassword" cssClass="error message" cssStyle="width: 900px"/>
        <c:if test="${not empty errMsg}">
            <h4 class="error message" style="width: 900px;color:red;">${errMsg}</h4>
        </c:if>
		</form:form>

	
</body>
</html>