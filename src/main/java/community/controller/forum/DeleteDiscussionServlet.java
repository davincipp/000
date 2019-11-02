package community.controller.forum;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.service.DiscussionService;
import community.service.impl.DiscussionServiceImpl;
import register.model.MemberBean;

@WebServlet("/forum/delete.do")
public class DeleteDiscussionServlet extends HttpServlet {
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
		Boolean b = false;
		
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
			b = ds.deleteDiscussion(mb, intId);
//			---------------------------------------------------

			
			// 5.
			if (b) {
				// 刪除成功
				response.sendRedirect("./");
			} else {
				// 刪除失敗
				response.sendRedirect("discussion?d=" + intId);
			}

		}
	}

}
