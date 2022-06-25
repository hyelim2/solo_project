package hl_project.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hl_project.member.db.MemberDAO;
import hl_project.member.db.MemberDTO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: MemberInfoAction_execute 호출");
		//세션 정보 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		// id 값 없으면 로그인 페이지로 이동
		ActionForward forward = new ActionForward();
		if(id== null){
			forward.setPath("./MemberLogin.mm");
			forward.setRedirect(true); // 주소변환 ok
			
			return forward;
		}
		
		//DAO 객체 생성하여 DB 정보 가져오기
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(id);
		
		//request 객체로 데이터 전달
		request.setAttribute("dto", dto);
		forward.setPath("./member/info.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
