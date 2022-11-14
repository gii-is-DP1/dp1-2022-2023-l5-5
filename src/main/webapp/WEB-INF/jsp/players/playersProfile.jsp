<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">

    <h2>Player profile</h2>

    <table class="table table-striped">
        <tr>
            <th>Firstname</th>
            <td><b><c:out value="${player.firstName}"/></b></td>
        </tr>
        <tr>
            <th>Lastname</th>
            <td><b><c:out value="${player.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Username</th>
            <td><b><c:out value="${player.user.username}"/></b></td>
        </tr>
        <tr>
            <th>Mail</th>
            <td><c:out value="${player.mail}"/></td>
        </tr>
        <tr>
            <th>Password</th>
            <td><c:out value="${player.user.password}"/></td>
    </table>

    <spring:url value="/players/myprofile/{id}/edit" var="editUrl">
        <spring:param name="id" value="${player.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Player</a>
    
    <spring:url value="/players/myprofile/{id}/delete" var="deleteUrl">
        <spring:param name="id" value="${player.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete Player</a>

    <br/>
    <br/>
    <br/>

</petclinic:layout>