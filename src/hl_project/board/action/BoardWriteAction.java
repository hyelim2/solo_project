package hl_project.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import hl_project.board.db.BoardDAO;
import hl_project.board.db.BoardDTO;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: BoardWriteAction_execute() 실행됨");
		// 1. 한글처리(생략)

		/* 파일 업로드 구현 */
		// 1) 파일 업로드
		// - 가상의 업로드 폴더 설정 upload 폴더 생성
		String path = request.getRealPath("/upload");
		System.out.println("M :"  +path);
		// - 업로드 파일의 크기를 설정 (제한)
		int maxSize = 5 * 1024 * 1024; // 5mb
		// - MultipartRequest 객체 생성 (업로드)
		MultipartRequest multi = new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());

		System.out.println(" M : 파일 업로드 완료! ");
		/* 파일 업로드 구현 */

		// 2.전달되는 파라미터 정보저장 ->BoardDTO생성
		// request로 받은 정보 -> multipartrequest로 바뀌었으니 multipartrequest에 정보저장
		BoardDTO dto = new BoardDTO();
		dto.setName(multi.getParameter("name"));
		dto.setPw(multi.getParameter("pw"));
		dto.setTitle(multi.getParameter("title"));
		dto.setContent(multi.getParameter("content"));
		//파라미터로 file을 제대로 가져올 수 없음
		//getFilesystemName()를 사용
		dto.setFile(multi.getFilesystemName("file"));

		System.out.println("M :"  +dto);

		// 3.BoardDAO객체생성
		BoardDAO dao = new BoardDAO();
		// inserBoard() 글쓰기 동작 실행
		dao.insertBoard(dto);

		ActionForward forward = new ActionForward();

		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);

		return forward;
	}
}