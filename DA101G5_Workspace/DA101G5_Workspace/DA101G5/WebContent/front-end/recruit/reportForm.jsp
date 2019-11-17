<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String recruit_id = null;
    if(request.getParameter("recruit_id")==null){
    	response.sendRedirect(request.getContextPath()+"/front-end/recruit/RecruitIndex.jsp");//直接打網址進來就重導向派單首頁
    }else{
    	recruit_id = request.getParameter("recruit_id");
    }
    pageContext.setAttribute("recruit_id",recruit_id);
%>    
<!DOCTYPE html>
<html>
<head>
<%@ include file="/front-end/file/head.file" %>
<style>
table{
    margin-left:auto;
    margin-right:auto;
}

body{
	font-family:Microsoft JhengHei;
}

</style>
<meta charset="UTF-8">
<title>填寫檢舉單</title>
</head>
<body>
<%@ include file="/front-end/file/header.jsp" %>
<br>
<br>
<br>
<br>
<br>
<br>
    <form action="<%=request.getContextPath()%>/recrepo/recrepo.do" method="POST">
        <table>
            <tr>
                <td>被檢舉的派單編號:${pageScope.recruit_id}</td>
            </tr>
            <tr>
                <th>檢舉理由:<br>
                    <textarea name="repocontent" cols="50" rows="10" required></textarea>${errorMsgs.repocontent}
                </th>
            </tr>
            <tr>
                <td><input type="submit" value="送出"></td>
            </tr>
        </table>
        <input type="hidden" name="recruit_id" value="${pageScope.recruit_id}">
        <input type="hidden" name="action" value="reportrecruit">
    </form>
    <br>
<%@ include file="/front-end/file/footer2.file" %>
</body>
</html>