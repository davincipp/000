package community.controller.forum;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.model.Discussion;
import community.service.DiscussionService;
import community.service.impl.DiscussionServiceImpl;
import register.model.MemberBean;

@WebServlet("/forum/update.do")
public class PreUpdateDiscussionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("./"); // !!!=> login
			return;
		}
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			response.sendRedirect("./"); // !!!=> login
			return;
		}
		
		String strId = "";
		Integer intId = null;
		Discussion discussion = null;
		
		// 1.
		strId = request.getParameter("d");
		
		
		
		// 2.
		if (strId == null || strId.equals("")) {
			// 沒有查詢字串 | ""
			response.sendRedirect("./");
			return;
			
		} else if (strId != null && !strId.equals("")) {
			try {
				intId = Integer.parseInt(strId);
			} catch (NumberFormatException e) {
				// 不是數字
				response.sendRedirect("./");
				return;
			}
			
			
			// 3.
			
			
			// 4.
//			---------------------------------------------------
			DiscussionService ds = new DiscussionServiceImpl();
			discussion = ds.preUpdateDiscussion(mb, intId);
//			---------------------------------------------------
			
			
			// 5.
			if(discussion != null) {
				request.setAttribute("ud", discussion);
				RequestDispatcher rd = request.getRequestDispatcher("editor.jsp?type=update&discussionId=" + intId);
				rd.forward(request, response);
			} else {
				// 非作者
				response.sendRedirect("discussion?d=" + intId);
			}

		}
	}

}
