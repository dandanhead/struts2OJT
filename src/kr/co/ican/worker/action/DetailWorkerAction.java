package kr.co.ican.worker.action;

import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import kr.co.ican.util.Helps;
import kr.co.ican.worker.service.WorkerService;
import kr.co.ican.worker.vo.ExperienceVO;
import kr.co.ican.worker.vo.MemLicenseVO;
import kr.co.ican.worker.vo.MemberVO;

// 상세페이지 보기
public class DetailWorkerAction extends ActionSupport {
	
	private static final long serialVersionUID = 258204559301057886L;
	//사번, 경력 년, 경력 월
	private int idx;
	private String expy;
	private String expm;
	private String experience;
	private int age;
	private String gender;
	private String regidate;
	// 기본정보, 자격증정보, 경력정보
	private MemberVO mvo;
	private ExperienceVO evo;
	private MemLicenseVO licvo;
	// 경력, 자격증 list
	private List<ExperienceVO> elist;
	private List<MemLicenseVO> liclist;
	//paging
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
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getRegidate() {
		return regidate;
	}
	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}
	@Override
	public String execute() throws Exception {
		
		WorkerService service = new WorkerService();
		Helps help = new Helps(); //경력 폼 변경
		
		try {

			// init
			if (mvo == null) {
				mvo = new MemberVO();
			}
			if (evo == null) {

				evo = new ExperienceVO();
			}
			if (licvo == null) {
				licvo = new MemLicenseVO();
			}
			// 1. 기본정보 가져오기
			mvo.setIm_idx(idx);
			mvo = service.getMemberDetail(idx); // 경력 기본정보
			experience = help.changFormCareer(expy, expm); // 경력
			age = help.getWorkerAge(mvo.getIm_scnum()); // 나이
			gender = help.getWorkerGender(mvo.getIm_scnum()); // 성별
			regidate = service.getRegiDate(mvo); // 입사일

			// 2. 경력 가져오기
			evo.setIme_im_idx(idx);
			evototal = service.getWorkerExpCount(evo); // count
			elist = service.getMemberExperiences(evo); // list

			// 3. 자격증 가져오기
			licvo.setIml_im_idx(idx);
			lictotal = service.getWorkerLicCount(licvo); // count
			liclist = service.getMemberLicenses(licvo); // list

			if (mvo == null || liclist == null || elist == null) {
				return "fail";
			}
			
			return "success";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	
}
