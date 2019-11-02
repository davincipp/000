package community.controller.grouping;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

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
import community.model.MemberUploadImage;
import community.service.ActivityService;
import community.service.ActivityService;
import community.service.impl.ActivityServiceImpl;
import community.service.impl.ActivityServiceImpl;
import register.model.MemberBean;

@WebServlet("/grouping/imgupload.do")
@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
public class UploadEditorImageServlet extends HttpServlet {
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

		Timestamp uploadTime = null;
		long ptMillis = 0L;
		MemberBean author = null;
		Blob image = null;
		InputStream bis = null;
		long bsize = 0L;
		String imageName = "";

		ptMillis = System.currentTimeMillis();
		uploadTime = new Timestamp(ptMillis);
		author = mb;

		// 1. 讀取使用者輸入資料
		Collection<Part> parts = request.getParts();
		if (parts != null) {
			for (Part part : parts) {
		// image (Blob)
				if (part.getContentType() != null && part.getName().contentEquals("file")) {
					imageName = GlobalService.getFileName(part);
					imageName = GlobalService.adjustFileName(imageName, GlobalService.IMAGE_FILENAME_LENGTH);
					bis = part.getInputStream();
					bsize = part.getSize();
					bis.close();
				}
			}
		}

		// 2. 資料轉換
		if (bis != null) {
			try {
				image = CommunityUtils.isToBlob(bis, bsize);
			} catch (SQLException e) {
				return;
			}
		}

		// 3. 檢查資料

		// 4. 存進資料庫
//		---------------------------------------------------
		ActivityService ds = new ActivityServiceImpl();
		MemberUploadImage mui = new MemberUploadImage(null, uploadTime, author, image, imageName);
		Integer uploadImageId = ds.saveMemberUploadImage(mui);
//		---------------------------------------------------
		
		
		// 5.
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String jsonStr = "{\"id\": \"" + uploadImageId + "\"}";
		out.print(jsonStr);
		out.flush();
		out.close();

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
