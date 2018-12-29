<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Show cars</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  
   <script type="text/javascript">
   			 var erowobj="";
   			$(document).ready(function(){
   				
   				$('#tphoto').change(function() {
   	  				var file = this.files[0]; //reading the selected file
   	  				getBase64(file)
   	  				.then(function (data) {
   	  					// data = base64 encoded image 
   	  					 $('#image').val(data);
   	  					//<img src="" id="review-photo"  style="height: 80px;">
   	  					$('#review-photo').attr('src', data);
   	  				}).catch(function (e) {
   	  					alert(e);
   	  				});
   	  			});
   				
   				$("#editConfirmBtn").click(function(){
					alert("@()@(@(@(@(@()))))"); 
					//Here we have to write code to send popup form data to the controller
					//AJAX only.
					var pdata = $('#cform').serialize();//cid, name, email ,mobile,gender,city
					//here we are not sending json data , we are sending form data
					//name=Pocker&email=pocker%40gmail.com&gender=Male&mobile=0292929229&photo=&city=Oakland&id=
					//$.get ,$.post
					$.ajax({
						type:'POST',
						url: "${pageContext.request.contextPath}/edit-car",
						data: pdata,
						dataType: 'json', //I want json response
						success: function(data){
							//below line will close the popup
							if(data.status=="success"){
								//Please write code to update the particular row
								erowobj.find('[tdname=vendor]').html($("#vendor").val());
								erowobj.find('[tdname=model]').html($("#model").val());
								erowobj.find('[tdname=color]').html($("#color").val());
								erowobj.find('[tdname=power]').html($("#power").val());
								erowobj.find('[tdname=mileage]').html($("#mileage").val());
								erowobj.find('[tdname=rowphoto]').attr("src",$("#image").val());
								
								$("#editCarModel").modal("hide");	
								
							}else{
								alert("Sorry! data could not be updated!!!!!!!!!!!!!!");
							}
						},
						 error: function (textStatus, errorThrown) {
				                Success = false;//doesnt goes here
				         }
					});	
					console.log("pdata: " + pdata);
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
   	
   			function deleteRow(cid){
   				//http://localhost:444/spring-profile-mvc/jdeleteCar?cid=1
   				$.get("${pageContext.request.contextPath}/jdeleteCar?cid="+cid,function(result){
					//printing the json response
					console.log(result);
					if(result.status="deleted"){
						$("#"+cid).hide();
					}
				});		
   			}
   			
   			function showEditModal(cid){
   				//below line gives you reference row object to be edited
   				var carRow=$("#"+cid);
   				//storing reference of current row inside a globle variable!@
   				erowobj=carRow;
   				var tvendor=carRow.find('[tdname=vendor]').html();
   				var tmodel=carRow.find('[tdname=model]').html();
   				var tcolor=carRow.find('[tdname=color]').html();
   				var tpower=carRow.find('[tdname=power]').html();
   				var tmileage=carRow.find('[tdname=mileage]').html();
   				//
   				var imageValue=carRow.find('[tdname=rowphoto]').attr("src");
   				
   				//setting name field inside the modal name
   				$("#cid").val(cid);
   				$("#vendor").val(tvendor);
   				$("#model").val(tmodel);
   				$("#color").val(tcolor);
   				$("#power").val(tpower);
   				$("#mileage").val(tmileage);
   				$("#review-photo").attr("src",imageValue);//<img id="review-tmileage" src="ahah.jpg"> 
   				$("#editCarModel").modal("show");		
   			}
   </script>
  
</head>
<body>

<div class="container">
  <h2>Learning Spring 5.x MVC!</h2>
  <img src="${pageContext.request.contextPath}/images/1.jpg" style="height: 130px;">
  
  <form action="${pageContext.request.contextPath}/save-review-car" method="post">
  
  <p>User Profile Review Page</p>            
  <table class="table table-bordered">
    <tbody>
      <tr style="background-color: fuchsia;">
        <td>Vendor</td>
        <td>Model</td>
         <td>Color</td>
          <td>Power</td>
           <td>Mileage</td>
            <td>Image</td>
      </tr>
      <c:forEach items="${cars}" var="car">
       <tr id="${car.cid}">
         <td tdname="vendor">${car.vendor}</td>
         <td tdname="model">${car.model}</td>
         <td tdname="color">${car.color}</td>
         <td tdname="power">${car.power}</td>
         <td tdname="mileage">${car.mileage}</td>
         <td>
	       <img tdname="rowphoto" id="${car.cid}_imageicon" src="${car.image}" style="height: 100px;"/>
	        <a href="javascript:deleteRow('${car.cid}');">
	        <img  src="${pageContext.request.contextPath}/images/delete.png"></a>
	        /
	        <a href="javascript:showEditModal('${car.cid}');">
	        <img src="${pageContext.request.contextPath}/images/edit.png"></a>
         </td>
      </tr>
      </c:forEach>
      
      <tr>
        <td>
        <a href="${pageContext.request.contextPath}/add-car">
        <button type="button" class="btn btn-danger btn-lg">Back!</button>
        </a>
        </td>
        <td align="right"></td>
        <td>&nbsp;</td>
          <td>&nbsp;</td>
            <td>&nbsp;</td>
              <td>&nbsp;</td>
      </tr>
      
    </tbody>
  </table>
  </form>
</div>

<form id="cform"  style="padding-left: 2rem; padding-right: 2rem;">
<div id="editCarModel" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Edit Car</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
						<div class="form-group">
							<label for="vendor">Vendor:</label> 
							<select	class="custom-select mr-sm-2" id="vendor" name="vendor">
								<option disabled>Choose...</option>

								<c:forEach items="${vendors}" var="vendor">
									<option value="${vendor}">${vendor}</option>
								</c:forEach>
							</select>
							
						</div>
						<div class="form-group">
							<label for="model">Model:</label> 
							<input type="text"
								class="form-control" id="model" placeholder="Enter model"
								name="model" value="">
						</div>
						<div class="form-group">
							<label for="color">Color:</label> 
							<input type="text"
								class="form-control" id="color" placeholder="Enter color"
								name="color" value="">
						</div>
						<div class="form-group">
							<label for="power">Power:</label> 
							<input type="text"
								class="form-control" id="power"
								placeholder="Enter power" name="power"
								value="">
						</div>
						<div class="form-group">
							<label for="mileage">Mileage:</label> 
							<input type="text"
								class="form-control" id="mileage" placeholder="Enter mileage"
								name="mileage" value="">
						</div>
						<div class="form-group">
							<label for="image">Image:</label> <br> 
							<img
								id="review-photo" alt="car image"
								src=""
								style="height: 100px;"> <br>
								
								<input type="file"
								class="form-control-file" id="tphoto" accept="image/*"> 
								
								<input
								id="image" name="image" type="hidden"
								value="">
						</div>
						
						<input type="hidden" id="cid" name="cid" value="">
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button id="editConfirmBtn" type="button" class="btn btn-success">Update</button>
				</div>
			</div>
		</div>
	</div>
	</form>
</body>
</html>
