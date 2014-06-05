<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<fmt:setLocale value="nl_BE"/>
<!doctype html>
<html lang="nl">
	<head>
		<title>Cultuurhuis - voorstellingen - ${naam}</title>
		<link rel="stylesheet" href="styles/default.css"/>
	</head>
	<body>
		<c:import url="/WEB-INF/JSP/welkom.jsp"/>
		<h2>${naam} voorstellingen</h2>
		<table class="zebra">
			<thead>
				<tr>
					<th>Datum</th>
					<th>Titel</th>
					<th>Uitvoerders</th>
					<th>Prijs</th>
					<th>Vrije plaatsen</th>
					<th>Reserveren</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="voorstelling" items="${voorstellingen}">
					<tr>
						<td><fmt:formatDate value="${voorstelling.datum}" type="both" dateStyle="short" timeStyle="short"/></td>
						<td>${voorstelling.titel}</td>
						<td>${voorstelling.uitvoerders}</td>
						<td>&euro;<fmt:formatNumber value="${voorstelling.prijs}" type="currency" currencySymbol=""/></td>
						<td>${voorstelling.vrijePlaatsen}</td>
						<td> 
							<c:if test="${voorstelling.vrijePlaatsen>0}">
								<c:url value="/voorstelling" var="voorstellingURL">
									<c:param name = "nummer" value="${voorstelling.voorstellingsNr}"/>
									
								</c:url>
								<a href="${voorstellingURL}">Reserveren</a>
								
							</c:if>	
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
