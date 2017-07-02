package kr.co.ican.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import kr.co.ican.worker.vo.MemberVO;


// Member DAO
public class MemberDAO {
	
	//Login check
	public MemberVO loginCheck(Connection conn, MemberVO mvo)throws SQLException{
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "";
		int cnt = 1;
		int result = 0;
		sql = " SELECT "
			+ " 		IM_IDX, IM_PW, IM_DNAME, IM_NAME, IM_PHONE, IM_EMAIL, IME_REGI_DATE "
     		+ " FROM "
     		+ " 		ICAN_MEMBER IM LEFT JOIN ICAN_MEM_EXP IME "
     		+ " ON "
     		+ "			IM.IM_IDX = IME.IME_IM_IDX "
     		+ " WHERE "
     		+ "			IME.IME_EXIT_DATE IS NULL AND IM.IM_IDX = ? AND IM.IM_PW = ? ";
		
		
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_idx());
			psmt.setString(cnt++, mvo.getIm_pw());
			
			rs = psmt.executeQuery();
			MemberVO vo = new MemberVO();
			while (rs.next()) {
				
				cnt = 1;
				vo.setIm_idx(rs.getInt(cnt++));
				vo.setIm_pw(rs.getString(cnt++));
				vo.setIm_dname(rs.getString(cnt++));
				vo.setIm_name(rs.getString(cnt++));
				vo.setIm_phone(rs.getString(cnt++));
				vo.setIm_email(rs.getString(cnt++));
				vo.setIme_regi_date(rs.getString(cnt++));
				result++;
			}
		//close
		if(rs != null){
			rs.close();
		}
		
		if(psmt != null){
			psmt.close();
		}
		//return	
		if(result == 0){
			
			return null;
		}else{
			
			return vo;
		}
	}
	
	// Find ID 
	public MemberVO findId(Connection conn, MemberVO mvo) throws SQLException{
		PreparedStatement psmt = null;
		ResultSet rs = null; 
		String sql = "";
		int cnt = 1;
		int result = 0;
		
	     sql = " SELECT "
	     		+ "		IM_IDX "
      	     + " FROM "
      	     + "		ICAN_MEMBER WHERE IM_NAME = ? AND IM_SCNUM = ? ";
	      
        psmt = conn.prepareStatement(sql);
        psmt.setString(cnt++, mvo.getIm_name());
        psmt.setString(cnt++, mvo.getIm_scnum());
        rs =psmt.executeQuery();
	      
        MemberVO vo = new MemberVO();
        while (rs.next()) {
        	cnt = 1;
        	vo.setIm_idx(rs.getInt(cnt++));
     	    result++;
        }
        
		//close
        if(psmt != null){
        	psmt.close();
        }
        if(rs != null){
        	rs.close(); 
        }
		       
        //return
        if(result == 0){
        	return null;
        }else{
        	return vo;
        }
	}
	
	// Find PW
	public MemberVO findPw(Connection conn, MemberVO mvo) throws SQLException{
		 PreparedStatement psmt = null;
		 ResultSet rs = null;
		int cnt = 1;
		int result = 0;
		String sql = " SELECT "
				+ "				IM_PW  "
				+ "	     FROM  "
				+ "				ICAN_MEMBER "
				+ "		WHERE "
				+ "				IM_NAME = ?"
				+ "		    AND IM_SCNUM = ? "
				+ "         AND IM_EMAIL = ? ";

		psmt = conn.prepareStatement(sql);
		psmt.setString(cnt++, mvo.getIm_name());
		psmt.setString(cnt++, mvo.getIm_scnum());
		psmt.setString(cnt++, mvo.getIm_email());
		rs = psmt.executeQuery();
		
		while (rs.next()) {
			cnt = 1;
			mvo.setIm_pw(rs.getString(cnt++));
			result++;
		}
		//close
		if(psmt != null){
			psmt.close();
		}
		if(rs != null){
			rs.close(); 
		}
		//return
		if (result == 0) {
			return null;
		} else {
			return mvo;
		}
	}
	
}
