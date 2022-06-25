package hl_project.admin.goods.action;

public class ActionForward {
	// 페이지 이동할 떄 사용하는 객체
	private String path; // 이동경로 저장
	private boolean isRedirect; // 이동 방식 저장

	
	// redirect 방식 : true 주소변환o 화면변환o
	// forward 방식 : false 주소변환x 화면변환o

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() { //isRedirect = get 메서드
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	@Override
	public String toString() {
		return "ActionForward [path=" + path + ", isRedirect=" + isRedirect + "]";
	}
	

}
