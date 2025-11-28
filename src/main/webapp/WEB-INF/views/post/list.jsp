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
        <a href="<%= request.getContextPath() %>/posts/new">글 작성</a><br>
        <p> 별명 : <%= ui.getNickname() %> </p>
    </nav>

    <% List<Post> posts = (List<Post>) request.getAttribute("posts");
        for (Post p : posts) {
    %>
        <div>
            <p>제목 : <%= p.getTitle() %></p>
            <p>작성자 : <%= p.getAuthor().getNickname() %></p>
            <p>내용 : <%= p.getContent() %></p>
            <hr>
        </div>
    <%
        }
    %>
</body>
</html>
