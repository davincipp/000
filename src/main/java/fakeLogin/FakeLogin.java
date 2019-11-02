package fakeLogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import register.model.MemberBean;

@WebServlet("/login/FakeLogin")
public class FakeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		MemberBean mb = null;
		String userId = request.getParameter("userId");

		if (userId.equals("test1")) {
			mb = new MemberBean();
			mb.setPkey(1);
		} else {
			mb = new MemberBean();
			mb.setPkey(2);
		}

		session.setAttribute("LoginOK", mb);
		System.out.println("LoginOK" + mb);
		response.sendRedirect("/fititude_community/forum/");

	}

}
