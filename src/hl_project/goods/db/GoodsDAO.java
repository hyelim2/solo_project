package hl_project.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import hl_project.admin.goods.db.GoodsDTO;

public class GoodsDAO {
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
		System.out.println(" DAO : " + con);

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

	// getGoodsList()
	public List<GoodsDTO> getGoodsList() {
		List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체 생성
			sql = "select * from p_goods";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			while (rs.next()) {
				// DTO -> List
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
			} // while

			System.out.println(" DAO : 상품정보 조회 ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return goodsList;
	}
	// getGoodsList()

	// getGoodsList(item)
	public List<GoodsDTO> getGoodsList(String item) {
		List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
		StringBuffer SQL = new StringBuffer();

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체 생성
			// sql = "select * from p_goods where all/category/best";
			SQL.append("select * from p_goods ");
			if (item.equals("all")) {
			} else if (item.equals("best")) {
				SQL.append("where best = ?");
			} else {
				SQL.append("where category=?");
			}

			// pstmt = con.prepareStatement(SQL.toString());
			pstmt = con.prepareStatement(SQL  +"");

			// ???
			if (item.equals("all")) {
			} else if (item.equals("best")) {
				pstmt.setInt(1, 1);
			} else {
				pstmt.setString(1, item);
			}

			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			while (rs.next()) {
				// DTO -> List
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
			} // while

			System.out.println(" DAO : 상품정보 조회 ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return goodsList;
	}
	// getGoodsList(item)

	// getGoods(num)

	public GoodsDTO getGoods(int num) {
		GoodsDTO dto = null;
		try {
			// 1.2 디비 연결
			con = getCon();
			// 3 sql 작성& pstmt
			sql = "select * from p_goods where num=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);

			// 4 sql 실행
			rs = pstmt.executeQuery();

			// 5 데이터 처리
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

			}

			System.out.println("DAO: 상품정보 1개 저장");
		} catch (Exception e) {

			e.printStackTrace();
		}

		return dto;
	}

}

// getGoods(num)