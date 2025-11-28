<%@ page import="kr.java.jpa.model.entity.UserInfo" %>
<%@ page import="kr.java.jpa.model.entity.Post" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JPA 게시판 프로젝트</title>
</head>
<body>
<h1>JPA 게시판 프로젝트</h1>
<%
    UserInfo ui = (UserInfo) session.getAttribute("userInfo");
%>
    <nav>
        <a href="<%= request.getContextPath() %>/logout">로그아웃</a><br>
        <a href="<%= request.getContextPath() %>/posts">글 목록</a><br>
        <p> 별명 : <%= ui.getNickname() %> </p>
    </nav>

<%--    <form name="postForm" action="<%= request.getContextPath() %>/posts/new" method="post">--%>
    <form name="postForm" method="post">
        <input name="title" placeholder="title"><br>
        <textarea name="content" placeholder="content"></textarea><br>
        <button>글 작성</button>
    </form>
</body>
</html>
