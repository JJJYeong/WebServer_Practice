<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 21.
  Time: 오후 4:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>회원정보</h1>
    아이디 : <input type="text" name="id" value="${dto.id}" disabled><br/>
    비밀번호 : <input type="password" name="pw" value="${dto.pw}" disabled><br/>
    이름 : <input type="text" name="name" value="${dto.name}" disabled><br/>
    <form action="/user/delete?id=${dto.id}" method="post">
        <button><a href="/user/update?id=${dto.id}">정보 수정</a></button>
        <button type="submit">회원 탈퇴</button><br/>
        <a href="/main">메인으로</a>
        <a href="/logout">로그아웃</a>
    </form>
</body>
</html>
