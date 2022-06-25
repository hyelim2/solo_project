package hl_project.basket.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hl_project.basket.db.BasketDAO;

public class BasketListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: BasketListAction_execute 호출");
		
		// 세션제어 (로그인 제어) 
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./GoodList.go");
			forward.setRedirect(true);

			return forward;
		}
		// DAO 객체 - 장바구니 정보 가져오기
		BasketDAO dao = new BasketDAO();
		
		//장바구니 정보  상품 정보(필요한 일부 정보)
		Vector totalList = dao.getBasketList(id);
		
		// System.out.println("M : totalList "totalList); <- test용
		
		// request 영역에 저장
		//request.setAttribute("totalList", totalList);

		//view 페이지에 정보를 전달할 때는 데이터를 바로 쓸 수 있는 형태로 전달하는 것이 좋다
		request.setAttribute("basketList", totalList.get(0));
		request.setAttribute("goodsList", totalList.get(1));
		
		
		// 페이지 이동("./basket/goods_basket_list.jsp")
		forward.setPath("./basket/goods_basket_list.jsp");
		forward.setRedirect(false);
		
		return forward;
		
		
	}

}