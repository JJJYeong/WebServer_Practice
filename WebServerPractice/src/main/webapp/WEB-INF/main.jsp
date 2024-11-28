<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-11-28
  Time: 오전 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>로그인한 유저 정보 : ${loginInfo}</h3>
    <a href="/user/list">회원 목록</a>
    <a href="/food/list">메뉴 목록</a><br/>
    <a href="/logout">로그아웃</a>
</body>
</html>
