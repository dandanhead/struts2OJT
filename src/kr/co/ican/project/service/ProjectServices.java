package kr.co.ican.project.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import kr.co.ican.project.dao.ProjectDAO;
import kr.co.ican.project.vo.AddAssignMemberVO;
import kr.co.ican.project.vo.AssignMemberVO;
import kr.co.ican.project.vo.ProjectJoinMemListVO;
import kr.co.ican.project.vo.ProjectJoinMemberVO;
import kr.co.ican.project.vo.ProjectVO;
import kr.co.ican.util.GetDBConn;
import kr.co.ican.util.Helps;
import kr.co.ican.worker.vo.MemberVO;

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
	
	public List<ProjectJoinMemListVO> getProjectJoinMembers(ProjectVO pvo)throws Exception{
		Connection conn = null;
		ProjectDAO pdao = new ProjectDAO();
		List<ProjectJoinMemListVO> pjlist = new ArrayList<ProjectJoinMemListVO>();
		
		try {
			conn = GetDBConn.getConnection();
			pjlist = pdao.getProjectJoinMembers(conn, pvo);
			
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
		
		return pjlist;
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
	
	public List<AssignMemberVO> getRemoveMemList(ProjectVO pvo)throws Exception{
		Connection conn = null;
		ProjectDAO pdao = new ProjectDAO();
		List<AssignMemberVO> aslist = new ArrayList<AssignMemberVO>();
		
		try {
			conn = GetDBConn.getConnection();
			aslist = pdao.getRemoveMemList(conn, pvo);
			
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
			// Open DB
			conn = GetDBConn.getConnection();
			conn.setAutoCommit(false);
			
			//1 .insert join table
			for (int idx = 0; idx < aavo.getChkvalues().length; idx++) {
				ProjectJoinMemberVO pjvo = new ProjectJoinMemberVO();
				
				pjvo.setIpjl_ipl_idx(aavo.getIpl_idx());
				pjvo.setIpjl_im_idx(aavo.getChkvalues()[idx]);
				pjvo.setIpjl_roll(aavo.getRolls()[idx]);
				
				result = pdao.addAssignMember(conn, pjvo);
				
				if(result == false){
					conn.rollback();
					return result;
				}
			}
			
			//2 . update member table >> status
			for (int iddx = 0; iddx < aavo.getChkvalues().length; iddx++) {
				MemberVO mvo = new MemberVO();
				mvo.setIm_idx(aavo.getChkvalues()[iddx]);
				
				result = pdao.memberStatusChange(conn, mvo);
				
				if(result == false){
					conn.rollback();
					return result;
				}
				
			}
			
			conn.commit();
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
		
		return result;
		
	}
	
	public boolean removeAssignMember(AddAssignMemberVO aavo) throws Exception{
		Connection conn = null;
		ProjectDAO pdao = new ProjectDAO();
		boolean result = false;
		
		try {
			// Open DB
			conn = GetDBConn.getConnection();
			conn.setAutoCommit(false);
			
			//1 .delete join table
			for (int idx = 0; idx < aavo.getChkvalues().length; idx++) {
				ProjectJoinMemberVO pjvo = new ProjectJoinMemberVO();
				
				pjvo.setIpjl_ipl_idx(aavo.getIpl_idx());
				pjvo.setIpjl_im_idx(aavo.getChkvalues()[idx]);
				
				result = pdao.deleteAssignMember(conn, pjvo); /////////////////////////////////////
				
				if(result == false){
					conn.rollback();
					return result;
				}
			}
			
			//2 . update member table >> status = 0
			for (int iddx = 0; iddx < aavo.getChkvalues().length; iddx++) {
				MemberVO mvo = new MemberVO();
				mvo.setIm_idx(aavo.getChkvalues()[iddx]);
				
				result = pdao.memberStatusDefault(conn, mvo); ///////////////////////////////////
				
				if(result == false){
					conn.rollback();
					return result;
				}
				
			}
			// commit
			conn.commit();
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
		
		return result;
		
	}
	
	public boolean deleteProject(int ipl_idx) throws Exception{
		
		Connection conn = null;
		ProjectDAO pdao = new ProjectDAO();
		boolean result = false;
		List<MemberVO> mlist = new ArrayList<MemberVO>();
 		try {
			conn = GetDBConn.getConnection();
			conn.setAutoCommit(false);
			
			//1. 프로젝트에 참여중인 인원이 있는지 체크
			mlist = pdao.projectAssignedWorker(conn, ipl_idx);
			if(mlist == null){
				return result;
			}
			//2. 참여인원 있을때 제외시키기
			if(mlist.size() >  0){
				
				// 참여중인 프로젝트 인원이 있다면 제외시키기
//				for (int idx = 0; idx < mlist.size(); idx++) {
//					
//					ProjectJoinMemberVO pjvo = new ProjectJoinMemberVO();
//					pjvo.setIpjl_ipl_idx(ipl_idx);
//					pjvo.setIpjl_im_idx(mlist.get(idx).getIm_idx());
//					
//					result = pdao.deleteAssignMember(conn, pjvo);
//					if(result == false){
//						conn.rollback();
//						return result;
//					}
//				}
				
				// 제외된 인원 status 원상복귀
				for (int iddx = 0; iddx < mlist.size(); iddx++) {
					
					result = pdao.memberStatusDefault(conn, mlist.get(iddx));
					if(result == false){
						conn.rollback();
						return result;
					}
				}
			}
			
			//3. 프로젝트 컬럼 업데이트
			result = pdao.deleteProject(conn, ipl_idx);
			if(result == false){
				conn.rollback();
				return result;
			}
			
			//commit;
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
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
		
 		return result;
	}
}
