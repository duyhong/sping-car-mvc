<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <!-- Below is jQuery library -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  
  <script type="text/javascript">
  		//wrtting ready handler for the jQuery
  		//every function is an object in JavaScript!
  		 //When dom is ready this passed callback method will be callled back
  		 $(document).ready(function(){
				$("input").keyup(function(){
						$('#emessage').html("");
				});	
	  		 		
  			 	//document.getElementById("login")
				//$("#login") - this will give us an object of button
				$("#login").click(function(){
						var pusername=$("#username").val();
						var ppassword=$("#password").val();
						if(pusername.length==0){
							$('#emessage').html("Hey! username cannot be blank!");
							$('#pusername').focus();
							return;	
						}
						if(ppassword.length==0){
							$('#emessage').html("Hey! password cannot be blank!");
							$('#ppassword').focus();
							return;	
						}
						
						//$("#customerForm").submit();
						//jQuery syntax to submit the form
						$.post("${pageContext.request.contextPath}/jauth",{username:pusername,password:ppassword},function(result){
							//printing the json response
							console.log(result);
							$("#imagepath").attr("src","${pageContext.request.contextPath}/images/"+result.status+".jpg");
						});
						
				});
  		 });		
  
  </script>
  
</head>
<body>

<div class="container">
  <h2>Learning Spring 5.x MVC!!!!!!!!!!!!!!!!!!!!!!!</h2>
  <img src="${pageContext.request.contextPath}/images/1.jpg" style="height: 130px;">
  <div style="width: 60%;">
  <form id="customerForm" name="customerForm"  method="post" action="${pageContext.request.contextPath}/auth">
  <br/>
       <span class="alert alert-danger" id="emessage">
      </span>
      <hr/>
    <div class="form-group">
      <label for="username">Username:</label>
      <input type="text" class="form-control" id="username"  placeholder="Enter Username" name="username">
    </div>
    <div class="form-group">
      <label for="email">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter Password" name="password";>
    </div>
    <button type="button" class="btn btn-primary btn-lg" id="login">Login!</button>
    <hr/>
    <img id="imagepath" src="${pageContext.request.contextPath}/images/${imageName}.jpg"/>
    
  </form>
  </div>
</div>

</body>
</html>
