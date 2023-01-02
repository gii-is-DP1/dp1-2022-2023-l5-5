<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">


    <spring:url value="/resources/images/mine.png" var="mineImage"/>
    <img width="200" height="200" src="${mineImage}"/>
	<br>
	<br>
    <h2>Something happened...</h2>

    <p>${exception.message}</p>

</petclinic:layout>
