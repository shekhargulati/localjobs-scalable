<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="resources/js/jquery.tmpl.min.js"></script>

<c:if test="${not empty message}">
<div class="${message.type.cssClass}">${message.text}</div>
</c:if>

<c:url value="/search" var="searchUrl" />

<form class="form-horizontal well" id="searchJobs" action="${searchUrl}" method="POST">
	<fieldset>
		<legend>Search Jobs</legend>
		<div class="control-group">
            <label class="control-label" for="skills">Skills</label>
            <div class="controls">
              <input type="text" class="input-xlarge" id="skills" placeholder="Comma Seperated List of Skills">
              <p class="help-block">Enter skills you want to search jobs for</p>
            </div>
         </div>
         
         <div class="control-group">
            <label class="control-label" for="address">Address</label>
            <div class="controls">
              <textarea rows="5" placeholder="Address" name="address" id="address"></textarea>
              <p class="help-block">Address near which you want to find jobs</p>
            </div>
         </div>
         
         <button class="btn btn-large btn-primary" type="submit" id="searchJobs">Search Jobs</button>
	</fieldset>
</form>

<div id="results">

</div>

<script type="text/javascript"> 

	$('#searchJobs').click(function(){
	    return getResult();
	});
	
    function getResult()
    {
    	var skills =  $("#skills").val();  
    	var address = $("#address").val();
    	var latitude= $("#latitude").val();
    	var longitude = $("#longitude").val();
    	var withinDistance = $("#withinDistance").val();

    	if(address){
    		$.get("resources/templates/jobs.html", function (template){
                $.get("jobs/near/"+address+"/"+skills  , function (data){ 
                    $("#results").html("<h3>We have found following Jobs for you..</h3>");
                    $.tmpl(template, data).appendTo("#results"); 
                });  

            }); 
    	}else if(latitude && longitude  && skills){
    		$.get("resources/templates/jobs.html", function (template){
                //alert(template);
                $.get("jobs/near/"+skills+"/?latitude="+latitude+"&longitude="+longitude , function (data){ 
                    $("#results").html("<h3>We have found following Jobs for you..</h3>");
                    $.tmpl(template, data).appendTo("#results"); 
                });  

            }); 
    	}else if(latitude && longitude){
    		$.get("resources/templates/jobs.html", function (template){
                //alert(template);
                $.get("jobs/near/"+"?latitude="+latitude+"&longitude="+longitude , function (data){ 
                    $("#results").html("<h3>We have found following Jobs for you..</h3>");
                    $.tmpl(template, data).appendTo("#results"); 
                });  

            }); 
    	}
        
        
        return false;
    }
    
    function getDetails(code)
    {
    	alert("***" + code);
        $.get("resources/template/jobdetails.html", function (template){
            $.get("/job/details/" + code, function (data){ 
                $("#dataRegion").html("");
                $.tmpl(template, data).appendTo("#dataRegion"); 
            });
        }); 
        return false;
    }

    getResult();

 </script>
