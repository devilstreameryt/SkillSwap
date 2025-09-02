<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Messages</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
    crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
    .chat-header {
        max-width: 600px;
        margin: 20px auto 0 auto;
        text-align: center;
        font-weight: bold;
        font-size: 1.5rem;
    }
    .chat-container {
        max-width: 600px;
        margin: 0 auto;
        border: 1px solid #ddd;
        border-radius: 10px;
        padding: 20px;
        height: 400px;
        overflow-y: auto;
        background: #f8f9fa;
        display: flex;
        flex-direction: column;
    }
    .message {
        padding: 10px 15px;
        border-radius: 15px;
        margin-bottom: 10px;
        max-width: 70%;
    }
    .from-me {
        background-color: #0d6efd;
        color: white;
        align-self: flex-end;
    }
    .from-them {
        background-color: #e2e3e5;
        color: black;
        align-self: flex-start;
    }
    .chat-input {
        max-width: 600px;
        margin: 10px auto;
        display: flex;
        gap: 10px;
    }
</style>
</head>
<body>

    <!-- عنوان الشات -->
    <div class="chat-header mb-3">
        <c:out value="${chat.userSender.id == currentUser.id ? chat.userReceiver.fullName : chat.userSender.fullName}" />
    </div>

    <!-- عرض الرسائل -->
		    <div class="chat-container">
		    <c:forEach var="msg" items="${messages}">
		        <div class="message ${msg.sender.id == currentUser.id ? 'from-me' : 'from-them'}">
		            <c:out value="${msg.messageText}" />
		        </div>
		    </c:forEach>
		</div>


    <!-- إرسال رسالة جديدة -->
    <form method="post" action="${pageContext.request.contextPath}/user/chats/${chat.id}/send" class="chat-input">
        <input type="text" name="content" class="form-control" placeholder="Type a message..." required />
        <button type="submit" class="btn btn-primary">Send</button>
    </form>

</body>

<script>
    const chatBox = document.querySelector(".chat-container");
    chatBox.scrollTop = chatBox.scrollHeight;
</script>

</html>



