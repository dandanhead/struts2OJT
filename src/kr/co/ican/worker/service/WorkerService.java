package kr.co.ican.worker.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kr.co.ican.project.vo.ProjectVO;
import kr.co.ican.util.GetDBConn;
import kr.co.ican.worker.dao.WorkerDAO;
import kr.co.ican.worker.vo.ExperienceVO;
import kr.co.ican.worker.vo.MemLicenseVO;
import kr.co.ican.worker.vo.MemberVO;

//Worker 관련 services
public class WorkerService {

	
	public MemberVO getMemberDetail(int im_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ExperienceVO> getMemberExperiences(int im_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTotalHistory(int im_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<MemLicenseVO> getMemberLicenses(int im_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRegiDate(int im_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProjectVO> getMemberProjects(int im_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MemberVO> getWorkerList( MemberVO mvo) {
	    Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		List<MemberVO> mlist = new ArrayList<MemberVO>();
		
		// Paging vo setting start - end
		int pageNumber = mvo.getPageNumber(); //current Page Number
		int start = (pageNumber)*mvo.getRecordCountPerPage() + 1; // List Strat Number
		int end = (pageNumber + 1)*mvo.getRecordCountPerPage(); //List End Number
		mvo.setStart(start);
		mvo.setEnd(end);
		
		try {
			conn = GetDBConn.getConnection();
			mlist = wdao.getWorkerList(conn , mvo);
		} catch (Exception e) {
			mlist = null;
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

	public int getWorkerCount() {// Total counting Member
	    Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		int totalcount = 0;
		try {
			conn = GetDBConn.getConnection();
			totalcount = wdao.getWorkerCount(conn); 
		} catch (Exception e) {
			totalcount = -1;
		}finally {
			
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return totalcount;
		
	}

	public boolean chkDuplPhone(MemberVO mvo){// Check Duplicate Phone Number
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		try {
			conn = GetDBConn.getConnection();
			flag = wdao.chkDuplPhone(mvo, conn);
		} catch (Exception e) {
			flag = false;
		}finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean chkDuplEmail(MemberVO mvo) { // Check Duplicate E-Mail Address
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		try {
			conn = GetDBConn.getConnection();
			flag = wdao.chkDuplEmail(mvo, conn);
		} catch (SQLException e) {
			flag = false;
		}finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean chkDuplScnum(MemberVO mvo) {// Check Duplicate Social Number
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		boolean flag  = false;
		try {
			
			conn = GetDBConn.getConnection();
			flag = wdao.chkDuplScnum(mvo, conn);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean addWorker(MemberVO mvo, List<ExperienceVO> elist, List<MemLicenseVO> liclist) { // add Worker
		//Insert Check Flag
		boolean basicInfoFlag = false;
		boolean licenseFlag = false;
		boolean basicExpFlag = false;
		boolean careerFlag = false;
		
		boolean lastResult = false;
		//Create
		Connection conn = null;
		try{
			conn = GetDBConn.getConnection();
			conn.setAutoCommit(false);

			WorkerDAO wdao = new WorkerDAO();
			
			// 1. 기본정보 and 기본 경력 추가
			basicInfoFlag = wdao.addWorkerInfo(mvo, conn); // 기본 정보
			basicExpFlag = wdao.basicWorkerExp(mvo, conn); // 기본 경력
			
			// 2. 자격증
			if (liclist != null && liclist.size() > 0) {

				for (int idx = 0; idx < liclist.size(); idx++) {

					licenseFlag = wdao.addWorkerLicense(liclist.get(idx), conn);
					if (!licenseFlag) {
						break;
					}
				}
			} else {
				licenseFlag = true;
			}
			// 3. 경력
			if (elist != null && elist.size() > 0) {

				for (int idx = 0; idx < elist.size(); idx++) {

					careerFlag = wdao.addWorkerExp(elist.get(idx), conn);
					if (!careerFlag) {
						break;
					}
				}
			} else {
				careerFlag = true;
			}

			// 4. insert check
			if (basicInfoFlag == false || !licenseFlag || !basicExpFlag || !careerFlag) {
				conn.rollback(); // roll back
				
				lastResult = false;
			} else {
				conn.commit(); // commit
				lastResult = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
			lastResult = false;
		}finally {
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
	
}	
