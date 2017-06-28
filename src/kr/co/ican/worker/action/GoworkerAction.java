package kr.co.ican.worker.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import kr.co.ican.worker.service.WorkerService;
import kr.co.ican.worker.vo.MemberVO;

//사원 목록 list 
public class GoworkerAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private List<MemberVO> workerlist; //workerlist
	private MemberVO mvo;
	private int totalRecordCount; // 총 인원수 
	private int pageCountPerScreen = 10; // 한 페이지당 보여줄 인원 수
	
	public List<MemberVO> getWorkerlist() {
		return workerlist;
	}

	public void setWorkerlist(List<MemberVO> workerlist) {
		this.workerlist = workerlist;
	}

	public MemberVO getMvo() {
		return mvo;
	}

	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}
	
	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	
	public int getPageCountPerScreen() {
		return pageCountPerScreen;
	}

	public void setPageCountPerScreen(int pageCountPerScreen) {
		this.pageCountPerScreen = pageCountPerScreen;
	}

	@Override
	public String execute() throws Exception {
		WorkerService wservice = new WorkerService();
		
		if(mvo == null){
			mvo = new MemberVO();
		}
		
		totalRecordCount =  wservice.getWorkerCount(); // total Count of Worker
		workerlist = wservice.getWorkerList(mvo);// get Worker List
		
		return "success";
	}
	
}
