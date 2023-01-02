<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="game" tagdir="/WEB-INF/tags" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->
<game:layout pageName="tableroPrueba">

	<div class= "col-2 text-center">
    <h1>Set difficulty</h1>
	<br>

    <h2>Choose the difficulty of the game:</h2>
    <br>
    <a href="http://localhost:8080/board/game?dificulty=1" class="btn btn-primary btn-lg" class="btn btn-primary btn-block btn-sm" style="background-color: rgb(255, 140, 198); border-radius: 6px; color: rgb(255, 236, 245);border: 2px solid #34302D;" >Easy</a>
    <a href="http://localhost:8080/board/game?dificulty=2" class="btn btn-primary btn-lg" class="btn btn-primary btn-block btn-sm" style="background-color: rgb(255, 140, 198); border-radius: 6px; color: rgb(255, 236, 245);border: 2px solid #34302D;">Medium</a>
    <a href="http://localhost:8080/board/game?dificulty=3" class="btn btn-primary btn-lg" class="btn btn-primary btn-block btn-sm" style="background-color: rgb(255, 140, 198); border-radius: 6px; color: rgb(255, 236, 245);border: 2px solid #34302D;">Difficult</a>	
    </div>

    <br/>
    <br/>
    <br/>
    

 </game:layout>