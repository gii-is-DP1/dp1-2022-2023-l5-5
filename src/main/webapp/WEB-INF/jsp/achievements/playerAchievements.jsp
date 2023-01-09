<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">
    <h2> Achievements acomplished by ${player}</h2>

    <table id="achievementsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;"> </th>
            <th style="width: 150px;">Title</th>
            <th style="width: 150px;">Achievement Type</th>
        </tr>  
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="achievement">
            <tr>
            <td>
                <div class="col-md-5">
                    <spring:url value="/resources/images/medal.png" htmlEscape="true" var="bmImage"/>
                    <img class="img-responsive" src="${bmImage}"/>
                </div>
            </td>
                <td>
                    <c:out value="${achievement.title}"/>
                </td> 
                <td>
                    <c:out value="${achievement.achievementType}"/>
                </td>                  
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table border="1">
        <tr>
            <c:if test="${hasPrevious}">
                <td><a
                    href="myprofile?title=${title}&page=${pageNumber - 1}"
                    class="btn btn-default">Previous</a></td>
            </c:if>

            <c:forEach begin="1" end="${totalPages+1}" var="i">
                <td><a href="myprofile?title=${title}&page=${i-1}">${i}</a></td>
            </c:forEach>

            <c:if test="${pageNumber != totalPages}">
                <td><a
                    href="myprofile?title=${title}&page=${pageNumber + 1}"
                    class="btn btn-default">Next</a></td>
            </c:if>

        </tr>
</petclinic:layout>
