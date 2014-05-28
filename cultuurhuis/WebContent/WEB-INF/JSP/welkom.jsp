<%@ page contentType="text/html" pageEncoding="UTF-8" session = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="nl">
	<head>
		<title>Cultuurhuis - voorstellingen</title>
		<link rel="stylesheet" href="styles/default.css"/>
	</head>
	<body>
		<h1>Het Cultuurhuis:voorstellingen <img src="images/voorstellingen.png"/></h1>
		<h2>Genres</h2>
		
		<c:forEach var="genre" items="${genres}">
			<c:url value="/voorstellingen" var="voorstellingenURL">
				<c:param name = "nummer" value="${genre.genreNr}"/>
				<c:param name="naam" value="${genre.naam}"/>
			</c:url>
			<a href="${voorstellingenURL}">${genre.naam}</a>
		</c:forEach>
		
	</body>
</html>
