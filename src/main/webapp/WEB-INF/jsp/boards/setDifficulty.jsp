<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="game" tagdir="/WEB-INF/tags" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->
<game:layout pageName="tableroPrueba">

    <h2>Set difficulty</h2>

    <p>Choose the difficulty:</p>
    <a href="http://localhost:8080/board/game?dificulty=1" class="btn btn-primary" >Easy</a>
    <a href="http://localhost:8080/board/game?dificulty=2" class="btn btn-primary">Medium</a>
    <a href="http://localhost:8080/board/game?dificulty=3" class="btn btn-primary">Difficult</a>	
    

    <br/>
    <br/>
    <br/>
    

 </game:layout>