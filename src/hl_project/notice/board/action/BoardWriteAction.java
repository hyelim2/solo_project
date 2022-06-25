package hl_project.notice.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hl_project.notice.board.db.NoticeBoardDAO;
import hl_project.notice.board.db.NoticeBoardDTO;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: NoticeBoardWriteAction_execute() 실행됨");
		// 1. 한글처리(생략)
		
		// 2.전달되는 파라미터 정보저장 ->NoticeBoardDTO생성
		NoticeBoardDTO dto = new NoticeBoardDTO();
		dto.setName(request.getParameter("name"));
		dto.setPw(request.getParameter("pw"));
		dto.setTitle(request.getParameter("title"));

		System.out.println("M :"  +dto);

		// 3.NoticeBoardDAO객체생성
		NoticeBoardDAO dao = new NoticeBoardDAO();
		// inserBoard() 글쓰기 동작 실행
		dao.insertBoard(dto);

		ActionForward forward = new ActionForward();

		forward.setPath("./BoardList.no");
		forward.setRedirect(true);

		return forward;
	}
}