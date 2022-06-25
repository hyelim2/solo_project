package hl_project.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hl_project.basket.db.BasketDAO;
import hl_project.basket.db.BasketDTO;

public class BasketAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: BasketAddAction_excute 호출");

		// 사용자 정보 체크 (로그인여부)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		// 이동
		ActionForward forward = new ActionForward();
		if (id == null) {
			System.out.println("M: ID정보 없음");
			// forward.setPath("./MemberLogin.me");
			forward.setPath("./GoodsList.go");
			forward.setRedirect(true); // 화면변환 주소변환
			return forward;
		}

		// 전달정보저장(상품 번호, 구매 수량, 크기, 색상, ID)
		// DTO 저장
		BasketDTO dto = new BasketDTO();

		dto.setB_g_num(Integer.parseInt(request.getParameter("num")));
		dto.setB_g_amount(Integer.parseInt(request.getParameter("amount")));
		dto.setB_g_size(request.getParameter("size"));
		dto.setB_m_id(id);

		// System.out.println("M: "dto);

		// BasketDAO 객체
		BasketDAO dao = new BasketDAO();

		// 동일상품 수량만 업데이트 -checkGoods(dto)
		int ck = dao.checkGoods(dto);
		
		//ck == 0 (해당옵션 x, 처음구매)
		//ck == 1 (해당옵션 o, 기존상품구매)

		// 장바구니 저장
		if (ck != 1) { // 선택한 게 없을 때(기존의 상품이 없음)
			dao.basketAdd(dto);
		}

		// 페이지 이동(BasketList.ba)
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);
		
		
		return forward;
	}

}