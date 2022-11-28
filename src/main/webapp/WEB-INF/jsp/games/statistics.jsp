<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <h2>Games</h2>
		 <c:out value="${nTotal}"/>
		
		    <table id="statisticsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Total number of Games</th>
            <th style="width: 150px;">Promedio</th>
            <th style="width: 150px;">Max</th>
            <th style="width: 120px">Min</th>
        </tr>
        </thead>
        </tbody>
    </table>
</petclinic:layout>