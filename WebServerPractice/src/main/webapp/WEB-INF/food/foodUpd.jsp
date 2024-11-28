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
    <h1>메뉴 수정</h1>
    <form action="" method="post">
        fno : <input type="text" name="fno" value="${dto.fno}" disabled><br/>
        title : <input type="text" name="title" value="${dto.title}"><br/>
        dueDate : <input type="date" name="dueDate" value="${dto.dueDate}"><br/>
        finished : <input type="checkbox" name="finished" ${dto.finished ? "checked" : ""}><br/>
        <button type="submit">수정</button>
    </form>
    <a href="/main">메인으로</a>
    <a href="/logout">로그아웃</a>
</body>
</html>
