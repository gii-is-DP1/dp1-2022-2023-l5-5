<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="gamesPlayed">
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
                    <c:out value="${board.player.user.username}"/>
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
                <c:choose>
                    <c:when test="${board.gameStatus == 'IN_PROGRESS'}">
                        <c:out value="---"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${board.durationString()}"/>
                    </c:otherwise>
                </c:choose>
                </td>
                <td>
                    <c:out value="${board.startTimeString()}"/>
                </td>
                <td>
                    <c:out value="${board.finishTimeString()}"/>
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
    
    <table class="center" border="0">
        <tr>
            <c:if test="${hasPrevious}">
                <td><a
                    style="margin-right:5px"  
                    href="/board/list?page=${pageNumber - 1}"
                    class="btn btn-default">Previous</a>
            	</td>
            </c:if>

            <c:forEach begin="1" end="${totalPages+1}" var="i">
                <td><a style="margin-left:5px; margin-right:5px;" href="/board/list?page=${i-1}">${i}</a></td>
            </c:forEach>

            <c:if test="${pageNumber != totalPages}">
                <td><a
                	style="margin-left:5px;" 
                    href="/board/list?page=${pageNumber + 1}"
                    class="btn btn-default">Next</a></td>
            </c:if>

        </tr>
     </table>
</petclinic:layout>