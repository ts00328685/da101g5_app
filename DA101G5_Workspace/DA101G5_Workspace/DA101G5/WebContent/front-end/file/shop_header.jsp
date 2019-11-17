<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
	<nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light d-flex">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">懶骨雞</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
			
			  
				<li class="nav-item active">
					<a class="nav-link" href="<%=request.getContextPath()%>/front-end/commodity/shop_index.jsp">商場首頁<span class="sr-only">(current)</span></a>
				</li>				
				
     		 
				<li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         		Commodity
        		</a>
        		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
         				<a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/commodity/select_page.jsp">Select Commodity</a>
          				<a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/commodity/listAllCommodity.jsp">List All</a>
         				<a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/commodity/addCommodity.jsp">Add Commodity</a>
       			 </div>
     		     </li>
     		     
     		     
			</ul>
			
			<% if (session.getAttribute("memberVO") == null) { %>
			
			<form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%>/loginhandler" method="post">
				<input class="form-control mr-sm-2 bg-white" type="text" placeholder="member_id" name="member_id">
				<input class="form-control mr-sm-2 bg-white" type="password" placeholder="password" name="mem_pwd">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
			</form>
			<% } else { %>
			<form class="form-inline my-2 my-lg-0"  method="GET" action="<%=request.getContextPath()%>/logouthandler">
				<span><span style="color:blue;">${memberVO.mem_nick}</span>，歡迎回來!&nbsp&nbsp</span>
				<span><span>目前點數尚餘:&nbsp</span><span style="color:red;">${memberVO.mem_point}</span>點&nbsp</span>
				&nbsp &nbsp<button style="padding:0px; padding-left:5px; padding-right:5px; height:28px;" type="submit" class="btn btn-secondary"><span style="font-size:12px;">登出</span></button>
			</form>
			<% } %>
			
		</div>
	</nav>
	
	
	<div id="carouselExampleInterval" class="carousel slide" data-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active" data-interval="10000">
				<img style="width:100%"  src="https://picsum.photos/1200/300?random=1">
			</div>
			<div class="carousel-item" data-interval="2000">
				<img style="width:100%" src="https://picsum.photos/1200/300?random=2">
			</div>
			<div class="carousel-item">
				<img style="width:100%"  src="https://picsum.photos/1200/300?random=3">
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleInterval" role="button" data-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span>
			<span class="sr-only">Previous</span>
		</a>
		<a class="carousel-control-next" href="#carouselExampleInterval" role="button" data-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
