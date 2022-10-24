<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="casillas">
    <h2>Casillas</h2>

    <table id="casillasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Numero de minas adyacentes</th>
            <th style="width: 200px;">Coordenada X</th>
            <th style="width: 120px">Coordenada Y</th>
            <th style="width: 120px">Esta cubierta</th>
            <th style="width: 120px">Contenido </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="casilla">
            <tr>
                <td>
                    <spring:url value="/casillas/{casillaId}" var="casillaUrl">
                        <spring:param name="casillaId" value="${casilla.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(casillaUrl)}"><c:out value="${casilla.numMinasAdyacentes}"/></a>
                </td>
                <td>
                    <c:out value="${casilla.coordenadaX}"/>
                </td>
                <td>
                    <c:out value="${casilla.coordenadaY}"/>
                </td>
                <td>
                    <c:out value="${casilla.estaCubierta}"/>
                </td>
                <td>
                    <c:out value="${casilla.contenido}"/>
                </td>
      
<!--
                <td> 
                    <c:out value="${owner.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${owner.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
