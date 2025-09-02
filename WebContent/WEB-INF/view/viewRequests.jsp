<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Requests</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
        integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
        
</head>

<style>
.request-td {
    max-width: 200px; 
    white-space: nowrap; 
    overflow: hidden; 
    text-overflow: ellipsis;
}



</style>

<body>

	<div class="container mx-auto mt-3">
    <h3 class="text-center mb-4">My Requests</h3>

    <table class="table table-bordered text-center align-middle">
        <thead class="table-light">
            <tr>
                <th>Receiver Name</th>
                <th>Skill</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="req" items="${requestsData}">
                <tr>
                    <td class="request-td">
                        ${req.recieverName}
                    </td>
                    <td class="request-td">
                        ${req.skillName}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${req.status == 'ACCEPTED'}">
                                <span class="btn btn-success btn-sm">Accepted</span>
                            </c:when>
                            <c:when test="${req.status == 'PENDING'}">
                                <span class="btn btn-warning btn-sm">Pending</span>
                            </c:when>
                            <c:when test="${req.status == 'REJECTED'}">
                                <span class="btn btn-dark btn-sm">Rejected</span>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
	 


	
	

</body>
</html>