<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">

    <div class="row" style="margin-top:0px; margin-bottom:0px;">
    <div class="col-md-6">
        <h2 style="font-size: 4rem;">Project Minesweeper </h2>
        <br>
        <h2 style="font-size: 2rem;color: rgb(255, 140, 198) ;">Group ${group}:</h2>
        <ul>
        <c:forEach items="${persons}" var="person">
           <b style="font-size: 1.8rem;"> <li> ${person.firstName} ${person.lastName}</li> </b>
        </c:forEach>
        </ul>
    </div>
    <br>
    <br>
    <div class="col-md-6 text-center">
        <spring:url value="/resources/images/minesweeper.png" htmlEscape="true" var="bmImage"/>
        <img class="img-responsive" src="${bmImage}"/>
    </div>
</div>
<br>
<br>
<br>
</petclinic:layout>
