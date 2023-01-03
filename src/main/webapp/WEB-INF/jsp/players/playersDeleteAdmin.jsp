<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="delete Player">
	<h1>Are you sure you want to delete the player?</h1>
   <!--  <form:form modelAttribute="player" class="form-horizontal" id="delete-player-form"> -->
        <br>
        <br>
        <div class="row form-group">
           
            	<spring:url value="/players/myprofile/{id}/deleteConfirmAdmin" var="deleteUrl">
        		  	<spring:param name="id" value="${player.id}"/>
    			</spring:url>
    			<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete Player</a>
                 
                <spring:url value="/players/list" var="cancel">
        		  	<spring:param name="id" value="${player.id}"/>
    			</spring:url>
    			<a href="${fn:escapeXml(cancel)}" class="btn btn-default">Cancel</a>
            
        </div>
<!--     </form:form> -->
</petclinic:layout>