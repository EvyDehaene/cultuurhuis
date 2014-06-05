<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<!doctype html>
<html lang="nl">
	<head>
		<title>Cultuurhuis - bevestiging reservaties</title>
		<link rel="stylesheet" href="${contextPath}/styles/default.css"/>
	</head>
	<body>
		<h1>Het Cultuurhuis:bevestiging reservaties<img src="${contextPath}/images/bevestig.png"/></h1>
		<c:url value="/welkom" var="welkomURL"/>
		<a href="${welkomURL}">Voorstellingen</a>
		<c:url value="/reservatiemandje" var="reservatiemandjeURL"/>
		<a href="${reservatiemandjeURL}">Reservatiemandje</a>
		<h2>Stap 1:wie ben je?</h2>
		<form method="post" action="${url }" id="bestaandegebruikerform">
			<label>Gebruikersnaam:<br><input type = "text" name="gebruikersnaam" autofocus/></label><br>
			<label>Paswoord:<br><input type="password" name="paswoord"/></label><br>
			<c:choose>
				<c:when test="${not empty klant}">
					<input type="submit" value="Zoek me op" id="zoekknop" disabled/>
				</c:when>
				<c:otherwise>
					<input type="submit" value="Zoek me op" id="zoekknop"/>
				</c:otherwise>
			</c:choose>
<!-- 			<input type="submit" value="Zoek me op" id="zoekknop"/> -->
		</form>
		<c:choose>
				<c:when test="${not empty klant}">
					<a href="${contextPath}/nieuweklant"><button type="button" disabled>Ik ben nieuw</button></a>
				</c:when>
				<c:otherwise>
					<a href="${contextPath}/nieuweklant"><button type="button">Ik ben nieuw</button></a>
				</c:otherwise>
			</c:choose>
<!-- 		<a href="/nieuweklant"><button type="button">Ik ben nieuw</button></a> -->
		<c:if test="${not empty klant}">
			<br>${klant.voornaam} ${klant.familienaam} ${klant.straat} ${klant.huisNr} ${klant.postcode} ${klant.gemeente}
		</c:if>
		<h2>Stap 2:Bevestigen</h2>
			<c:choose>
				<c:when test="${not empty klant}">
					<a href="${contextPath}/overzicht"><button type="button">Bevestigen</button></a>
				</c:when>
				<c:otherwise>
					<a href="${contextPath}/overzicht"><button type="button" disabled>Bevestigen</button></a>
				</c:otherwise>
			</c:choose>
<!-- 		<button type="button">Bevestigen</button> -->
			<c:if test="${not empty fouten}">
				<ul class="fout">
					<c:forEach var="fout" items="${fouten}">
						<li>${fout.value}</li>
					</c:forEach>
				</ul>
				
			</c:if>
	</body>
</html>