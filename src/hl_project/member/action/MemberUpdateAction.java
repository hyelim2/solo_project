package hl_project.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hl_project.member.db.MemberDAO;
import hl_project.member.db.MemberDTO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: MemberUpdateActio_execute 호출");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setPhone(request.getParameter("phone"));
		dto.setEmail(request.getParameter("email"));
		dto.setName(request.getParameter("name"));
		dto.setAddress(request.getParameter("address"));
		
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMember(dto);
		
	/*	if(result == 0){ //비밀번호 오류일 때
			//자바스크립트사용하여 alert()창띄우기
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호오류');");
			out.print("history.back();");
			out.print("</script>");
			//자원해제
			out.close();
			return null; 
			
			
		}else if(result == -1){
			//자바스크립트사용하여 alert()창띄우기
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('존재하지 않는 아이디입니다');");
			out.print("history.back();");
			out.print("</script>");
			//자원해제
			out.close();
			return null; */		
		
		//4-1. 정상 로그인처리
		//result == 1 //정상 로그인처리
		//자바스크립트사용하여 alert()창띄우기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('회원정보 수정이 정상 처리되었습니다');");
		out.print("location.href='./Main.mm'");
		out.print("</script>");
		//자원해제
		out.close();
		
		return null;
	}
}