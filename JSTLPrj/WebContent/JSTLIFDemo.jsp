<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="i" value="100">
	</c:set>
	The value is ${i}
	<br>
	<c:if test="${i>100 }">
The value is greater than 100
</c:if>
	<c:if test="${i==100 }">
The value is equal to 100<br>
</c:if>
<c:set var="j" value="1">
	</c:set>
<c:choose>
<c:when test="${j==0 }">
The value is zero ${j }<br>
</c:when>
<c:when test="${j<0 }">
The value is negative ${j }<br>
</c:when>
<c:when test="${j>0 }">
The value is positive ${j}<br>
</c:when>
<c:otherwise>
Invalid Number<br>

</c:otherwise>
</c:choose>
</body>
</html>
















