<%@ page contentType="text/html" pageEncoding="UTF-8" session = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<fmt:setLocale value="nl_BE"/>
<!doctype html>
<html lang="nl">
	<head>
		<title>Cultuurhuis - reserveren</title>
		<link rel="stylesheet" href="${contextPath}/styles/default.css"/>
	</head>
	<body>
		<h1>Het Cultuurhuis:reserveren <img src="${contextPath}/images/reserveren.png"/></h1>
		<nav>
		<c:url value="/welkom" var="welkomURL"/>
		<a href="${welkomURL}">Voorstellingen</a>
		</nav>
		<form method="post" action="${url}" id="reserverenform">
			<dl>
				<dt>Voorstelling:<input type="hidden" name="nummer" value="${voorstelling.voorstellingsNr}"></dt>
				<dd>${voorstelling.titel}</dd>
				<dt>Uitvoerders:</dt>
				<dd>${voorstelling.uitvoerders}</dd>
				<dt>Datum:</dt>
				<dd><fmt:formatDate value="${voorstelling.datum}" type="both" dateStyle="short" timeStyle="short"/></dd>
				<dt>Prijs:</dt>
				<dd>&euro;<fmt:formatNumber value="${voorstelling.prijs}" type="currency" currencySymbol=""/></td></dd>
				<dt>Vrije plaatsen:</dt>
				<dd>${voorstelling.vrijePlaatsen}</dd>
				<dt>Plaatsen:</dt>
				<c:choose>
					<c:when test="${not empty gereserveerdePlaatsen}">
						<dd><label><input name="plaatsen" value="${gereserveerdePlaatsen}" autofocus> </label><br>
						<input type="submit" value="Reserveren"></dd>
					</c:when>
					<c:otherwise>
						<dd><label><input name="plaatsen" autofocus> </label><br>
						<input type="submit" value="Reserveren"></dd>
					</c:otherwise>
				</c:choose>
				
			</dl>
		</form>
		<c:if test="${not empty fouten}">
			<c:forEach var="fout" items="${fouten}">
				<div class="fout">${fout.value}</div>
			</c:forEach>
		</c:if>
	</body>
</html>
