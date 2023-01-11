<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">
    <h2> Achievements acomplished by @${player}</h2>
    <br>
    
    <c:if test="${selections.size()==0}">
        <h3>@${player} doesn't have any achievements </h3>
    </c:if>

    <table id="achievementsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 100px;"> </th>
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
        <c:forEach items="${selections2}" var="achievement">
            <tr>
            <td>

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
</petclinic:layout>
