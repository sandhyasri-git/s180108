<%@include file="Header.jsp" %>
<body>
<div class="container">
<form action="chkLogin" method="get" role="form" >
<div class="form-group">
<label for="txtEmail">Enter Email Id</label>
		<input type="email" name="txtEmail" required class="form-control"><br>
		<label for="txtPass">Enter Password: </label><input type="password" name="txtPass" required class="form-control"><br>
		
		<input type="submit" value="Login"> <input type="reset"
			value="Cancel">
			
</div>
	</form>
	<div>
	<p>Click
	<span class="caret"></span>
	</p>
	</div>
	</div>
</body>
</html>