package listener;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatroom.controller.JedisPoolUtil;
import com.friendchoose.model.FriendChooseService;
import com.friendchoose.model.FriendChooseVO;
import com.friendmanage.model.FriendManageService;
import com.friendmanage.model.FriendManageVO;
import com.member.model.MemberVO;
import com.message.model.MessageService;
import com.message.model.MessageVO;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class FriendListener implements ServletContextListener{
	private static final long serialVersionUID = 1L;
	List<FriendChooseVO> chooseList = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();

		Timer timer = (Timer) context.getAttribute("timer");

		if (timer != null) {
			timer.cancel();
		}
		

		context.removeAttribute("timer");
		System.out.println("timer has been shutdown");
		
		JedisPoolUtil.shutdownJedisPool();
		System.out.println("JedisPool has been shutdown");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();

		try {
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					/*修改會員抽籤資格*/
					FriendChooseService friendChooseSvc = new FriendChooseService();
					List<MemberVO> list = friendChooseSvc.getAllMember();
					if(list.size() > 0 || !(list.isEmpty())) {
						for(MemberVO memberVO : list) {
							memberVO.setFriend_choose(0);
							friendChooseSvc.updateMemberPro(memberVO);						
						}
					}
					
					/*修改尚未確認關係的學伴關係*/
					FriendManageService friendManageSvc = new FriendManageService();
					List<FriendManageVO> friendList = friendManageSvc.getAll();
					List<FriendManageVO> changeList = null;
					
					if(friendList.size() > 0 || !(friendList.isEmpty())) {
						changeList = friendList.stream()
								.filter(fm -> fm.getFriend_status() == 0 || fm.getFriend_status() == 3)
								.collect(Collectors.toList());
						if(changeList.size() > 0 || !(changeList.isEmpty())) {
							for(FriendManageVO friendManageVO : changeList) {
								friendManageSvc.updateFriendManage(friendManageVO.getFriend_member_id(), friendManageVO.getFriend_member_fid(), friendManageVO.getFriend_time(), 2);
							}
						}
					}
					
					
					/*修改邀請訊息*/
					MessageService messageSvc = new MessageService();
					List<MessageVO> new_mList = null;
					List<MessageVO> mList = messageSvc.getAll();
					if(mList.size() > 0 || !(mList.isEmpty())) {
						new_mList = mList.stream()
								.filter(mv -> mv.getMemmsg_state() == 0)
								.collect(Collectors.toList());
						if(new_mList.size() > 0 || !(new_mList.isEmpty())) {
							for(MessageVO messageVO : new_mList) {
								messageSvc.updateMessage(messageVO.getMessage_id(), messageVO.getMember_id(), messageVO.getMember_id2(), messageVO.getMemmsg_date(), "逾時", 1);
							}
						}
					}
				}

			};
			Calendar c = new GregorianCalendar(2019,Calendar.JUNE,6,0,0,0);
			timer.scheduleAtFixedRate(task, 0, 24*60*60*1000L);
			context.setAttribute("timer", timer);
			System.out.println("timer has been Initialized");

		} catch (Exception e) {
			System.out.println("the timer has some problem! " + e.getMessage());
		}
	}
}
