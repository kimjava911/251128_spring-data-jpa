<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JPA 게시판 프로젝트</title>
</head>
<body>
    <h1>JPA 게시판 프로젝트</h1>
    <a href="<%= request.getContextPath() %>/">돌아가기</a><br>
    <p>
        <%= request.getAttribute("error") %>
    </p>
    <form action="<%= request.getContextPath() %>/login" method="post">
        <input name="username" placeholder="username"><br>
        <input name="password" type="password" placeholder="password"><br>
        <button>로그인</button>
    </form>
</body>
</html>
