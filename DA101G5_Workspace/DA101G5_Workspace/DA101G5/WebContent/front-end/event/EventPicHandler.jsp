<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="com.event.model.*" %>    
    
<%
out.clear();
out = pageContext.pushBody();

String event_id =null;

if(request.getParameter("event_id")==null){//沒參數進來 回活動首頁
    response.sendRedirect("http://localhost:8081/DA101G5/front-end/event/EventIndex.jsp");
}else{
    event_id = request.getParameter("event_id");
}

EventService eventSvc = new EventService();

EventVO eventVO = eventSvc.findEventByPK(event_id);

byte[] eventfile = eventVO.getEvpic();

String filetype = eventVO.getFiletype();

String subtype = filetype.substring(filetype.lastIndexOf("/")+1);//副檔名取得
String filename = "download."+subtype;

if (filetype.indexOf("image") > -1) {//圖片
    response.setContentType("image/jpeg");
    ServletOutputStream sos = response.getOutputStream();
    //response.setHeader("Content-Disposition", "attachment;filename=\""+filename+"\"");//指定下載的檔案名稱 點了就下載
    response.setHeader("Content-Disposition", "filename=\""+filename+"\"");//指定下載的檔案名稱
    try {
        sos.write(eventfile);
        sos.flush();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (sos != null) {
            sos.close();
        }
    }
} else {//影片/聲音
   
    response.setContentType("video/webm");
    ServletOutputStream sos = response.getOutputStream();
  //response.setHeader("Content-Disposition", "attachment;filename=\""+filename+"\"");//指定下載的檔案名稱 點了就下載
    response.setHeader("Content-Disposition", "filename=\""+filename+"\"");//指定下載的檔案名稱
    try {
        sos.write(eventfile);
        sos.flush();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (sos != null) {
            sos.close();
        }
    }
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