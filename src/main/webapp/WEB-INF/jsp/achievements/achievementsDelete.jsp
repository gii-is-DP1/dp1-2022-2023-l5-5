<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="deleteAchievements">
	<h1>Are you sure you want to delete the achievement?</h1>
   <!--  <form:form modelAttribute="player" class="form-horizontal" id="delete-player-form"> -->
        <br>
        <br>
        <div class="row form-group">
           
            	<spring:url value="/achievements/{id}/deleteConfirm" var="deleteUrl">
        		  	<spring:param name="id" value="${achievement.id}"/>
    			</spring:url>
    			<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete Achievement</a>
                 
                <spring:url value="/achievements/list" var="cancel">
        		  	<spring:param name="id" value="${achievement.id}"/>
    			</spring:url>
    			<a href="${fn:escapeXml(cancel)}" class="btn btn-default">Cancel</a>
            
        </div>
</petclinic:layout>