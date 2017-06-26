package kr.co.ican.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemberVO;

// 사원 추가, 삭제 , update 관련
public class WorkerDAO {

	
	public List<MemberVO> getWorkerList(Connection conn, PreparedStatement psmt, ResultSet rs, MemberVO mvo) throws SQLException{
		
        List<MemberVO> list = new ArrayList<MemberVO>();
        int cnt = 1;
       	String sql = "";
       
       	// 첫 시작 & 전체 검색 
       	sql = " SELECT IM_IDX, IM_NAME, IM_DNAME, IM_PHONE , IM_AUTH, IM_STATUS , YEAR#, MONTH# "
       		+ " FROM( "
       		+ "       SELECT "
       		+ "              ROW_NUMBER() OVER (ORDER BY IM_IDX) AS RNUM, "
       		+ "              IM_IDX, "
       		+ "              IM_NAME, "
       		+ "              IM_DNAME, "
       		+ "              IM_PHONE, "
       		+ "              IM_AUTH, "
       		+ "              IM_STATUS, "
       		+ "              TRUNC(DATETERM / 12) AS YEAR#, "
       		+ "              TRUNC(MONTHS_BETWEEN (SYSDATE, ADD_MONTHS (MINDATE, 12 * TRUNC (DATETERM / 12)))) AS MONTH#"
       		+ "  	  FROM "
       		+ "             ICAN_MEMBER IM LEFT JOIN ("
       		+ "                                       SELECT "
       		+ "                                               IME_IM_IDX,"
       		+ "                                               MIN(IME_REGI_DATE) AS MINDATE, "
       		+ "                                               MONTHS_BETWEEN (SYSDATE, MIN(IME_REGI_DATE)) AS DATETERM "
       		+ "                                       FROM ICAN_MEM_EXP group by IME_IM_IDX "
       		+ "                                       ) IME"
       		+ "              ON IM.IM_IDX = IME.IME_IM_IDX "
       		+ "       WHERE "
       		+ "               IM_RESIGN = 0 "
       		+ "       )"
       		+ " WHERE RNUM BETWEEN ? AND ? ";
       	
    	psmt = conn.prepareStatement(sql);
       	psmt.setInt(1, mvo.getStart());
       	psmt.setInt(2, mvo.getEnd());
 
       	rs = psmt.executeQuery();
  	   
	    while (rs.next()) {
	   		cnt = 1;
	       	MemberVO vo = new MemberVO();
	       	vo.setIm_idx(rs.getInt(cnt++));
	       	vo.setIm_name(rs.getString(cnt++));
	       	vo.setIm_dname(rs.getString(cnt++));
	       	vo.setIm_phone(rs.getString(cnt++));
	       	vo.setIm_auth(rs.getInt(cnt++));
	       	vo.setIm_status(rs.getInt(cnt++));
	       	vo.setExpYear(rs.getInt(cnt++));
	       	vo.setExpMonth(rs.getInt(cnt++));
	       	
			list.add(vo);
	     }
	    
        return list;
	}

	public int getWorkerCount(Connection conn, PreparedStatement psmt, ResultSet rs) throws SQLException {
		
	    int result= 0;
	    
	    String sql = "";
	    sql = " SELECT NVL(COUNT(*), 0) AS CNT FROM ICAN_MEMBER WHERE IM_RESIGN = 0 ";
		
	    psmt = conn.prepareStatement(sql);
		
		rs = psmt.executeQuery();
		
		while (rs.next()) {
	   		 
			result = rs.getInt(1);
		}
	    return result;
	}
	
	// 휴대폰 중복찾기
	public boolean chkDuplPhone(MemberVO mvo, Connection conn, PreparedStatement psmt, ResultSet rs) throws SQLException{
		
		int result = 0;
		String sql = "  SELECT * FROM ICAN_MEMBER WHERE IM_PHONE = ?  ";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, mvo.getIm_phone());
		
		rs = psmt.executeQuery();
		while (rs.next()) {
			result++;
		}
		
		return result > 0 ? true : false;
	}
	// 이메일 중복찾기
	public boolean chkDuplEmail(MemberVO mvo, Connection conn, PreparedStatement psmt, ResultSet rs) throws SQLException{
		
		int result = 0;
		String sql = "  SELECT * FROM ICAN_MEMBER WHERE IM_EMAIL = ?  ";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, mvo.getIm_email());
		
		rs = psmt.executeQuery();
		while (rs.next()) {
			result++;
		}
		
		return result > 0 ? true : false;
	}
	// 주민등록번호 중복찾기
	public boolean chkDuplScnum(MemberVO mvo, Connection conn, PreparedStatement psmt, ResultSet rs) throws SQLException{
		
		int result = 0;
		String sql = "  SELECT * FROM ICAN_MEMBER WHERE IM_SCNUM = ?  ";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, mvo.getIm_scnum());
		
		rs = psmt.executeQuery();
		while (rs.next()) {
			result++;
		}
		
		return result > 0 ? true : false;
	}
	// 사원 기본정보 insert
	public boolean addWorkerInfo(MemberVO mvo , Connection conn, PreparedStatement psmt, ResultSet rs)throws SQLException { // 사원 기본정보 넣기
        String sql = "";
        int cnt = 1;
        int result = 0;
			sql = " INSERT INTO ICAN_MEMBER(IM_IDX, IM_PW, IM_DNAME, IM_NAME, IM_PHONE, IM_EMAIL, IM_RESIGN, IM_STATUS, IM_SCNUM, IM_ADDRESS, IM_DETAILADDR, IM_POSTCODE, IM_AUTH, IM_SKILL) "
				 +" VALUES(MEMBER_SEQ.NEXTVAL, ? , ? , ? , ? , ? , 0 , 0 , ? , ? , ? , ? , ? , ?)";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(cnt++, mvo.getIm_pw()); // 패스워드
			psmt.setString(cnt++, mvo.getIm_dname()); // 부서이름
			psmt.setString(cnt++, mvo.getIm_name()); //이름
			psmt.setString(cnt++, mvo.getIm_phone()); //전화번호
			psmt.setString(cnt++, mvo.getIm_email()); //이메일
			psmt.setString(cnt++, mvo.getIm_scnum()); //주번
			psmt.setString(cnt++, mvo.getIm_address()); //집주소
			psmt.setString(cnt++, mvo.getIm_detailaddr());
			psmt.setString(cnt++, mvo.getIm_postcode());
			psmt.setInt(cnt++, mvo.getIm_auth()); // 직급
			psmt.setString(cnt++, mvo.getIm_skill()); //스킬
			
			result = psmt.executeUpdate();

		return result > 0 ? true : false;
	}
	// 기본 경력 insert
	public boolean basicWorkerExp(MemberVO mvo, Connection conn, PreparedStatement psmt, ResultSet rs) throws SQLException{
		String sql = "";
        int cnt = 1;
        int result = 0;
        
		if("타업체인력".equals(mvo.getIm_dname())){
			sql = " INSERT INTO ICAN_MEM_EXP(IME_IM_IDX, IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL) "
					+ " VALUES(MEMBER_SEQ.CURRVAL , SYSDATE, NULL, ? , ? , '미배정') ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, mvo.getOutsideperson());
			psmt.setInt(cnt++, mvo.getIm_auth());
		}else{
			sql = " INSERT INTO ICAN_MEM_EXP(IME_IM_IDX, IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL) "
					+ " VALUES(MEMBER_SEQ.CURRVAL , SYSDATE, NULL, '아이캔매니지먼트(주)', ? , '미배정') ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_auth());
		}
		
		result = psmt.executeUpdate();
			
		return result > 0 ? true : false;
	}
	// 경력 insert
	public boolean addWorkerExp(ExperienceVO evo, Connection conn, PreparedStatement psmt, ResultSet rs) throws SQLException{
		String sql = "";
        int cnt = 1;
        int result = 0;
    	System.out.println("DAO(경험치 삽입 ) = "+evo.toString());
		sql = " INSERT INTO ICAN_MEM_EXP(IME_IM_IDX, IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL) "
			+ " VALUES(MEMBER_SEQ.CURRVAL , ? , ? , ?, ? , ?) ";

		psmt = conn.prepareStatement(sql);
		psmt.setString(cnt++, evo.getIme_regi_date());
		psmt.setString(cnt++, evo.getIme_exit_date());
		psmt.setString(cnt++, evo.getIme_coname());
		psmt.setInt(cnt++, evo.getIme_auth());
		psmt.setString(cnt++, evo.getIme_roll());
		
		result = psmt.executeUpdate();
			
        
		return result > 0 ? true : false;
	}
	
	// 자격증 insert
	public boolean addWorkerLicense(MemLicenseVO mlvo, Connection conn, PreparedStatement psmt, ResultSet rs) throws SQLException{
		 String sql = "";
	        int cnt = 1;
	        int result = 0;
			
	        sql = " INSERT INTO ICAN_MEM_LICENSE ( IML_IM_IDX, IML_LNAME , IML_ACQDATE, IML_ORGANIZATION ) "
				+ " VALUES( MEMBER_SEQ.CURRVAL , ? , ? , ?) ";
			
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, mlvo.getIml_lname());
			psmt.setString(cnt++, mlvo.getIml_acqdate());
			psmt.setString(cnt++, mlvo.getIml_organization());
			
			result = psmt.executeUpdate();
				
			return result > 0 ? true : false;
	}
}
