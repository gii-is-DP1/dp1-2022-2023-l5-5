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

<minesweeper:layout pageName="statistics">

	<c:if
		test="${player.user.username eq principal.username or fn:contains(principal.authorities, 'player')}">
		
		<h2>GAME STATISTICS</h2><br/>
			<table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th colspan=3 scope="col" style="text-align:center">HALL OF FAME</th>
			    </tr>
			    <tr>
			      <th width="10%" style="text-align:center" scope="col">#</th>
			      <th width="40%" style="text-align:center" scope="col">Player (Username)</th>
			      <th style="text-align:center" scope="col">Games Won</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr style="background-color:#ffdb4d">
			      <th style="text-align:center" scope="row">TOP 1</th>
			      <th style="text-align:center"><c:out value="${playerTop1Username}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerTop1WinGames}" /></td>
			    </tr>
			    <tr style="background-color:#cccccc">
			      <th style="text-align:center" scope="row">TOP 2</th>
			      <th style="text-align:center"><c:out value="${playerTop2Username}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerTop2WinGames}" /></td>
			    </tr>
			    <tr style="background-color:#e6cbb3">
			      <th style="text-align:center" scope="row">TOP 3</th>
			      <th style="text-align:center"><c:out value="${playerTop3Username}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerTop3WinGames}" /></td>
			    </tr>
			  </tbody>
			</table>
			<br/>
			<table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th colspan=2 scope="col" style="text-align:center">GLOBAL STATS</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      	<th width="50%">Total games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${nTotal}" /></td>
			    </tr>
<%-- 			    <tr>
			      	<th>Average games played by player</th>
					<td style="font-size:20px;text-align:center"><c:out value="${averageNumberGlobalGames}" /></td>
			    </tr> --%>
			    <tr>
					<th>Average duration games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${averageDurationGlobalGames}" /> minutes</td>
				</tr>
				<tr>
					<th>Total duration games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${minutesTotalPlayed}" /> minutes and <c:out value="${secondsTotalPlayed}" /> seconds</td>
				</tr>
				<tr>
					<th>Maximum duration of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${maxDurationGlobalGames}" /> minutes</td>
				</tr>
				<tr>
					<th>Minimum duration of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${minDurationGlobalGames}" /> minutes</td>
				</tr>
			  </tbody>
			</table>
			<table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th colspan=2 scope="col" style="text-align:center">PLAYER STATS</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
					<th width="50%" >Number of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${gamesPlayerTotal}" /></td>
				</tr>
				<tr>
					<th>Average duration games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${averageDurationPlayerGames}" /> minutes</td>
				</tr>
				<tr>
					<th>Total duration games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${totalDurationPlayerGames}" /> minutes</td>
				</tr>
				<tr>
					<th>Maximum duration of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${maxDurationPlayerGames}" /> minutes</td>
				</tr>
				<tr>
					<th>Minimum duration of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${minDurationPlayerGames}" /> minutes</td>
				</tr>
			  </tbody>
			  <thead>
			    <tr>
			      <th colspan=2 style="text-align:center" scope="col">Game stats</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<tr>
					<th width="50%">TOTAL GAMES WON</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numberWonGames}" /></td>
				</tr>
			    <tr>
					<th width="50%">Total activated mines</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numberActivatedMines}" /></td>
				</tr>
				<tr>
					<th>Total mine explosions contained</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numberGuessedMines}" /></td>
				</tr>
				<tr>
					<th>Total flags placed</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numberTotalFlags}" /></td>
				</tr>
				<tr>
					<th>Total cells clicked</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numberCellsClicked}" /></td>
				</tr>
			  </tbody>
			  <thead>
			    <tr>
			      <th colspan=2 style="text-align:center" scope="col">Achievements</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<tr>
					<th>BRONZE LEVEL: You have won <c:out value="${bronzeMinimumGames}" /> games.</th>
				</tr>
				<tr>
					<th>SILVER LEVEL: You have won <c:out value="${silverMinimumGames}" /> games.</th>
				</tr>
				<tr>
					<th>GOLD LEVEL: You have won <c:out value="${goldMinimumGames}" /> games.</th>
				</tr>
			  </tbody>
			</table>
	</c:if>
	<c:if test="${fn:contains(principal.authorities, 'admin')}">
		<h2>GAME STATS</h2><br/>
			<table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th colspan=3 scope="col" style="text-align:center">HALL OF FAME</th>
			    </tr>
			    <tr>
			      <th width="10%" style="text-align:center" scope="col">#</th>
			      <th width="40%" style="text-align:center" scope="col">Player (Username)</th>
			      <th style="text-align:center" scope="col">Games Won</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr style="background-color:#ffdb4d">
			      <th style="text-align:center" scope="row">TOP 1</th>
			      <th style="text-align:center"><c:out value="${playerTop1Username}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerTop1WinGames}" /></td>
			    </tr>
			    <tr style="background-color:#cccccc">
			      <th style="text-align:center" scope="row">TOP 2</th>
			      <th style="text-align:center"><c:out value="${playerTop2Username}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerTop2WinGames}" /></td>
			    </tr>
			    <tr style="background-color:#e6cbb3">
			      <th style="text-align:center" scope="row">TOP 3</th>
			      <th style="text-align:center"><c:out value="${playerTop3Username}" /></th>
			      <td style="font-size:20px;text-align:center"><c:out value="${playerTop3WinGames}" /></td>
			    </tr>
			  </tbody>
			</table>
			<br/>
			<table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th colspan=2 scope="col" style="text-align:center">GLOBAL STATS</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      	<th width="50%">Total games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numberGlobalGames}" /></td>
			    </tr>
<%-- 			    <tr>
			      	<th>Average games played by player</th>
					<td style="font-size:20px;text-align:center"><c:out value="${averageNumberGlobalGames}" /></td>
			    </tr> --%>
			    <tr>
					<th>Average duration games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${averageDurationGlobalGames}" /> minutes</td>
				</tr>
				<tr>
					<th>Total duration games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${timeTotalPlayed}" /> minutes</td>
				</tr>
				<tr>
					<th>Maximum duration of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${maxDurationGlobalGames}" /> minutes</td>
				</tr>
				<tr>
					<th>Minimum duration of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${minDurationGlobalGames}" /> minutes</td>
				</tr>
			  </tbody>
			</table>
	</c:if>
</minesweeper:layout>