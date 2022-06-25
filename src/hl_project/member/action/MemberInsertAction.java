package hl_project.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hl_project.member.db.MemberDAO;
import hl_project.member.db.MemberDTO;

public class MemberInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: MemberInsertAction_execute() 호출");

		// 전달된 정보 저장
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setPhone(request.getParameter("phone"));
		dto.setEmail(request.getParameter("email"));

		// DAO 객체 생성 - insertMember();
		MemberDAO dao = new MemberDAO();
		dao.insertMember(dto);

	
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.mm");
		forward.setRedirect(true);

		return forward;
	}

}
