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
  <h1>메뉴 목록</h1>
  <ul>
    <c:forEach var="dto" items="${list}">
      <li><a href="/food/read?fno=${dto.fno}">${dto.title}</a></li>
    </c:forEach>
  </ul>
  <a href="/food/register">메뉴 등록</a><br/>
  <a href="/main">메인으로</a>
  <a href="/logout">로그아웃</a>
</body>
</html>
