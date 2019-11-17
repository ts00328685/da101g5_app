<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<% session.setAttribute("location",request.getRequestURI()); %>
<% session.setAttribute("location1",request.getServletPath()); %>
<%-- <c:if test="${isSystemManager==false}"> --%>
<!-- <script> -->


<!--  swal("請先登入",  -->
<!--    {button: false, -->
<!--  }); -->
<!-- </script> -->
<%-- <% session.setAttribute("isSystemManager",null); %> --%>
<%-- </c:if> --%>

<style>
.header_area{

font-color:white;
}
/* .container { */

/* background-color:#d2d2d2; */
/* } */

#container{
	position:relative;
	width:100%;
	min-height:100%;
	padding-bottom:400px;
	box-sizing:border-box;
}

footer{
	width:100%;
	height:400px;
	position:absolute;
	bottom:0px;
	left:0px;
}
</style>

<!--================ Start Header Menu Area =================-->


     <header class="header_area " style="background-color:gray;color:gray;">
      <div class="main_menu">
        <div class="search_input" id="search_input_box">
          <div class="container-fluid ">
            <FORM class="d-flex justify-content-between"METHOD="post" ACTION="<%=request.getContextPath()%>/teacher/teacher.do">
              <input
              style="color:#002347;"
                type="text"
                class="form-control"
                id="search_input"
                placeholder="搜尋課程"
                name="action" value=""
                onfocus="this.placeholder = ''" onblur="this.placeholder = '搜尋課程'"
              />
              <button  type="submit" class="btn"></button>
              <i class="fas fa-times"id="close_search"
                title="Close Search"></i>  
            </FORM>
          </div>
        </div>

        <nav class="navbar navbar-expand-lg navbar-light row justify-content-between"style="line-height:0px; height:80px; background-color:#ffb925ad;">
          <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            
            <h1 style="padding:0px 0px 0px 50px;" ><a class="navbar-brand logo_h" href="<%=request.getContextPath()%>/back-end/home.jsp"  
              ><img src="<%=request.getContextPath()%>/front-end/home/images/02.png" width="150px"/>
           </a></h1>
            <button
              class="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
            <i class="fas fa-search"></i>
            <!-- <i class="fas fa-search"></i> -->
              <span class="icon-bar"></span> <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div
              class="collapse navbar-collapse offset"
              id="navbarSupportedContent"
            >
<!--               <ul class="nav navbar-nav menu_nav ml-auto"> -->
<!--                  <li class="nav-item submenu dropdown"> -->
<!--                   <a -->
<!--                     href="#" -->
<!--                     class="nav-link dropdown-toggle" -->
<!--                     data-toggle="dropdown" -->
<!--                     role="button" -->
<!--                     aria-haspopup="true" -->
<!--                     aria-expanded="false" -->
<!--                     >會員中心</a -->
<!--                   > -->
<!--                   <ul class="dropdown-menu"style="border-width:1px;border-style:dotted;border-color:#FFAC55;padding:0px;"> -->
<!--                     <li class="nav-item"> -->
<%--                       <a class="nav-link" href="<%= request.getContextPath() %>/front-end/member/listOneMember.jsp">會員資料</a> --%>
<!--                     </li> -->
<!--                     <li class="nav-item"> -->
<%--                       <a class="nav-link" href="<%= request.getContextPath() %>/front-end/member/memberCalendar.jsp" --%>
<!--                         >行事曆</a -->
<!--                       > -->
<!--                     </li> -->
<!--                     <li class="nav-item"> -->
<!--                       <a class="nav-link" href="#">單字學習</a> -->
<!--                     </li> -->
<!--                     <li class="nav-item"> -->
<%--                       <a class="nav-link" href="<%= request.getContextPath() %>/front-end/friend/select_page.jsp">我要學伴</a> --%>
<!--                     </li> -->
<!--                     <li class="nav-item"> -->
<%--                       <a class="nav-link" href="<%= request.getContextPath() %>/front-end/point_trans/listOnePoint_trans.jsp">儲值</a> --%>
<!--                     </li> -->
<!--                     <li class="nav-item"> -->
<%--                       <a class="nav-link" href="<%=request.getContextPath()%>/front-end/message/listOneMessage.jsp">會員訊息</a> --%>
<!--                     </li> -->
<!--                     <li class="nav-item"> -->
<%--                       <a class="nav-link" href="<%=request.getContextPath()%>/member/logoutHandler.do">登出</a> --%>
<!--                     </li> -->
<!--                   </ul> -->
<!--                 </li> -->
                
<!--                 li_active 紅色關注在你所在的頁面 -->
<!--                  <li class="nav-item submenu dropdown"> -->
<!--                   <a -->
<!--                     href="#" -->
<!--                     class="nav-link dropdown-toggle" -->
<!--                     data-toggle="dropdown" -->
<!--                     role="button" -->
<!--                     aria-haspopup="true" -->
<!--                     aria-expanded="false" -->
<!--                     >老師專區</a -->
<!--                   > -->
<!--                   <ul class="dropdown-menu"style="border-width:1px;border-style:dotted;border-color:#FFAC55;padding:0px;"> -->
<!--                     <li class="nav-item"> -->
<%--                       <a class="nav-link" href="<%=request.getContextPath()%>/front-end/teacher/update_teacher_input.jsp">老師資料</a> --%>
<!--                     </li> -->
<!--                     <li class="nav-item"> -->
<%--                       <a class="nav-link" href="<%=request.getContextPath()%>/front-end/teacher/teacherCalendar.jsp" --%>
<!--                         >行事曆</a -->
<!--                       > -->
<!--                     </li> -->
<!--                     <li class="nav-item"> -->
<%--                       <a class="nav-link" href="<%=request.getContextPath()%>/front-end/card/select_page.jsp">單字管理</a> --%>
<!--                     </li> -->
<!--                     <li class="nav-item"> -->
<!--                       <a class="nav-link" href="#" -->
<!--                         >學生學習</a> -->
<!--                     </li> -->
<!--                   </ul> -->
<!--                 </li> -->
<!--                 <li class="nav-item submenu dropdown"> -->
<%--                   <a class="nav-link" href="<%=request.getContextPath()%>/front-end/commodity/shop_index.jsp">筆記商城</a> --%>
<!--                 </li> -->
<!--                 <li class="nav-item submenu dropdown"> -->
<%--                   <a class="nav-link" href="<%=request.getContextPath()%>/front-end/Discuss/DiscussIndex.jsp">討論區</a> --%>
<!--                 </li> -->
                
<!--                  <li style="padding:20px 0px 0px 0px;"class="nav-item" > -->
<!--                   <i class="fas fa-bell fa-2x" ></i> -->
<!--                 </li> -->
                 
<!--    <!-- 					登入====================== -->   
      
<!--    				<li>         -->
<%--                 	<% if (session.getAttribute("memberVO") == null) { %> --%>
<!-- 					<script> -->
<!-- // // 					alert('欲瀏覽其他頁面，請先登入!'); -->
<!-- 					</script> -->
					
<%-- 					<form style="padding:20px 20px 0px 0px;"class="form-inline " action="<%=request.getContextPath()%>/loginhandler" method="post"> --%>
<!-- 						<input style="width:100px;" class="form-control bg-white" type="text" placeholder="帳號" name="member_id"> -->
<!-- 						<input style="margin:0px 5px 0px 5px; width:100px;"class="form-control  bg-white" type="password" placeholder="密碼" name="mem_pwd"> -->
<!-- 						<button class="btn btn-outline-success my-1 my-sm-0" type="submit">Login</button> -->
<!-- 					</form> -->
<%-- 					<% } else { %> --%>
<%-- 					<form style="padding:20px 20px 0px 0px;"class="form-inline "  method="GET" action="<%=request.getContextPath()%>/logouthandler"> --%>
<%-- 						<span>安安!&nbsp&nbsp<span style="color:blue;">${memberVO.mem_nick}, </span></span> --%>
<%-- 						<span><span> 您剩餘&nbsp</span><span style="color:red;">${memberVO.mem_point}</span>點&nbsp</span> --%>
<!-- 						&nbsp &nbsp<button style="padding:0px; padding-left:5px; padding-right:5px; height:28px;" type="submit" class="btn btn-secondary"><span style="font-size:12px;">登出</span></button> -->
<!-- 					</form> -->
<%-- 					<% } %> --%>
<!-- 				</li> -->
<!-- <!-- 					登入====================== --> 
<!--                 <li class="nav-item" style="padding:23px 20px 0px 0px;"> -->
<!--                   <a href="#" class="nav-link search" id="search"> -->
<!--                     <i class="fas fa-search"></i> -->
<!--                   </a> -->
<!--                 </li> -->
                
<!--               </ul> -->


            </div>
          
          </div>
<!--           ================= -->
<div>
	<c:if test="${sessionScope.system_managerVO!=null}">
            <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/AdministratorServlet" style="padding-right:50px;">
		       <input class="btn btn-outline-success"type="submit" value="登出">
		       <input type="hidden" name="action" value="Adminlogout">
		  	</FORM>
	</c:if>
	<c:if test="${sessionScope.system_managerVO==null}">
			<input class="btn btn-outline-success"type="button" value="登入管理員" onclick="location.href='<%=request.getContextPath()%>/SMLogin.jsp'">
	</c:if>	  	
</div>
<!-- 		  	========== -->
        </nav>
      </div>
    </header>
    

    <!--================ End Header Menu Area =================-->