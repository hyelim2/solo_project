package hl_project.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hl_project.admin.goods.db.AdminGoodsDAO;
import hl_project.admin.goods.db.GoodsDTO;

public class GoodsModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: GoodsModifyAction_execute 호출");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if (id == null || !id.equals("admin")) {
			// response.sendRedirect("./Main.mm"); 컨트롤러통해서 이동시키기
			forward.setPath("./Main.mm");
			forward.setRedirect(true);
			return forward;
		}

		// 상품번호(pk로 구분함)
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("M: 상품번호 : "  +num);

		// DAO 객체 생성 - 상품번호에 해당하는 상품정보 모두를 가져오는 동작
		AdminGoodsDAO dao = new AdminGoodsDAO();
		GoodsDTO dto = dao.getGoods(num);
		// request영역에 저장
		request.setAttribute("dto", dto);
		// 페이지 이동(./admin_goods_modify.jsp)
		forward.setPath("./admin_goods/admin_goods_modify.jsp");
		forward.setRedirect(false);

		return forward;
	}

}