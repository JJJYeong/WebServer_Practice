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
    <h1>메뉴 상세</h1>
    fno : <input type="text" name="fno" value="${dto.fno}" disabled><br/>
    title : <input type="text" name="title" value="${dto.title}" disabled><br/>
    dueDate : <input type="date" name="dueDate" value="${dto.dueDate}" disabled><br/>
    finished : <input type="checkbox" name="finished" ${dto.finished ? "checked" : ""} disabled><br/>
    <form action="/food/delete?fno=${dto.fno}" method="post">
        <a href="/food/update?fno=${dto.fno}">수정하기</a>
        <button type="submit">삭제</button>
    </form>
    <a href="/food/list">목록으로</a>
    <a href="/main">메인으로</a>
    <a href="/logout">로그아웃</a>
</body>
</html>
