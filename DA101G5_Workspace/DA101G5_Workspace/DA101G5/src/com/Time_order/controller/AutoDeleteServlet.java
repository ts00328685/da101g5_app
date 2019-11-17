package com.Time_order.controller;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.Course_order.model.Course_orderService;
import com.Course_order.model.Course_orderVO;
import com.Time_order.model.Time_orderService;
import com.Time_order.model.Time_orderVO;

public class AutoDeleteServlet  extends HttpServlet implements Runnable{
	Thread searcher;  
	Timer timer =new java.util.Timer();
	Date date;
	 public void destroy() {
		 timer.cancel();
		    searcher=null;
	}
	 public void init() throws ServletException {
		 
		 date=new Date(System.currentTimeMillis());
		    searcher = new Thread(this);
		    searcher.setPriority(Thread.MIN_PRIORITY);  // be a good citizen
		    searcher.start();
	}
	@Override
	public void run() {
		TimerTask timerTask = new TimerTask() {
		    @Override
		    public void run() {
		    	deleteTime_order();
		    }
		};
		timer.scheduleAtFixedRate(timerTask
			
			,date, 1000*60*20);
		
	}
	
	private void deleteTime_order() {
		Time_orderService time_orderSvc = new Time_orderService();
		List<Time_orderVO> list=time_orderSvc.getAll();
		for(Time_orderVO time_orderVO:list) {
			Timestamp currentTime=new Timestamp(System.currentTimeMillis());
			Timestamp start_time=time_orderVO.getStart_time();
			//取得現在時間>預約開始時間   時間訂單狀態還尚未預約者自動刪除
			if(currentTime.getTime()>start_time.getTime()&&time_orderVO.getC_state()==0||time_orderVO.getC_state()==1) {
				String time_order_id=time_orderVO.getTime_order_id();
				String course_order_id=time_orderVO.getCourse_order_id();
				Course_orderService course_orderSvc=new Course_orderService();
				Course_orderVO course_orderVO=course_orderSvc.getOneCourse_order(course_order_id);
				Integer remain_hour =course_orderVO.getRemain_hour();
				Integer hour=new Integer((int) (time_orderVO.getEnd_time().getTime()-time_orderVO.getStart_time().getTime())/(60*60*1000));
				remain_hour=remain_hour+hour;
				course_orderVO.setRemain_hour(remain_hour);
//把課程時數還回去 刪除時間訂單				
course_orderSvc.updateCourse_order(course_orderVO);
time_orderSvc.deleteTime_order(time_order_id);


			}
			
		}
		
		
		
	}

}
