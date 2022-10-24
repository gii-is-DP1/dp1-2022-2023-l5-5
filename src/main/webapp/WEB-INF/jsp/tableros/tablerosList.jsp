<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="tableros">
    <h2>Tableros</h2>

    <table id="tablerosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Numero de minas</th>
            <th style="width: 200px;">Filas</th>
            <th style="width: 120px">Columnas</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="tablero">
            <tr>
                <td>
                    <spring:url value="/tableros/{tableroId}" var="tableroUrl">
                        <spring:param name="tableroId" value="${tablero.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(tableroUrl)}"><c:out value="${tablero.numeroMinas}"/></a>
                </td>
                <td>
                    <c:out value="${tablero.filas}"/>
                </td>
                <td>
                    <c:out value="${tablero.columnas}"/>
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
