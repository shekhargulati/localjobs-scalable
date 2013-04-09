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