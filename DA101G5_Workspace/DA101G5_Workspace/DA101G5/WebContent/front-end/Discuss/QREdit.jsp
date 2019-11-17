<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.res.model.*" %>
<%@ page import="com.que.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.hashtag.model.*" %>
<%@ page import="com.qhashtag.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    if(request.getAttribute("queVO")==null && request.getAttribute("resVO")==null){
    	response.sendRedirect(request.getContextPath()+"/front-end/Discuss/DiscussIndex.jsp");
    	return;
    }/*else if(request.getAttribute("queVO")!=null && request.getAttribute("resVO")!=null){
    	response.sendRedirect("/DA101G5/front-end/Discuss/DiscussIndex.jsp");
        return;
    }*/

List<HashtagVO> hashtaglist = null;
List<QhashtagVO> qhashtaglist = null;
  if(request.getAttribute("queVO")!=null){
       hashtaglist = (ArrayList)request.getAttribute("hashtaglist");//全hashtag
       qhashtaglist =(ArrayList)request.getAttribute("qhashtaglist");//que的hashtag
      pageContext.setAttribute("hashtaglist",hashtaglist);
      pageContext.setAttribute("qhashtaglist",qhashtaglist);
  }
%>    
<!DOCTYPE html>
<html>
<head>
<%@ include file="/front-end/file/head.file" %>
<style type="text/css">
    #picPreview,#videoPreview{
        max-height: 300px;
        display:none;
    }
    .oripic{
    max-height: 300px;
    }
    
    body{
	font-family:Microsoft JhengHei;
}
    
</style>
<meta charset="UTF-8">
<title>編輯</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
        $("input[name='newquefile']").change(function(){//que預覽
            
            if(this.files && this.files[0]){
                var filetype = this.files[0].type;
                var reader = new FileReader();
                if(filetype.indexOf('image')>-1){
                    reader.onload=function(e){
                        $('#picPreview').show();
                        $('#videoPreview').hide();
                        $('#picPreview').attr('src', e.target.result);
                    }   
                }
                if(filetype.indexOf('video')>-1){
                    reader.onload=function(e){
                        $('#videoPreview').show();
                        $('#picPreview').hide();
                        $('#videoPreview').attr('src', e.target.result);
                    }
                }
             }
                reader.readAsDataURL(this.files[0]);
            });
        
        $("input[name='newresfile']").change(function(){//que預覽
            
            if(this.files && this.files[0]){
                var filetype = this.files[0].type;
                var reader = new FileReader();
                if(filetype.indexOf('image')>-1){
                    reader.onload=function(e){
                        $('#picPreview').show();
                        $('#videoPreview').hide();
                        $('#picPreview').attr('src', e.target.result);
                    }   
                }
                if(filetype.indexOf('video')>-1){
                    reader.onload=function(e){
                        $('#videoPreview').show();
                        $('#picPreview').hide();
                        $('#videoPreview').attr('src', e.target.result);
                    }
                }
             }
                reader.readAsDataURL(this.files[0]);
            });
        $("form").submit(function(){//送出前確認
            var r=confirm('確定修改?');
                if (r==true){
                   return true; 
                }else{
                    return false;
                } 
        });
        
        $("#cancel").click(function(){//取消確認
            var r=confirm('確定取消?');
                if (r==true){
                   history.back(); 
                }
        });
        
        
   }) ;

</script>
</head>
<body>
<%@ include file="/front-end/file/header.jsp" %>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<div class="container">
<a href="<%=request.getContextPath()%>/front-end/Discuss/DiscussIndex.jsp">回討論區首頁</a><br>
<c:if test="${queVO!=null}">
<form action="<%=request.getContextPath()%>/que/que.do" method="POST" enctype="multipart/form-data" >
    <table class="table table-striped table-bordered">
    <tr>
        <td>問題編號:${queVO.que_id}</td>
		<td>發問者:${queVO.member_id}</td>
		<td>
			發文時間:
		    ${queVO.quetime.toString().substring(0,18)}
		</td>
		<td>讚:${queVO.quethumb }</td>
		<td>觀看次數:${queVO.watchcount }</td>
		<td>回應數:${queVO.rescount }</td>
	</tr>
	<tr>	
		<td colspan="6"><%--標題 --%>
		  <c:if test="${queVO.questatus==1 }">
		      文章標題:${queVO.quetitle }
		  </c:if>
		  <c:if test="${queVO.questatus==0 }">
                                文章已被隱藏
          </c:if>   
		</td>
	</tr>
	
	<tr>	
		<td colspan="6"><%--內容 --%>
		    <c:if test="${queVO.questatus==1 }">
		              內文:<br>${queVO.quecontent }
		    </c:if>
		    <c:if test="${queVO.questatus==0 }">
                                     文章已被隱藏
            </c:if>  
		</td>
	</tr>
	
	<tr>	
		<td colspan="6"><%--圖片 --%>
		  <c:if test="${queVO.questatus==1 }">
		  <c:if test="${queVO.quefile !=null && queVO.getFiletype().trim().substring(0,5) eq'image'}"> 
              <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}">
              <img class="oripic" src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}"/>
              </a>
               
               </c:if>
                <c:if test="${queVO.quefile !=null && queVO.getFiletype().trim().substring(0,5) eq'video'}">
                    <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}"><video class="oripic" controls loop>
                        <source src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}" type="video/webm">
                    </video></a>
               
               </c:if>
                <c:if test="${queVO.quefile !=null && queVO.getFiletype().trim().substring(0,5) eq'audio'}">
                    <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}"><video class="oripic" controls loop>
                        <source src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}" type="audio/webm">
                    </video></a>
               </c:if>
            <c:if test="${queVO.quefile ==null }">
                                        無圖片<br>
            </c:if>
		    </c:if>      
		           重新上傳圖片:<input id="newquefile" type="file" name="newquefile">${errorMsgs.quefile}
		</td>
		</tr>
		
		<tr>
		<td colspan="6"><%--qhashtag+all hashtag  --%>
			hashtag:<% to:
			    for(int i = 0;i<hashtaglist.size();i++){ 
			    	for(int j = 0;j<qhashtaglist.size();j++){
			        	if(hashtaglist.get(i).getHashtag_id().equals(qhashtaglist.get(j).getHashtag_id())){%>
			        		<input type="checkbox" name="hashtagArray" value="<%=hashtaglist.get(i).getHashtag_id()%>" checked="checked">
			        		<%=hashtaglist.get(i).getHashtag() %>
			        		<%continue to;
			        	}%>
			         <% }%>
			 <input type="checkbox" name="hashtagArray" value="<%=hashtaglist.get(i).getHashtag_id()%>" >
             <%=hashtaglist.get(i).getHashtag() %>
			    <% } %>
		</td>
    </tr>
    <tr>
        <td colspan="6">
                            上傳檔案預覽:<br>
           <img id="picPreview" src=""/>
           <video id="videoPreview" src="" controls="controls" loop="loop"></video>
        </td>
    </tr>
    </table><br>
    <input type="hidden" name="action" value="editqueconfirm" >
    <input type="hidden" name="que_id" value="${queVO.que_id}" >
    <input type="hidden" name="quefile" value="${queVO.quefile}" >
    <input type="hidden" name="filetype" value="${queVO.filetype}" >
    <input type="submit" value="修改" id="edit" ><input id="cancel" type ="button"  value="取消"></input>
</form>
</c:if>
</div>
<!------------------- 編輯回應 ------------------------>
<c:if test="${resVO!=null}">
<div class="container">
<form action="<%=request.getContextPath()%>/res/res.do" method="POST" enctype="multipart/form-data" >
    <table class="table table-striped table-bordered">
    <tr>
        <td>回應編號:${resVO.res_id}</td>
		<td>回應者:${resVO.member_id}</td>
		<td>
			回應時間:
		    ${resVO.restime.toString().substring(0,18)}
		</td>
		<td>讚:${resVO.resthumb }</td>
		
		
	</tr>
	
	<tr>	
		<td colspan="4"><%--內容 --%>
		    <c:if test="${resVO.resstatus==1 }">
		      內文:<br>${resVO.rescontent }
		    </c:if>
		    <c:if test="${resVO.resstatus==0 }">
                                     文章已被隱藏
            </c:if>  
		</td>
	</tr>
	
	<tr>	
		<td colspan="4"><%--圖片 --%>
		  <c:if test="${resVO.resstatus==1 }">
		  <c:if test="${resVO.resfile !=null && resVO.getFiletype().trim().substring(0,5) eq'image'}"> 
              <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}">
              <img class="oripic" src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}"/>
              </a>
               
               </c:if>
                <c:if test="${resVO.resfile !=null && resVO.getFiletype().trim().substring(0,5) eq'video'}">
                    <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}"><video class="oripic" controls loop>
                        <source src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}" type="video/webm">
                    </video></a>
               
               </c:if>
                <c:if test="${resVO.resfile !=null && resVO.getFiletype().trim().substring(0,5) eq'audio'}">
                    <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}"><video class="oripic" controls loop>
                        <source src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}" type="audio/webm">
                    </video></a>
               </c:if>
            <c:if test="${resVO.resfile ==null }">
                                        無圖片
            </c:if>
		    </c:if>      
		           重新上傳圖片:<input id="newresfile" type="file" name="newresfile">${errorMsgs.resfile}
		</td>
    </tr>
    <tr>
        <td colspan="4">
                        上傳檔案預覽:<br>
            <img id="picPreview" src=""/>
            <video id="videoPreview" src="" controls="controls" loop="loop"></video>
        </td>
    </tr>
    
    </table><br>
    <input type="hidden" name="action" value="editresconfirm" >
    <input type="hidden" name="res_id" value="${resVO.res_id}" >
    <input type="hidden" name="resfile" value="${resVO.resfile}" >
    <input type="hidden" name="filetype" value="${resVO.filetype}" >
    <input type="submit" value="修改" id="edit" ><input id="cancel" type ="button"  value="取消"></input>
</form>
</div>
</c:if>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<%@ include file="/front-end/file/footer2.file" %>

</body>
</html>