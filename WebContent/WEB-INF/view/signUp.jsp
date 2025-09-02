<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
</head>
<body>

 <div class="d-flex justify-content-center align-items-center vh-100">
 
        <f:form action="register" modelAttribute="user" class="container p-4 shadow rounded bg-light" style="max-width: 400px;" method="post" >
            <div class="form-group row">
                <div class="col">
                    <label for="firstName">First Name</label>
                    <f:input type="text" path="firstName" class="form-control" id="firstName" placeholder="Enter first name"/>
                </div>
                <div class="col">
                    <label for="lastName">Last Name</label>
                    <f:input type="text" path="lastName" class="form-control" id="lastName" placeholder="Enter last name"/>
                </div>
            </div>


            <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <f:input type="email" path="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email address"/>
            </div>
            
            <div class="form-group mt-3">
                <label for="exampleInputPassword1">Password</label>
                <f:input type="password" path="password" class="form-control" id="exampleInputPassword1" placeholder="Password"/>
            </div>

           <c:if test="${not empty errorFN}">
				<div class="alert alert-danger mt-3">
				    ${errorFN}
				</div>
			</c:if>
			
			<c:if test="${not empty errorLN}">
				<div class="alert alert-danger mt-3">
				    ${errorLN}
				</div>
			</c:if>
			
			
			<c:if test="${not empty errorE}">
				<div class="alert alert-danger mt-3">
				    ${errorE}
				</div>
			</c:if>
			
            <c:if test="${not empty errorP}">
		        <div class="alert alert-danger mt-3">
		            ${errorP}
		        </div>
    		</c:if>

			<c:if test="${not empty errorEE}">
				<div class="alert alert-danger mt-3">
				    ${errorEE}
				</div>
			</c:if>
    		
    		
    	
            <button type="submit" class="btn btn-dark mt-4 w-100">Sign Up</button>
        </f:form>
    </div>

</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
    crossorigin="anonymous"></script>


</html>