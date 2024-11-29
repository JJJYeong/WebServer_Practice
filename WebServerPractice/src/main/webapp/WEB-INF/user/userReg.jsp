<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-11-21
  Time: 오전 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>회원가입</h1>
    <c:if test="${param.result == 'error'}">
        <h3>이미 존재하는 아이디입니다.</h3>
    </c:if>
    <form action="" method="post">
        아이디 : <input type="text" name="id"><br/>
        비밀번호 : <input type="password" name="pw"><br/>
        이름 : <input type="text" name="name"><br/>
        <button type="submit">가입하기</button>
    </form>
</body>
</html>
