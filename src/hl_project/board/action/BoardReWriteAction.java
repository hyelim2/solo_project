package hl_project.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hl_project.board.db.BoardDAO;
import hl_project.board.db.BoardDTO;

public class BoardReWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M :BoardReWriteAction_excute 호출");
		
		// 전달되는 파라메터 정보 저장 (num, re_ref, re_lev, re_seq, pw, name, content, title)
		//hidden으로 가져온 정보 저장
		BoardDTO dto = new BoardDTO();
		dto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		dto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		dto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		
		
		//입력값 저장
		dto.setName(request.getParameter("name"));
		dto.setContent(request.getParameter("content"));
		dto.setPw(request.getParameter("pw"));
		dto.setTitle(request.getParameter("title"));	
		
		System.out.println(dto);
		//BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		dao.reInsertBoard(dto);
		//페이지 이동	
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true); // 화면 변환, 주소 변환
		
		return forward;
	}

}