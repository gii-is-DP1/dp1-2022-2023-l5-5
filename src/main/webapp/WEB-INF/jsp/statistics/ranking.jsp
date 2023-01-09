<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authentication var="principal" property="principal" />

<minesweeper:layout pageName="rankings">

		<h2>GAME RANKING</h2><br/>
			<table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th colspan=3 scope="col" style="text-align:center">GLOBAL RANKING</th>
			    </tr>
			    <tr>
			      <th width="10%" style="text-align:center" scope="col">Rank</th>
			      <th width="40%" style="text-align:center" scope="col">Username of the player</th>
			      <th style="text-align:center" scope="col">Games Won</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr style="background-color:#FFD700">
			      <th style="text-align:center" scope="row">TOP 1</th>
			      <th style="text-align:center"><c:out value="${player1}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playergameswon1}" /></td>
			    </tr>
			    <tr style="background-color:#BEBEBE">
			      <th style="text-align:center" scope="row">TOP 2</th>
			      <th style="text-align:center"><c:out value="${player2}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playergameswon2}" /></td>
			    </tr>
			    <tr style="background-color:#bf8970">
			      <th style="text-align:center" scope="row">TOP 3</th>
			      <th style="text-align:center"><c:out value="${player3}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playergameswon3}" /></td>
			    </tr>
			  </tbody>
			</table>
			<br/>
			<table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th colspan=3 scope="col" style="text-align:center">EASY DIFFICULTY RANKING</th>
			    </tr>
			    <tr>
			      <th width="10%" style="text-align:center" scope="col">Rank</th>
			      <th width="40%" style="text-align:center" scope="col">Username of the player</th>
			      <th style="text-align:center" scope="col">Games Won</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr style="background-color:#FFD700">
			      <th style="text-align:center" scope="row">TOP 1</th>
			      <th style="text-align:center"><c:out value="${playerEasy1}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerEasygameswon1}" /></td>
			    </tr>
			    <tr style="background-color:#BEBEBE">
			      <th style="text-align:center" scope="row">TOP 2</th>
			      <th style="text-align:center"><c:out value="${playerEasy2}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerEasygameswon2}" /></td>
			    </tr>
			    <tr style="background-color:#bf8970">
			      <th style="text-align:center" scope="row">TOP 3</th>
			      <th style="text-align:center"><c:out value="${playerEasy3}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerEasygameswon3}" /></td>
			    </tr>
			  </tbody>
			</table>
			<br>
						<table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th colspan=3 scope="col" style="text-align:center">MEDIUM DIFFICULTY RANKING</th>
			    </tr>
			    <tr>
			      <th width="10%" style="text-align:center" scope="col">Rank</th>
			      <th width="40%" style="text-align:center" scope="col">Username of the player</th>
			      <th style="text-align:center" scope="col">Games Won</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr style="background-color:#FFD700">
			      <th style="text-align:center" scope="row">TOP 1</th>
			      <th style="text-align:center"><c:out value="${playerMed1}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerMedgameswon1}" /></td>
			    </tr>
			    <tr style="background-color:#BEBEBE">
			      <th style="text-align:center" scope="row">TOP 2</th>
			      <th style="text-align:center"><c:out value="${playerMed2}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerMedgameswon2}" /></td>
			    </tr>
			    <tr style="background-color:#bf8970">
			      <th style="text-align:center" scope="row">TOP 3</th>
			      <th style="text-align:center"><c:out value="${playerMed3}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerMedgameswon3}" /></td>
			    </tr>
			  </tbody>
			</table>
			<br>
			<table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th colspan=3 scope="col" style="text-align:center">DIFFICULT DIFFICULTY RANKING</th>
			    </tr>
			    <tr>
			      <th width="10%" style="text-align:center" scope="col">Rank</th>
			      <th width="40%" style="text-align:center" scope="col">Username of the player</th>
			      <th style="text-align:center" scope="col">Games Won</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr style="background-color:#FFD700">
			      <th style="text-align:center" scope="row">TOP 1</th>
			      <th style="text-align:center"><c:out value="${playerDiff1}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerDiffgameswon1}" /></td>
			    </tr>
			    <tr style="background-color:#BEBEBE">
			      <th style="text-align:center" scope="row">TOP 2</th>
			      <th style="text-align:center"><c:out value="${playerDiff2}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerDiffgameswon2}" /></td>
			    </tr>
			    <tr style="background-color:#bf8970">
			      <th style="text-align:center" scope="row">TOP 3</th>
			      <th style="text-align:center"><c:out value="${playerDiff3}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerDiffgameswon3}" /></td>
			    </tr>
			  </tbody>
			</table>
	
</minesweeper:layout>