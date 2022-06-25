package hl_project.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hl_project.board.db.BoardDAO;
import hl_project.board.db.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: BoardUpdateAction_execute 호출");

		// 전달정보 저장 (num, pageNum)
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		// DAO 객체
		BoardDAO dao = new BoardDAO();
		// 글정보 가져오는 메서드 호출 (getBoard())
		BoardDTO dto = dao.getBoard(num);
		// 전달된 글 정보를 저장 - request 영역
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);

		// 페이지 이동(./board/updateWrite.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/updateWrite.jsp");
		forward.setRedirect(false);

		return forward;
	}

}