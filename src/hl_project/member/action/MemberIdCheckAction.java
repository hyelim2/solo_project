package hl_project.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hl_project.member.db.MemberDAO;
import hl_project.member.db.MemberDTO;

public class MemberIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M: MemberIdCheckAction_execute() 호출");
		//아이디 중복 체크
        String id = request.getParameter("id");
        
        MemberDAO dao = new MemberDAO();
        
        boolean result = dao.duplicateIdCheck(id);
        
        System.out.println(result);
        
        
        request.setAttribute("result", result);
    
        
        ActionForward forward = new ActionForward();
        forward.setPath("./member/IdCheck.jsp");
        forward.setRedirect(false);
        return forward;


	}
}
