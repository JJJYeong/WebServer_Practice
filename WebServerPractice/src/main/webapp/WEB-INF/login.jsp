<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-11-27
  Time: 오후 3:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>로그인</h1>
    <c:if test="${param.result == 'error'}">
        <h3>로그인 정보 다시 확인 후 로그인 해주세요.</h3>
    </c:if>
    <form action="" method="post">
        ID : <input type="text" name="id"><br/>
        PW : <input type="password" name="pw"><br/>
        <input type="checkbox" name="auto">자동로그인<br/>
        <button type="submit">로그인</button>
    </form>
    <a href="/register">회원가입</a>
</body>
</html>
