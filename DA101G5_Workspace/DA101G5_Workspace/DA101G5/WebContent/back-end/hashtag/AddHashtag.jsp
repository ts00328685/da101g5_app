<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/hashtag/hashtag.do" method="post">
    <table>
        <tr>
            <td>新增hashtag:</td><td><input type="text" name="hashtag" required></td>
        </tr>
    </table>
    <input type="submit" value="新增hashtag">
    <input type="hidden" name="action" value="addhashtagconfirm">
</form>
</body>
</html>