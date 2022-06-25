package hl_project.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hl_project.basket.db.BasketDAO;

public class BasketDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : BasketDeleteAction_execute 호출");
		
		//세션 값 제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./GoodsList.go");
			forward.setRedirect(true);
			return forward;
			
		}
		
		// 전달정보 저장 (b_num)
		int b_num = Integer.parseInt(request.getParameter("b_num"));
	
		//DAO 생성 - BasketDelete(b_num)
		BasketDAO dao = new BasketDAO();
		
		dao.basketDelete(b_num);
		
		//페이지 이동
		
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);
		
	
		return forward;
	}

}