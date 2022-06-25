package hl_project.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import hl_project.admin.goods.db.GoodsDTO;

public class BasketDAO {
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

	// checkGoods(dto)
	public int checkGoods(BasketDTO dto) {
		int check = 0;
		// 1.2 디비연결
		try {
			con = getCon();
			// 3. sql 쿼리작성 & pstmt객체
			// 동일 옵션의 상품이 있는지 없는지 체크

			sql = "select * from p_basket where b_m_id=? and b_g_num=? and b_g_size=? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getB_m_id());
			pstmt.setInt(2, dto.getB_g_num());
			pstmt.setString(3, dto.getB_g_size());

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터처리
			if (rs.next()) {
				// 동일한 옵션의 상품이 존재하는 경우 수량만 변경
				// 3. sql (update)
				sql = "update p_basket set b_g_amount=b_g_amount? where b_mid=? and b_g_num=? and b_g_size=? "; // 복합누적(데이터)연산

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, dto.getB_g_amount());
				pstmt.setString(2, dto.getB_m_id());
				pstmt.setInt(3, dto.getB_g_num());
				pstmt.setString(4, dto.getB_g_size());

				// 4 pstmt 객체 실행
				check = pstmt.executeUpdate(); // 정상적인 실행이면 무조건 1 리턴

			}

			System.out.println("DAO: 장바구니 중복 체크 결과: " + check);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return check;

	}
	// checkGoods(dto)

	// basketAdd(dto)
	public void basketAdd(BasketDTO dto) {
		int b_num = 0;
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql작성 & pstmt객체
			sql = "select max(b_num) from p_basket";
			pstmt = con.prepareStatement(sql);

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터처리
			if (rs.next()) {
				b_num = rs.getInt(1) + 1; // 컬럼 하나만 가져와서 1
			}
			System.out.println("DAO : 장바구니 번호 (b_num):" + b_num);
			// 장바구니에 정보 저장(insert)
			// 3. sql 작성 &pstmt 객체 생성
			sql = "insert into p_basket values(?,?,?,?,?, now())";
			pstmt = con.prepareStatement(sql);

			// 장바구니 num 생성
			pstmt.setInt(1, b_num);
			pstmt.setString(2, dto.getB_m_id());
			pstmt.setInt(3, dto.getB_g_num());
			pstmt.setInt(4, dto.getB_g_amount());
			pstmt.setString(5, dto.getB_g_size());

			// 4. sql 실행
			pstmt.executeUpdate();

			System.out.println("DAO: 장바구니 저장완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}
	// basketAdd(dto)

	// id에 해당하는 장바구니 정보 장바구니에 담긴 상품정보
	// getBasketList(id)
	public Vector<List> getBasketList(String id) { // 가변길이배열
		// 장바구니 상품정보저장 리스트
		Vector totalList = new Vector();
		// 장바구니 정보
		List basketList = new ArrayList();
		// 상품정보
		List goodsList = new ArrayList();

		// 1.2 디비 연결
		try {
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			// id에 해당하는 장바구니 정보 가져오기
			sql = "select * from p_basket where b_m_id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5.데이터 처리
			while (rs.next()) { // 장바구니 정보가 있을 때 마다
				// 장바구니 정보(DTO) 모두 저장 (List)함

				BasketDTO dto = new BasketDTO();
				dto.setB_date(rs.getDate("b_date"));
				dto.setB_g_amount(rs.getInt("b_g_amount"));
				dto.setB_g_num(rs.getInt("b_g_num")); // basket에서의 상품번호
				dto.setB_g_size(rs.getString("b_g_size"));
				dto.setB_m_id(rs.getString("b_m_id"));
				dto.setB_num(rs.getInt("b_num"));

				// DTO -> LIST
				basketList.add(dto);

				// 장바구니(상품번호)에 저장된 상품 정보를 가져오기(이름, 가격, 이미지)

				// 3. slq 작성
				sql = "select * from p_goods where num=?";
				PreparedStatement pstmt2 = con.prepareStatement(sql);

				pstmt2.setInt(1, dto.getB_g_num()); // 장바구니에서 가져온 상품번호(basket)
				// 4. sql 실행
				ResultSet rs2 = pstmt2.executeQuery();

				// 5. 데이터 처리
				if (rs2.next()) {
					GoodsDTO gDTO = new GoodsDTO();

					gDTO.setName(rs2.getString("name"));
					gDTO.setPrice(rs2.getInt("price"));
					gDTO.setImage(rs2.getString("image"));

					// ... 필요에 따라서 추가 정보 저장

					goodsList.add(gDTO);

				} // if
				rs2.close();
				pstmt2.close();

				System.out.println("DAO : 상품 저장 완료");

			} // while
			System.out.println("DAO : 장바구니 저장 완료");

			// 장바구니 상품정보를 각각 저장
			totalList.add(basketList);
			totalList.add(goodsList);

			// System.out.println(totalList); <- test용

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return totalList;

	}

	// getBasketList(id)

	// basketDelete(b_num)
	public void basketDelete(int b_num) {
		// 1.2 디비연결
		try {
			con = getCon();
			// 3 sql 작성
			sql = "delete from p_basket where b_num=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, b_num);
			// 4 sql 실행
			pstmt.executeUpdate();
			System.out.println("DAO : 장바구니 삭제 완료");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// basketDelete(b_num)

	// basketDelete(id)
	public void basketDelete(String id) {
		// 1.2 디비연결
		try {
			con = getCon();
			// 3 sql 작성
			sql = "delete from p_basket where b_m_id=?";
			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setString(1, id);
			// 4 sql 실행
			pstmt.executeUpdate();

			System.out.println("DAO : " + id + "님 구매 후 장바구니 비우기");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
}