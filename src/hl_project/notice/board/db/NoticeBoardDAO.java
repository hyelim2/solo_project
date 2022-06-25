
package hl_project.notice.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import hl_project.board.db.BoardDTO;


public class NoticeBoardDAO {

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
	public void insertBoard(NoticeBoardDTO bb) {
		int num = 0; // 글번호 저장변수

		try {
			// 1.2. 디비연결
			con = getCon();

			// 3 sql 작성(글번호계산) & pstmt 객체
			sql = "select max(num) from n_board";
			pstmt = con.prepareStatement(sql);

			// 4 sql 실행
			rs = pstmt.executeQuery();

			// 5 데이터 처리
			if (rs.next()) { // max(num) 컬럼의 결과는 항상 존재함(커서 있음)
				num = rs.getInt(1)+  1;// 인덱스
				// num = rs.getInt("max(num)")1;//컬럼명
			}

			System.out.println(" DAO : 글번호 " + num);

			/////////////////////////////////////////////////////////////
			// 글쓰기

			// 이미 연결됨 (1.2. 단계 생략)
			// 3. sql(insert) 작성 & pstmt 객체
			sql = "insert into n_board(num,name,pw,title,content," + "readcount,date) "
					+ "values(?,?,?,?,?,?,now())";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num); // 직접 계산한 글번호
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPw());
			pstmt.setString(4, bb.getTitle());
			pstmt.setString(5, bb.getContent());

			pstmt.setInt(6, 0); // readcount 모든글의 조회수 0

			// 4. sql 실행
			pstmt.executeUpdate();

			System.out.println(" DAO : 글쓰기 완료! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// insertBoard(bb)

	// getBoardCount()
	public int getBoardCount() {
		int result = 0;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(num) from n_board";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if (rs.next()) {
				// result = rs.getInt("count(num)");
				result = rs.getInt(1);
			}

			System.out.println(" DAO : 게시판 글개수 " + result + "개");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}
	// getBoardCount()

	// getBoardList()
	public ArrayList getBoardList() {
		ArrayList boardList = new ArrayList();

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체 (num 내림차순 정렬)
			sql = "select * from n_board";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			while (rs.next()) { // 데이터 있을때 DB정보를 모두 저장
				// 글 1개의 정보 => NoticeBoardDTO 객체
				NoticeBoardDTO bb = new NoticeBoardDTO();

				bb.setNum(rs.getInt("num"));
				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setName(rs.getString("name"));
				bb.setPw(rs.getString("pw"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setTitle(rs.getString("title"));

				// BoardBean 객체의 정보 => ArrayList 한칸에 저장

				boardList.add(bb);
			} // while

			System.out.println(" DAO : 게시판 글 전체 목록 저장완료! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return boardList;
	}
	// getBoardList()

	// getBoardList(startRow,pageSize)
	public ArrayList getBoardList(int startRow, int pageSize) {
		ArrayList boardList = new ArrayList();

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체 (num 내림차순 정렬)
			// => re_ref 내림차순, re_seq 오름차순 / limit 시작행-1,개수
			sql = "select * from n_board order by num desc limit ?,?";
			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setInt(1, startRow - 1); // 시작행 - 1
			pstmt.setInt(2, pageSize); // 개수

			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			while (rs.next()) { // 데이터 있을때 DB정보를 모두 저장
				// 글 1개의 정보 => NoticeBoardDTO 객체
				NoticeBoardDTO bb = new NoticeBoardDTO();

				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setName(rs.getString("name"));
				bb.setNum(rs.getInt("num"));
				bb.setPw(rs.getString("pw"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setTitle(rs.getString("title"));

				// BoardBean 객체의 정보 => ArrayList 한칸에 저장

				boardList.add(bb);
			} // while

			System.out.println(" DAO : 게시판 글 전체 목록 저장완료! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return boardList;
	}
	// getBoardList(startRow,pageSize)

	// updateReadCount(num)
	public void updateReadCount(int num) {
		try {
			con = getCon();
			sql = "update n_board set readcount = readcount  1 where num=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);

			pstmt.executeUpdate();
			System.out.println("DAO : " + num  +"번글 조회수 1 증가!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}

	// updateReadCount(num)
	// getBoard(num)
	public NoticeBoardDTO getBoard(int num) {
		NoticeBoardDTO bb = null;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select * from n_board where num =?";
			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setInt(1, num);

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				bb = new NoticeBoardDTO();

				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setName(rs.getString("name"));
				bb.setNum(rs.getInt("num"));
				bb.setPw(rs.getString("pw"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setTitle(rs.getString("title"));
			}
			System.out.println(" DAO : 게시판 글 1개 저장완료 ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return bb;
	}
	// getBoard(num)

	// updateBoard(ubb)
	public int updateBoard(NoticeBoardDTO ubb) {
		int result = -1;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			// 글이 존재하는지 체크 (글을 쓴사람일때만 수정)
			sql = "select pw from n_board where num=?";
			pstmt = con.prepareStatement(sql);

			// ????
			pstmt.setInt(1, ubb.getNum());

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터처리
			if (rs.next()) { // 비밀번호가 존재한다 => 게시판글이 있음 => 수정
				if (ubb.getPw().equals(rs.getString("pw"))) {
					// 데이터 수정
					// 3. sql 생성(update) & pstmt 객체
					sql = "update n_board set name=?,title=?,content=? where num=?";
					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, ubb.getName());
					pstmt.setString(2, ubb.getTitle());
					pstmt.setString(3, ubb.getContent());
					pstmt.setInt(4, ubb.getNum());

					// 4. sql 실행
					pstmt.executeUpdate();

					result = 1;

				} else {
					result = 0;
				}
			} else {
				result = -1;
			}

			System.out.println(" DAO : 정보 수정완료 ("+  result + ")");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}

	// updateBoard(ubb)
	// dao.deleteBoard
	public int deleteBoard(int num, String pw) {
		int result = -1;

		// 1.2디비연결
		try {
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select pw from n_board where num=?";
			pstmt = con.prepareStatement(sql);
			// ????
			pstmt.setInt(1, num);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터처리
			if (rs.next()) {
				if (pw.equals(rs.getString("pw"))) {
					// sql 작성(delete) &pstmt 객체
					sql = "delete from n_board where num=?";
					pstmt = con.prepareStatement(sql);
					// ??
					pstmt.setInt(1, num);
					// sql 실행
					result = pstmt.executeUpdate();
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
			System.out.println("DAO: 게시판 글 삭제 완");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;

	}
	public int getBoardCount(String search) {
		int searchCnt = 0; // 검색어 갯수

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(*) from n_board where title like ?";
			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setString(1, "%" + search + "%"); // ? => '%검색어%'

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				searchCnt = rs.getInt(1); // 인덱스
				// searchCnt = rs.getInt("count(*)"); //컬럼명
			}
			System.out.println(" DAO : 검색된 글 개수 " + searchCnt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return searchCnt;
	}
	// getBoardCount(search) - 오버로딩
	// getBoardList(startRow, pageSize, search) 오버로딩

	public List<NoticeBoardDTO> getBoardList(int startRow, int pageSize, String search) {
		List<NoticeBoardDTO> searchBoardList = new ArrayList<NoticeBoardDTO>();

		try {
			con = getCon();
			// 정렬: re_ref (내림차순), re_seq(오름차순)
			// limit 원하는 만큼만 가져오기
			// 검색어 가져오기
			sql = "select * from n_board where title like? order by num limit ?,?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "%"  +search + "%");
			pstmt.setInt(2, startRow - 1);
			pstmt.setInt(3, pageSize);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeBoardDTO bb = new NoticeBoardDTO();

				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setName(rs.getString("name"));
				bb.setNum(rs.getInt("num"));
				bb.setPw(rs.getString("pw"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setTitle(rs.getString("title"));

				searchBoardList.add(bb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return searchBoardList;

	}

}