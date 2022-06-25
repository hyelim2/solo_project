package hl_project.admin.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hl_project.admin.goods.db.AdminGoodsDAO;
import hl_project.admin.goods.db.GoodsDTO;

public class GoodsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
System.out.println(" M : GoodsListAction_execute() 호출 ");
		
		// DAO 객체 - 상품정보를 모두 가져오는 동작
		AdminGoodsDAO dao = new AdminGoodsDAO();
		
		List<GoodsDTO> goodsList = dao.getGoodsList();
		// reqeust 영역에 저장
		request.setAttribute("goodsList", goodsList);
		//request.setAttribute("goodsList", dao.getGoodsList());
		
		// 페이지 이동 (./adminGoods/admin_goods_list.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./admin_goods/admin_goods_list.jsp");
		forward.setRedirect(false);// 1. request 저장하면 무조건 false, 2. 주소가 바뀌면 xx 포워딩
		
		return forward;
	}

}