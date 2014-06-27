<%@ page contentType="text/html" pageEncoding="UTF-8" session = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<fmt:setLocale value="nl_BE"/>
<!doctype html>
<html lang="nl">
	<head>
		<title>Cultuurhuis - reservatiemandje</title>
		<link rel="stylesheet" href="${contextPath}/styles/default.css"/>
	</head>
	<body>
		<h1>Het Cultuurhuis:reservatiemandje <img src="${contextPath}/images/mandje.png"/></h1>
		
		<nav>
		<p>
			<c:url value="/welkom" var="welkomURL"/><a href="${welkomURL}">Voorstellingen</a>
			<c:url value="/bevestiging" var="bevestigURL"/><a href="${bevestigURL}">Bevestiging reservatie</a>
		</p>
		</nav>
		
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
							<td>&euro;<fmt:formatNumber value="${reservatie.prijs}" type="currency" currencySymbol=""/></td>
							<td>${reservatie.plaatsen}</td>
							<td style ="text-align:center;"><input type="checkbox" name="verwijderNr" value="${reservatie.nummer}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<c:if test="${not empty totaalTeBetalen}">
			<p>Te betalen:${totaalTeBetalen}</p>
		</c:if>
		<c:if test="${not empty fouten}">
		<p>
			<c:forEach var="fout" items="${fouten}">
				<div class="fout">${fout.value}</div>
			</c:forEach>
		</p>
		</c:if>
	</body>
</html>