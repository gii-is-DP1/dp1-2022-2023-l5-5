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
            
            <th style="width: 150px;">Username</th>
            <th style="width: 120px">Result</th>
            <th style="width: 120px">Time</th>
            <th style="width: 120px">Start Date</th>
            <th style="width: 120px">Finish Date</th>
            <th style="width: 120px">Difficulty</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="board">
            <tr>
                <td>
                	<spring:url value="/players/myprofile" var="profileUrl">
                        <spring:param name="username" value="${board.player.user.username}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(profileUrl)}"><c:out value="@${board.player.user.username}"/></a>
                </td>
                <td>
                    <c:if test="${board.gameStatus == 'IN_PROGRESS'}">
							<c:out value="In progress.." />
					</c:if> <c:if test="${board.gameStatus == 'WON'}">
							<c:out value="Won" />
					</c:if> <c:if test="${board.gameStatus == 'LOST'}">
							<c:out value="Lost" />
					</c:if>
				</td>
                <td>
                    <c:if test="${board.gameStatus == 'IN_PROGRESS'}">
						<c:out value="In progress.." />
					</c:if>
					<c:if test="${board.gameStatus == 'WON'}">
						<c:out value="${board.durationString()}"/>
					</c:if>
					<c:if test="${board.gameStatus == 'LOST'}">
						<c:out value="${board.durationString()}"/>
					</c:if>
                </td>
                <td>
                    <c:out value="${board.startTimeString()}"/>
                </td>
				<td>
                    <c:if test="${board.gameStatus == 'IN_PROGRESS'}">
						<c:out value="In progress.." />
					</c:if>
					<c:if test="${board.gameStatus == 'WON'}">
						<c:out value="${board.finishTimeString()}"/>
					</c:if>
					<c:if test="${board.gameStatus == 'LOST'}">
						<c:out value="${board.finishTimeString()}"/>
					</c:if>
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
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>