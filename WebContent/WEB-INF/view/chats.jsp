<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Chats</title>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    
</head>
<style>
    .person-card {
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 10px 15px;
        border: 1px solid #ddd;
        border-radius: 10px;
        margin-bottom: 10px;
        background-color: #f8f9fa;
    }
    .person-info {
        display: flex;
        align-items: center;
        gap: 10px;
    }
    .person-info img {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        object-fit: cover;
    }
    .container{
        width: 642px; 
    }
</style>
<body>

	
<div class="container mt-4">
    	<h3 class="text-center mb-4">My Chats</h3>

    
    <!-- Person 1 -->
    <c:forEach var="chat" items="${chats}">
	    <div class="person-card">
	        <div class="person-info">
	            <img src="${pageContext.request.contextPath}/user/photo/${chat.userSender.id == currentUser.id 
				    ? chat.userReceiver.id 
				    : chat.userSender.id}" 
				    width="50" height="50" class="rounded-circle" />

	            <span>
					<c:out value="${chat.userSender.id == currentUser.id 
								    ? chat.userReceiver.fullName 
								    : chat.userSender.fullName}"/>
	            </span>
	        </div>
	        <a href="chats/${chat.id}" class="btn btn-primary sendBtn">Open</a>
	    </div>
	</c:forEach>


    


</div>

</body>
</html>