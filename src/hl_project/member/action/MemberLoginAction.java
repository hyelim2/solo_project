package hl_project.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hl_project.member.db.MemberDAO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M: MemberLoginAction_execute() 호출");

		// 정보 불러오기
		// MemberDTO dto = new MemberDTO();
		// dto.setId(request.getParameter("id"));
		// dto.setPw(request.getParameter("pw"));

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		MemberDAO dao = new MemberDAO();
		int result = dao.loginCheck(id, pw);

		System.out.println(result);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (result == 1) {
			out.print("ok");
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
		} else {
			out.print("fail");
			// 자원해제
		}
		out.close();

		return null;
	}

}
