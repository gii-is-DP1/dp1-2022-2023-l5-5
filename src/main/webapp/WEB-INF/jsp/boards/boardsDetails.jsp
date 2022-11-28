<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="tableros">

    <h2>Tablero Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <td><b><c:out value="${tablero.id}"/></b></td>
        </tr>
        <tr>
            <th>Numero de minas</th>
            <td><b><c:out value="${tablero.numeroMinas}"/></b></td>
        </tr>
        <tr>
            <th>Filas</th>
            <td><c:out value="${tablero.filas}"/></td>
        </tr>
        <tr>
            <th>Columnas</th>
            <td><c:out value="${tablero.columnas}"/></td>
        </tr>
    </table>


    <br/>
    <br/>
    <br/>
    

</petclinic:layout>
