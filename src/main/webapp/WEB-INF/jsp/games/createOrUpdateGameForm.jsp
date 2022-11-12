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
         <br class="form-group has-feedback">
            <label for="difficulty">difficulty: </label>
            <input type="text" label="Difficulty" name="difficulty"/>
            <br></br>
            <label for="startTime">startTime: </label>
            <input type="datetime-local" label="Start time" name="startTime"/>
            <br></br>
            <label for="finishTime">finishTime: </label>
            <input type="datetime-local" label="Finish time" name="finishTime"/>
            <br></br>
            <label for="numClicks">numClicks: </label>
            <input type="number" label="Number of clicks" name="numClicks"/>
            <br></br>
            <label for="inProgress">inProgress: </label>
            <input type="checkbox" label="Is the game in progress?" name="inProgress"/>
            <br></br>
            <label for="lostGame">lostGame: </label>
            <input type="checkbox" label="Is the game lost?" name="lostGame"/>
            <br></br>
            <label for="player.user.username">username: </label>
            <input type="text" label="Username" name="player.user.username"/>
            <br></br>
            <label for="tablero.id">id tablero: </label>
            <input type="number" label="Id tablero" name="tablero.id"/>
        </div>
        <div class="form-group">
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
