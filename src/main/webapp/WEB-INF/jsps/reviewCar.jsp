<!DOCTYPE html>
<html lang="en">
<head>
  <title>Review Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>Learning Spring 5.x MVC!</h2>
  <img src="${pageContext.request.contextPath}/images/1.jpg" style="height: 130px;">
  
  <form action="${pageContext.request.contextPath}/save-review-car" method="post">
  
  <p>User Car Review Page</p>            
  <table class="table table-bordered">
    <tbody>
      <tr>
        <td>Vendor</td>
        <td>${car.vendor}
        
         <input type="hidden" value="${car.vendor}" name="vendor"/>
        </td>
      </tr>
       <tr>
        <td>Model</td>
         <td>${car.model}
          <input type="hidden" value="${car.model}" name="model"/>
         </td>
      </tr>
      <tr>
        <td>Color</td>
         <td>${car.color}
           <input type="hidden" value="${car.color}" name="color"/>
         </td>
      </tr>
      <tr>
        <td>Power</td>
         <td>${car.power}
         
          <input type="hidden" value="${car.power}" name="power"/>
         </td>
      </tr>
      
      <tr>
        <td>Mileage</td>
        <td>${car.mileage}
        
         <input type="hidden" value="${car.mileage}" name="mileage"/>
        </td>
      </tr>
      
      
      
         <tr>
        <td>Image</td>
        <td> 
        
        
        <img src="${car.image}" style="height: 130px;">
        
         <input type="hidden" value="${car.image}" name="image"/>
         
        </td>
      </tr>
      
        <tr>
        <td><button type="button" class="btn btn-danger btn-lg">Back!</button></td>
        <td align="right"><button type="submit" class="btn btn-primary btn-lg">Save!</button>
        
        <a href="${pageContext.request.contextPath}/">
        <button type="button" class="btn btn-primary btn-lg">Show Cars!</button>
        
        </a></td>
      </tr>
      
    </tbody>
  </table>
  </form>
</div>


</body>
</html>
