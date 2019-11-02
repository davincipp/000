package community.controller.grouping;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import _00.util.CommunityUtils;
import community.model.Activity;
import community.model.ActivityReply;
import community.model.Reply;
import community.service.ActivityService;
import community.service.impl.ActivityServiceImpl;
import register.model.MemberBean;

@WebServlet("/grouping/reply.do")
@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
public class SaveActivityReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
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

		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMsg);

		String type = "";
		String strDid = "";
		Integer intDid = null;

		Timestamp postTime = null;
		long ptMillis = 0L;
		MemberBean author = null;
		
		Clob content = null;
		InputStream cis = null;
		long csize = 0L;

		ptMillis = System.currentTimeMillis();
		postTime = new Timestamp(ptMillis);
		author = mb;

		// 1. 讀取使用者輸入資料
		type = request.getParameter("type");
		if (!type.equalsIgnoreCase("reply")) {
			response.sendRedirect("./");
			return;
		}
		strDid = request.getParameter("activityId");
		
		Collection<Part> parts = request.getParts();
		if (parts != null) {
			for (Part part : parts) {
		// content (Clob)
				if (part.getContentType() == null && part.getName().contentEquals("content")) {
					cis = part.getInputStream();
					csize = part.getSize();
					cis.close();
				}
			}
		}

		
		// 2. 資料轉換
		if (strDid == null || strDid.equals("")) {
			// 沒有查詢字串 | ""
			response.sendRedirect("./");
			return;
			
		} else if (strDid != null && !strDid.equals("")) {
			try {
				intDid = Integer.parseInt(strDid);
			} catch (NumberFormatException e) {
				// 不是數字
				response.sendRedirect("./");
				return;
			}
		}

		if (cis != null) {
			try {
				content = CommunityUtils.isToClob(cis, "UTF-8");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}

		
		// 3. 檢查資料
		// content
		if (content == null || csize <= 6) {
			errorMsg.put("content", "文章內容不能空白");
		}
		
		// ----------------------------------------------------
		if (!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("editor.jsp");
			rd.forward(request, response);
			return;
		}

		
		// 4. 存進資料庫
//		---------------------------------------------------
		ActivityService ds = new ActivityServiceImpl();
		Activity activity = new Activity();
		activity.setId(intDid);
		ActivityReply r = new ActivityReply(null, activity, content, postTime, author);
		Integer i = ds.saveReply(r);
		System.out.println(i);
//		---------------------------------------------------

		
		// 5. 轉到頁面
		String newpage = "activity?d=" + intDid;
		response.sendRedirect(newpage);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
