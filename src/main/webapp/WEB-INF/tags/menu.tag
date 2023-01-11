<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, games, players, statistics or error"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		
		
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<petclinic:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Home</span>
				</petclinic:menuItem>
				
				<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'players'}" url="/players/list/"
						title="find players">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						<span>Find players</span>
					</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'gamesProgress'}"
						url="/board/listinprogress" title="games in progress">
						<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
						<span>Games in progress</span>
					</petclinic:menuItem>
				</sec:authorize>
				
				
				<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'gamesPlayed'}" 
					    url="/board/list" title="games played">
						<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
						<span>Games Played</span>
					</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize access="hasAuthority('player')">
					<petclinic:menuItem active="${name eq 'new_games'}" url="/board/setDifficulty"
						title="New Game">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						<span>New game</span>
				</petclinic:menuItem>
				</sec:authorize>


				<sec:authorize access="hasAuthority('player')">
					<petclinic:menuItem active="${name eq 'gamesPlayer'}" url="/board/listplayer"
						title="My games">
						<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
						<span>My games</span>
					</petclinic:menuItem>
				</sec:authorize>
			
				<sec:authorize access="isAuthenticated()">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-star"></span> 
							Stats <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
										</div>
										<div class="col-lg-8">
											<br>
											<p>
											<a href="<c:url value="/statistic/statistics" />"
											class="btn btn-primary btn-block btn-sm" style="background-color: rgb(255, 140, 198); border-radius: 6px; color: rgb(255, 236, 245);border: 2px solid #34302D;font-size: 15px;margin-left: -30px;">Statistics</a>
                         					</p>
										</div>
										
										<div class="col-lg-4">
										</div>
										<div class="col-lg-8">
											<br>
											<p>
											<a href="<c:url value="/statistic/rankings" />"
											class="btn btn-primary btn-block btn-sm" style="background-color: rgb(255, 140, 198); border-radius: 6px; color: rgb(255, 236, 245);border: 2px solid #34302D;font-size: 15px;margin-left: -30px;">Rankings</a>
                         					</p>
										</div>
										
										<sec:authorize access="hasAuthority('player')">
										<div class="col-lg-4">
										</div>
										<div class="col-lg-8">
											<br>
											<p>
											<a href="<c:url value="/achievements/myprofile" />"
											class="btn btn-primary btn-block btn-sm" style="background-color: rgb(255, 140, 198); border-radius: 6px; color: rgb(255, 236, 245);border: 2px solid #34302D;font-size: 15px;margin-left: -30px;">Achievs</a>
                         					</p>
										</div>
										</sec:authorize>

										<sec:authorize access="hasAuthority('admin')">
										<div class="col-lg-4">
										</div>
										<div class="col-lg-8">
											<br>
											<p>
											<a href="<c:url value="/achievements/list" />"
											class="btn btn-primary btn-block btn-sm" style="background-color: rgb(255, 140, 198); border-radius: 6px; color: rgb(255, 236, 245);border: 2px solid #34302D;font-size: 15px;margin-left: -30px;">Achievs</a>
                         					</p>
										</div>
										</sec:authorize>

										<sec:authorize access="hasAuthority('admin')">
										<div class="col-lg-4">
										</div>
										<div class="col-lg-8">
											<br>
											<p>
											<a href="<c:url value="/achievements/new" />"
											class="btn btn-primary btn-block btn-sm" style="background-color: rgb(255, 140, 198); border-radius: 6px; color: rgb(255, 236, 245);border: 2px solid #34302D;font-size: 14.5px;margin-left: -30px;">New achiev</a>
                         					</p>
										</div>
										</sec:authorize>
									</div>
								</div>
							</li>
							<li class="divider"></li>
						</ul></li>
				</sec:authorize>

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/players/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span> 
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
							

											<br>
											
											<p class="text-left">
                        					<sec:authorize access="hasAuthority('player')">
												 <a href="<c:url value="/players/myprofile" />"
													  class="btn btn-primary btn-block btn-sm" style="background-color: rgb(255, 140, 198); border-radius: 6px; color: rgb(255, 236, 245);border: 2px solid #34302D;font-size: 15px;margin-left: -30px;">My profile</a>
											 </sec:authorize>
                         					</p>
                         					
											<br>

											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm" style="background-color: rgb(255, 140, 198); border-radius: 6px; color: rgb(255, 236, 245);border: 2px solid #34302D;font-size: 15px;margin-left: -30px;">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>
