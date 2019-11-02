package community.controller.forum;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.Discussion;
import community.model.Reply;
import community.service.DiscussionService;
import community.service.impl.DiscussionServiceImpl;

@WebServlet("/forum/discussion")
public class GetDiscussionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			discussion = ds.queryDiscussion(intId);
			List<Reply> replies = ds.getReplyList(discussion); // !!!!!
//			---------------------------------------------------

			
			// 5.
			if (discussion != null) {
				request.setAttribute("discussion", discussion);
				request.setAttribute("replies", replies); // !!!!!
				RequestDispatcher rd = request.getRequestDispatcher("discussion.jsp");
				rd.forward(request, response);
			} else {
				// 找不到
				response.sendRedirect("./");
			}

		}
	}

}
