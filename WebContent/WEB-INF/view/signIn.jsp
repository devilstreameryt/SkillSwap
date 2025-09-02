<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
</head>

<body>

    <div class="d-flex justify-content-center align-items-center vh-100">
        <f:form action="login" method="post" modelAttribute="user" class="container p-4 shadow rounded bg-light" style="max-width: 400px;">
            <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <f:input type="email" class="form-control" path="email" id="exampleInputEmail1" aria-describedby="emailHelp"/>
            </div>
            <div class="form-group mt-3">
                <label for="exampleInputPassword1">Password</label>
                <f:input type="password" path="password" class="form-control" id="exampleInputPassword1"/>
            </div>

            <!-- Error message -->
            <div id="errorMessage" class="alert alert-danger mt-3 d-none">
                Incorrect email or password!
            </div>

			<c:if test="${not empty loginError}">
 				  <div class="alert alert-danger">${loginError}</div>
			</c:if>
			

            <button type="submit" class="btn btn-primary mt-4 w-100">Sign In</button>
        </f:form>
    </div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
    crossorigin="anonymous"></script>

</html>