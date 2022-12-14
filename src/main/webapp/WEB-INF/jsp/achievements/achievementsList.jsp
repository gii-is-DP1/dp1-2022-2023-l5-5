<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">
    <h2>Achievements</h2>

    <table id="achievementsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Title</th>
            <th style="width: 150px;">Achievement Type</th>
            <th style="width: 150px;">Number</th>
            <th style="width: 50px;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="achievement">
            <tr>
                <td>
                    <c:out value="${achievement.title}"/>
                </td> 
                <td>
                    <c:out value="${achievement.achievementType}"/>
                </td>   
                <td>
                    <c:out value="${achievement.number}"/>
                </td>   
                <td>
                    <spring:url value="/achievements/{id}/edit" var="editUrl">
                        <spring:param name="id" value="${achievement.id}"/>
                    </spring:url>
                    <a style="color:black" href="${fn:escapeXml(editUrl)}">&#128456 Edit</a>
                    
                    <br>
           			
                    <spring:url value="/achievements/{id}/delete" var="deleteUrl">
                        <spring:param name="id" value="${achievement.id}"/>
                    </spring:url>
                    <a style="color:black" href="${fn:escapeXml(deleteUrl)}">&#x1F5D1 Delete</a>
                </td>            
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table border="0">
        <tr>
            <c:if test="${hasPrevious}">
                <td>
                <a  style="margin-right:5px"
                    href="/achievements/list?title=${title}&page=${pageNumber - 1}"
                    class="btn btn-default">Previous</a>
            	</td>
            </c:if>

            <c:forEach begin="1" end="${totalPages+1}" var="i">
                <td>
                	<a style="margin-left:5px; margin-right:5px;" href="/achievements/list?title=${title}&page=${i-1}">${i}
                	</a>
            	</td>
            </c:forEach>

            <c:if test="${pageNumber != totalPages}">
                <td>
                	<a style="margin-left:5px;"
                    href="/achievements/list?title=${title}&page=${pageNumber + 1}"
                    class="btn btn-default">Next</a></td>
            </c:if>
        </tr>
	</table>
</petclinic:layout>
