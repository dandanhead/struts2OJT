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

	// 프로젝트 총 개수 
	public int getTotalProjectCount(Connection conn)throws Exception{
		
		PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        int result= 0;
        int cnt = 1;
        
        try {
			 sql = " SELECT NVL(COUNT(*), 0) AS CNT FROM ICAN_PROJECT_LIST WHERE IPL_DEL = 0 ";
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
	// 프로젝트 리스트 가져오기
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
					+ "					ICAN_PROJECT_LIST "
					+ "			WHERE"
					+ "					IPL_DEL = 0 )"
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
	// 프로젝트 추가
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
					+ "										IPL_POSTCODE, IPL_SKILL, IPL_DEL"
					+ "									) "
					+ " VALUES( PROJECT_LIST_SEQ.NEXTVAL, ? , ? , ? , NULL , ? , ? , ? , ? , ? , ? , ? , ? , 0 ) ";
				
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
	
	// 프로젝트 상세정보 가져오기
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
	
	// 프로젝트에 추가하기 위한 사원(프로젝트 참여중이 아닌) 리스트 가져오기
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
	// 프로젝트에 투입된 인원 철수 시키기 위한 사원목록 가져오기(프로젝트에 투입된 사원 목록 가져오기)
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
	// 프로젝트에 사원 투입 시키기
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
	// 프로젝트에 투입된 사원 제외하기, 철수 시키기
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
	// 사원 상태 변경 (프로젝트 진행 중...)
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
	// 사원 상태 변경(대기중...)
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
	// 프로젝트 상세페이지에서 프로젝트에 투입된 사원리스트 보여주기
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
	// 진행중 프로젝트 삭제시 해당 프로젝트에 투입된 인원 제외 하기 위한 프로젝트 참여인원 가져오기
	public List<MemberVO> projectAssignedWorker(Connection conn, int ipl_idx) throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<MemberVO> mlist = new ArrayList<MemberVO>();
		int cnt = 1;
		String sql = "";
		
		try {
			sql = " SELECT IPJL_IM_IDX FROM ICAN_PROJECT_JOIN_LIST WHERE IPJL_IPL_IDX = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, ipl_idx);
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				MemberVO vo  = new MemberVO();
				cnt = 1;
				vo.setIm_idx(rs.getInt(cnt++));
				
				mlist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(rs != null){
				rs.close();
			}
			
			if(psmt != null){
				psmt.close();
			}
		}
		
		return mlist;
		
	}
	// 프로젝트 삭제하기
	public boolean deleteProject(Connection conn, int ipl_idx) throws Exception{
		PreparedStatement psmt = null;
		int cnt = 1;
		int result = 0;
		
		try {
			String sql = " UPDATE ICAN_PROJECT_LIST SET IPL_DEL = 1 WHERE IPL_IDX = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, ipl_idx);
			
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
}
