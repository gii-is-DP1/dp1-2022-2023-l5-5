<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <h2>Games</h2>

    <table id="gamesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 120px">Id de partida</th>
            <th style="width: 150px;">Nombre Usuario</th>
            <th style="width: 200px;">Dificultad</th>
            <th style="width: 120px">Id de tablero</th>
            <th style="width: 120px">Ha perdido?</th>
            <th style="width: 120px">Esta en progreso?</th>
            <th style="width: 120px">Fecha de inicio</th>
            <th style="width: 120px">Fecha de fin</th>
            <th style="width: 120px">Numero de clicks</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="game">
            <tr>
                <td>
                    <spring:url value="/games/{gameId}" var="gameUrl">
                        <spring:param name="gameId" value="${game.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(gameUrl)}"><c:out value="${game.id}"/></a>
                </td>
                <td>
                    <c:out value="${game.player.user.username}"/>
                </td>
                <td>
                    <c:out value="${game.difficulty}"/>
                </td>
                <td>
                    <c:out value="${game.tablero.id} "/>
                </td>
                <td>
                    <c:out value="${game.lostGame}"/>
                </td>
                <td>
                    <c:out value="${game.inProgress}"/>
                </td>
                <td>
                    <c:out value="${game.startTime}"/>
                </td>
                <td>
                    <c:out value="${game.finishTime}"/>
                </td>
                <td>
                    <c:out value="${game.numClicks}"/>
                </td>
                
                
      
<!--
                <td> 
                    <c:out value="${owner.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${owner.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>