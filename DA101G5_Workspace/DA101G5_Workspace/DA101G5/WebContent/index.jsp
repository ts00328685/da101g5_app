<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>懶骨雞</title>
<%@ include file="front-end/file/head.file"%>
<%--

      /*廣告*/
      
          #popWindow{ /*此行指定放置廣告的div */ 
          width:440px; /*此行設定div的寬度*/ 
          height:330px; /*此行設定div的高度*/ 
          background:url("<%=request.getContextPath()%>/front-end/home/images/廣告.jpg")no-repeat ; /*此行設定div的背景圖形*/ 
         
 --%>        
          <%-- background:
          <c:if test="${eventVO.evpic!=null && eventVO.filetype.indexOf('image')>-1}">
                 <img src="<%=request.getContextPath()%>/front-end/event/EventPicHandler.jsp?event_id=${eventVO.event_id}">
            </c:if>
            no-repeat ;
         --%> 
 <%--          
          box-shadow:5px 5px 10px black; /*此行設定div的外框陰影*/ 
          border:2px gray solid; /*此行設定div的外框線條*/ 
          border-radius:20px; /*此行設定div的外框圓角*/ 
          position:fixed; /*此行設定div為不隨捲動而移動的模式*/ 
          location:"no";
          z-index:999;
          }
          
         --%> 
    <style type="text/css"> 
          .header_area .navbar .search {
    		margin-top: -25px;
		}
    </style>
   
</head>


<body>

 <!-- -------廣告------- -->
<!--     <div class="button-group-area mt-40" id="popWindow" onload="window.focus()"> -->
<!--       <button  class="Primary" id="bt1" style="margin-left:180px ;margin-top:280px;width:100px;">關閉視窗</button>  -->
<!--       <button href="#" class="genric-btn primary circle" id="bt1"style="margin-left:170px ;margin-top:280px;">關閉視窗</button> -->
<!--     </div> -->
<!-- ==================================================== -->
<%@ include file="front-end/file/header.jsp"%>
<!--================ Start Home Banner Area =================-->
    <section class="home_banner_area ">
      <div class="banner_inner" >
        <div class="container">
          <div class="row">
            <div class="col-lg-12">
              <div class="banner_content text-center "style="margin-top:0px;">
                <h2 class="text-uppercase mt-0 mb-4-0">
<!--                  緣起 -->
                </h2>
                <br>
               <div class="text-left">
                  
<!--                    為了減輕老師備課壓力及提升學生主動學習的積極度，本組希望能建置一個線上教學整合平台—懶骨雞，提供客製化教材方式，方便老師管理備課內容及學生學習進度、增進學習記憶力，此外透過學伴配對功能，尋找有志一同互相學習的夥伴，增加練習機會，完成學習目標。 -->
                
               </div>
                  
               
                <br>
                <div>
<!--                   <a href="#" class="primary-btn2 mb-3 mb-sm-0"style="margin-top:200px;height:50px;color:#00D600;"><p>加入會員</p></a> -->
                   <c:if test="${ empty memberVO}">
                   <a href="<%=request.getContextPath()%>/front-end/member/register.jsp" class="primary-btn ml-sm-3 ml-0" style="margin-top:200px;height:50px;"><p style="color:#CD2626;">加入會員</p></a> 
                   </c:if>
                   
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--================ End Home Banner Area =================-->

    <!--================ Start Feature Area =================-->
    <section class="feature_area section_gap_top">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-5">
            <div class="main_title">
              <h2 class="mb-3">為什麼要加入懶骨雞!?</h2>
              <p>
                	只要有懶骨雞!!學習不再勉強～
              </p>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-4 col-md-6">
            <div class="single_feature">
              <!-- <div class="icon">
                <span class="flaticon-student"></span>
              </div> -->
              <div align="center">
                <img src="<%=request.getContextPath()%>/front-end/home/images/單字卡.png" width="150px"height="200px">
              </div>
              <div class="desc">
                <h4 class="mt-3 mb-2 text-center">單字卡</h4>
                <p>
                  	隨著記憶曲線加深印象，輕鬆學習
                </p>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6">
            <div class="single_feature">
              <img src="<%=request.getContextPath()%>/front-end/home/images/學伴.jpg" width="300px">
              <div class="desc">
                <h4 class="mt-3 mb-2 text-center">學伴</h4>
                <p>
                  	大朋友、小朋友為共同目標一起努力!!!
                </p>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6">
            <div class="single_feature">
              <img src="<%=request.getContextPath()%>/front-end/home/images/提問.jpg" width="300px" height="200px">
              <div class="desc ">
                <h4 class="mt-3 mb-2 text-center">提問區</h4>
                <p>
                 	 與老師一起腦力激盪，不再埋頭苦幹!
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--================ End Feature Area =================-->

<!--     ================ Start Popular Courses Area ================= -->
<!--     <div class="popular_courses"> -->
<!--       <div class="container"> -->
<!--         <div class="row justify-content-center"> -->
<!--           <div class="col-lg-5"> -->
<!--             <div class="main_title"> -->
<!--               <h2 class="mb-3">老師廣告牆</h2> -->
<!--               <p> -->
<!--                 Replenish man have thing gathering lights yielding shall you -->
<!--               </p> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="row"> -->
<!--           single course -->
<!--           <div class="col-lg-12"> -->
<!--             <div class="owl-carousel active_course"> -->
<!--               <div class="single_course"> -->
<!--                 <div class="course_head"> -->
<!--                   <img class="img-fluid" src="img/courses/c1.jpg" alt="" /> -->
<!--                 </div> -->
<!--                 <div class="course_content"> -->
<!--                   <span class="price">$25</span> -->
<!--                   <span class="tag mb-4 d-inline-block">design</span> -->
<!--                   <h4 class="mb-3"> -->
<!--                     <a href="course-details.html">Custom Product Design</a> -->
<!--                   </h4> -->
<!--                   <p> -->
<!--                     One make creepeth man bearing their one firmament won't fowl -->
<!--                     meat over sea -->
<!--                   </p> -->
<!--                   <div -->
<!--                     class="course_meta d-flex justify-content-lg-between align-items-lg-center flex-lg-row flex-column mt-4" -->
<!--                   > -->
<!--                     <div class="authr_meta"> -->
<!--                       <img src="img/courses/author1.png" alt="" /> -->
<!--                       <span class="d-inline-block ml-2">Cameron</span> -->
<!--                     </div> -->
<!--                     <div class="mt-lg-0 mt-3"> -->
<!--                       <span class="meta_info mr-4"> -->
<!--                         <a href="#"> <i class="ti-user mr-2"></i>25 </a> -->
<!--                       </span> -->
<!--                       <span class="meta_info" -->
<!--                         ><a href="#"> <i class="ti-heart mr-2"></i>35 </a></span -->
<!--                       > -->
<!--                     </div> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </div> -->

<!--               <div class="single_course"> -->
<!--                 <div class="course_head"> -->
<!--                   <img class="img-fluid" src="img/courses/c2.jpg" alt="" /> -->
<!--                 </div> -->
<!--                 <div class="course_content"> -->
<!--                   <span class="price">$25</span> -->
<!--                   <span class="tag mb-4 d-inline-block">design</span> -->
<!--                   <h4 class="mb-3"> -->
<!--                     <a href="course-details.html">Social Media Network</a> -->
<!--                   </h4> -->
<!--                   <p> -->
<!--                     One make creepeth man bearing their one firmament won't fowl -->
<!--                     meat over sea -->
<!--                   </p> -->
<!--                   <div -->
<!--                     class="course_meta d-flex justify-content-lg-between align-items-lg-center flex-lg-row flex-column mt-4" -->
<!--                   > -->
<!--                     <div class="authr_meta"> -->
<!--                       <img src="img/courses/author2.png" alt="" /> -->
<!--                       <span class="d-inline-block ml-2">Cameron</span> -->
<!--                     </div> -->
<!--                     <div class="mt-lg-0 mt-3"> -->
<!--                       <span class="meta_info mr-4"> -->
<!--                         <a href="#"> <i class="ti-user mr-2"></i>25 </a> -->
<!--                       </span> -->
<!--                       <span class="meta_info" -->
<!--                         ><a href="#"> <i class="ti-heart mr-2"></i>35 </a></span -->
<!--                       > -->
<!--                     </div> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </div> -->

<!--               <div class="single_course"> -->
<!--                 <div class="course_head"> -->
<!--                   <img class="img-fluid" src="img/courses/c3.jpg" alt="" /> -->
<!--                 </div> -->
<!--                 <div class="course_content"> -->
<!--                   <span class="price">$25</span> -->
<!--                   <span class="tag mb-4 d-inline-block">design</span> -->
<!--                   <h4 class="mb-3"> -->
<!--                     <a href="course-details.html">Computer Engineering</a> -->
<!--                   </h4> -->
<!--                   <p> -->
<!--                     One make creepeth man bearing their one firmament won't fowl -->
<!--                     meat over sea -->
<!--                   </p> -->
<!--                   <div -->
<!--                     class="course_meta d-flex justify-content-lg-between align-items-lg-center flex-lg-row flex-column mt-4" -->
<!--                   > -->
<!--                     <div class="authr_meta"> -->
<!--                       <img src="img/courses/author3.png" alt="" /> -->
<!--                       <span class="d-inline-block ml-2">Cameron</span> -->
<!--                     </div> -->
<!--                     <div class="mt-lg-0 mt-3"> -->
<!--                       <span class="meta_info mr-4"> -->
<!--                         <a href="#"> <i class="ti-user mr-2"></i>25 </a> -->
<!--                       </span> -->
<!--                       <span class="meta_info" -->
<!--                         ><a href="#"> <i class="ti-heart mr-2"></i>35 </a></span -->
<!--                       > -->
<!--                     </div> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
<!--     ================ End Popular Courses Area ================= -->

    <!--================ Start Registration Area =================-->
<!--     <div class="section_gap registration_area"> -->
<!--       <div class="container"> -->
<!--         <div class="row align-items-center"> -->
<!--           <div class="col-lg-7"> -->
<!--             <div class="row clock_sec clockdiv" id="clockdiv"> -->
<!--               <div class="col-lg-12"> -->
<!--                 <h1 class="mb-3">Register Now</h1> -->
<!--                 <p> -->
<!--                   There is a moment in the life of any aspiring astronomer that -->
<!--                   it is time to buy that first telescope. It’s exciting to think -->
<!--                   about setting up your own viewing station. -->
<!--                 </p> -->
<!--               </div> -->
<!--               <div class="col clockinner1 clockinner"> -->
<!--                 <h1 class="days">150</h1> -->
<!--                 <span class="smalltext">Days</span> -->
<!--               </div> -->
<!--               <div class="col clockinner clockinner1"> -->
<!--                 <h1 class="hours">23</h1> -->
<!--                 <span class="smalltext">Hours</span> -->
<!--               </div> -->
<!--               <div class="col clockinner clockinner1"> -->
<!--                 <h1 class="minutes">47</h1> -->
<!--                 <span class="smalltext">Mins</span> -->
<!--               </div> -->
<!--               <div class="col clockinner clockinner1"> -->
<!--                 <h1 class="seconds">59</h1> -->
<!--                 <span class="smalltext">Secs</span> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
<!--           <div class="col-lg-4 offset-lg-1"> -->
<!--             <div class="register_form"> -->
<!--               <h3>Courses for Free</h3> -->
<!--               <p>It is high time for learning</p> -->
<!--               <form -->
<!--                 class="form_area" -->
<!--                 id="myForm" -->
<!--                 action="mail.html" -->
<!--                 method="post" -->
<!--               > -->
<!--                 <div class="row"> -->
<!--                   <div class="col-lg-12 form_group"> -->
<!--                     <input -->
<!--                       name="name" -->
<!--                       placeholder="Your Name" -->
<!--                       required="" -->
<!--                       type="text" -->
<!--                     /> -->
<!--                     <input -->
<!--                       name="name" -->
<!--                       placeholder="Your Phone Number" -->
<!--                       required="" -->
<!--                       type="tel" -->
<!--                     /> -->
<!--                     <input -->
<!--                       name="email" -->
<!--                       placeholder="Your Email Address" -->
<!--                       pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" -->
<!--                       required="" -->
<!--                       type="email" -->
<!--                     /> -->
<!--                   </div> -->
<!--                   <div class="col-lg-12 text-center"> -->
<!--                     <button class="primary-btn">Submit</button> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </form> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
    <!--================ End Registration Area =================-->

    <!--================ Start Trainers Area =================-->
<!--     <section class="trainer_area section_gap_top"> -->
<!--       <div class="container"> -->
<!--         <div class="row justify-content-center"> -->
<!--           <div class="col-lg-5"> -->
<!--             <div class="main_title"> -->
<!--               <h2 class="mb-3">Our Expert Trainers</h2> -->
<!--               <p> -->
<!--                 Replenish man have thing gathering lights yielding shall you -->
<!--               </p> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="row justify-content-center d-flex align-items-center"> -->
<!--           <div class="col-lg-3 col-md-6 col-sm-12 single-trainer"> -->
<!--             <div class="thumb d-flex justify-content-sm-center"> -->
<!--               <img class="img-fluid" src="img/trainer/t1.jpg" alt="" /> -->
<!--             </div> -->
<!--             <div class="meta-text text-sm-center"> -->
<!--               <h4>Mated Nithan</h4> -->
<!--               <p class="designation">Sr. web designer</p> -->
<!--               <div class="mb-4"> -->
<!--                 <p> -->
<!--                   If you are looking at blank cassettes on the web, you may be -->
<!--                   very confused at the. -->
<!--                 </p> -->
<!--               </div> -->
<!--               <div class="align-items-center justify-content-center d-flex"> -->
<!--                 <a href="#"><i class="ti-facebook"></i></a> -->
<!--                 <a href="#"><i class="ti-twitter"></i></a> -->
<!--                 <a href="#"><i class="ti-linkedin"></i></a> -->
<!--                 <a href="#"><i class="ti-pinterest"></i></a> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->

<!--           <div class="col-lg-3 col-md-6 col-sm-12 single-trainer"> -->
<!--             <div class="thumb d-flex justify-content-sm-center"> -->
<!--               <img class="img-fluid" src="img/trainer/t2.jpg" alt="" /> -->
<!--             </div> -->
<!--             <div class="meta-text text-sm-center"> -->
<!--               <h4>David Cameron</h4> -->
<!--               <p class="designation">Sr. web designer</p> -->
<!--               <div class="mb-4"> -->
<!--                 <p> -->
<!--                   If you are looking at blank cassettes on the web, you may be -->
<!--                   very confused at the. -->
<!--                 </p> -->
<!--               </div> -->
<!--               <div class="align-items-center justify-content-center d-flex"> -->
<!--                 <a href="#"><i class="ti-facebook"></i></a> -->
<!--                 <a href="#"><i class="ti-twitter"></i></a> -->
<!--                 <a href="#"><i class="ti-linkedin"></i></a> -->
<!--                 <a href="#"><i class="ti-pinterest"></i></a> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->

<!--           <div class="col-lg-3 col-md-6 col-sm-12 single-trainer"> -->
<!--             <div class="thumb d-flex justify-content-sm-center"> -->
<!--               <img class="img-fluid" src="img/trainer/t3.jpg" alt="" /> -->
<!--             </div> -->
<!--             <div class="meta-text text-sm-center"> -->
<!--               <h4>Jain Redmel</h4> -->
<!--               <p class="designation">Sr. Faculty Data Science</p> -->
<!--               <div class="mb-4"> -->
<!--                 <p> -->
<!--                   If you are looking at blank cassettes on the web, you may be -->
<!--                   very confused at the. -->
<!--                 </p> -->
<!--               </div> -->
<!--               <div class="align-items-center justify-content-center d-flex"> -->
<!--                 <a href="#"><i class="ti-facebook"></i></a> -->
<!--                 <a href="#"><i class="ti-twitter"></i></a> -->
<!--                 <a href="#"><i class="ti-linkedin"></i></a> -->
<!--                 <a href="#"><i class="ti-pinterest"></i></a> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->

<!--           <div class="col-lg-3 col-md-6 col-sm-12 single-trainer"> -->
<!--             <div class="thumb d-flex justify-content-sm-center"> -->
<!--               <img class="img-fluid" src="img/trainer/t4.jpg" alt="" /> -->
<!--             </div> -->
<!--             <div class="meta-text text-sm-center"> -->
<!--               <h4>Nathan Macken</h4> -->
<!--               <p class="designation">Sr. web designer</p> -->
<!--               <div class="mb-4"> -->
<!--                 <p> -->
<!--                   If you are looking at blank cassettes on the web, you may be -->
<!--                   very confused at the. -->
<!--                 </p> -->
<!--               </div> -->
<!--               <div class="align-items-center justify-content-center d-flex"> -->
<!--                 <a href="#"><i class="ti-facebook"></i></a> -->
<!--                 <a href="#"><i class="ti-twitter"></i></a> -->
<!--                 <a href="#"><i class="ti-linkedin"></i></a> -->
<!--                 <a href="#"><i class="ti-pinterest"></i></a> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </section> -->
    <!--================ End Trainers Area =================-->

    <!--================ Start Events Area =================-->
<!--     <div class="events_area"> -->
<!--       <div class="container"> -->
<!--         <div class="row justify-content-center"> -->
<!--           <div class="col-lg-5"> -->
<!--             <div class="main_title"> -->
<!--               <h2 class="mb-3 text-white">Upcoming Events</h2> -->
<!--               <p> -->
<!--                 Replenish man have thing gathering lights yielding shall you -->
<!--               </p> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="row"> -->
<!--           <div class="col-lg-6 col-md-6"> -->
<!--             <div class="single_event position-relative"> -->
<!--               <div class="event_thumb"> -->
<!--                 <img src="img/event/e1.jpg" alt="" /> -->
<!--               </div> -->
<!--               <div class="event_details"> -->
<!--                 <div class="d-flex mb-4"> -->
<!--                   <div class="date"><span>15</span> Jun</div> -->

<!--                   <div class="time-location"> -->
<!--                     <p> -->
<!--                       <span class="ti-time mr-2"></span> 12:00 AM - 12:30 AM -->
<!--                     </p> -->
<!--                     <p> -->
<!--                       <span class="ti-location-pin mr-2"></span> Hilton Quebec -->
<!--                     </p> -->
<!--                   </div> -->
<!--                 </div> -->
<!--                 <p> -->
<!--                   One make creepeth man for so bearing their firmament won't -->
<!--                   fowl meat over seas great -->
<!--                 </p> -->
<!--                 <a href="#" class="primary-btn rounded-0 mt-3">View Details</a> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
<!--           <div class="col-lg-6 col-md-6"> -->
<!--             <div class="single_event position-relative"> -->
<!--               <div class="event_thumb"> -->
<!--                 <img src="img/event/e2.jpg" alt="" /> -->
<!--               </div> -->
<!--               <div class="event_details"> -->
<!--                 <div class="d-flex mb-4"> -->
<!--                   <div class="date"><span>15</span> Jun</div> -->

<!--                   <div class="time-location"> -->
<!--                     <p> -->
<!--                       <span class="ti-time mr-2"></span> 12:00 AM - 12:30 AM -->
<!--                     </p> -->
<!--                     <p> -->
<!--                       <span class="ti-location-pin mr-2"></span> Hilton Quebec -->
<!--                     </p> -->
<!--                   </div> -->
<!--                 </div> -->
<!--                 <p> -->
<!--                   One make creepeth man for so bearing their firmament won't -->
<!--                   fowl meat over seas great -->
<!--                 </p> -->
<!--                 <a href="#" class="primary-btn rounded-0 mt-3">View Details</a> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->

<!--           <div class="col-lg-12"> -->
<!--             <div class="text-center pt-lg-5 pt-3"> -->
<!--               <a href="#" class="event-link"> -->
<!--                 View All Event <img src="img/next.png" alt="" /> -->
<!--               </a> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
    <!--================ End Events Area =================-->

    <!--================ Start Testimonial Area =================-->
<!--     <div class="testimonial_area section_gap"> -->
<!--       <div class="container"> -->
<!--         <div class="row justify-content-center"> -->
<!--           <div class="col-lg-5"> -->
<!--             <div class="main_title"> -->
<!--               <h2 class="mb-3">最佳老師</h2> -->
<!--               <p> -->
<!--                 	熱心在提問區解決學員們問題的老師 -->
<!--               </p> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->

<!--         <div class="row"> -->
<!--           <div class="testi_slider owl-carousel"> -->
<!--             <div class="testi_item"> -->
<!--               <div class="row"> -->
<!--                 <div class="col-lg-4 col-md-6"> -->
<%--                   <img src="<%=request.getContextPath()%>/front-end/home/img/testimonials/t1.jpg" alt="" /> --%>
<!--                 </div> -->
<!--                 <div class="col-lg-8"> -->
<!--                   <div class="testi_text"> -->
<!--                     <h4>Elite Martin</h4> -->
<!--                     <p> -->
<!--                       Him, made can't called over won't there on divide there -->
<!--                       male fish beast own his day third seed sixth seas unto. -->
<!--                       Saw from -->
<!--                     </p> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </div> -->
<!--             </div> -->
<!--             <div class="testi_item"> -->
<!--               <div class="row"> -->
<!--                 <div class="col-lg-4 col-md-6"> -->
<%--                   <img src="<%=request.getContextPath()%>/front-end/home/img/testimonials/t2.jpg" alt="" /> --%>
<!--                 </div> -->
<!--                 <div class="col-lg-8"> -->
<!--                   <div class="testi_text"> -->
<!--                     <h4>Davil Saden</h4> -->
<!--                     <p> -->
<!--                       Him, made can't called over won't there on divide there -->
<!--                       male fish beast own his day third seed sixth seas unto. -->
<!--                       Saw from -->
<!--                     </p> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </div> -->
<!--             </div> -->
<!--             <div class="testi_item"> -->
<!--               <div class="row"> -->
<!--                 <div class="col-lg-4 col-md-6"> -->
<%--                   <img src="<%=request.getContextPath()%>/front-end/home/img/testimonials/t1.jpg" alt="" /> --%>
<!--                 </div> -->
<!--                 <div class="col-lg-8"> -->
<!--                   <div class="testi_text"> -->
<!--                     <h4>Elite Martin</h4> -->
<!--                     <p> -->
<!--                       Him, made can't called over won't there on divide there -->
<!--                       male fish beast own his day third seed sixth seas unto. -->
<!--                       Saw from -->
<!--                     </p> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </div> -->
<!--             </div> -->
<!--             <div class="testi_item"> -->
<!--               <div class="row"> -->
<!--                 <div class="col-lg-4 col-md-6"> -->
<%--                   <img src="<%=request.getContextPath()%>/front-end/home/img/testimonials/t2.jpg" alt="" /> --%>
<!--                 </div> -->
<!--                 <div class="col-lg-8"> -->
<!--                   <div class="testi_text"> -->
<!--                     <h4>Davil Saden</h4> -->
<!--                     <p> -->
<!--                       Him, made can't called over won't there on divide there -->
<!--                       male fish beast own his day third seed sixth seas unto. -->
<!--                       Saw from -->
<!--                     </p> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </div> -->
<!--             </div> -->
<!--             <div class="testi_item"> -->
<!--               <div class="row"> -->
<!--                 <div class="col-lg-4 col-md-6"> -->
<%--                   <img src="<%=request.getContextPath()%>/front-end/home/img/testimonials/t1.jpg" alt="" /> --%>
<!--                 </div> -->
<!--                 <div class="col-lg-8"> -->
<!--                   <div class="testi_text"> -->
<!--                     <h4>Elite Martin</h4> -->
<!--                     <p> -->
<!--                       Him, made can't called over won't there on divide there -->
<!--                       male fish beast own his day third seed sixth seas unto. -->
<!--                       Saw from -->
<!--                     </p> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </div> -->
<!--             </div> -->
<!--             <div class="testi_item"> -->
<!--               <div class="row"> -->
<!--                 <div class="col-lg-4 col-md-6"> -->
<%--                   <img src="<%=request.getContextPath()%>/front-end/home/img/testimonials/t2.jpg" alt="" /> --%>
<!--                 </div> -->
<!--                 <div class="col-lg-8"> -->
<!--                   <div class="testi_text"> -->
<!--                     <h4>Davil Saden</h4> -->
<!--                     <p> -->
<!--                       Him, made can't called over won't there on divide there -->
<!--                       male fish beast own his day third seed sixth seas unto. -->
<!--                       Saw from -->
<!--                     </p> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
    <!--================ End Testimonial Area =================-->

	


<!-- ================================================= -->
<%@ include file="front-end/file/footer.file"%>
<!-- -*-------------廣告------------------- -->

<c:if test="${isMember1==false}">
</c:if> 
<script>
//  function centerHandler(){/*設定置中的函式*/
//   var scrollDist=$(window).scrollTop();/*取得捲動長度*/ 
//   var myTop=($(window).height()-$("#popWindow").height())/2+scrollDist; 
//   /*取得垂直中央位置*/ 
//   var myLeft=($(window).width()-$("#popWindow").width())/2; 
//   /*取得水平中央位置*/ 
//   $("#popWindow").offset({top:myTop,left:myLeft}).focus(); 
//   /*設定區塊於水平與垂直置中*/


//   }

//   centerHandler () /*呼叫置中函式，使廣告區塊置中*/
//    $(document).ready(function() {
//       $("#bt1").click(function() {
//         $("#popWindow").hide();
      
//       }); 
//     })
</script>


</body>
</html>