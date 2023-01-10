<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">
    <h2 class="text-center">
        New Achievement
    </h2>
    <br>
    <br>
    <form:form modelAttribute="achievement" class="form-horizontal" id="add-achievement-form">
        <div class="form-group has-feedback" style="width: 700px; margin-left: 200px;">
            <petclinic:inputField label="Title" name="title" />
            <br>
            <div class = "text-center">
            <h3 style="margin-right: 200px;">Choose the achievement type..</h3>
            <select name="achievementType" size="4" style="width: 550px; margin-left: 125px;">
                <c:forEach items="${achievementtypes}" var="type">
                <option value="${type.id}"><c:out value="${type.name}"></c:out></option>
                </c:forEach>
            </select>
            </div>
            <br>
            <br>
            <petclinic:inputField label="Number" name="number"/>
        </div>
        <br>
        <div class="form-group">
            <div class="text-center">     
            	<button class="btn btn-default" type="submit">Add Achievement</button>                  
            </div>
        </div>
      	<br>
      	<br>
      	<br>
    </form:form>
</petclinic:layout>
