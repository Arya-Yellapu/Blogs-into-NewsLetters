<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/subscribe" method = 'POST'>
<label>email</label>
<input type="text" name="email">
<br>
<label>Links seperated by a comma if there are multiple links</label>
<input type="text" name="links">
<input type="submit" name="submit" value="subscribe">
</form>
</body>
</html>