<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SkillSwap – A Skill Exchange Platform</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
</head>
<body>

 <div class="container">
        <div class="text-right mt-4 mr-5 mb-5">
            <a href="login" type="button" class="btn btn-primary px-4 py-2">Sign In</a>
        </div>


        <div class="row">
            <div class="col-8 pt-4 ">
                <h2 class="pb-3">Find the right skill match today</h2>
                <h5>Join a growing community of professionals</h5>


                <div class="mt-5">
                    <a href="register" type="button" class="btn btn-dark px-4 py-2 ">Sign Up</a>
                </div>

            </div>
            <div class="col-4">
				<img src="${pageContext.request.contextPath}/resources/images/Screenshot_1.png" alt="SkillSwap">
            </div>
        </div>
    </div>



</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</html>