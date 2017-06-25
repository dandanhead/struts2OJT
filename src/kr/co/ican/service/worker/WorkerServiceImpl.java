package kr.co.ican.service.worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kr.co.ican.dao.WorkerDAO;
import kr.co.ican.dbconn.GetDBConn;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemberVO;

public class WorkerServiceImpl implements WorkerService {

	@Override
	public List<MemberVO> getWorkerList( MemberVO mvo) throws SQLException {
	    Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		WorkerDAO wdao = new WorkerDAO();
		List<MemberVO> mlist = new ArrayList<MemberVO>();
		conn = GetDBConn.getConnection();
		
		int pageNumber = mvo.getPageNumber();
		int start = (pageNumber)*mvo.getRecordCountPerPage() + 1;
		int end = (pageNumber + 1)*mvo.getRecordCountPerPage();
		mvo.setStart(start);
		mvo.setEnd(end);
		mlist = wdao.getWorkerList(conn, psmt, rs, mvo);
		
		GetDBConn.close(conn, psmt, rs);
		
		return mlist;
	}

	@Override
	public int getWorkerCount() throws SQLException {
	    Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		conn = GetDBConn.getConnection();
		
		int totalcount = 0;
		WorkerDAO wdao = new WorkerDAO();
		
		totalcount = wdao.getWorkerCount(conn, psmt, rs);
		
		GetDBConn.close(conn, psmt, rs);
		
		return totalcount;
		
	}

	@Override
	public boolean chkDuplPhone(MemberVO mvo) throws SQLException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = GetDBConn.getConnection();
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		
		flag = wdao.chkDuplPhone(mvo, conn, psmt, rs);
		
		GetDBConn.close(conn, psmt, rs);
		
		return flag;
	}

	@Override
	public boolean chkDuplEmail(MemberVO mvo) throws SQLException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = GetDBConn.getConnection();
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		flag = wdao.chkDuplEmail(mvo, conn, psmt, rs);
		GetDBConn.close(conn, psmt, rs);
		return flag;
	}

	@Override
	public boolean chkDuplScnum(MemberVO mvo) throws SQLException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = GetDBConn.getConnection();
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		flag = wdao.chkDuplScnum(mvo, conn, psmt, rs);
		GetDBConn.close(conn, psmt, rs);
		return flag;
	}

	@Override
	public boolean addWorker(MemberVO mvo, List<ExperienceVO> elist, List<MemLicenseVO> liclist) throws SQLException{
		boolean basicInfoFlag = false;
		boolean licenseFlag = false;
		boolean basicExpFlag = false;
		boolean careerFlag = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		conn = GetDBConn.getConnection();
		conn.setAutoCommit(false);
		
		WorkerDAO wdao = new WorkerDAO();
		//1. 기본정보 and 기본 경력 추가
		basicInfoFlag = wdao.addWorkerInfo(mvo, conn, psmt, rs); // 기본 정보
		basicExpFlag = wdao.basicWorkerExp(mvo, conn, psmt, rs); // 기본 경력
		//2. 자격증
		if(liclist != null && liclist.size() > 0){
			
			for (int idx = 0; idx < liclist.size(); idx++) {
				
				licenseFlag = wdao.addWorkerLicense(liclist.get(idx), conn, psmt, rs);
				if(!licenseFlag){
					break;
				}
			}
			
		}
		//3. 경력
		if (elist != null && elist.size() > 0) {
			
			for (int idx = 0; idx < elist.size(); idx++) {
				
				careerFlag = wdao.addWorkerExp(elist.get(idx), conn, psmt, rs);
				if(!careerFlag){
					break;
				}
			}
			
		}
		
		//4. insert check
		if(!basicInfoFlag || !licenseFlag || !basicExpFlag || !careerFlag){
			conn.rollback();
			GetDBConn.close(conn, psmt, rs);
			return false;
		}else{
			conn.commit();
			GetDBConn.close(conn, psmt, rs);
			return true;
		}
		
	}
	
	
	 
	
}	
