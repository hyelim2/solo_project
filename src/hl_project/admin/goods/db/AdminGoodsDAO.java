package hl_project.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminGoodsDAO {

	// 공통변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 디비연결 메서드
	private Connection getCon() throws Exception {
		// 1.2. 디비연결

		// 1) 프로젝트 정보를 초기화
		Context initCTX = new InitialContext();
		// 2) 프로젝트에 저장된 리소스 정보를 불러오기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/projectdb");
		// 3) 디비연결
		con = ds.getConnection();

		System.out.println(" DAO : 디비연결 성공(커넥션풀) ");
		System.out.println(" DAO : "  +con);

		return con;
	}
	// 디비연결 메서드

	// 디비 자원해제 메서드
	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
			System.out.println(" DAO : 디비 연결 자원해제 ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 디비 자원해제 메서드
	// insertGoods(goods)
	public void insertGoods(GoodsDTO dto) {
		int goodsNum = 0;
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. SQL작성 & pstmt 객체
			sql = "select max(num) from p_goods";
			pstmt = con.prepareStatement(sql);
			// 4. SQL 실행 ( 상품번호 계산, 상품 등록 )
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if (rs.next()) {
				goodsNum = rs.getInt(1) + 1;
			}

			//////////////////////////////////////////////////////////////////
			// 상품 등록

			// 3. sql 작성 & pstmt 객체
			sql = "insert into p_goods (num,category,name,content,size,"+
					 "amount,price,image,best,date) values(?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, goodsNum);
			pstmt.setString(2, dto.getCategory());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getSize());
			pstmt.setInt(6, dto.getAmount());
			pstmt.setInt(7, dto.getPrice());
			pstmt.setString(8, dto.getImage());
			pstmt.setInt(9, dto.getBest());

			// 4. sql 실행
			pstmt.executeUpdate();

			System.out.println(" DAO : 관리자 상품등록완료 ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}

	// insertGoods(goods)
	// getGoodsList()
	public List<GoodsDTO> getGoodsList() {

		List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>(); // 업캐스팅

		// 1.2 디비연결
		try {
			con = getCon();
			// 3. sql 작성(select) &pstmt 객체
			sql = "select * from p_goods";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터처리 (DB -> DTO ->Array)

			while (rs.next()) {
				GoodsDTO dto = new GoodsDTO();

				dto.setAmount(rs.getInt("amount"));
				dto.setBest(rs.getInt("best"));
				dto.setCategory(rs.getString("category"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				dto.setPrice(rs.getInt("price"));
				dto.setSize(rs.getString("size"));

				goodsList.add(dto);

			}
			System.out.println("DAO: 관리자 상품 목록 조회 성공");

		} catch (Exception e) {
			System.out.println("DAO: 관리자 상품 목록 조회 실패");
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return goodsList;
	}

	// getGoods(num)
	public GoodsDTO getGoods(int num) {
		GoodsDTO dto = null;
		try {
			// 1. 2 디비연결
			con = getCon();
			// 3. sql 작성, pstmt 객체 생성
			sql = "select * from p_goods where num =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리(DB데이터 -> DTO에 옮겨담아라)
			if (rs.next()) {
				dto = new GoodsDTO();

				dto.setAmount(rs.getInt("amount"));
				dto.setBest(rs.getInt("best"));
				dto.setCategory(rs.getString("category"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				dto.setPrice(rs.getInt("price"));
				dto.setSize(rs.getString("size"));

			} // if

			System.out.println("DAO: " + num + "상품정보 저장완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return dto;

	}
	// getGoods(num)

	// modifyGoods(dto)
	public void modifyGoods(GoodsDTO dto) {
		System.out.println("DAO" + dto);
		try {
			// 1 2 디비연결
			con = getCon();
			// 3. sql 작성 (update), pstmt 객체
			// (상품번호(pk), 카테고리, 가격, 이름, 컬럼, 수량, 사이즈, 상품정보, 인기상품)
			sql = "update p_goods set "+ "category=?, price=?, name=?, amount=?, size=?, content=?, best=? "+
					 "where num=?";

			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setString(1, dto.getCategory());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getAmount());
			pstmt.setString(5, dto.getSize());
			pstmt.setString(6, dto.getContent());
			pstmt.setInt(7, dto.getBest());
			pstmt.setInt(8, dto.getNum());

			// 4 sql 실행
			pstmt.executeUpdate();

			System.out.println("DAO: 관리자 상품정보 수정완료");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}

	// modifyGoods(dto)

	// GoodsDelete(num)
	public void deleteGoods(int num) {
		try {
			// 1 2 디비연결
			con = getCon();
			// 3 sql 작성 (delect)&pstmt 객체
			sql = "delete from p_goods where num=?";
			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setInt(1, num);

			// 4.sql 실행
			pstmt.executeUpdate();

			System.out.println("DAO : 관리자 등록상품 삭제 완료");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}
	// GoodsDelete(num)

}