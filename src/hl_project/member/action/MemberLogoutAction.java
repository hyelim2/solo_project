package hl_project.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M: MemberLogoutAction_execute() 호출");

		HttpSession session = request.getSession();
		session.invalidate();

		// 메인페이지이동 : 결과에 따른 페이지이동처리, 무조건 Controller로 이동
		System.out.println("C : 로그아웃 성공 후 페이지 이동 ");

		// 자바스크립트사용하여 alert()창띄우기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('정상적으로 로그아웃 되었습니다');");
		out.print("location.href='./Main.mm';");
		out.print("</script>");
		// 자원해제
		out.close();
		return null;
	}

}
