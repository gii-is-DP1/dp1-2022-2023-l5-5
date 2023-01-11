<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="delete Player">
	<h1>Are you sure you want to delete the player @<c:out value="${player.user.username}"/> ?</h1>
        <br>
        <br>
        <div class="row form-group">
           
            	<spring:url value="/players/myprofile/{id}/deleteConfirmAdmin" var="deleteUrl">
        		  	<spring:param name="id" value="${player.id}"/>
    			</spring:url>
    			<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">&#x1F5D1 Delete Player</a>
                 
                <spring:url value="/players/list" var="cancel">
        		  	<spring:param name="id" value="${player.id}"/>
    			</spring:url>
    			<a href="${fn:escapeXml(cancel)}" class="btn btn-default">Cancel</a>
            
        </div>
</petclinic:layout>