<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <h2>
        New Game
    </h2>
    
    <form:form modelAttribute="game"
                   class="form-horizontal">
            <form:hidden  path="id" />
            <form:hidden  path="startTime" />
            <form:hidden  path="finishTime" />
            <form:hidden  path="numClicks" />
            <form:hidden  path="inProgress" />
            <form:hidden  path="lostGame" />

            <div class="control-group">
                   <petclinic:selectField name="difficulty" label="Difficulty " names="${difficulties}" size="1"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">

                    <button class="btn btn-default" type="submit">Create game</button>
                </div>
            </div>
        </form:form>
</petclinic:layout>
