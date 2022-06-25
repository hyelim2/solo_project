package hl_project.admin.goods.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import hl_project.admin.goods.db.AdminGoodsDAO;
import hl_project.admin.goods.db.GoodsDTO;


	public class GoodsAddAction implements Action {

		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {
			System.out.println("M : GoodsAddAction_execute() 호출");
			
			//파일 업로드 준비 : multipart객체생성과 
			//`./upload`폴더생성 for 가상경로
			//파일이 저장되는 실제위치 : request.getRealPath는 deprecated이므로 context.getRealPath()사용 할 것
			ServletContext context = request.getServletContext();
			String realpath = context.getRealPath("/upload");
			System.out.println("realpath: "+realpath);
			//크기 : 10MB (크기가 클수록 서버에는 부담)
			int maxSize = 10 * 1024 * 1024; 
			
			// multipart객체생성
			MultipartRequest multi = new MultipartRequest(
					request, realpath, maxSize, "UTF-8", new DefaultFileRenamePolicy()
					);
			System.out.println("M : 파일업로드완료"+multi);
			
			//4. 상품정보를 저장(파라미터) = GoodsDTO 객체 생성, DBMS에 테이블 생성
			GoodsDTO gdto = new GoodsDTO();
			gdto.setCategory(multi.getParameter("category"));
			gdto.setName(multi.getParameter("name"));
			gdto.setPrice(Integer.parseInt(multi.getParameter("price")));
			gdto.setAmount(Integer.parseInt(multi.getParameter("amount")));
			gdto.setSize(multi.getParameter("size"));
			gdto.setContent(multi.getParameter("content"));		
			gdto.setBest(0); //0인경우:일반상품, 1인경우:인기상품
			//이미지정보처리
			String img = multi.getFilesystemName("file1")+","
					+ multi.getFilesystemName("file2")+","
					+ multi.getFilesystemName("file3")+","
					+ multi.getFilesystemName("file4");
			System.out.println("img 4개합: "+img);
			gdto.setImage(img);
			
			//AdminGoodsDAO 객체생성 -> insertGoods()메서드 생성
			AdminGoodsDAO agdao = new AdminGoodsDAO();
			agdao.insertGoods(gdto);
			
			//페이지 이동
			ActionForward forward = new ActionForward();
			forward.setPath("./AdminGoodsList.ag");
			forward.setRedirect(true);		
			return forward;
		}
	}