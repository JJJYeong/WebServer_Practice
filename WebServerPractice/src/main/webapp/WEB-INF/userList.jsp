<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 21.
  Time: 오전 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>회원목록</h1>
  <ul>
    <c:forEach var="dto" items="${list}">
      <li>${dto.id} <a href="/user/info?id=${dto.id}">${dto.name}</a></li>
    </c:forEach>
  </ul>
  <a href="/logout">로그아웃</a>
</body>
</html>
