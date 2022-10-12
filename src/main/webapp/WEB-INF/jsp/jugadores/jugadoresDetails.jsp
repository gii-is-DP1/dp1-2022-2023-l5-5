<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">

    <h2>Jugador Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre de Usuario</th>
            <td><b><c:out value="${jugador.nombreUsuario}"/></b></td>
        </tr>
        <tr>
            <th>Contrasena</th>
            <td><c:out value="${jugador.contrasena}"/></td>
        </tr>
        <tr>
            <th>Correo Electronico</th>
            <td><c:out value="${jugador.correoElectronico}"/></td>
        </tr>
    </table>

    <spring:url value="{jugadorId}/edit" var="editUrl">
        <spring:param name="jugadorId" value="${jugador.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Jugador</a>

    <br/>
    <br/>
    <br/>
    

</petclinic:layout>
