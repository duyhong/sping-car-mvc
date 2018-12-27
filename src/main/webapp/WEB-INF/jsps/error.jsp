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
  
</head>
<body>

<div class="container">
  <h2>Learning Spring 5.x MVC!!!!!!!!!!!!!!!!!!!!!!!</h2>
  <img src="${pageContext.request.contextPath}/images/1.jpg" style="height: 130px;">
  <div style="width: 60%;">
	<h1>Hey! transaction did not go through!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!</h1>  
	Cause : ${cause}
	requestedURI : ${requestedURI}
	
  </div>
</div>

</body>
</html>
