package hl_project.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hl_project.member.db.MemberDAO;
import hl_project.member.db.MemberDTO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: MemberDeleteAction_excute 호출");
		
		HttpSession session = request.getSession(); 
		String id = (String) session.getAttribute("id");
		String pw = request.getParameter("pw");
		
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./MemberLogin.mm");
			forward.setRedirect(true);
			return forward;
		}
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		int result = dao.deleteMember(id, pw);
		
		//자바스크립트사용하여 alert()창띄우기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if(result == 0){ //로그인 비번오류
			out.print("<script>");
			out.print("alert('비밀번호를 확인하세요');");
			out.print("history.back();");
			out.print("</script>");
			//자원해제
			out.close();
			return null; //null이란 Controller에서 페이지 이동하지않겠다는  의미
		}else if(result == -1){
			out.print("<script>");
			out.print("alert('존재하지 않는 아이디입니다');");
			out.print("history.back();");
			out.print("</script>");
			//자원해제
			out.close();
			return null; //null이란 Controller에서 페이지 이동하지않겠다는  의미			
		}
		
		//if(result == 1)
		session.invalidate();
		out.print("<script>");
		out.print("alert('정상적으로 탈퇴 되었습니다.');");
		out.print("location.href='./Main.mm';");
		out.print("</script>");
		//자원해제
		out.close();
		return null;
	}
}