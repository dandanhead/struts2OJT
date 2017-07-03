package kr.co.ican.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kr.co.ican.project.vo.ProjectVO;

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
		}
		
		return vo;
	}
	
}
