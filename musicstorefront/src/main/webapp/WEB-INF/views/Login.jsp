<%@include file="Header.jsp" %>
<body>
<div class="container">
<!-- <form action="chkLogin" method="get" role="form" > -->
<form action="perform_login" method="POST">

<div class="form-group">
<label for="username">Enter Email Id</label>
		<input type="email" name="username" required class="form-control"><br>
		<label for="password">Enter Password: </label><input type="password" name="password" required class="form-control"><br>
		
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