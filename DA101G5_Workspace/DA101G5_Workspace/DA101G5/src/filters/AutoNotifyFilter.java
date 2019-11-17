package filters;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Time_order.model.Time_orderService;
import com.Time_order.model.Time_orderVO;
import com.member.model.MemberVO;
@WebFilter("/front-end/*")
public class AutoNotifyFilter implements Filter{
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {


		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
	    req.setCharacterEncoding("UTF-8");
	    res.setCharacterEncoding("UTF-8");
	    res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object account = session.getAttribute("memberVO");
		 if(account instanceof MemberVO){
				MemberVO memberVO=(MemberVO) account;
				String member_id=memberVO.getMember_id();
				Time_orderService time_orderSvc=new Time_orderService();
				List<Time_orderVO> list=time_orderSvc.getAllForMember(member_id);
				for(int i=0;i<list.size();i++) {
					Long start=list.get(i).getStart_time().getTime();
					Long current=new Timestamp(System.currentTimeMillis()).getTime();
					//現在時間大於開始時間五分鐘
//					System.out.println(789);
					if(start>=current&&current>=(start-1000*60*60*60)&&list.get(i).getC_state()==1) {
//						errorMsgs.add(0, "上課時間要到囉!!");
						session.setAttribute("isTimeOrder", false);
//						errorMsgs.add("上課時間要到囉!!");
//						req.setAttribute("errorMsgs", errorMsgs);
//						chain.doFilter(req, res);
//						req.setAttribute("errorMsgs", errorMsgs);
//						chain.doFilter(req, res);
						break;
					}
				}
				chain.doFilter(req, res);
				
				
			}
			
			
			
			
			
		else 
		{	chain.doFilter(request, response);
		
		}
		
		
	}
	
}
