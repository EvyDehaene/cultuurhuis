<%@ page contentType="text/html" pageEncoding="UTF-8" session = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<!doctype html>
<html lang="nl">
	<head>
		<title>Cultuurhuis - reservatiemandje</title>
		<link rel="stylesheet" href="${contextPath}/styles/default.css"/>
	</head>
	<body>
		<h1>Het Cultuurhuis:reservatiemandje <img src="${contextPath}/images/mandje.png"/></h1>
		<c:url value="/welkom" var="welkomURL"/>
		<a href="${welkomURL}">Voorstellingen</a>
		<c:url value="/bevestiging" var="bevestigURL"/>
		<a href="${bevestigURL}">Bevestig reservatie</a>
		<form method="post" action="${url}">
			<table>
				<thead>
					<tr>
						<th>Datum</th>
						<th>Titel</th>
						<th>Uitvoerders</th>
						<th>Prijs</th>
						<th>Plaatsen</th>
						<th><input type="submit" value="Verwijderen"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reservatie" items="${reservaties}">
						<tr>
							<td><fmt:formatDate value="${reservatie.datum}" type="both" dateStyle="short" timeStyle="short"/></td>
							<td>${reservatie.titel}</td>
							<td>${reservatie.uitvoerders}</td>
							<td>&euro;${reservatie.prijs}</td>
							<td>${reservatie.plaatsen}</td>
							<td style ="text-align:center;"><input type="checkbox" name="verwijderNr" value="${reservatie.nummer}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		Te betalen:${totaalTeBetalen}
		
	</body>
</html>