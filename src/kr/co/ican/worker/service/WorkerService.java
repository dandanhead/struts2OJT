package kr.co.ican.worker.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.ican.project.dao.ProjectDAO;
import kr.co.ican.util.GetDBConn;
import kr.co.ican.util.Helps;
import kr.co.ican.worker.dao.WorkerDAO;
import kr.co.ican.worker.vo.ExperienceVO;
import kr.co.ican.worker.vo.MemLicenseVO;
import kr.co.ican.worker.vo.MemberVO;
import kr.co.ican.worker.vo.UpdateWorkerVO;

//Worker 관련 services
public class WorkerService {

	// 사원 상세정보 가져오기
	public MemberVO getMemberDetail(int im_idx)throws Exception {
		
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		MemberVO mvo = new MemberVO();
		try {
			conn = GetDBConn.getConnection();
			mvo = wdao.getMemberDetail(conn, im_idx);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			mvo = null;
			throw e;
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return mvo;
	}

	public List<ExperienceVO> getMemberExperiences(ExperienceVO evo) throws Exception{// worker`s project history list
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		List<ExperienceVO> elist = new ArrayList<ExperienceVO>();
		
		try {
			conn = GetDBConn.getConnection();
			elist = wdao.getMemberExperiences(evo, conn);
		} catch (Exception e) {
			e.printStackTrace();
			elist = null;
			throw e;
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return elist;
	}

	public List<MemLicenseVO> getMemberLicenses(MemLicenseVO licvo)throws Exception {// worker`s License list
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		List<MemLicenseVO> liclist = new ArrayList<MemLicenseVO>();
		try {
			conn = GetDBConn.getConnection();
			liclist = wdao.getMemberLicenses(licvo, conn);
		} catch (Exception e) {
			e.printStackTrace();
			liclist = null;
			throw e;
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return liclist;
	}

	public int getWorkerExpCount(ExperienceVO evo) throws Exception{ // Counting worker`s Experience 
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		int result = 0;
		
		try {
			conn = GetDBConn.getConnection();
			result = wdao.getWorkerExpCount(evo, conn);

		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public int getWorkerLicCount(MemLicenseVO licvo) throws Exception{ // Counting worker`s License 
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		int result = 0;
		
		try {
			conn = GetDBConn.getConnection();
			result = wdao.getWorkerLicCount(licvo, conn);

		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<MemberVO> getWorkerList( MemberVO mvo) throws Exception{ // 사원 리스트
	    Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		List<MemberVO> mlist = new ArrayList<MemberVO>();
		Helps help = new Helps();
		mvo = help.setWorkerListPaging(mvo); // set paging
		
		try {
			conn = GetDBConn.getConnection();
			mlist = wdao.getWorkerList(conn , mvo);
		} catch (Exception e) {
			mlist = null;
			e.printStackTrace();
			throw e;
		}finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return mlist;
	}

	public int getWorkerCount() throws Exception{// Total counting Member
	    Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		int totalcount = 0;
		try {
			conn = GetDBConn.getConnection();
			totalcount = wdao.getWorkerCount(conn); 
		} catch (Exception e) {
			totalcount = -1;
			e.printStackTrace();
			throw e;
		}finally {
			
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				totalcount = -1;
				e.printStackTrace();
			}
		}
		
		return totalcount;
		
	}

	public boolean chkDuplPhone(MemberVO mvo)throws Exception{// Check Duplicate Phone Number
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		try {
			conn = GetDBConn.getConnection();
			flag = wdao.chkDuplPhone(mvo, conn);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			throw e;
		}finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public boolean updateChkDuplPhone(MemberVO mvo)throws Exception{// Check Duplicate Phone Number
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		try {
			conn = GetDBConn.getConnection();
			flag = wdao.updateChkDuplPhone(mvo, conn);///
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			throw e;
		}finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean chkDuplEmail(MemberVO mvo) throws Exception{ // Check Duplicate E-Mail Address
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		try {
			conn = GetDBConn.getConnection();
			flag = wdao.chkDuplEmail(mvo, conn);
		} catch (SQLException e) {
			flag = false;
			
		}catch(Exception ee){
			throw ee;
		}
		finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				flag =false;
			}
		}
		return flag;
	}
	
	public boolean updateChkDuplEmail(MemberVO mvo) throws Exception{ // Check Duplicate E-Mail Address
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		try {
			conn = GetDBConn.getConnection();
			flag = wdao.updateChkDuplEmail(mvo, conn);
		} catch (SQLException e) {
			flag = false;
			
		}catch(Exception ee){
			throw ee;
		}
		finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				flag =false;
			}
		}
		return flag;
	}

	public boolean chkDuplScnum(MemberVO mvo) throws Exception{// Check Duplicate Social Number
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		try {
			
			conn = GetDBConn.getConnection();
			flag = wdao.chkDuplScnum(mvo, conn);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			throw e;
		}finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				flag =false;
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean addWorker(MemberVO mvo, List<ExperienceVO> elist, List<MemLicenseVO> liclist) throws Exception{ // add Worker
		//Insert Check Flag
		boolean lastResult = false;
		//Create
		Connection conn = null;
		try{
			conn = GetDBConn.getConnection();
			conn.setAutoCommit(false);
			WorkerDAO wdao = new WorkerDAO();
			
			// 1. 기본사원정보 추가
			lastResult = wdao.addWorkerInfo(mvo, conn); // 기본 정보
			
			if(lastResult == false){
				conn.rollback();
				return lastResult;
			}
			
			// 2. 기본 경력 추가 
			lastResult = wdao.basicWorkerExp(mvo, conn); // 기본 경력
			
			if(lastResult == false){
				conn.rollback();
				return lastResult;
			}
			
			// 3. 자격증
			if (liclist != null && liclist.size() > 0) {
				
				for (int idx = 0; idx < liclist.size(); idx++) {

					lastResult = wdao.addWorkerLicense(liclist.get(idx), conn);
					
					if (lastResult == false) {
						conn.rollback();
						return lastResult;
					}
				}
			} 
			// 4. 경력
			if (elist != null && elist.size() > 0) {
				
				for (int idx = 0; idx < elist.size(); idx++) {

					lastResult = wdao.addWorkerExp(elist.get(idx), conn);
					
					if (lastResult == false) {
						conn.rollback();
						return lastResult;
					}
				}
			}
			
			if(lastResult == true){
				conn.commit();
			}
			
		}catch(Exception ee){
			
			conn.rollback();
			ee.printStackTrace();
			lastResult = false;
			throw ee;
		}
		finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lastResult;
		
		
	}
	public String getRegiDate(MemberVO mvo) throws Exception{
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		String resultstr = "";
		try {
			conn = GetDBConn.getConnection();
			resultstr = wdao.getRegiDate(conn, mvo);
		} catch (Exception e) {
			e.printStackTrace();
			resultstr = null;
			throw e;
		}finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return resultstr;
		
	}
	// make Experience List
	public List<ExperienceVO> makeListExp(String[] regi, String[] exit, String[] coname, String[] auth, String[] roll,	int count) {
		List<ExperienceVO> elist = new ArrayList<ExperienceVO>();

		for (int idx = 0; idx < count; idx++) {
			ExperienceVO evo = new ExperienceVO();
			evo.setIme_regi_date(regi[idx]);
			evo.setIme_exit_date(exit[idx]);
			evo.setIme_coname(coname[idx]);
			evo.setIme_auth(Integer.parseInt(auth[idx]));
			evo.setIme_roll(roll[idx]);
			elist.add(evo);
		}
		return elist;
	}
	// make License List
	public List<MemLicenseVO> makeListLicense(String[] lname, String[] acqdate, String[] organization, int count) {
		List<MemLicenseVO> liclist = new ArrayList<MemLicenseVO>();

		for (int idx = 0; idx < count; idx++) {
			MemLicenseVO licvo = new MemLicenseVO();
			licvo.setIml_lname(lname[idx]);
			licvo.setIml_acqdate(acqdate[idx]);
			licvo.setIml_organization(organization[idx]);
			liclist.add(licvo);
		}
		return liclist;
	}
	
	public UpdateWorkerVO goWorkerUpdate(UpdateWorkerVO uvo) throws Exception{
		
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		
		MemberVO mvo = new MemberVO();
		List<ExperienceVO> elist = new ArrayList<ExperienceVO>();
		List<MemLicenseVO> liclist = new ArrayList<MemLicenseVO>();
		ExperienceVO evo = new ExperienceVO();
		
		try {
			
			conn = GetDBConn.getConnection();
			
			mvo = wdao.getMemberDetail(conn, uvo.getMvo().getIm_idx()); // mvo
			liclist = wdao.getMemberLicenses(uvo.getLicvo(), conn); //license list
			elist = wdao.getMemberExperiences(uvo.getEvo(), conn); //exp list
			evo = wdao.getOutSidePersonCompany(uvo.getMvo().getIm_idx(), conn);
			
			uvo.setMvo(mvo);
			uvo.setLiclist(liclist);
			uvo.setElist(elist);
			uvo.setEvo(evo);
			
		} catch (Exception e) {
			
			uvo = null;
			e.printStackTrace();
			throw e;
		}finally {
			if(conn != null){
				try {
					
					conn.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return uvo;
	}
	
	public boolean updateWorkerInfo(MemberVO mvo, List<MemLicenseVO> liclist, List<ExperienceVO> elist, int chkTa)throws Exception{
		//Insert Check Flag
		boolean lastResult = false; // return check
		boolean existCheck = false; // 삭제 여부 check
		//Create
		Connection conn = null;
		
		conn = GetDBConn.getConnection();
		conn.setAutoCommit(false);
		WorkerDAO wdao = new WorkerDAO();
		
		try {
			
			// 1. 기본사원정보 update
			lastResult = wdao.updateWorkerInfo(mvo, conn);
			if(lastResult == false){
				return lastResult;
			}
			// 2. 자격증 지우기
			existCheck = wdao.chkExistLicence(mvo, conn); // 자격증이 DB에 있는지 체크
			if(existCheck == true){ // 있다면
				lastResult = wdao.preupdateWorkerLicense(mvo, conn); // 자격증 삭제
				if(lastResult == false){
					return lastResult;
				}
			}
			// 3. 자격증 insert
			if(liclist.size() != 0 && liclist != null){
				
				for (int idx = 0; idx < liclist.size(); idx++) {
					//liclist.get(idx) == MemLicenseVO
					lastResult = wdao.updateWorkerLicense(liclist.get(idx), conn , mvo);
					
					if(lastResult == false){
						return lastResult;
					}
					
				}
				
			}
			// 4. 경력 지우기
			existCheck = wdao.chkExistExp(mvo, conn);
			if(existCheck == true){
				lastResult = wdao.preupdateWorkerExp(mvo, conn);
				if(lastResult == false){
					return lastResult;
				}
			}
			
			
			// 5. 경력 insert
			if(elist.size() != 0 && elist != null){
				
				for (int idx = 0; idx < elist.size(); idx++) {
					
					lastResult = wdao.updateWorkerExp(elist.get(idx), conn , mvo);
					
					if(lastResult == false){
						return lastResult;
					}
				}
			}
			// 6. 타업체 회사명 바꾸기
			if(chkTa > 0){
				lastResult =  wdao.updateWorkerConame(mvo, conn);
				if(lastResult == false){
					return lastResult;
				}
			}else{
				lastResult = wdao.updateWorkerConameReturn(mvo, conn);
				if(lastResult == false){
					return lastResult;
				}
			}
			
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
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
		
		return lastResult;
	}
	
	public String getProjectName(int idx) throws Exception{
		Connection conn = null;
		ProjectDAO pdao = new ProjectDAO();
		String result = "";
		try {
			conn = GetDBConn.getConnection();
			result = pdao.getProjectName(conn, idx);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
}	
