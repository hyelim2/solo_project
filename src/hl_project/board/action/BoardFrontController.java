package hl_project.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" BoardFrontController - doProcess() 호출");
		System.out.println("GET /POST 방식 모두 처리 !! \n\n\n");

		/////////////////////////////// 1. 가상 주소
		/////////////////////////////// 계산///////////////////////////////////////
		System.out.println("C: 1. 가상 주소 계산 시작");

		// 가상주소 가져오기
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - " + requestURI);
		// 프로젝트명 가져오기
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - " + ctxPath);
		// 가상주소 계산 (가상주소 - 프로젝트명)
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - " + command);

		System.out.println("C: 1. 가상 주소 계산 끝");
		/////////////////////////////// 1. 가상 주소
		/////////////////////////////// 계산///////////////////////////////////////

		/////////////////////////////// 2. 가상 주소
		/////////////////////////////// 매핑///////////////////////////////////////
		System.out.println("C: 2. 가상 주소 매핑 시작");
		Action action = null;
		ActionForward forward = null;

		if (command.equals("/BoardWrite.bo")) {
			System.out.println("C :/BoardWrite.bo 호출");
			System.out.println("C: DB사용 하지 않고 정보만 입력(view)");
			// 이동 객체 생성 DB 사용하지 않을 때 이동하는 패턴임!
			// BoardWrite.bo <-페이지로 가면 BoardWrite.jsp페이지나옴
			forward = new ActionForward();
			forward.setPath("./board/boardWrite.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/BoardWriteAction.bo")) {
			System.out.println("C: BoardWriteAction.bo 호출");
			System.out.println("C : DB 사용, 페이지 이동!");
			// DB 사용하면서 이동하는 패턴임 BoardWriteAction 객체 생성!
			action = new BoardWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 글목록 페이지
		} else if (command.equals("/BoardList.bo")) {
			System.out.println("C: /BoardList.bo 호출");
			System.out.println("C: DB사용, 해당 페이지 출력");
			// DB를 사용하고 가져온 정보를 페이지에 바로 보여줌!!!
			// BoardListAction 객체 생성
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 글 본문 페이지
		} else if (command.equals("/BoardContent.bo")) {
			System.out.println("C: /BoardContent.bo 호출");
			System.out.println("C: DB 사용, 해당 페이지 출력");
			// DB에 저장된 정보를 페이지에 바로 보여줌!!
			// BoardContentAction 객체 생성
			action = new BoardContentAction();
			try {
				forward = action.execute(request, response);
				System.out.println(" C : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 글 수정 페이지
		} else if (command.equals("/BoardUpdate.bo")) {
			System.out.println("C: /BoardUpdate.bo 호출");
			System.out.println("C: DB정보를 가져와서, 화면에 출력");

			// boardWrite.jsp 활용 view 페이지 생성
			/// BoardUpdateAction 객체 생성
			action = new BoardUpdateAction();

			try {
				forward = action.execute(request, response);
				System.out.println(" C : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardUpdateProAction.bo")) {
			System.out.println("C: BoardUpdateProAction.bo 호출");
			System.out.println("C: DB사용 (수정), 페이지 이동");

			// BoardUpdateProAction 객체 생성
			action = new BoardUpdateProAction();
			try {
				forward = action.execute(request, response);
				System.out.println("C :" + forward);
			} catch (Exception e) {
				e.printStackTrace();
			} // 글 삭제 페이지
		} else if (command.equals("/BoardDelete.bo")) {
			System.out.println("C: /BoardDelete.bo 호출");
			System.out.println("C: DB사용하지 않고 페이지 이동 jsp -> bo");

			forward = new ActionForward();
			forward.setPath("./board/deleteWrite.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/BoardDeleteAction.bo")) {
			System.out.println("C:/BoardDeleteAction.bo 호출");
			System.out.println("C: DB사용, 페이지 이동");
			// BoardDeleteAction 객체 생성
			action = new BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("C :" + forward);
				e.printStackTrace();
			} // 답글 페이지
		} else if (command.equals("/BoardReWrite.bo")) {
			System.out.println("C: /BoardReWrite.bo 호출");
			System.out.println("C: DB 사용x, view 출력");
			// view 출력 - forward 바로 생성
			forward = new ActionForward();
			forward.setPath("./board/boardReWrite.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/BoardReWriteAction.bo")) {
			System.out.println("/BoardReWriteAction.bo 호출");
			System.out.println("DB 사용하고, 페이지 이동");
			// BoardReWriteAction 객체 생성
			action = new BoardReWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardSearch.bo")) {
			System.out.println("/BoardSearch.bo 호출");
			System.out.println("DB사용하고, 페이지 출력");
			// BoardSerchAction 객체 생성
			// DB에서 search 정보 찾아서 페이지에 보여줌 => 출력
			action = new BoardSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("C: 2. 가상 주소 매핑 끝 \n");
		/////////////////////////////// 2. 가상 주소
		/////////////////////////////// 매핑///////////////////////////////////////
		/////////////////////////////// 3. 페이지
		/////////////////////////////// 이동///////////////////////////////////////
		System.out.println(" C : 3. 페이지 이동 시작");
		if (forward != null) { // 페이지 이동정보가 있을때

			if (forward.isRedirect()) { // true
				System.out.println(" C : redirect방식, " + forward.getPath() + "로 이동");
				response.sendRedirect(forward.getPath());

			} else { // false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());

				System.out.println(" C : forward방식, " + forward.getPath() + "로 이동");
				dis.forward(request, response);
			}

		}
		System.out.println(" C : 3. 페이지 이동 끝\n ");
		/////////////////////////////// 3. 페이지
		/////////////////////////////// 이동///////////////////////////////////////

	} // doProcess

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" BoardFrontController - doGET() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" BoardFrontController - doPOST() 호출");
		doProcess(request, response);
	}

}