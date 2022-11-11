<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <h2>
        <c:if test="${game['new']}">New </c:if> Game
    </h2>
    <form:form modelAttribute="game" class="form-horizontal" id="add-game-form">
        <!--  <div class="form-group has-feedback">
            <petclinic:inputField label="First Name" name="firstName"/>
            <petclinic:inputField label="Last Name" name="lastName"/>
            <petclinic:inputField label="Mail" name="mail"/>
        </div>
        <div class="form-group"> -->
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${game['new']}">
                        <button class="btn btn-default" type="submit">Add Game</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Game</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
