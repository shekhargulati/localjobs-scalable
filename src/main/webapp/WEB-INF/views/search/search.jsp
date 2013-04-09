<form id="jobSearchForm" class="form-horizontal">
	<div class="control-group">
		<div class="controls">
			<input type="text" id="skills" name="skills" class="input-xlarge"
				placeholder="Enter skills for which you want to search jobs"
				required>
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<textarea rows="3" id="location" class="input-xlarge"
				placeholder="Enter location near which you want to search jobs"
				required></textarea>
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button id="findJobsButton" type="submit" class="btn btn-success">Find
				Jobs</button>
		</div>
	</div>

</form>

<div id="results"></div>

	<script type="text/x-mustache-template" id="job-template">

    
    <div class="jobBox">
	   <h3>{{jobtitle}}</h3>
       <p> {{company}} </p>
      <address> {{address}} </address>
	  <p> {{skills}}</p>
	  <p> {{distance}} </p>
    </div>
    </script>
    
	<script src="/resources/js/jquery.tagsinput.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.loadmask.min.js"></script>
	<script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATvS5bY-3CkeiedEWtr5WFHEQFOi-9uYs&sensor=true">
    </script>
	<script src="/resources/js/underscore.js"></script>
	<script src="/resources/js/backbone.js"></script>
	<script src="/resources/js/mustache.js"></script>
	<script type="text/javascript">
	$( document ).ready( function() {
		$('#skills').tagsInput({
			defaultText : "add skills"
		});
	});
	</script>
	<script src="/resources/js/app.js"></script>
