package community.controller.grouping;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.Activity;
import community.model.Reply;
import community.service.ActivityService;
import community.service.impl.ActivityServiceImpl;

@WebServlet("/grouping/activity")
public class GetActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String strId = "";
		Integer intId = null;
		Activity activity = null;

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
			ActivityService ds = new ActivityServiceImpl();
			activity = ds.queryActivity(intId);
			List<Reply> replies = ds.getReplyList(activity); // !!!!!
//			---------------------------------------------------

			
			// 5.
			if (activity != null) {
				request.setAttribute("activity", activity);
				request.setAttribute("activityReplies", replies); // !!!!!
				RequestDispatcher rd = request.getRequestDispatcher("activity.jsp");
				rd.forward(request, response);
			} else {
				// 找不到
				response.sendRedirect("./");
			}

		}
	}

}
