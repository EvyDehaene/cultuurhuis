<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<!doctype html>
<html lang="nl">
	<head>
		<title>Cultuurhuis - nieuwe klant</title>
		<link rel="stylesheet" href="${contextPath}/styles/default.css"/>
	</head>
	<body>
		<h1>Cultuurhuis:nieuwe klant<img src="${contextPath}/images/nieuweklant.png"/></h1>
		<nav>
			<c:url value="/welkom" var="welkomURL"/><a href="${welkomURL}">Voorstellingen</a>
			<c:url value="/reservatiemandje" var="reservatiemandjeURL"/><a href="${reservatiemandjeURL}">Reservatiemandje</a>
			<c:url value="/bevestiging" var="bevestigingURL"/><a href="${bevestigingURL}">Bevestiging reservatie</a>
		</nav>
		<form method="post" action="${url}" id="nieuweklantform">
			<label>Voornaam:<br><input type="text" name="voornaam" autofocus/></label><br>
			<label>Familienaam:<br><input type="text" name="familienaam"/></label><br>
			<label>Straat:<br><input type="text" name="straat"/></label><br>
			<label>Huisnr.:<br><input type="text" name="huisNr"/></label><br>
			<label>Postcode:<br><input type="text" name="postcode"/></label><br>
			<label>Gemeente:<br><input type="text" name="gemeente"/></label><br>
			<label>Gebruikersnaam:<br><input type="text" name="gebruikersnaam"/></label><br>
			<label>Paswoord:<br><input type="password" name="paswoord"/></label><br>
			<label>Herhaal paswoord:<br><input type="password" name="herhaalPaswoord"/></label><br>
			<input type="submit" value="OK" id="maakklantknop"/>
		</form>
<%-- 		<c:if test="${not emtpy fouten||not empty nietIngevuld}"> --%>
			<ul class="fout">
				<c:if test="${not empty nietIngevuld}">
					<c:forEach var="fout" items="${nietIngevuld}">
						<li>${fout} niet ingevuld.</li>
					</c:forEach>
				</c:if>
				<c:if test="${not empty fouten}">
					<c:forEach var="fout" items="${fouten}">
						<li>${fout.value}</li>
					</c:forEach>
				</c:if>
			</ul>
<%-- 		</c:if> --%>
	</body>
</html>