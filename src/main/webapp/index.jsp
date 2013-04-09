<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>LocalJobs : Helps you find right jobs near your location</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 20px;
	padding-bottom: 40px;
}

/* Custom container */
.container-narrow {
	margin: 0 auto;
	max-width: 700px;
}

.container-narrow>hr {
	margin: 30px 0;
}

/* Main marketing message and sign up button */
.jumbotron {
	margin: 60px 0;
	text-align: center;
}

.jumbotron h1 {
	font-size: 72px;
	line-height: 1;
}

.jumbotron .btn {
	font-size: 21px;
	padding: 14px 24px;
}

/* Supporting marketing content */
.marketing {
	margin: 60px 0;
}

.marketing p+h4 {
	margin-top: 28px;
}
</style>
<link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="shortcut icon" href="resources/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="resources/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="resources/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="resources/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="resources/ico/apple-touch-icon-57-precomposed.png">
</head>

<body>

	<div class="container-narrow">

		<div class="masthead">
			<ul class="nav nav-pills pull-right">
				<sec:authorize access="isAuthenticated()">
					<li class="active"><a href="home">Home</a></li>
				</sec:authorize>

				<sec:authorize access="!isAuthenticated()">
					<li class="active"><a href="">Home</a></li>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<li><a href="signout">Sign Out</a></li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li><a href="signin">Sign in</a></li>
				</sec:authorize>
			</ul>
			<h3 class="muted">LocalJobs</h3>
		</div>

		<hr>

		<div class="jumbotron">
			<h1>Fastest way to find right Jobs!</h1>
			<p class="lead">The application will assist you in finding the
				right job near to your location and matching your skills. No need to
				relocate to a new place.</p>
			<a class="btn btn-large btn-success" href="signup">Sign up today</a><br />
			<br>
			<p clas="lead">Or</p>
			<!-- LINKEDIN SIGNIN -->
			<form name="li_signin" id="li_signin"
				action="<c:url value="/signin/linkedin"/>" method="POST">
				<button class="btn btn-large btn-success" type="submit">Sign
					In with LinkedIn</button>
			</form>
		</div>

		<hr>

		<div class="row-fluid marketing">
			<div class="span6">
				<h4>Jobs near to your location</h4>
				<p>You can find jobs closest to your current application</p>

				<h4>Matching your skills</h4>
				<p>Jobs that match your skills and experience</p>

				<h4>More than 150 Jobs</h4>
				<p>We have a big database of Jobs</p>
			</div>

			<div class="span6">
				<h4>Jobs on latest technologies</h4>
				<p>Jobs on latest technologies like MongoDB, OpenShift, Scala,
					Clojure, NoSQL,etc.</p>

				<h4>Free</h4>
				<p>It is free to signup and apply for jobs</p>

				<h4>Fast</h4>
				<p>Let you find jobs quickly.</p>
			</div>
		</div>

		<hr>

		<div class="footer">
			<p>
				Powered by <a href="https://openshift.redhat.com/" target="_blank">OpenShift</a>
			</p>
		</div>

	</div>
	<!-- /container -->

	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/bootswatch.js"></script>
	<script src="resources/js/bootstrap-tab.js"></script>
	<script src="resources/js/bootstrap-scrollspy.js"></script>

</body>
</html>
