package community.controller.grouping;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.Activity;
import community.model.MemberUploadImage;
import community.service.ActivityService;
import community.service.impl.ActivityServiceImpl;
import register.model.MemberBean;

@WebServlet("/grouping/img")
public class GetImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = "";
		String strId = "";
		Integer intId = null;
		Blob blob = null;
		String imageName = "";
		String mimeType = "";

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
			ActivityService ds = new ActivityServiceImpl();
			
			if (type.equalsIgnoreCase("cover")) {
				Activity d = ds.queryActivity(intId);
				if (d != null) {
					imageName = d.getCoverImageName();
					blob = d.getCoverImage();
				}
			} else if (type.equalsIgnoreCase("member")) {
				MemberBean mb = ds.queryMember(intId);
				if (mb != null) {
					imageName = mb.getFileName();
					blob = mb.getMemberImage();
				}
			} else if (type.equalsIgnoreCase("mUpload")) {
				MemberUploadImage mui = ds.queryMemberUploadImage(intId);
				if (mui != null) {
					imageName = mui.getImageName();
					blob = mui.getImage();
				}
			}
//			---------------------------------------------------
			
			// 5.
			mimeType = getServletContext().getMimeType(imageName);
			response.setContentType(mimeType);

			try (
				OutputStream os = response.getOutputStream();
				InputStream is = blob.getBinaryStream();
			) {
				int len = 0;
				byte[] bytes = new byte[8192];
				while ((len = is.read(bytes)) != -1) {
					os.write(bytes, 0, len);
				}
			} catch (SQLException e) {
				;
			}
		}

	}

}
