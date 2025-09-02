<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notifications</title>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
        integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

	    
<div class="container mt-4">

	<h3 class="text-center mb-4">Notifications</h3>

    <!-- Card -->
    <div class="d-flex align-items-center justify-content-between border p-2 mb-2 person-card">
        
        
       
     <table class="table table-bordered">
    <thead>
        <tr>
            <th>Sender</th>
            <th>Skill</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="req" items="${receivedRequests}">
            <tr>
            <c:if test="${req.status == 'PENDING'}">
                <td>${req.sender.firstName} ${req.sender.lastName}</td>
                <td>${req.notifications.skill.skillName}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/user/respondRequest" method="post">
                            <input type="hidden" name="requestId" value="${req.id}">
                            <button href="" type="submit" name="action" value="ACCEPT" class="btn btn-success btn-sm">Accept</button>
                            <button type="submit" name="action" value="REJECT" class="btn btn-danger btn-sm">Reject</button>
                        </form>
                    </c:if>
                    
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

       
       
       
    </div>

    </div>

</body>


</html>