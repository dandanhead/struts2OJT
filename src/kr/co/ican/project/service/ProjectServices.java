package kr.co.ican.project.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import kr.co.ican.project.dao.ProjectDAO;
import kr.co.ican.project.vo.AddAssignMemberVO;
import kr.co.ican.project.vo.AssignMemberVO;
import kr.co.ican.project.vo.ProjectVO;
import kr.co.ican.util.GetDBConn;
import kr.co.ican.util.Helps;

public class ProjectServices {

	
	public List<ProjectVO> getProjectList(ProjectVO pvo)throws Exception{
		Connection conn = null;
		ProjectDAO pdao = new ProjectDAO();
		List<ProjectVO> plist = new ArrayList<ProjectVO>();
		Helps help = new Helps();
		pvo = help.setProjectListPaging(pvo);
		
		try {
			conn = GetDBConn.getConnection();
			plist = pdao.getProjectList(pvo, conn);
		} catch (Exception e) {
			plist = null;
			e.printStackTrace();
			throw e;
		}finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return plist;
	}
	
	public int getProjectCount()throws Exception{
		
		Connection conn = null;
		ProjectDAO pdao = new ProjectDAO();
		int result = 0;
		try {
			conn  = GetDBConn.getConnection();
			result = pdao.getTotalProjectCount(conn);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean addProject(ProjectVO pvo) throws Exception{
		Connection conn = null;
		ProjectDAO pdao = new ProjectDAO();
		boolean chkResult = false;
		
		try {
			conn = GetDBConn.getConnection();
			conn.setAutoCommit(false);
			
			chkResult = pdao.addProject(pvo, conn);
			
			if(chkResult == false){
				conn.rollback();
				return chkResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		}finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return chkResult;
	}
	
	public ProjectVO getProjectDetail(ProjectVO pvo)throws Exception{
		Connection conn = null;
		ProjectDAO pdao = new ProjectDAO();
		ProjectVO vo = new ProjectVO();
		try {
			
			conn = GetDBConn.getConnection();
			vo = pdao.getProjectDetail(pvo, conn);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(conn != null){
				try {
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return vo;
	}
	
	public List<AssignMemberVO> getAssignMemList(AssignMemberVO asvo)throws Exception{
		Connection conn = null;
		ProjectDAO pdao = new ProjectDAO();
		List<AssignMemberVO> aslist = new ArrayList<AssignMemberVO>();
		
		try {
			conn = GetDBConn.getConnection();
			aslist = pdao.getAssignMemList(conn, asvo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(conn != null){
				try {
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return aslist;
	}
	
	public boolean addAssignMember(AddAssignMemberVO aavo) throws Exception{
		Connection conn = null;
		ProjectDAO pdao = new ProjectDAO();
		boolean result = false;
		
		try {
			
			pdao.
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(conn != null){
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		
		return result;
		
	}
}
