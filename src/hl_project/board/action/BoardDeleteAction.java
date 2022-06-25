package hl_project.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hl_project.board.db.BoardDAO;
import hl_project.board.db.BoardDTO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: BoardDeleteAction_execute 호출");
		
		//전달 정보 저장(num, pageNum, pw)
		//DTO에 저장
		BoardDTO dto = new BoardDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setPw(request.getParameter("pw"));
		String pageNum = request.getParameter("pageNum");

		// DAO 객체 -> 삭제 메서드
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard(dto.getNum(), dto.getPw());

		// 삭제 처리 결과에 따른 페이지 이동 (js)
		// 처리 응답 결과는 html 형태로 보여주겠다. (mine타입)
		response.setContentType("text/html; charset=UTF8");
		// 응답 결과를 처리하는 연결통로를 지정 (데이터 보낼 준비)
		PrintWriter out = response.getWriter();

		if (result == 0) { // 비밀번호 오류
			// out.print("HTML코드를 출력가능!");
			out.print("<script>");
			out.print(" alert('비밀번호를 확인해 주세요 '); ");
			out.print(" history.back(); ");
			out.print("</script>");

			// 응답처리 연결통로를 제거 (자원해제)
			out.close();

			return null; // 컨트롤러로 이동 X
		} else if (result == -1) {
			out.print("<script>");
			out.print(" alert('글 정보가 없습니다'); ");
			out.print(" history.back(); ");
			out.print("</script>");

			out.close();
			return null; // 컨트롤러로 이동 X
		}
		// result == 1 수정 성공
		out.print("<script>");
		out.print(" alert('삭제 되었습니다'); ");
		out.print(" location.href='./BoardList.bo?pageNum=" + pageNum+  "'; ");
		out.print("</script>");

		out.close();

		return null;
	}
}