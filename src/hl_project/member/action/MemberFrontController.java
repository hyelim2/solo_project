package hl_project.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet {

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

		//////////////////////// 2. 가상 주소
		//////////////////////// 매핑/////////////////////////////////////
		System.out.println(" C : 2. 가상 주소 매핑 시작 ");
		Action action = null;
		ActionForward forward = null;
		// 회원가입처리
		if (command.equals("/MemberInsert.mm")) {
			System.out.println(" C : /MemberInsert.mm 호출 ");
			// DB사용 x, view페이지 이동

			forward = new ActionForward();
			forward.setPath("./member/join.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MemberInsertAction.mm")) {
			System.out.println(" C : /MemberInsertAction.mm 호출 ");
			// DB사용 O, 페이지 이동

			// MemberInsertAction 객체
			action = new MemberInsertAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 로그인 처리
		} else if (command.equals("/MemberLogin.mm")) {
			System.out.println("C: /MemberLogin.mm 호출");

			forward = new ActionForward();
			forward.setPath("./member/login.jsp");
			forward.setRedirect(false); // 주소 안 바뀌고 화면 전환

		} else if (command.equals("/MemberLoginAction.mm")) {
			System.out.println("C: /MemberLoginAction.mm 호출");

			action = new MemberLoginAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 메인 페이지 이동
		} else if (command.equals("/Main.mm")) {
			forward = new ActionForward();
			forward.setPath("./main/main.jsp");
			forward.setRedirect(false);
			// 로그아웃 연결 -> view 필요 xx
		} else if (command.equals("/MemberLogout.mm")) {
			System.out.println("C /MemberLogout.mm 호출");
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 회원 정보 조회
		} else if (command.equals("/MemberInfo.mm")) {
			System.out.println("C: /MemberInfo 호출");
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 회원 삭제
		} else if (command.equals("/MemberDelete.mm")) {
			System.out.println("C: /MemberDelete 호출");
			// DB 사용 xx, 페이지 이동
			// MemberDeleteAction 객체 생성
			forward = new ActionForward();
			forward.setPath("./member/delete.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MemberDeleteAction.mm")) {
			// new MemberDeleteAction() 객체 생성
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 회원 아이디 체크
		} else if (command.equals("/MemberIdCheck.mm")) {
			System.out.println("C: /MemberIdCheck.mm 호출");
			action = new MemberIdCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 회원 정보 수정
		} else if (command.equals("/MemberUpdate.mm")) {
			System.out.println("c: MemberUpdate.mm 호출");
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println(" C : 2. 가상 주소 매핑 끝\n ");
		//////////////////////// 2. 가상 주소
		//////////////////////// 매핑/////////////////////////////////////
		//////////////////////// 3. 페이지 이동/////////////////////////////////////
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
		//////////////////////// 3. 페이지 이동/////////////////////////////////////

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}