<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">
    <h2>
        Update achievement
    </h2>
    <form:form modelAttribute="achievement" class="form-horizontal" id="update-achievement-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Title" name="title"/>
            <select name="achievementType" size="3">
                <c:forEach items="${achievementtypes}" var="type">
                <option value="${type.id}"><c:out value="${type.name}"></c:out></option>
                </c:forEach>
            </select>
            <petclinic:inputField label="Number" name="number"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<button class="btn btn-default" type="submit">Update Achievement</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
