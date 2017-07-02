package kr.co.ican.worker.action;

import java.util.List;
import com.opensymphony.xwork2.ActionSupport;

import kr.co.ican.validation.ValidationCheck;
import kr.co.ican.validation.ValidationVO;
import kr.co.ican.worker.service.WorkerService;
import kr.co.ican.worker.vo.AddWorkerVO;
import kr.co.ican.worker.vo.ExperienceVO;
import kr.co.ican.worker.vo.MemLicenseVO;
import kr.co.ican.worker.vo.MemberVO;
import kr.co.ican.worker.vo.UpdateWorkerVO;

public class InfoUpdateWorkerAction extends ActionSupport {

	private static final long serialVersionUID = 9095698787104678824L;

	private int idx;
	private MemberVO mvo;
	private MemLicenseVO licvo;
	private ExperienceVO evo;
	private List<MemLicenseVO> liclist;
	private List<ExperienceVO> elist;
	////////////////////////
	
	private String confirmPw; // pw dupl check
	private String snumF; // 주민번호 앞자리
	private String snumE; // 주민번호 뒷자리
	private String sphone; //전화번호 앞자리
	private String mphone; // 중간자리
	private String ephone; // 끝자리
	
	//license
	private int chkLicense; // license count
	private String[] iml_lname;
	private String[] iml_acqdate;
	private String[] iml_organization;
 	//타업체
	private int chkTa;
	private String outsideperson;
	//career
	private int chkCareer; //career count
	private String[] ime_regi_date;
	private String[] ime_exit_date;
	private String[] ime_coname;
	private String[] ime_auth;
	private String[] ime_roll;
	
	//err Message
	private String errMessage;
	
	public InfoUpdateWorkerAction() {
		
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public MemberVO getMvo() {
		return mvo;
	}
	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}
	public List<MemLicenseVO> getLiclist() {
		return liclist;
	}
	public void setLiclist(List<MemLicenseVO> liclist) {
		this.liclist = liclist;
	}
	public List<ExperienceVO> getElist() {
		return elist;
	}
	public void setElist(List<ExperienceVO> elist) {
		this.elist = elist;
	}
	public MemLicenseVO getLicvo() {
		return licvo;
	}
	public void setLicvo(MemLicenseVO licvo) {
		this.licvo = licvo;
	}
	public ExperienceVO getEvo() {
		return evo;
	}
	public void setEvo(ExperienceVO evo) {
		this.evo = evo;
	}
	
	public String getConfirmPw() {
		return confirmPw;
	}
	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}
	public String getSnumF() {
		return snumF;
	}
	public void setSnumF(String snumF) {
		this.snumF = snumF;
	}
	public String getSnumE() {
		return snumE;
	}
	public void setSnumE(String snumE) {
		this.snumE = snumE;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getEphone() {
		return ephone;
	}
	public void setEphone(String ephone) {
		this.ephone = ephone;
	}
	public int getChkLicense() {
		return chkLicense;
	}
	public void setChkLicense(int chkLicense) {
		this.chkLicense = chkLicense;
	}
	public String[] getIml_lname() {
		return iml_lname;
	}
	public void setIml_lname(String[] iml_lname) {
		this.iml_lname = iml_lname;
	}
	public String[] getIml_acqdate() {
		return iml_acqdate;
	}
	public void setIml_acqdate(String[] iml_acqdate) {
		this.iml_acqdate = iml_acqdate;
	}
	public String[] getIml_organization() {
		return iml_organization;
	}
	public void setIml_organization(String[] iml_organization) {
		this.iml_organization = iml_organization;
	}
	public int getChkTa() {
		return chkTa;
	}
	public void setChkTa(int chkTa) {
		this.chkTa = chkTa;
	}
	public String getOutsideperson() {
		return outsideperson;
	}
	public void setOutsideperson(String outsideperson) {
		this.outsideperson = outsideperson;
	}
	public int getChkCareer() {
		return chkCareer;
	}
	public void setChkCareer(int chkCareer) {
		this.chkCareer = chkCareer;
	}
	public String[] getIme_regi_date() {
		return ime_regi_date;
	}
	public void setIme_regi_date(String[] ime_regi_date) {
		this.ime_regi_date = ime_regi_date;
	}
	public String[] getIme_exit_date() {
		return ime_exit_date;
	}
	public void setIme_exit_date(String[] ime_exit_date) {
		this.ime_exit_date = ime_exit_date;
	}
	public String[] getIme_coname() {
		return ime_coname;
	}
	public void setIme_coname(String[] ime_coname) {
		this.ime_coname = ime_coname;
	}
	public String[] getIme_auth() {
		return ime_auth;
	}
	public void setIme_auth(String[] ime_auth) {
		this.ime_auth = ime_auth;
	}
	public String[] getIme_roll() {
		return ime_roll;
	}
	public void setIme_roll(String[] ime_roll) {
		this.ime_roll = ime_roll;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	@Override
	public String execute() throws Exception {
		
		WorkerService wservice = new WorkerService();
		try {
			
			// init
			if(mvo == null){
				mvo = new MemberVO();
			}
			if(licvo == null){
				licvo = new MemLicenseVO();
			}
			if(evo == null){
				evo = new ExperienceVO();
			}
			// 1.vo setting
			mvo.setIm_idx(idx);
			licvo.setIml_im_idx(idx);
			evo.setIme_im_idx(idx);
			
			UpdateWorkerVO uvo = new UpdateWorkerVO();
			
			//vo setting
			uvo.setMvo(mvo);
			uvo.setLicvo(licvo);
			uvo.setEvo(evo);
			
			uvo = wservice.goWorkerUpdate(uvo);
			
			mvo = uvo.getMvo(); //1. 사원 기본정보
			elist = uvo.getElist(); //2. 자격증 리스트
			liclist = uvo.getLiclist(); //3. 경력 리스트
			evo = uvo.getEvo();
			outsideperson = uvo.getEvo().getIme_coname();
			sphone = uvo.getMvo().getIm_phone().split("-")[0];
			mphone = uvo.getMvo().getIm_phone().split("-")[1];
			ephone = uvo.getMvo().getIm_phone().split("-")[2];
			snumF = uvo.getMvo().getIm_scnum().split("-")[0];
			snumE = uvo.getMvo().getIm_scnum().split("-")[1];
			chkLicense = liclist.size();
			chkCareer = elist.size();
			
			return "success";
			
		} catch (Exception e) {
			
			return "error";
		}
	}

	public String updateInfo(){
		
		//1. validation
		ValidationCheck vcheck = new ValidationCheck();
		ValidationVO vvo = new ValidationVO();
		AddWorkerVO avo = new AddWorkerVO();
		WorkerService service = new WorkerService();
		boolean updatecheck = false;
		// vo setting
		mvo.setIm_idx(idx);
		avo.setMvo(mvo);
		avo.setConfirmPw(confirmPw);
		avo.setSnumF(snumF); // 새로 입력 한 값
		avo.setSnumE(snumE);
		avo.setSphone(sphone);
		avo.setMphone(mphone);
		avo.setEphone(ephone);
		avo.setChkLicense(chkLicense);
		avo.setIml_lname(iml_lname);
		avo.setIml_acqdate(iml_acqdate);
		avo.setIml_organization(iml_organization);
		avo.setChkTa(chkTa);
		avo.setOutsideperson(outsideperson);
		avo.setChkCareer(chkCareer);
		avo.setIme_regi_date(ime_regi_date);
		avo.setIme_exit_date(ime_exit_date);
		avo.setIme_coname(ime_coname);
		avo.setIme_auth(ime_auth);
		avo.setIme_roll(ime_roll);
		
		
		try {
			liclist  = service.makeListLicense(iml_lname, iml_acqdate, iml_organization, chkLicense);
			elist =  service.makeListExp(ime_regi_date, ime_exit_date, ime_coname, ime_auth, ime_roll, chkCareer);
			mvo.setOutsideperson(outsideperson);
			
			// validation
			vvo = vcheck.updateWorkerAction(avo);
			
			if(vvo.isResultfalg() == true){
				// update
				updatecheck = service.updateWorkerInfo(mvo, liclist, elist, chkTa);
				
			}else{
				errMessage = vvo.getMsg(); 
				return "fail"; // validation return
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";  //exception return
			
		}
		
		//return
		if(updatecheck == false){
			errMessage = "지금은 사원정보를 업데이트 할 수 없습니다. 관리자에게 문의하세요.";
			return "fail"; //update fail return;
		}else{
			
			return "success"; // update success return
		}
		
	}
}
