<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
  <title>JSTL網頁測試</title>
  </head>
  
  <body>
    <c:out value="<<JSTL網頁測試>>"/><br><br>
  
    <b>你可接受的語言:</b><br>
    <c:out value="${header['Accept-Language']}"/><br><br>
    
    <b>你使用的瀏覽器:</b><br>
    <c:out value="${header['User-Agent']}"/><br><br>
    
    <b>收到的username請求參數:</b><br>
    <c:out value="${param.username}" default="未收到username請求參數"/><br><br>
    
    <b>特殊字元:</b><br>
    <c:out value="Hello World ! </我是 Tomcat>" escapeXml="true"/>
  </body>
</html>