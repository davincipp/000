package community.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.service.ActivityService;
import community.service.DiscussionService;
import community.service.impl.ActivityServiceImpl;
import community.service.impl.DiscussionServiceImpl;

//?type=reply/discussion&id=id
@WebServlet("/forum/text")
public class GetTextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = "";
		String strId = "";
		Integer intId = null;
		Clob clob = null;

		// 1.
		type = request.getParameter("type");
		strId = request.getParameter("id");
		
		
		// 2.
		if (strId == null || strId.equals("")) {
			// 沒有查詢字串 | ""
			return;
		} else if (strId != null && !strId.equals("")) {
			try {
				intId = Integer.parseInt(strId);
			} catch (NumberFormatException e) {
				// 不是數字
				return;
			}

			
			// 3.
			
			// 4.
//			---------------------------------------------------
			DiscussionService ds = new DiscussionServiceImpl();
			
			if(type.equalsIgnoreCase("discussion")) {
				clob = ds.getContentText(intId);
			} else if (type.equalsIgnoreCase("reply")) {
				clob = ds.getReplyContentText(intId);
			}
//			---------------------------------------------------
			
			// 5.
			PrintWriter out = response.getWriter();
			if (clob != null) {
				try (
					Reader in = clob.getCharacterStream();
					BufferedReader br = new BufferedReader(in);
				) {
					int count = 0;
					char[] ca = new char[8192];
					while ((count = br.read(ca)) != -1) {
						for (int i = 0; i < count; i++) {
							out.print(ca[i]);
						}
					}
				} catch (SQLException e) {
					;
				}
			}

		}

	}
}
