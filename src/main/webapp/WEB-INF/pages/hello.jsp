<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<h1>${message}</h1>
	<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>