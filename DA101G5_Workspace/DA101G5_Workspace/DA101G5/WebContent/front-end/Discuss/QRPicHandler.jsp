<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.io.*"%>
<%@page import="com.que.model.*"%>
<%@page import="com.res.model.*"%>
<%
    out.clear(); 
    out = pageContext.pushBody(); 
/*以上2行是為了避免拋出IllegalStateException  
這是web容器生成的servlet代碼中有out.write(””)，這個和JSP中調用的response.getOutputStream()產生衝突. 
即Servlet規範說明，不能既調用 response.getOutputStream()，又調用response.getWriter()，無論先調用哪一個，在調用第二個時候應會拋出 IllegalStateException，因為在jsp中，out變量是通過response.getWriter得到的，在程序中既用了response.getOutputStream，又用了out變量，故出現以上錯誤。 
解決方案： 
1.在程序中添加： 
out.clear(); 
out = pageContext.pushBody(); 
就可以了； 

2，不要在%〕〔%之間寫內容包括空格和換行符

3，在頁面寫入圖片的時候，需要flush（） 
　OutputStream output=response.getOutputStream(); 
　output.flush(); 
4，在頁面確定寫入<meta http-equiv=”Content-Type” content=”text/html; charset=gb2312”> 
*/    
    String article_id = request.getParameter("article_id");
	QueService queSvc = new QueService();
	ResService resSvc = new ResService();
	QueVO queVO = null;
	ResVO resVO = null;
	/*判斷文章是回應/問題*/
	if (article_id.startsWith("RS")) {//回應
		resVO = resSvc.findResByPK(article_id);
	    byte[] file = resVO.getResfile();
	    String filetype = resVO.getFiletype();
	    String subtype = filetype.substring(filetype.lastIndexOf("/")+1);//副檔名取得
	    String filename = "download."+subtype;
	        /*判斷檔案格式*/
	        if (filetype.indexOf("image") > -1) {//圖片
	            response.setContentType("image/jpeg");
	            ServletOutputStream sos = response.getOutputStream();
	            //response.setHeader("Content-Disposition", "attachment;filename=\""+filename+"\"");//指定下載的檔案名稱 點了就下載
	            response.setHeader("Content-Disposition", "filename=\""+filename+"\"");//指定下載的檔案名稱
	            try {
	                sos.write(file);
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
	            ServletOutputStream sos = response.getOutputStream();//拿管子
	          //response.setHeader("Content-Disposition", "attachment;filename=\""+filename+"\"");//指定下載的檔案名稱 點了就下載
	            response.setHeader("Content-Disposition", "filename=\""+filename+"\"");//指定下載的檔案名稱
	            try {
	                sos.write(file);
	                sos.flush();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } finally {
	                if (sos != null) {
	                    sos.close();
	                }
	            }
	        }

	} else {//問題
		queVO = queSvc.findQueByPK(article_id);
		byte[] file = queVO.getQuefile();//拿檔案
		String filetype = queVO.getFiletype();
		String subtype = filetype.substring(filetype.lastIndexOf("/")+1);//副檔名取得
        String filename = "download."+subtype;
		/*判斷檔案格式*/
		if (filetype.indexOf("image") > -1) {//圖片
			response.setContentType("image/jpeg");
			//response.setHeader("Content-Disposition", "attachment;filename=\""+filename+"\"");//指定下載的檔案名稱 點了就下載
			response.setHeader("Content-Disposition", "filename=\""+filename+"\"");//指定下載的檔案名稱
			ServletOutputStream sos = response.getOutputStream();//拿管子
			try {
				sos.write(file);
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
				sos.write(file);
				sos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (sos != null) {
					sos.close();
				}
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="Content-Type" charset="UTF-8"><%-- http-equiv="Content-Type" 解決out跟getOutputStream衝突的辦法3 --%>
<title>Insert title here</title>
</head>
<body>

</body>
</html>