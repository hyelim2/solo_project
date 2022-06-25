package hl_project.member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
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

	public void closeDB() {

		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

			System.out.println("DAO: 디비 연결 지원해제!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원가입insertMember(dto)
	public void insertMember(MemberDTO dto) {

		try {
			con = getCon();
			sql = "insert into p_member values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getAddress());
			pstmt.setTime(7, dto.getReg_date());

			// sql 실행
			pstmt.executeUpdate();
			// 데이터 처리

			System.out.println("DAO: 회원가입 완");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();

		}
	}

	// loginCheck(id, pw)
	public int loginCheck(String id, String pw) {
		int result = -1;
		// 디비 연결
		try {
			con = getCon();
			// sql 작성 (select) & pstmt 객체
			sql = "select * from p_member where id=? and pw=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			// sql 실행
			rs = pstmt.executeQuery();

			// 데이터 처리
			if (rs.next()) {
				result = 1;
				// 아이디에 해당하는 비밀번호가 있다. => 회원
				if (pw.equals(rs.getString("pw"))) {
					// 입력받은 비밀번호와 디지에 저장된 비밀번호가 동일하다
					// => 본인

					System.out.println("DAO 결과 회원");

				}

			} else {
				// 아이디에 해당하는 비밀번호가 없다. => 비회원
				System.out.println("DAO 결과 비회원");
				result = -1;
			}

			System.out.println("DAO :로그인 체크 완료!(" + result + ")");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();

		}
		return result;
	}

	// duplicateIdCheck(id)
	public boolean duplicateIdCheck(String id) {
		boolean x = false;

		try {
			// 쿼리
			con = getCon();
			sql = "select pw from p_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				/* System.out.println("로그인체크"); */
				x = true; // 해당 아이디 존재
			}
			System.out.println(x);
			return x;

		} catch (Exception sql) {
			throw new RuntimeException(sql.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}// duplicateIdCheck(id)

	// getMember(id)
	public MemberDTO getMember(String id) {
		MemberDTO dto = null;
		// 디비 연결
		try {
			con = getCon();
			// sql 작성 & pstmt 객체
			sql = "select * from p_member where id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			// sql 실행
			rs = pstmt.executeQuery();

			// 데이터처리
			if (rs.next()) {
				dto = new MemberDTO();

				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setReg_date(rs.getTime("reg_date"));
			}
			System.out.println("DAO : 횐정 저장 완");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return dto;

	}

	// getMember(id)

	// deleteMember(id, pw)
	public int deleteMember(String id, String pw) {
		int result = -1;
		try {
			//  DB연결
			con = getCon();
			// SQL & pstmt 생성
			sql = "select pw from p_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 7-3. 실행 -> rs저장
			rs = pstmt.executeQuery();
			// 데이터처리
			if (rs.next()) {
				if (pw.equals(rs.getString("pw"))) {
					// 비번일치하면 정보삭제 작업
					//  SQL 구문작성 & pstmt 생성
					sql = "delete from p_member where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					result = 1;
					System.out.println("회원삭제ok");
				} else {
					result = 0;
					System.out.println("회원삭제fail");
				}
			} else {
				result = -1;
				System.out.println("회원삭제fail :아이디불일치");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}// delete닫힘
	
	public int updateMember(MemberDTO dto){
		int result = -1;
		
		try {
			// 1 2 디비연결
			con = getCon();
			// 3 sql 작성(select) & pstmt 객체
			sql = "select pw from p_member where id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ??
			pstmt.setString(1, dto.getId());
			
			// 4 sql 실행
			rs = pstmt.executeQuery();
			
			// 5 데이터 처리
			if(rs.next()){// 수정할 정보가 있을때(회원)
				if(dto.getPw().equals(rs.getString("pw"))){
					// 본인 => 정보 수정
					
					// 3 sql 작성(update) & pstmt 객체 
					sql = "update p_member set name=?,gender=?,age=? where id=?";
					pstmt = con.prepareStatement(sql);
					
					// ????
					pstmt.setString(1, dto.getName());
					pstmt.setString(2, dto.getId());
					
					// 4 sql 실행
					result = pstmt.executeUpdate();
					//result = 1;					
				}else{
					// 본인 X, 정보수정 X  
					result = 0;
					System.out.println(" DAO : 비밀번호 오류, 정보수정 X");
				}
			}else{
				result = -1;
				System.out.println(" DAO : 회원정보가 없음, 정보수정 X");
			}			
			
			System.out.println(" DAO : 회원정보 수정완료! ("+result+")");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}		
		
		return result;
	}
	// updateMember닫힘
}