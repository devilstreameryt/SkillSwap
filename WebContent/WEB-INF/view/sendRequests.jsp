<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Requests</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
        integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>


	<div class="container mx-auto mt-3">
	<h3 class="text-center mb-4">Send Requests</h3>
    <!-- Search -->
    <div class="w-50 mx-auto">
        <form method="get" action="${pageContext.request.contextPath}/user/sendRequests">
            <div class="input-group mb-3 mt-3">
                <select class="form-control" name="skillId" required>
                    <option value="" disabled selected>Choose Wanted Skill</option>
                    <c:forEach var="wanted" items="${wantedSkills}">
                        <c:if test="${wanted.skill != null}">
                            <option value="${wanted.skill.id}">
                                ${wanted.skill.skillName}
                            </option>
                        </c:if>
                    </c:forEach>
                </select>
                <button class="btn btn-outline-secondary ml-2" type="submit">Search</button>
            </div>
        </form>

       <c:if test="${not empty usersWithLevel}">
    	<h5>Users offering ${skill.skillName}:</h5>
		    <ul class="list-group">
		    
		    
		      <c:forEach var="user" items="${usersWithLevel}">
			    <li class="list-group-item d-flex justify-content-between align-items-center">
			        <span>
			            ${user.key.firstName} ${user.key.lastName} - ${user.value}
			        </span>
			        
			        <form action="${pageContext.request.contextPath}/user/sendRequestToUser" method="post" class="d-inline">
			            <input type="hidden" name="receiverId" value="${user.key.id}">
			            <input type="hidden" name="skillId" value="${skill.id}">
			
			            <c:choose>
			                <c:when test="${reqeustStatus[user.key.id]}">
			                    <button class="btn btn-success btn-sm" disabled>Done</button>
			                </c:when>
			                <c:otherwise>
			                    <button class="btn btn-info btn-sm send-btn">Send</button>
			                </c:otherwise>
			            </c:choose>
			        </form>
			
			       
			    </li>
			</c:forEach>

		        
		        
		    </ul>
		</c:if>



        <div class="d-flex justify-content-end mt-3">
            <a class="btn btn-light" href="dashboard" role="button">Back</a>
        </div>
    </div>
</div>






	
</body>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
    crossorigin="anonymous"></script>
    
    
 



</html>