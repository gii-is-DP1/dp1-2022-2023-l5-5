<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h1><fmt:message key="welcome"/></h1>
    <div class="row">
    <h2>Project ${tittle}</h2>
    <h2>Group ${group}</h2>
    <ul>
    <c:forEach items="${persons}" var="person">
        <li>${person.firstName} ${person.lastName}</li>
    </c:forEach>
    </ul>
    </div>
    <br>
    <div class="row">
        <div class="col-md-5">
            <spring:url value="/resources/images/minesweeper.png" htmlEscape="true" var="bmImage"/>
            <img class="img-responsive" src="${bmImage}"/>
        </div>
    
    </div>

</petclinic:layout>
