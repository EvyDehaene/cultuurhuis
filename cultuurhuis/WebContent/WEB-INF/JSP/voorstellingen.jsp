<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%-- <%@taglib uri="http://cultuurhuis.be" prefix="cultuur"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="nl">
   <head>
        <c:choose>
            <c:when test="${empty genre}">
                <title>Het cultuurhuis - voorstellingen</title>
            </c:when>
            <c:otherwise>
                <title>Het cultuurhuis - voorstellingen - ${genre.naam}</title>
            </c:otherwise>
        </c:choose>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/default.css"/>
    </head>
    <body>
<%--         <cultuur:titel subTitel="voorstellingen" formID="voorstellingen" image="voorstellingen.png"/> --%>
        <h2>Genres</h2>
        <p>
            <c:forEach items="${genres}" var="genre">
                <c:url value="voorstellingen.htm" var="url">
                    <c:param name="genreNr" value="${genre.genreNr}"/>
                </c:url>
                <a href="${url}">${genre.naam}</a>
            </c:forEach>
        </p>
        <c:forEach var="voorstelling" items="${voorstellingen}" varStatus="status">
            <c:if test="${status.first}">
                <h2>${genre.naam} voorstellingen</h2>
                 <table class="zebra">
                    <thead>
                        <tr class="HoofdingTabel">
                            <th>Datum</th>
                            <th>Titel</th>
                            <th>Uitvoerders</th>
                            <th>Prijs</th>
                            <th>Vrije plaatsen</th>
                            <th>Reserveren</th>
                        </tr>
                    </thead>
                    <tbody>
                    </c:if>
                    <tr>
                        <td><fmt:formatDate value="${voorstelling.datum}" type="both" dateStyle="short" timeStyle="short"/></td>
                        <td><c:out value="${voorstelling.titel}"/></td>
                        <td><c:out value="${voorstelling.uitvoerders}"/></td>
                        <td>&euro;<fmt:formatNumber value="${voorstelling.prijs}" type="number" maxFractionDigits="2" minFractionDigits="2"/></td>
                        <td style="text-align:right">${voorstelling.vrijePlaatsen}</td>
                        <td>
                            <c:if test="${voorstelling.vrijePlaatsen != 0}">
                                <c:url value="/voorstelling.htm" var="url">
                                    <c:param name='voorstellingsNr' value='${voorstelling.voorstellingsNr}'/>
                                </c:url>
                                <a href="${url}">Reserveren</a>
                            </c:if>
                        </td>
                    </tr>
                    <c:if test="${status.last}">
                    </tbody>
                </table>
            </c:if>
        </c:forEach>
    </body>
</html>
