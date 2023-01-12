<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="game" tagdir="/WEB-INF/tags" %>

<game:layout pageName="new_games">

	<div class= "col-2 text-center">
    <h2>You have already a game in progress...</h2>
	<br> 
    <h2>You must complete a game for starting another one!</h2>
    <br>
    <a href="http://localhost:8080/board/game?dificulty=1" class="btn btn-primary btn-lg" class="btn btn-primary btn-block btn-sm" style="background-color: rgb(255, 140, 198); border-radius: 6px; color: rgb(255, 236, 245);border: 2px solid #34302D;" >Continue</a>	
    </div>

    <br/>
    <br/>
    <br/>
    

 </game:layout>