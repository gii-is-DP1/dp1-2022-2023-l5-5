<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="game" tagdir="/WEB-INF/tags" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->
<game:layout pageName="tableroPrueba">

    <h2>Tablero Prueba</h2>


    <p>	
        <h2><c:out value="${now}"/></h2>
    
        <div class="row">
            <div class="col-md-12">
                <game:tablero tablero="${tablero}" tableroImage="${tableroImagen}">
                <c:forEach items="${tablero.casilla}" var="casilla">
                    <game:casilla size="100" casilla="${casilla}"/>            	
                </c:forEach> 
                </game:tablero>
            </div>
        </div>

    <br/>
    <br/>
    <br/>
    

 </game:layout>