<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="casillas">

    <h2>Casilla Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Numero de minas adyacentes</th>
            <td><b><c:out value="${casilla.numMinasAdyacentes}"/></b></td>
        </tr>
        <tr>
            <th>coordenadaX</th>
            <td><c:out value="${casilla.coordenadaX}"/></td>
        </tr>
        <tr>
            <th>coordenadaY</th>
            <td><c:out value="${casilla.coordenadaY}"/></td>
        </tr>

        <tr>
            <th>esta cubierta</th>
            <td><c:out value="${casilla.estaCubierta}"/></td>
        </tr>



        <tr>
            <th>contenido</th>
            <td><c:out value="${casilla.contenido}"/></td>
        </tr>
    </table>


    <br/>
    <br/>
    <br/>
    

</petclinic:layout>