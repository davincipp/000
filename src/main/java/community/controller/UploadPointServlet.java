package community.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.model.Discussion;
import community.model.Point;
import community.model.Reply;
import community.service.DiscussionService;
import community.service.impl.DiscussionServiceImpl;
import register.model.MemberBean;

@WebServlet({"/forum/point.do","/grouping/point.do"})
@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
public class UploadPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			return;
		}

		String strDid = "";
		Integer intDid = null;
		Discussion discussion = null;
		String strRid = "";
		Integer intRid = null;
		Reply reply = null;
		MemberBean member = null;
		String type = "";
		Integer pointVal = null;
		Timestamp pointTime = null;
		long ptMillis = 0L;

		ptMillis = System.currentTimeMillis();
		pointTime = new Timestamp(ptMillis);
		member = mb;

		// 1.
		type = request.getParameter("type");
		strDid = request.getParameter("did");
		strRid = request.getParameter("rid");

		// 2.
		if (!type.equals("") || type.trim().length() != 0) {
			if (type.trim().equalsIgnoreCase("plus")) {
				pointVal = 1;
			} else if (type.trim().equalsIgnoreCase("minus")) {
				pointVal = -1;
			} else {
				return;
			}
		} else {
			return;
		}
		if (strDid == null || strDid.trim().equals("")) {
			return;

		} else if (strDid != null && !strDid.trim().equals("")) {
			try {
				intDid = Integer.parseInt(strDid);
			} catch (NumberFormatException e) {
				return;
			}
		}
		if (strRid != null && !strRid.trim().equals("")) {
			try {
				intRid = Integer.parseInt(strRid);
			} catch (NumberFormatException e) {
				return;
			}
		}

		// 3.

		// 4.
//		---------------------------------------------------
		DiscussionService ds = new DiscussionServiceImpl();
		Boolean deleteRecord = ds.preModifyPointRecord(member, intDid, intRid);

		Integer pointType = 0;
		if (!deleteRecord) {
			discussion = new Discussion();
			discussion.setId(intDid);
			if (intRid != null) {
				reply = new Reply();
				reply.setId(intRid);
			}
			Point point = new Point(null, discussion, reply, member, pointVal, pointTime);
			pointType = ds.savePoint(point);
		}
		Long newTotalPoint = ds.getTotalPoint(intDid, intRid);

//		---------------------------------------------------

		// 5.
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String tagId = "";
		if (strRid != null && !strRid.trim().equals("")) {
			tagId = "r" + intRid;
		} else {
			tagId = "d" + intDid;
		}
		String jsonStr = "{\"id\": \"" + tagId + "\", \"point\": \"" + newTotalPoint + "\", \"type\": \"" + pointType
				+ "\"}";
		out.print(jsonStr);
		out.flush();
		out.close();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
