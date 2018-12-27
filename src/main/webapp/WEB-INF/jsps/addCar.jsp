<%@ taglib uri="http://www.springframework.org/tags/form" prefix="duy"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  
  <script>
  
  		$(document).ready(function(){
  			
  			$('#tphoto').change(function() {
  				var file = this.files[0]; //reading the selected file
  				getBase64(file)
  				.then(function (data) {
  					// data = base64 encoded image 
  					 $('#photo').val(data);
  					//<img src="" id="review-photo"  style="height: 80px;">
  					$('#review-photo').attr('src', data);
  				}).catch(function (e) {
  					alert(e);
  				});
  			});
  			
  			
  		});	

  		//This will give you select file into base64 encoding
  		//file - file to be converted!
		function getBase64(file) {
			return new Promise(function (resolve, reject) {
				const reader = new FileReader();
				reader.readAsDataURL(file);
				reader.onload = function() {
					resolve(reader.result);
				};
				reader.onerror = function(error) {
					reject(error);
				};
			});
		}
  		
  		function clearText(){
  			document.getElementById("emessage").innerHTML="";
  		}	
        //creating function in JavaScript!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  		function validateForm(){
        	var pmodel=window.document.carForm.model.value;
        	if(pmodel.length==0){
        		document.getElementById("emessage").innerHTML="Sorry! model cannot be empty!";
        		window.document.carForm.model.focus();
        		return;
        	}
        	
        	var pcolor=window.document.carForm.color.value;
        	if(pcolor.length==0){
        		document.getElementById("emessage").innerHTML="Sorry! color cannot be empty!";
        		window.document.carForm.color.focus();
        		return;
        	}
        	
        	var ppower=window.document.carForm.power.value;
        	if(ppower.length==0){
        		document.getElementById("emessage").innerHTML="Sorry! power cannot be empty!";
        		window.document.carForm.mobile.focus();
        		return;
        	}
        	//Now we have to submit form using JavaScript!!!!!!!!!!
        	document.carForm.submit();
  		}
  </script>
  
</head>
<body>

<div class="container">
  <h2>Learning Spring 5.x MVC!!!!!!!!!!!!!!!!!!!!!!!</h2>
  <img src="${pageContext.request.contextPath}/images/1.jpg" style="height: 130px;">
  <form name="carForm" action="${pageContext.request.contextPath}/add-car" method="post">
  <br/>
       <span class="alert alert-danger" id="emessage">
      </span>
      <hr/>
    <div class="form-group">
      <label for="vendor">Vendor:</label>
      <select class="form-control" id="vendor"  name="vendor" style="width: 50%;">
        <c:forEach items="${vendors}" var="pitem">
      			<option>${pitem}</option>
      	</c:forEach>
      </select>
      
    </div>
    <div class="form-group">
      <label for="model">Model:</label>
      <input type="text" class="form-control" id="model" placeholder="Enter Model" name="model" onkeydown="clearText()";>
    </div>
  
    <hr/>
     <div class="form-group">
      <label for="color">Color:</label>
      <input type="text" class="form-control" id="color" placeholder="Enter Color" name="color" onkeydown="clearText()";>
    </div>
    <hr/>
    
     <div class="form-group">
      <label for="power">Power:</label>
      <input type="text" class="form-control" id="power" placeholder="Enter Power" name="power" onkeydown="clearText()">
    </div>
    
    <div class="form-group">
      <label for="mileage">Mileage:</label>
      <input type="text" class="form-control" id="mileage" placeholder="Enter Mileage" name="mileage" onkeydown="clearText();">
    </div>
    
     <div class="form-group">
      <label for="image">Image:</label>
      <input type="file" class="form-control" id="tphoto"  name="tphoto" style="width: 35%;display: inline;">
       <input type="hidden" id="photo"  name="photo" style="width: 35%;display: inline;">
       <img src="" id="review-photo"  style="height: 80px;">
    </div>
    
    
    <button type="button" class="btn btn-primary btn-lg" onclick="validateForm();">Submit!</button>
    <a href="${pageContext.request.contextPath}/">
        <button type="button" class="btn btn-warning btn-lg">Show Cars!</button>
    </a>
  </form>
</div>

</body>
</html>
