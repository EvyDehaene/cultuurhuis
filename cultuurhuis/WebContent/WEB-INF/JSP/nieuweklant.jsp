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
			<label>Voornaam:<br><input type="text" name="voornaam" size=50 autofocus/></label><br>
			<label>Familienaam:<br><input type="text" name="familienaam" size=50/></label><br>
			<label>Straat:<br><input type="text" name="straat" size=50/></label><br>
			<label>Huisnr.:<br><input type="text" name="huisNr" size=50/></label><br>
			<label>Postcode:<br><input type="text" name="postcode" size=50/></label><br>
			<label>Gemeente:<br><input type="text" name="gemeente" size=50/></label><br>
			<label>Gebruikersnaam:<br><input type="text" name="gebruikersnaam" size=50/></label><br>
			<label>Paswoord:<br><input type="password" name="paswoord" size=50/></label><br>
			<label>Herhaal paswoord:<br><input type="password" name="herhaalPaswoord" size=50/></label><br>
			<input type="submit" value="OK" id="maakklantknop"/>
		</form>
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
	</body>
</html>