<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.RequestDispatcher" %>        
<%
     if(request.getParameter("que_id").trim().length()==0||request.getParameter("que_id")==null){
    	 response.sendRedirect("/front-end/Discuss/DiscussIndex.jsp");
         return;
     }else if(request.getParameter("action").trim().length()==0||request.getParameter("action")==null ){
         response.sendRedirect("/front-end/Discuss/DiscussIndex.jsp");
         return;
     }else{
    	RequestDispatcher reqTruck = request.getRequestDispatcher("/que/que.do");
        reqTruck.forward(request,response);
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