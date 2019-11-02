package community.controller.forum;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import _00.util.GlobalService;
import community.service.DiscussionService;
import community.service.impl.DiscussionServiceImpl;
import register.model.MemberBean;

@WebServlet("/forum/submitUpdate.do")
@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
public class UpdateDiscussionServlet extends HttpServlet {
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

//		Timestamp postTime = null;
		long ptMillis = 0L;
		MemberBean author = null;
		
		String title = "";
		Clob content = null;
		InputStream cis = null;
		long csize = 0L;
//		---------------------------------------
		// 揪團、拍賣用
		Blob coverImage = null;
		InputStream bis = null;
		long bsize = 0L;
		String coverImageName = "";
		// 揪團用
		Timestamp deadline = null;
		String dlStr = null;
		long dlMillis = 0L;

		ptMillis = System.currentTimeMillis();
//		postTime = new Timestamp(ptMillis);
		author = mb;

		// 1. 讀取使用者輸入資料
		type = request.getParameter("type");
		if (!type.equalsIgnoreCase("update")) {
			response.sendRedirect("./");
			return;
		}
		strDid = request.getParameter("discussionId");
		
		// title
		title = request.getParameter("title");
		Collection<Part> parts = request.getParts();
		if (parts != null) {
			for (Part part : parts) {
		// content (Clob)
				if (part.getContentType() == null && part.getName().contentEquals("content")) {
					cis = part.getInputStream();
					csize = part.getSize();
					cis.close();
//		-------------------------------------------------------------------------------------------------
		// coverImage (Blob)
				} else if (part.getContentType() != null && part.getName().contentEquals("coverImage")) {
					coverImageName = GlobalService.getFileName(part);
					coverImageName = GlobalService.adjustFileName(coverImageName, GlobalService.IMAGE_FILENAME_LENGTH);
					bis = part.getInputStream();
					bsize = part.getSize();
					bis.close();
				}
			}
		}
		// deadline
		dlStr = request.getParameter("deadlineDate") + request.getParameter("deadlineTime");

		
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
//		---------------------------------------
		if (bis != null) {
			try {
				coverImage = CommunityUtils.isToBlob(bis, bsize);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
		if (!dlStr.equals("") || dlStr.trim().length() != 0) {
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-ddHH:mm");
			try {
				dlMillis = formatDate.parse(dlStr).getTime();
				deadline = new Timestamp(dlMillis);
			} catch (ParseException e) {
				errorMsg.put("deadline", "活動截止日期格式錯誤");
			}
		}

		
		// 3. 檢查資料
		// title
		if (title.equals("") || title.trim().length() == 0) {
			errorMsg.put("title", "標題欄位不能空白");
		}
		// content
		if (content == null || csize <= 11) {
			errorMsg.put("content", "文章內容不能空白");
		}
		
		// deadline
		if (deadline == null) {
			errorMsg.put("deadline", "活動截止日期必須輸入");
		} else if (ptMillis > dlMillis) {
			errorMsg.put("deadline", "活動截止日期不可為過去時間");
		}
		
		// ----------------------------------------------------
		if (!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("editor.jsp");
			rd.forward(request, response);
			return;
		}

		
		// 4. 存進資料庫
//		---------------------------------------------------
		DiscussionService ds = new DiscussionServiceImpl();
		boolean b = ds.updateDiscussion(author, intDid, title, content, coverImage, coverImageName, deadline);
		System.out.println(b);
//		---------------------------------------------------

		
		// 5. 轉到頁面
		String newpage = "discussion?d=" + intDid;
		response.sendRedirect(newpage);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
