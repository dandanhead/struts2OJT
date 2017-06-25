package kr.co.ican.service.worker;

import java.sql.SQLException;
import java.util.List;

import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemberVO;

public interface WorkerService {

	public List<MemberVO> getWorkerList(MemberVO mvo) throws SQLException;
	public int getWorkerCount() throws SQLException;
	public boolean chkDuplPhone(MemberVO mvo) throws SQLException;
	public boolean chkDuplEmail(MemberVO mvo) throws SQLException;
	public boolean chkDuplScnum(MemberVO mvo) throws SQLException;
	public boolean addWorker(MemberVO mvo, List<ExperienceVO> elist, List<MemLicenseVO> liclist) throws SQLException;
}
