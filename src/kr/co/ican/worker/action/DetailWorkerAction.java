package kr.co.ican.worker.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.worker.service.WorkerService;
import kr.co.ican.worker.vo.ExperienceVO;
import kr.co.ican.worker.vo.MemLicenseVO;
import kr.co.ican.worker.vo.MemberVO;

// 상세페이지 보기
public class DetailWorkerAction extends ActionSupport {
	
	private static final long serialVersionUID = 258204559301057886L;
	
	private int idx;
	private String expy;
	private String expm;
	
	private MemberVO mvo;
	private ExperienceVO evo;
	private MemLicenseVO licvo;
	
	private List<ExperienceVO> elist;
	private List<MemLicenseVO> liclist;
	
	private int evototal; // 경력 수
	private int lictotal;  // 자격증 수
	private int pageCountPerScreen = 5; // 한 페이지당 보여줄 인원 수
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getExpy() {
		return expy;
	}
	public void setExpy(String expy) {
		this.expy = expy;
	}
	public String getExpm() {
		return expm;
	}
	public void setExpm(String expm) {
		this.expm = expm;
	}
	
	public MemberVO getMvo() {
		return mvo;
	}
	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}
	public ExperienceVO getEvo() {
		return evo;
	}
	public void setEvo(ExperienceVO evo) {
		this.evo = evo;
	}
	public List<ExperienceVO> getElist() {
		return elist;
	}
	public void setElist(List<ExperienceVO> elist) {
		this.elist = elist;
	}
	public List<MemLicenseVO> getLiclist() {
		return liclist;
	}
	public void setLiclist(List<MemLicenseVO> liclist) {
		this.liclist = liclist;
	}
	public MemLicenseVO getLicvo() {
		return licvo;
	}
	public void setLicvo(MemLicenseVO licvo) {
		this.licvo = licvo;
	}
	public int getPageCountPerScreen() {
		return pageCountPerScreen;
	}
	public void setPageCountPerScreen(int pageCountPerScreen) {
		this.pageCountPerScreen = pageCountPerScreen;
	}
	
	public int getEvototal() {
		return evototal;
	}
	public void setEvototal(int evototal) {
		this.evototal = evototal;
	}
	public int getLictotal() {
		return lictotal;
	}
	public void setLictotal(int lictotal) {
		this.lictotal = lictotal;
	}
	@Override
	public String execute() throws Exception {
		
		WorkerService service = new WorkerService();
		//1. 기본정보 가져오기
		if(mvo == null){
			mvo = new MemberVO();
		}
		mvo.setIm_idx(idx);
		mvo = service.getMemberDetail(idx);
		//2. 경력 가져오기
		if(evo == null){
			
			evo = new ExperienceVO();
		}
		evo.setIme_im_idx(idx);
		evototal = service.getWorkerExpCount(evo); //count 
		elist = service.getMemberExperiences(evo); //list
		//3. 자격증 가져오기
		if(licvo == null){
			licvo = new MemLicenseVO();
		}
		licvo.setIml_im_idx(idx);
		lictotal = service.getWorkerLicCount(licvo); //count
		liclist = service.getMemberLicenses(licvo); // list
		if(mvo == null || liclist == null || elist == null ){
			return "fail";
		}
		
		return "success";
	}
	
	
	
}
