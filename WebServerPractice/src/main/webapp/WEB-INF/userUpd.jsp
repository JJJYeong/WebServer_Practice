<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-11-27
  Time: 오전 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>회원 수정</h1>
    <form action="" method="post">
        아이디 : <input type="text" name="id" value="${dto.id}" disabled><br/>
        비밀번호 : <input type="password" name="pw" value="${dto.pw}"><br/>
        이름 : <input type="text" name="name" value="${dto.name}"><br/>
        <button type="submit">수정하기</button><br/>
        <a href="/user/list">목록으로</a>
    </form>
</body>
</html>
