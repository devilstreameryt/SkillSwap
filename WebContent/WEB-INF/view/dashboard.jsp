<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
        integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<style>
    .card {
        margin-right: 20px;
    }

    .card:last-child {
        margin-right: 0;
    }
</style>

<body>

<div class="container mt-3">
    <!-- Header -->
    <div class="d-flex justify-content-between align-items-center p-3 bg-light shadow rounded">
    <h2 class="m-0">
        Welcome back, <c:out value="${user.firstName}"/>
    </h2>
    <div class="d-flex align-items-center">
        <a href="chats" class="fa fa-comments fa-lg text-dark mr-3 position-relative" aria-hidden="true"></a>

        <a href="notifications" class="fa fa-bell fa-lg text-dark mr-3 position-relative" aria-hidden="true">
            <c:if test="${pendingCount > 0}">
                <span style="
                    position: absolute;
                    top: -10px;
                    right: -7px;
                    background: red;
                    color: white;
                    border-radius: 50%;
                    padding: 2px 6px;
                    font-size: 12px;
                ">
                    ${pendingCount}
                </span>
            </c:if>
        </a>

        <a href="profile" class="fa fa-user fa-lg text-dark mr-3" aria-hidden="true"></a>
        <a href="logout" class="fa fa-sign-out fa-lg text-dark" aria-hidden="true"></a>
    </div>
</div>


    <!-- Cards Section -->
    <div class="mt-5">
        <!-- Row 1 -->
        <div class="d-flex justify-content-center gap-4 mb-4">
            <div class="card shadow-sm" style="width: 18rem;">
                <div class="card-body text-center">
                    <h2 class="card-title">Skills Offered</h2>
                   <h2 class="card-text mt-4">
                   		<c:out value="${offeredSkills}"/>
                   </h2> 
                </div>
            </div>

            <div class="card shadow-sm" style="width: 18rem;">
                <div class="card-body text-center">
                    <h2 class="card-title">Skills Wanted</h2>
                    <h2 class="card-text mt-4">
                    	<c:out value="${wantedSkills}"/>
                    </h2>
                </div>
            </div>
            
        </div>

        <!-- Row 2 -->
        <div class="d-flex justify-content-center gap-4">
            
            <a href="sendRequests" type="button" class="btn btn-outline-secondary mr-4">Send Swap Request</a>
            <a href="myRequests" type="button" class="btn btn-outline-info">View Swap Request</a>

            
        </div>
    </div>
    </div>

</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
    crossorigin="anonymous"></script>


</html>