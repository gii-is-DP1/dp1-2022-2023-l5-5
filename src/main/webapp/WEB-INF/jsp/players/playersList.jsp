<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">
    <h2>Jugadores</h2>

    <table id="playersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Username</th>
            <th style="width: 150px;">Name</th>
            <th style="width: 150px;">Lastname</th>
            <th style="width: 120px">Mail</th>
            <th style="width: 200px;">Password</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="player">
            <tr>
                <td>
                    <spring:url value="/players/profile/{id}" var="playerUrl">
                        <spring:param name="playerId" value="${player.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(playerUrl)}"><c:out value="${player.user.username}"/></a>
                </td>
                <td>
                    <c:out value="${person.name}"/>
                </td>
                <td>
                    <c:out value="${person.lastname}"/>
                </td>
                <td>
                    <c:out value="${player.mail}"/>
                </td>
                <td>
                    <c:out value="${player.user.password}"/>
                </td>

                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
