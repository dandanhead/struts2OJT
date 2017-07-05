package kr.co.ican.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kr.co.ican.project.vo.AssignMemberVO;
import kr.co.ican.project.vo.ProjectJoinMemListVO;
import kr.co.ican.project.vo.ProjectJoinMemberVO;
import kr.co.ican.project.vo.ProjectVO;
import kr.co.ican.worker.vo.MemberVO;

public class ProjectDAO {

	
	public int getTotalProjectCount(Connection conn)throws Exception{
		
		PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        int result= 0;
        int cnt = 1;
        
        try {
			 sql = " SELECT NVL(COUNT(*), 0) AS CNT FROM ICAN_PROJECT_LIST ";
			 psmt = conn.prepareStatement(sql);
			 rs = psmt.executeQuery();
			 
				while (rs.next()) {
			   		 
					result = rs.getInt(cnt++);
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}

        return result;
		
	}
	
	public List<ProjectVO> getProjectList(ProjectVO pvo , Connection conn) throws Exception{
			
			PreparedStatement psmt = null;
	        ResultSet rs = null;
	        List<ProjectVO> plist = new ArrayList<ProjectVO>();
	        int cnt = 1;
	        String sql = "";
	        
	        try {
				sql = " SELECT "
					+ "         IPL_IDX, "
					+ "			IPL_PNAME, "
					+ "			IPL_SDATE, "
					+ "			IPL_EPTDATE, "
					+ "			IPL_EDATE, "
					+ "			IPL_CONTENT, "
					+ "			IPL_DOC, "
					+ "			IPL_CHARGE, "
					+ "			IPL_CLIENT, "
					+ "			IPL_ADDRESS, "
					+ "			IPL_DETAILADDR, "
					+ "			IPL_POSTCODE, "
					+ "			IPL_SKILL "
					+ " FROM ( "
					+ "			SELECT   "
					+ "					ROW_NUMBER() OVER (ORDER BY IPL_IDX DESC) AS RNUM,   "
					+ "					IPL_IDX, IPL_PNAME ,"
					+ "					IPL_SDATE, IPL_EPTDATE, "
					+ "					IPL_EDATE, IPL_CONTENT, "
					+ "					IPL_DOC, IPL_CHARGE, "
					+ "					IPL_CLIENT, "
					+ "					IPL_ADDRESS, "
					+ "					IPL_DETAILADDR, "
					+ "					IPL_POSTCODE, "
					+ "					IPL_SKILL  "
					+ "			FROM  "
					+ "					ICAN_PROJECT_LIST )"
					+ " WHERE  RNUM BETWEEN ? AND ?  ";
				
				psmt =conn.prepareStatement(sql);
				psmt.setInt(cnt++, pvo.getStart());
				psmt.setInt(cnt++, pvo.getEnd());
				rs = psmt.executeQuery();
				
				while (rs.next()) {
					cnt = 1;
					ProjectVO vo = new ProjectVO();
					
					vo.setIpl_idx(rs.getInt(cnt++));
					vo.setIpl_pname(rs.getString(cnt++));
					vo.setIpl_sdate(rs.getString(cnt++));
					vo.setIpl_eptdate(rs.getString(cnt++));
					vo.setIpl_edate(rs.getString(cnt++));
					vo.setIpl_content(rs.getString(cnt++));
					vo.setIpl_doc(rs.getString(cnt++));
					vo.setIpl_charge(rs.getString(cnt++));
					vo.setIpl_client(rs.getString(cnt++));
					vo.setIpl_address(rs.getString(cnt++));
					vo.setIpl_detailaddr(rs.getString(cnt++));
					vo.setIpl_postcode(rs.getString(cnt++));
					vo.setIpl_skill(rs.getString(cnt++));
					
					plist.add(vo);
					
				}
					
			} catch (Exception e) {
				e.getStackTrace();
				throw e;
				
			}finally {
				if(psmt != null){
					psmt.close();
				}
				if(rs != null){
					rs.close();
				}
			}
	        
	        return plist;
			
		}
	
	public boolean addProject(ProjectVO pvo , Connection conn)throws Exception{
			
			PreparedStatement psmt = null;
	        String sql = "";
	        int cnt = 1;
	        int result = 0;
	        
	        try {
				sql = " INSERT INTO "
					+ "				ICAN_PROJECT_LIST"
					+ "  								( "
					+ "										IPL_IDX, IPL_PNAME, IPL_SDATE, IPL_EPTDATE, IPL_EDATE, IPL_CONTENT, "
					+ "    									IPL_DOC, IPL_CHARGE, IPL_CLIENT, IPL_ADDRESS, IPL_DETAILADDR , "
					+ "										IPL_POSTCODE, IPL_SKILL"
					+ "									) "
					+ " VALUES( PROJECT_LIST_SEQ.NEXTVAL, ? , ? , ? , NULL , ? , ? , ? , ? , ? , ? , ? , ? ) ";
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(cnt++, pvo.getIpl_pname());
				psmt.setString(cnt++, pvo.getIpl_sdate());
				psmt.setString(cnt++, pvo.getIpl_eptdate());
				psmt.setString(cnt++, pvo.getIpl_content());
				psmt.setString(cnt++, pvo.getIpl_doc());
				psmt.setString(cnt++, pvo.getIpl_charge());
				psmt.setString(cnt++, pvo.getIpl_client());
				psmt.setString(cnt++, pvo.getIpl_address());
				psmt.setString(cnt++, pvo.getIpl_detailaddr());
				psmt.setString(cnt++, pvo.getIpl_postcode());
				psmt.setString(cnt++, pvo.getIpl_skill());
				
				result = psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
				
			}finally {
				if(psmt != null){
					psmt.close();
				}
			}
	        
	        return result > 0 ? true : false;
		}
	
	public ProjectVO getProjectDetail(ProjectVO pvo, Connection conn) throws Exception{

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "";
		int cnt = 1;
		ProjectVO vo = new ProjectVO();
		
		try {
			
			sql = " SELECT "
				+ "			IPL_IDX, IPL_PNAME, IPL_SDATE, IPL_EPTDATE, IPL_EDATE, IPL_CONTENT, "
				+ "			IPL_DOC, IPL_CHARGE, IPL_CLIENT, IPL_ADDRESS, IPL_DETAILADDR, "
				+ "			IPL_POSTCODE, IPL_SKILL"
				+ " FROM "
				+ "			ICAN_PROJECT_LIST "
				+ " WHERE "
				+ "			IPL_IDX = ? ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, pvo.getIpl_idx());

			rs = psmt.executeQuery();

			while (rs.next()) {
				cnt = 1;
				vo.setIpl_idx(rs.getInt(cnt++));
				vo.setIpl_pname(rs.getString(cnt++));
				vo.setIpl_sdate(rs.getString(cnt++));
				vo.setIpl_eptdate(rs.getString(cnt++));
				vo.setIpl_edate(rs.getString(cnt++));
				vo.setIpl_content(rs.getString(cnt++));
				vo.setIpl_doc(rs.getString(cnt++));
				vo.setIpl_charge(rs.getString(cnt++));
				vo.setIpl_client(rs.getString(cnt++));
				vo.setIpl_address(rs.getString(cnt++));
				vo.setIpl_detailaddr(rs.getString(cnt++));
				vo.setIpl_postcode(rs.getString(cnt++));
				vo.setIpl_skill(rs.getString(cnt++));
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
		
		return vo;
	}
	
	
	public List<AssignMemberVO> getAssignMemList(Connection conn, AssignMemberVO asvo) throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;
        List<AssignMemberVO> aslist = new ArrayList<AssignMemberVO>();
        int cnt = 1;
       	String sql = "";
       	try {
       	// 첫 시작 & 전체 검색 
           	sql = " SELECT "
           			+ "			 IM_IDX, IM_NAME, IM_SKILL , IM_STATUS, YEAR#, MONTH# "
           		+ " FROM( "
           		+ "       SELECT "
           		+ "              ROW_NUMBER() OVER (ORDER BY IM_IDX) AS RNUM,"
           		+ "				 IM_IDX, "
           		+ "              IM_NAME, "
           		+ "              IM_STATUS,"
           		+ "				 IM_SKILL, "
           		+ "              TRUNC(DATETERM / 12) AS YEAR#, "
           		+ "              TRUNC(MONTHS_BETWEEN (SYSDATE, ADD_MONTHS (MINDATE, 12 * TRUNC (DATETERM / 12)))) AS MONTH#"
           		+ "  	  FROM "
           		+ "             ICAN_MEMBER IM LEFT JOIN ("
           		+ "                                       SELECT "
           		+ "                                               IME_IM_IDX,"
           		+ "                                               MIN(IME_REGI_DATE) AS MINDATE, "
           		+ "                                               MONTHS_BETWEEN (SYSDATE, MIN(IME_REGI_DATE)) AS DATETERM "
           		+ "                                       FROM "
           		+ "												  ICAN_MEM_EXP "
           		+ "										  GROUP BY "
           		+ "												  IME_IM_IDX "
           		+ "                                       ) IME"
           		+ "              ON IM.IM_IDX = IME.IME_IM_IDX "
           		+ "       WHERE "
           		+ "               IM_RESIGN = 0 AND IM_STATUS = 0 "
           		+ "       )"
           		+ " WHERE RNUM BETWEEN ? AND ? ";
           	
        	psmt = conn.prepareStatement(sql);
           	psmt.setInt(cnt++, asvo.getStart());
           	psmt.setInt(cnt++, asvo.getEnd());
     
           	rs = psmt.executeQuery();
      	   
    	    while (rs.next()) {
    	   		cnt = 1;
    	   		AssignMemberVO vo = new AssignMemberVO();
    	       	vo.setIm_idx(rs.getInt(cnt++));
    	       	vo.setIm_name(rs.getString(cnt++));
    	       	vo.setIm_skill(rs.getString(cnt++));
    	       	vo.setIm_status(rs.getInt(cnt++));
    	       	vo.setExpYear(rs.getInt(cnt++));
    	       	vo.setExpMonth(rs.getInt(cnt++));
    	       	
    	   		aslist.add(vo);
    	     }
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
        return aslist;
	}
	
	public List<AssignMemberVO> getRemoveMemList(Connection conn, ProjectVO pvo) throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;
        List<AssignMemberVO> aslist = new ArrayList<AssignMemberVO>();
        int cnt = 1;
       	String sql = "";
       	try {
       	// 첫 시작 & 전체 검색 
       		sql = " SELECT "    
					   + " 		IM_IDX, IM_NAME, IM_SKILL , IM_STATUS,  YEAR#, MONTH# "
					   + " FROM "
					   + "      ( "
					   + "         SELECT "
					   + "					 ROW_NUMBER() OVER( ORDER BY IM_IDX ) AS RNUM,  IM_IDX, IM_NAME, IM_SKILL, "
					   + "					 IM_STATUS, TRUNC(DATETERM / 12) AS YEAR#,  "
					   + "					 TRUNC(MONTHS_BETWEEN( SYSDATE, ADD_MONTHS( MINDATE,12 * TRUNC(DATETERM / 12)))) AS MONTH#"
					   + "         FROM "
					   + "            		 ICAN_MEMBER IM "
					   + "			         LEFT JOIN ( "
					   + "				                SELECT "
					   + "										   IME_IM_IDX,  MIN(IME_REGI_DATE) AS MINDATE, "
					   + "										   MONTHS_BETWEEN( SYSDATE,  MIN(IME_REGI_DATE)) AS DATETERM "
					   + "				                FROM "
					   + "						                   ICAN_MEM_EXP "
					   + "				                GROUP BY "
					   + "										   IME_IM_IDX "
					   + "								) IME "
					   + "		             ON "
					   + "								IM.IM_IDX = IME.IME_IM_IDX "
					   + "		             LEFT JOIN( "
					   + "				                SELECT "
					   + "											IPJL_IPL_IDX , IPJL_IM_IDX "
					   + "				                FROM "
					   + "											ICAN_PROJECT_JOIN_LIST "
					   + "			           		  )  IPJL "
					   + "		            ON IM.IM_IDX = IPJL.IPJL_IM_IDX "
					   + "         WHERE "
					   + "		            IM.IM_RESIGN = 0 AND IM.IM_STATUS = 1 AND IPJL.IPJL_IPL_IDX = ? "
					   + "    ) "
					   + " WHERE "
					   + "    RNUM BETWEEN ? AND ? ";
           	
        	psmt = conn.prepareStatement(sql);
           	psmt.setInt(cnt++, pvo.getIpl_idx());
        	psmt.setInt(cnt++, pvo.getStart());
           	psmt.setInt(cnt++, pvo.getEnd());
     
           	rs = psmt.executeQuery();
      	   
    	    while (rs.next()) {
    	   		cnt = 1;
    	   		AssignMemberVO vo = new AssignMemberVO();
    	       	vo.setIm_idx(rs.getInt(cnt++));
    	       	vo.setIm_name(rs.getString(cnt++));
    	       	vo.setIm_skill(rs.getString(cnt++));
    	       	vo.setIm_status(rs.getInt(cnt++));
    	       	vo.setExpYear(rs.getInt(cnt++));
    	       	vo.setExpMonth(rs.getInt(cnt++));
    	       	
    	   		aslist.add(vo);
    	     }
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
        return aslist;
	}
	
	public boolean addAssignMember(Connection conn, ProjectJoinMemberVO pjvo)throws Exception{
		PreparedStatement psmt = null;
		int result = 0;
		int cnt = 1;
       	String sql = "";

       	try {
       		sql = " INSERT INTO "
       	       		+ "				ICAN_PROJECT_JOIN_LIST( "
       	       		+ "										IPJL_IM_IDX , IPJL_IPL_IDX , IPJL_ROLL "
       	       		+ "									  ) "
       	       		+ " VALUES( "
       	       		+ "				?, ?, ? "
       	       		+ "       ) ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, pjvo.getIpjl_im_idx());
			psmt.setInt(cnt++, pjvo.getIpjl_ipl_idx());
			psmt.setInt(cnt++, pjvo.getIpjl_roll());

			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
       	
       	return result > 0 ? true : false;
       	
	}
	
	public boolean deleteAssignMember(Connection conn, ProjectJoinMemberVO pjvo)throws Exception{
		PreparedStatement psmt = null;
		int result = 0;
		int cnt = 1;
       	String sql = "";

       	try {
       		sql = " DELETE FROM "
       			+ "				ICAN_PROJECT_JOIN_LIST "
       			+ " WHERE "
       			+ "				IPJL_IPL_IDX = ? "
       			+ " AND "
       			+ "				IPJL_IM_IDX = ? ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, pjvo.getIpjl_ipl_idx());
			psmt.setInt(cnt++, pjvo.getIpjl_im_idx());

			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
       	
       	return result > 0 ? true : false;
       	
	}
	
	public boolean memberStatusChange(Connection conn, MemberVO mvo) throws Exception{
		PreparedStatement psmt = null;
		int result = 0;
		String sql = "";
		int cnt = 1;
		try {
			sql = " UPDATE "
				+ "		ICAN_MEMBER "
				+ " SET "
				+ "		IM_STATUS = 1 "
				+ " WHERE "
				+ "		IM_IDX = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_idx());
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
		
		return result > 0 ? true : false;
	}
	
	public boolean memberStatusDefault(Connection conn, MemberVO mvo) throws Exception{
		PreparedStatement psmt = null;
		int result = 0;
		String sql = "";
		int cnt = 1;
		try {
			sql = " UPDATE "
				+ "		ICAN_MEMBER "
				+ " SET "
				+ "		IM_STATUS = 0 "
				+ " WHERE "
				+ "		IM_IDX = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_idx());
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
		
		return result > 0 ? true : false;
	}	
	public List<ProjectJoinMemListVO> getProjectJoinMembers(Connection conn, ProjectVO pvo) throws Exception{
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int cnt = 1;
		String sql  = "";
		List<ProjectJoinMemListVO> pjlist = new ArrayList<ProjectJoinMemListVO>();
		try {
			sql = " SELECT "
					   + " 		IM_IDX, IM_DNAME, IM_NAME, IM_PHONE , IM_STATUS,  YEAR#, MONTH#, IM_EMAIL "
					   + " FROM "
					   + "      ( "
					   + "         SELECT "
					   + "					 ROW_NUMBER() OVER( ORDER BY IM_IDX ) AS RNUM,  IM_IDX, IM_DNAME, IM_NAME, IM_PHONE , "
					   + "					 IM_STATUS, IM_EMAIL, TRUNC(DATETERM / 12) AS YEAR#,  "
					   + "					 TRUNC(MONTHS_BETWEEN( SYSDATE, ADD_MONTHS( MINDATE,12 * TRUNC(DATETERM / 12)))) AS MONTH#"
					   + "         FROM "
					   + "            		 ICAN_MEMBER IM "
					   + "			         LEFT JOIN ( "
					   + "				                SELECT "
					   + "										   IME_IM_IDX,  MIN(IME_REGI_DATE) AS MINDATE, "
					   + "										   MONTHS_BETWEEN( SYSDATE,  MIN(IME_REGI_DATE)) AS DATETERM "
					   + "				                FROM "
					   + "						                   ICAN_MEM_EXP "
					   + "				                GROUP BY "
					   + "										   IME_IM_IDX "
					   + "								) IME "
					   + "		             ON "
					   + "								IM.IM_IDX = IME.IME_IM_IDX "
					   + "		             LEFT JOIN( "
					   + "				                SELECT "
					   + "											IPJL_IPL_IDX , IPJL_IM_IDX "
					   + "				                FROM "
					   + "											ICAN_PROJECT_JOIN_LIST "
					   + "			           		  )  IPJL "
					   + "		            ON IM.IM_IDX = IPJL.IPJL_IM_IDX "
					   + "         WHERE "
					   + "		            IM.IM_RESIGN = 0 AND IM.IM_STATUS = 1 AND IPJL.IPJL_IPL_IDX = ? "
					   + "    ) "
					   + " WHERE "
					   + "    RNUM BETWEEN ? AND ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, pvo.getIpl_idx());
			psmt.setInt(cnt++, pvo.getStart());
			psmt.setInt(cnt++, pvo.getEnd());
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				ProjectJoinMemListVO vo = new ProjectJoinMemListVO();
				cnt = 1;
				vo.setIm_idx(rs.getInt(cnt++));
				vo.setIm_dname(rs.getString(cnt++));
				vo.setIm_name(rs.getString(cnt++));
				vo.setIm_phone(rs.getString(cnt++));
				vo.setIm_status(rs.getInt(cnt++));
				vo.setExpYear(rs.getInt(cnt++));
				vo.setExpMonth(rs.getInt(cnt++));
				vo.setIm_email(rs.getString(cnt++));
				
				pjlist.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
		
		return pjlist;
	}
	
	public String getProjectName(Connection conn, int idx) throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "";
		String result = "";
		int cnt = 1;
		try {
			sql = " SELECT "
					+ "			IPL_PNAME "
					+ " FROM "
					+ "			ICAN_PROJECT_LIST IPL "
					+ " LEFT JOIN  "
					+ "			ICAN_PROJECT_JOIN_LIST IPJL "
					+ "	ON "
					+ "		IPL.IPL_IDX = IPJL.IPJL_IPL_IDX "
					+ "	WHERE "
					+ "		IPJL.IPJL_IM_IDX = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, idx);
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				cnt = 1;
				result = rs.getString(cnt++);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
}
