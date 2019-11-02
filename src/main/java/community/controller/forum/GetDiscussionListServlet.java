package community.controller.forum;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.Discussion;
import community.service.DiscussionService;
import community.service.impl.DiscussionServiceImpl;

@WebServlet("/forum/index.html")
public class GetDiscussionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sort = "";
		String strPage = "";
		Integer intPage = null;
		
		
		// 1.
		sort = request.getParameter("sort");
		strPage = request.getParameter("page");
		
		
		if (sort != null && !sort.equals("")) {
			if (sort.equalsIgnoreCase("new")) {
				sort = "new";
			} else if (sort.equalsIgnoreCase("old")) {
				sort = "old";
			} else {
				response.sendRedirect("./");
				return;
			}
		} else {
			sort = "new";
		}
		
		if (strPage != null && !strPage.equals("")) {
			try {
				intPage = Integer.parseInt(strPage);
			} catch (NumberFormatException e) {
				response.sendRedirect("./");
				return;
			}
		} else {
			intPage = 1;
		}
		
		
		// 4.
//		---------------------------------------------------
		DiscussionService ds = new DiscussionServiceImpl();
		List<Discussion> discussions = ds.getDiscussionList(sort, intPage);
		Integer totalPage = ds.getTotalPages();
//		---------------------------------------------------
		
		
		// 5.
		request.setAttribute("discussions", discussions);
		Map<String, Integer> pageInfo = new HashMap<String, Integer>();
		pageInfo.put("now", intPage);
		pageInfo.put("total", totalPage);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("sort", sort);
		RequestDispatcher rd = request.getRequestDispatcher("discussionList.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
