 <%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="gamesPlayer">
    <h2>Games</h2>

    <table id="gamesTable" class="table table-striped">
        <thead>
        <tr>
<!--             <th style="width: 120px">Board id</th>
            <th style="width: 150px;">Username</th>
            <th style="width: 200px;">Difficulty</th>
            <th style="width: 120px">Id de tablero</th>
            <th style="width: 120px">Ha perdido?</th>
            <th style="width: 120px">Esta en progreso?</th>
            <th style="width: 120px">Fecha de inicio</th>
            <th style="width: 120px">Fecha de fin</th>
            <th style="width: 120px">Numero de clicks</th> -->
            
            <th style="width: 150px;">Username</th>
            <th style="width: 120px">Board id</th>
            <th style="width: 120px">Result</th>
            <th style="width: 120px">Time</th>
            <th style="width: 120px">Start Date</th>
            <th style="width: 120px">Finish Date</th>
            <th style="width: 120px">Difficulty</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${board}" var="board">
            <tr>
<%--                 <td>
                    <spring:url value="/games/{gameId}" var="gameUrl">
                        <spring:param name="gameId" value="${game.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(gameUrl)}"><c:out value="${game.id}"/></a>
                </td> --%>
                <td>
                    <c:out value="${board.player.user.username}"/>
                </td>
                <td>
                    <c:out value="${board.id} "/>
                </td>
                <td>
                    <c:out value="${board.gameStatus}"/>
                </td>
                <td>
                    <c:out value="${board.duration}"/>
                </td>
                <td>
                    <c:out value="${board.startTime}"/>
                </td>
				<td>
                    <c:out value="${board.finishTime}"/>
                </td>
                <td>
				<c:if test = "${board.rowsNumber == 8}">
                    <c:out value="Easy"/>
                </c:if>
                <c:if test = "${board.rowsNumber == 14}">
                    <c:out value="Medium"/>
                </c:if>
                <c:if test = "${board.rowsNumber == 24}">
                    <c:out value="Difficult"/>
                </c:if>
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