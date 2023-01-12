<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">
    <h2>Players</h2>

    <table id="playersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Username</th>
            <th style="width: 150px;">Name</th>
            <th style="width: 150px;">Lastname</th>
            <th style="width: 120px">Mail</th>
            <th style="width: 200px;">Password</th>
            <th style="width: 50px;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="player">
            <tr>
                <td>
                    <spring:url value="/players/list/{username}" var="profileUrl">
                        <spring:param name="username" value="${player.user.username}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(profileUrl)}"><c:out value="@${player.user.username}"/></a>
                </td>
                <td>
                    <c:out value="${player.firstName}"/>
                </td>
                <td>
                    <c:out value="${player.lastName}"/>
                </td>
                <td>
                    <c:out value="${player.mail}"/>
                </td>
                <td>
                    <%-- <c:out value="${player.user.password}"/> --%>
                    <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
                </td>           
                <td>
                    <spring:url value="/players/myprofile/{id}/edit" var="editUrl">
                        <spring:param name="id" value="${player.id}"/>
                    </spring:url>
                    <a style="color:black" href="${fn:escapeXml(editUrl)}">&#128456 Edit</a>
           
           			<br>
           			
                    <spring:url value="/players/myprofile/{id}/deleteAdmin" var="deleteUrl">
                        <spring:param name="id" value="${player.id}"/>
                    </spring:url>
                    <a style="color:black" href="${fn:escapeXml(deleteUrl)}">&#x1F5D1 Delete</a>
                </td>
  
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="center" border="0">
        <tr>
        	<td>
            <c:if test="${hasPrevious}">
                <td><a
                    style="margin-right:5px"  
                    href="/players/list?firstName=${player.firstName}&page=${pageNumber - 1}"
                    class="btn btn-default">Previous</a>
            	</td>
            </c:if>

            <c:forEach begin="1" end="${totalPages+1}" var="i">
                <td><a style="margin-left:5px; margin-right:5px;" href="/players/list?firstName=${player.firstName}&page=${i-1}">${i}</a></td>
            </c:forEach>

            <c:if test="${pageNumber != totalPages}">
                <td><a
                	style="margin-left:5px;" 
                    href="/players/list?firstName=${player.firstName}&page=${pageNumber + 1}"
                    class="btn btn-default">Next</a></td>
            </c:if>
            <td>
            <a style="margin-left:900px;" href="/players/new" class="btn btn-default">Create new player</a>

        </tr>
     </table>
     
</petclinic:layout>
