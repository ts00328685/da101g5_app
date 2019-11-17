<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<% session.setAttribute("location",request.getRequestURI()); %>
<% session.setAttribute("location1",request.getServletPath()); %>
<!--================ Start Header Menu Area =================-->
<c:if test="${isTeacher1==false}">
<script>
swal("尚未成為老師", 
		  {button: false,
		});
</script>
<% session.setAttribute("isTeacher1",null); %>
</c:if>
<!-- ===================================== -->
<c:if test="${isTimeOrder==false}">
<script>
// swal("趕緊上課去吧!!", 
// 		  {button: false,
// 		});
alertify.log('趕緊上課去吧！');  
</script>
<% session.setAttribute("isTimeOrder",null); %>
</c:if>
<!-- ============================== -->
<c:if test="${isMember1==false}">
<script>
// swal({
// 	  title: "Good job!",
// 	  text: "You clicked the button!",
// 	  icon: "success",
// 	  button: "Aww yiss!",
// 	});

swal("請先加入會員", 
  {button: false,
});
</script>
<% session.setAttribute("isMember1",null); %>
</c:if>

     <header class="header_area" >
      <div class="main_menu">
        <div class="search_input" id="search_input_box">
          <div class="container-fluid">
            <FORM class="d-flex justify-content-between"METHOD="post" ACTION="<%=request.getContextPath()%>/teacher/teacher.do">
              <input
              style="color:#002347;"
                type="text"
                class="form-control"
                id="search_input"
                placeholder="搜尋課程"
                name="keyword"
                onfocus="this.placeholder = ''" onblur="this.placeholder = '搜尋課程'"
              />
              <button  type="submit" class="btn"name="action" value="search"></button>
              <i class="fas fa-times"id="close_search"
                title="Close Search"></i>  
            </FORM>
          </div>
        </div>

        <nav class="navbar navbar-expand-lg navbar-light row"style="line-height:0px; height:80px;">
          <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            
            <h1 style="padding:0px 0px 0px 50px;" ><a class="navbar-brand logo_h" href="<%=request.getContextPath()%>/index.jsp"  
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
              <ul class="nav navbar-nav menu_nav ml-auto">
                 <li class="nav-item submenu dropdown">
                  <a
                    href="#"
                    class="nav-link dropdown-toggle"
                    data-toggle="dropdown"
                    role="button"
                    aria-haspopup="true"
                    aria-expanded="false"
                    >會員中心</a
                  >
                  <ul class="dropdown-menu"style="border-width:1px;border-style:dotted;border-color:#FFAC55;padding:0px;">
                    <li class="nav-item">
                      <a class="nav-link" href="<%= request.getContextPath() %>/front-end/member/listOneMember.jsp">會員資料</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="<%= request.getContextPath() %>/front-end/member/memberCalendar.jsp"
                        >行事曆</a
                      >
                    </li>
                   
                    <li class="nav-item">
                      <a class="nav-link" href="<%= request.getContextPath() %>/front-end/friend/select_page.jsp">我要學伴</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="<%=request.getContextPath()%>/front-end/recruit/RecruitIndex.jsp">學習派單</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="<%=request.getContextPath()%>/member/logoutHandler.do">登出</a>
                    </li>
                  </ul>
                </li>
                
                <!-- li_active 紅色關注在你所在的頁面 -->
                
                 <li class="nav-item submenu dropdown">
                  <a
                    href="#"
                    class="nav-link dropdown-toggle"
                    data-toggle="dropdown"
                    role="button"
                    aria-haspopup="true"
                    aria-expanded="false"
                    >老師專區</a
                  >
                  <ul class="dropdown-menu"style="border-width:1px;border-style:dotted;border-color:#FFAC55;padding:0px;">
                     <c:if test="${teacherVO!=null}">
                    <li class="nav-item">
                      <a class="nav-link" href="<%=request.getContextPath()%>/front-end/teacher/update_teacher_input.jsp">老師資料</a>
                    </li>
                    </c:if>
                    <c:if test="${teacherVO!=null}">
                    <li class="nav-item">
                      <a class="nav-link" href="<%=request.getContextPath()%>/front-end/teacher/teacherCalendar.jsp"
                        >行事曆</a>
                    </li>
                    </c:if>
                  </ul>
                </li>
                
                <li class="nav-item submenu dropdown">
                  <a class="nav-link" href="<%=request.getContextPath()%>/front-end/commodity/shop_index.jsp">筆記商城</a>
                </li>
                <li class="nav-item submenu dropdown">
                  <a class="nav-link" href="<%=request.getContextPath()%>/front-end/Discuss/DiscussIndex.jsp">討論區</a>
                </li>
                <li class="nav-item submenu dropdown">
                  <a class="nav-link" href="<%=request.getContextPath()%>/front-end/event/EventIndex.jsp">近期活動</a>
                </li>
                 
                 
   <!-- 					登入====================== -->  
      
   				<li>        
                	<% if (session.getAttribute("memberVO") == null) { %>
					
					
					<form style="padding:20px 20px 0px 0px;"class="form-inline " action="<%=request.getContextPath()%>/member/loginHandler.do" method="post">
						<input style="width:100px;" class="form-control bg-white" type="text" placeholder="帳號" name="member_id">
						<input style="margin:0px 5px 0px 5px; width:100px;"class="form-control  bg-white" type="password" placeholder="密碼" name="mem_pwd">
						<button class="btn btn-outline-success my-1 my-sm-0" type="submit">Login</button>
					</form>
					<% } else { %>
					<form style="padding:20px 20px 0px 0px;"class="form-inline "  method="GET" action="<%=request.getContextPath()%>/member/logoutHandler.do">
						<span>安安!&nbsp&nbsp<span style="color:blue;">${memberVO.mem_nick}, </span></span>
						<span><span> 您剩餘&nbsp</span><span style="color:red;">${memberVO.mem_point}</span>點&nbsp</span>
						&nbsp &nbsp<button style="padding:0px; padding-left:5px; padding-right:5px; height:28px;" type="submit" class="btn btn-secondary"><span style="font-size:12px;">登出</span></button>
					</form>
					<% } %>
				</li>
<!-- 					登入====================== -->
                <li class="nav-item" style="padding:23px 20px 0px 0px;">
                  <a href="#" class="nav-link search" id="search">
                    <i class="fas fa-search"></i>
                  </a>
                </li>
                
              </ul>
            </div>
          </div>
        </nav>
      </div>
    </header>
    <!--================ End Header Menu Area =================-->