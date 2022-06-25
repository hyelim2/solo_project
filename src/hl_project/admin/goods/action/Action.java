package hl_project.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// 인터페이스 안에 상수, 추상메서드만 가능
	// 인터페이스에 있는 메서드는 반드시 추상메서드여야 한다
	// 기본적으로 public 사용, abstract 키워드 사용 => 모두 생략 가능함
	
	/*public abstract void method1();
	public <abstract> void method(); // 업무에서 주로 많이 사용
	<public>abstract void method2();
	<public abstract>void method3(); */
	
	// 호출할 때 request, response 정보를 필요로 하고,
	// 처리동작 후 ActionForward(페이지 이동객체) 리턴
	
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception; 
	
	
}
