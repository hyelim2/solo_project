package hl_project.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hl_project.board.db.BoardDAO;
import hl_project.board.db.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : BoardContentAction_execute 호출");

		// 글 정보 가지고 오기(num, pageNum)
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		// BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();

		// 조회수 증가 동작
		dao.updateReadCount(num);
		System.out.println("M: 조회수 1 증가 완료");
		// 글 번호에 해당하는 글의 전체 정보 가져오기!!
		BoardDTO dto = dao.getBoard(num);
		System.out.println("M: 글 정보 1 조회 완료");
		// request 영역에 글 정보 저장
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		// 목록창 이동시 내가 클릭했던 글이 있던 페이지로 돌아가게 해줌

		// 페이지 이동(출력) (./board/boardContent.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardContent.jsp");
		forward.setRedirect(false);

		return forward;
	}

}