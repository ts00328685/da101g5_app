<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(request.getParameter("que_id")==null || request.getParameter("res_id")==null){
	    response.sendRedirect("/back-end/qrreport/QRReportIndex.jsp");
	    return;//2者皆不可能為空
	}else if(request.getParameter("que_id").trim().length()==0 && request.getParameter("res_id").trim().length()==0){
        response.sendRedirect("/back-end/qrreport/QRReportIndex.jsp");
        return;//2者都空格
    }else if(request.getParameter("que_id").trim().length()!=0 && request.getParameter("res_id").trim().length()!=0){
        response.sendRedirect("/back-end/qrreport/QRReportIndex.jsp");
        return;//2者都有值
    }else if(request.getParameter("action").trim().length()==0||request.getParameter("action")==null){
    	response.sendRedirect("/back-end/qrreport/QRReportIndex.jsp");
        return;//action無值or空
    }else{
        request.getRequestDispatcher("/qrreport/qrreport.do").forward(request,response);
    }

    
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>