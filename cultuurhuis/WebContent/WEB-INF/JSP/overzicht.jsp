<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<fmt:setLocale value="nl_BE"/>
<!doctype html>
<html lang="nl">
	<head>
		<title>Cultuurhuis - overzicht</title>
		<link rel="stylesheet" href="${contextPath}/styles/default.css"/>
	</head>
	<body>
		<h1>Het Cultuurhuis:overzicht<img src="${contextPath}/images/bevestig.png"/></h1>
		<nav><c:url value="/welkom" var="welkomURL"/><a href="${welkomURL}">Voorstellingen</a></nav>
		<c:if test="${not empty gelukt}">
			<h2>Gelukte reserveringen</h2>
			<table class="zebra">
				<thead>
					<tr>
						<th>Datum</th>
						<th>Titel</th>
						<th>Uitvoerders</th>
						<th>Prijs(&euro;)</th>
						<th>Plaatsen</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reservatie" items="${gelukt}">
						<tr>
							<td><fmt:formatDate value="${reservatie.datum}" type="both" dateStyle="short" timeStyle="short"/></td>
							<td>${reservatie.titel}</td>
							<td>${reservatie.uitvoerders}</td>
							<td>${reservatie.prijs}</td>
							<td>${reservatie.plaatsen}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${not empty mislukt}">
			<h2>Mislukte reserveringen</h2>
			<table class="zebra">
				<thead>
					<tr>
						<th>Datum</th>
						<th>Titel</th>
						<th>Uitvoerders</th>
						<th>Prijs(&euro;)</th>
						<th>Plaatsen</th>
						<th>Vrije plaatsen</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reservatie" items="${mislukt}">
						<tr>
							<td><fmt:formatDate value="${reservatie.datum}" type="both" dateStyle="short" timeStyle="short"/></td>
							<td>${reservatie.titel}</td>
							<td>${reservatie.uitvoerders}</td>
							<td><fmt:formatNumber value="${reservatie.prijs}" type="currency"/></td>
							<td>${reservatie.plaatsen}</td>
							<td>${reservatie.vrijePlaatsen}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</body>
</html>