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
		
		<h2>STATISTICS</h2>
		<br/>

			<table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th colspan=2 scope="col" style="text-align:center">GLOBAL STATISTICS</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      	<th width="50%">TOTAL games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${nTotal}" /></td>
			    </tr>
			    <tr>
					<th width="50%" >Number of games WON</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numWonPlayed}" /></td>
				</tr>
				<tr>
					<th width="50%" >Number of games WON with EASY difficulty</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numGamesWinEasyPlayed}" /></td>
				</tr>
				
				<tr>
					<th width="50%" >Number of games WON with MEDIUM difficulty</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numGamesWinMediumPlayed}" /></td>
				</tr>
				<tr>
					<th width="50%" >Number of games WON with DIFFICULT difficulty</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numGamesWinDifficultPlayed}" /></td>
				</tr>
				
				<tr>
					<th width="50%" >Number of games LOST</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numLostPlayed}" /></td>
				</tr>

			    <tr>
					<th>Average duration games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${avgminTimePlayed}" /> minutes and <c:out value="${avgsecTimePlayed}" /> seconds</td>
				</tr>
				<tr>
					<th>Total duration games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${minutesTotalPlayed}" /> minutes and <c:out value="${secondsTotalPlayed}" /> seconds</td>
				</tr>
				<tr>
					<th>Maximum duration of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${maxminTimePlayed}" /> minutes and <c:out value="${maxsecTimePlayed}" /> seconds</td>
				</tr>
				<tr>
					<th>Minimum duration of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${minminTimePlayed}" /> minutes and <c:out value="${minsecTimePlayed}" /> seconds</td>
				</tr>
			  </tbody>
			</table>
			<table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th colspan=2 scope="col" style="text-align:center">PLAYER STATISTICS</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
					<th width="50%" >TOTAL number of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${gamesPlayerTotal}" /></td>
				</tr>
				<tr>
					<th width="50%" >Number of games WON</th>
					<td style="font-size:20px;text-align:center"><c:out value="${gamesPlayerTotalWon}" /></td>
				</tr>
				<tr>
					<th width="50%" >Number of games WON with EASY difficulty</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numGamesWinEasyPlayer}" /></td>
				</tr>
				
				<tr>
					<th width="50%" >Number of games WON with MEDIUM difficulty</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numGamesWinMediumPlayer}" /></td>
				</tr>
				<tr>
					<th width="50%" >Number of games WON with DIFFICULT difficulty</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numGamesWinDifficultPlayer}" /></td>
				</tr>
				
				<tr>
					<th width="50%" >Number of games LOST</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numLostPlayer}" /></td>
				</tr>
				<tr>
					<th>Average duration games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${avgminTimePlayer}" /> minutes and <c:out value="${avgsecTimePlayer}" /> seconds</td>
				</tr>
				<tr>
					<th>Total duration games played by player</th>
					<td style="font-size:20px;text-align:center"><c:out value="${minutesTotalPlayer}" /> minutes and <c:out value="${secondsTotalPlayer}" /> seconds</td>
				</tr>
				<tr>
					<th>Maximum duration of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${maxminTimePlayer}" /> minutes and <c:out value="${maxsecTimePlayer}" /> seconds</td>
				</tr>
				<tr>
					<th>Minimum duration of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${minminTimePlayer}" /> minutes and <c:out value="${minsecTimePlayer}" /> seconds</td>
				</tr>
			  </tbody>
			  <thead>
			    <tr>
			      <th colspan=2 style="text-align:center" scope="col">Game stats</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
					<th width="50%">Total activated mines</th>
					<td style="font-size:20px;text-align:center"><c:out value="${minesActivated}" /></td>
				</tr>
				<tr>
					<th>Total flags placed</th>
					<td style="font-size:20px;text-align:center"><c:out value="${gamesPlacedFlags}" /></td>
				</tr>
			  </tbody>
			</table>
	</c:if>
	<c:if test="${fn:contains(principal.authorities, 'admin')}">
		<h2>GAME STATS</h2><br/>
			<table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th colspan=2 scope="col" style="text-align:center">GLOBAL STATISTICS</th>
			    </tr>
			  </thead>
			  <tbody>
			   <tr>
			      	<th width="50%">TOTAL games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${nTotal}" /></td>
			    </tr>
			    <tr>
					<th width="50%" >Number of games WON</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numWonPlayed}" /></td>
				</tr>
				<tr>
					<th width="50%" >Number of games WON with EASY difficulty</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numGamesWinEasyPlayed}" /></td>
				</tr>
				
				<tr>
					<th width="50%" >Number of games WON with MEDIUM difficulty</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numGamesWinMediumPlayed}" /></td>
				</tr>
				<tr>
					<th width="50%" >Number of games WON with DIFFICULT difficulty</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numGamesWinDifficultPlayed}" /></td>
				</tr>
				
				<tr>
					<th width="50%" >Number of games LOST</th>
					<td style="font-size:20px;text-align:center"><c:out value="${numLostPlayed}" /></td>
				</tr>

			    <tr>
					<th>Average duration games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${avgminTimePlayed}" /> minutes and <c:out value="${avgsecTimePlayed}" /> seconds</td>
				</tr>
				<tr>
					<th>Total duration games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${minutesTotalPlayed}" /> minutes and <c:out value="${secondsTotalPlayed}" /> seconds</td>
				</tr>
				<tr>
					<th>Maximum duration of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${maxminTimePlayed}" /> minutes and <c:out value="${maxsecTimePlayed}" /> seconds</td>
				</tr>
				<tr>
					<th>Minimum duration of games played</th>
					<td style="font-size:20px;text-align:center"><c:out value="${minminTimePlayed}" /> minutes and <c:out value="${minsecTimePlayed}" /> seconds</td>
				</tr>
			  </tbody>
			</table>
	</c:if>
</minesweeper:layout>