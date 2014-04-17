<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Insert title here</title>
	</head>
	<body>
		<h1>Het Cultuurhuis:voorstellingen</h1>
		<h2>Genres</h2>
		<c:forEach var="genre" items="${genres}">
			<c:url value="/voorstellingen/${genre}" var="genreURL"/>
			<a href="${genreURL}">${genre}</a>
		</c:forEach>
	</body>
</html>