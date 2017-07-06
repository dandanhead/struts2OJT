package kr.co.ican.validation;

import kr.co.ican.login.service.LoginService;
import kr.co.ican.project.vo.ProjectVO;
import kr.co.ican.util.ValidationMethods;
import kr.co.ican.worker.service.WorkerService;
import kr.co.ican.worker.vo.AddWorkerVO;
import kr.co.ican.worker.vo.MemberVO;

// validation check 
public class ValidationCheck extends ValidationMethods{
	
	public ValidationVO findIdValidation(MemberVO mvo , String startnum , String endnum) throws Exception{
		
		boolean valflag = true;
		ValidationVO vvo = new ValidationVO();
		LoginService service = new LoginService();
		
		// validation
		// 1.nullcheck
		valflag = isblank(mvo.getIm_name());	
		if(valflag == false){
			vvo.setMsg("이름을 적어주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(startnum);
		if(valflag == false){
			vvo.setMsg("주민등록 번호를 입력해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(endnum);
		if(valflag == false){
			vvo.setMsg("주민등록 번호를 입력해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		// 2. Type Check
		valflag = onlyInputNumber(startnum);
		if(valflag == false){
			vvo.setMsg("주민등록 번호는 숫자만 입력 가능 합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = onlyInputNumber(endnum);
		if(valflag == false){
			vvo.setMsg("주민등록 번호는 숫자만 입력 가능 합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		// 3. exist Data Check
		mvo.setIm_scnum(startnum+"-"+endnum);
		mvo = service.findId(mvo);
		if(mvo == null){
			vvo.setMsg("사원번호를 찾을 수 없습니다. 이름 과 주민등록 번호를 정확하게 입력 해 주세요.");
			valflag = false;
			vvo.setResultfalg(valflag);
			return vvo;
			
		}else{
			vvo.setMsg("찾으시는 사원 번호는  <" + mvo.getIm_idx() + "> 입니다.");
			valflag = true;
			vvo.setResultfalg(valflag);
			return vvo;
		}
	}
	
	public ValidationVO findPwValidation(MemberVO mvo , String startnum , String endnum) throws Exception{
		
		boolean valflag = true;
		ValidationVO vvo = new ValidationVO();
		LoginService service = new LoginService();
		
		// validation
		// 1.nullcheck
		valflag = isblank(mvo.getIm_name());	
		if(valflag == false){
			vvo.setMsg("이름을 적어주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(startnum);
		if(valflag == false){
			vvo.setMsg("주민등록 번호를 입력해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(endnum);
		if(valflag == false){
			vvo.setMsg("주민등록 번호를 입력해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		// 2. Type Check
		valflag = onlyInputNumber(startnum);
		if(valflag == false){
			vvo.setMsg("주민등록 번호는 숫자만 입력 가능 합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = onlyInputNumber(endnum);
		if(valflag == false){
			vvo.setMsg("주민등록 번호는 숫자만 입력 가능 합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = isblank(mvo.getIm_email());
		if(valflag == false){
			vvo.setMsg("이메일을 입력해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isMailFormCheck(mvo.getIm_email());
		if(valflag == false){
			vvo.setMsg("이메일 형식이 아닙니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		// 3. exist Data Check
		mvo.setIm_scnum(startnum+"-"+endnum);
		mvo = service.findPw(mvo);
		if(mvo == null){
			vvo.setMsg("Password 를 찾을 수 없습니다. 이름 과 주민등록 번호, 이메일을 정확하게 입력 해 주세요.");
			valflag = false;
			vvo.setResultfalg(valflag);
			return vvo;
			
		}else{
			vvo.setMsg("찾으시는 Password는  <" + mvo.getIm_pw() + "> 입니다.");
			valflag = true;
			vvo.setResultfalg(valflag);
			return vvo;
		}
	}
	
	public ValidationVO addWorkerValidation(AddWorkerVO avo) throws Exception{
		
		boolean valflag = true;
		ValidationVO vvo = new ValidationVO();
		WorkerService service = new WorkerService();
		
		//1.null check
		valflag = isblank(avo.getMvo().getIm_pw()); //pw null chk
		if(valflag == false){
			vvo.setMsg("패스워드를 입력해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getConfirmPw()); //pw confirm null chk
		if(valflag == false){
			vvo.setMsg("패스워드 확인을 입력해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = stringLengthCheck(avo.getMvo().getIm_pw()); // pw length
		if(valflag == false){
			vvo.setMsg("패스워드는 6자 이상 15자 이하로 정해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = equalStringCheck(avo.getMvo().getIm_pw(), avo.getConfirmPw()); // 비번 같은지 확인
		if(valflag == false){
			vvo.setMsg("비밀번호가 일치 하지 않습니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getMvo().getIm_name()); // name null check
		if(valflag == false){
			vvo.setMsg("이름을 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getSnumF()); // 민번 앞자리
		if(valflag == false){
			vvo.setMsg("주민등록번호를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getSnumE()); // 민번 뒷자리
		if(valflag == false){
			vvo.setMsg("주민등록번호를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = onlyInputNumber(avo.getSnumF()); // 민번 숫자만
		if(valflag == false){
			vvo.setMsg("주민등록번호는 숫자만 입력 가능합니다");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = onlyInputNumber(avo.getSnumE()); // 민번 숫자만
		if(valflag == false){
			vvo.setMsg("주민등록번호는 숫자만 입력 가능합니다");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = scnumValidation(avo.getSnumF(),avo.getSnumE()); //유효 주빈 번호 확인
		if(valflag == false){
			vvo.setMsg("유효한 주민등록번호가 아닙니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		// 민번 합치기
		avo.getMvo().setIm_scnum(avo.getSnumF()+"-"+avo.getSnumE());
		valflag = service.chkDuplScnum(avo.getMvo()); // 민번 중복 확인
		if(valflag == false){
			vvo.setMsg("이미 등록된 주민등록 번호입니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getSphone()); // 전화번호 앞자리 null check
		if(valflag == false){
			vvo.setMsg("핸드폰 번호를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = isblank(avo.getMphone()); // 전화번호 가운데자리 null check
		if(valflag == false){
			vvo.setMsg("핸드폰 번호를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}		
		
		valflag = isblank(avo.getEphone()); // 전화번호 뒷자리 null check
		if(valflag == false){
			vvo.setMsg("핸드폰 번호를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}	
		
		valflag = onlyInputNumber(avo.getSphone()); // 전화번호 숫자만
		if(valflag == false){
			vvo.setMsg("전화 번호는 숫자만 입력 가능합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = onlyInputNumber(avo.getMphone()); // 전화번호 숫자만
		if(valflag == false){
			vvo.setMsg("전화 번호는 숫자만 입력 가능합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = onlyInputNumber(avo.getEphone()); // 전화번호 숫자만
		if(valflag == false){
			vvo.setMsg("전화 번호는 숫자만 입력 가능합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = phoneLengthThree(avo.getSphone()); // 전홤번호 자리수
		if(valflag == false){
			vvo.setMsg("전화번호를 정확하게 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = phoneLengthThreeOrFour(avo.getMphone());// 전홤번호 자리수
		if(valflag == false){
			vvo.setMsg("전화번호를 정확하게 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = phoneLengthThreeOrFour(avo.getEphone()); // 전홤번호 자리수
		if(valflag == false){
			vvo.setMsg("전화번호를 정확하게 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		// phone vo setting
		avo.getMvo().setIm_phone(avo.getSphone()+"-"+avo.getMphone()+"-"+avo.getEphone());
		valflag = service.chkDuplPhone(avo.getMvo()); // 이미 등록된 전화번호
		if(valflag == false){
			vvo.setMsg("이미 등록된 전화번호 입니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getMvo().getIm_email()); // 이메일 null chk
		if(valflag == false){
			vvo.setMsg("이메일 주소를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isMailFormCheck(avo.getMvo().getIm_email()); // 이메일 폼체크
		if(valflag == false){
			vvo.setMsg("이메일 주소 형식이 아닙니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = service.chkDuplEmail(avo.getMvo()); // 이메일 중복
		if(valflag == false){
			vvo.setMsg("이미 가입 된 이메일 주소 입니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = isblank(avo.getOutsideperson()); // 타업체 null chk
		if(avo.getChkTa() == 1 && valflag == false){
			vvo.setMsg("타업체 인력인 경우 회사명을 반드시 입력 하셔야 합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = isblank(avo.getMvo().getIm_postcode()); // 우편번호 chk null
		if(valflag == false){
			vvo.setMsg("우편번호를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = isblank(avo.getMvo().getIm_address()); // 주소 chk null
		if(valflag == false){
			vvo.setMsg("주소를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getMvo().getIm_detailaddr()); // 상세주소 null chk
		if(valflag == false){
			vvo.setMsg("상세 주소를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getMvo().getIm_skill()); // 스킬 null chk
		if(valflag == false){
			vvo.setMsg("사용 스킬을 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		// 경력이 존재할 때
		if(avo.getChkCareer() > 0){
			valflag = arrayNullCheck(avo.getIme_regi_date()); // 경력 시작 null chk
			if(valflag == false){
				vvo.setMsg("경력 사항에 입력하지 않은 곳이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = arrayNullCheck(avo.getIme_exit_date()); // 경력 종료 null chk
			if(valflag == false){
				vvo.setMsg("경력 사항에 입력하지 않은 곳이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = arrayNullCheck(avo.getIme_coname()); // 경력 회사 null chk
			if(valflag == false){
				vvo.setMsg("경력 사항에 입력하지 않은 곳이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = arrayNullCheck(avo.getIme_auth()); // 경력 직급 null chk
			if(valflag == false){
				vvo.setMsg("경력 사항에 입력하지 않은 곳이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = arrayNullCheck(avo.getIme_roll()); // 경력 역할 null chk
			if(valflag == false){
				vvo.setMsg("경력 사항에 입력하지 않은 곳이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = dateCompareCurr(avo.getIme_regi_date()); //경력 시작 현재 비교
			if(valflag == false){
				vvo.setMsg("경력 기간은 현재날짜 보다 이후 일 수 없습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = dateCompareCurr(avo.getIme_exit_date()); //경력 종료 현재 비교
			if(valflag == false){
				vvo.setMsg("경력 기간은 현재날짜 보다 이후 일 수 없습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = dateCompartFromTo(avo.getIme_regi_date(), avo.getIme_exit_date()); // 경력 시작일 종료일 비교
			if(valflag == false){
				vvo.setMsg("경력 종료 기간이 시작 기간보다 이전 일 수 없습니다. ");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = duplChkDate(avo.getIme_regi_date(), avo.getIme_exit_date()); // 경력 기간 중복 체크
			if(valflag == false){
				vvo.setMsg("중복된 경력 기간이 존재합니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
		}
		
		//라이센스 존재할 때
		if(avo.getChkLicense() > 0){
			valflag = arrayNullCheck(avo.getIml_lname()); // 자격증명 null chk
			if(valflag == false){
				vvo.setMsg("자격증란에 입력 하지 않은 값이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			valflag = arrayNullCheck(avo.getIml_acqdate()); // 자격증 취득일 null chk
			if(valflag == false){
				vvo.setMsg("자격증란에 입력 하지 않은 값이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			valflag = arrayNullCheck(avo.getIml_organization()); // 자격증명 발급기관 null chk
			if(valflag == false){
				vvo.setMsg("자격증란에 입력 하지 않은 값이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = dateCompareCurr(avo.getIml_acqdate()); //취득일 현재날짜 비교
			if(valflag == false){
				vvo.setMsg("자격증 취득일은 현재 날짜 보다 이후 일 수 없습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
		}
		vvo.setResultfalg(valflag);
		return vvo;
		
	}
	
	public ValidationVO updateWorkerAction(AddWorkerVO avo) throws Exception{
		

		boolean valflag = true;
		ValidationVO vvo = new ValidationVO();
		WorkerService service = new WorkerService();
		
		//1.null check
		valflag = isblank(avo.getMvo().getIm_pw()); //pw null chk
		if(valflag == false){
			vvo.setMsg("패스워드를 입력해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getConfirmPw()); //pw confirm null chk
		if(valflag == false){
			vvo.setMsg("패스워드 확인을 입력해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = stringLengthCheck(avo.getMvo().getIm_pw()); // pw length
		if(valflag == false){
			vvo.setMsg("패스워드는 6자 이상 15자 이하로 정해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = equalStringCheck(avo.getMvo().getIm_pw(), avo.getConfirmPw()); // 비번 같은지 확인
		if(valflag == false){
			vvo.setMsg("비밀번호가 일치 하지 않습니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getMvo().getIm_name()); // name null check
		if(valflag == false){
			vvo.setMsg("이름을 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getSnumF()); // 민번 앞자리
		if(valflag == false){
			vvo.setMsg("주민등록번호를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getSnumE()); // 민번 뒷자리
		if(valflag == false){
			vvo.setMsg("주민등록번호를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = onlyInputNumber(avo.getSnumF()); // 민번 숫자만
		if(valflag == false){
			vvo.setMsg("주민등록번호는 숫자만 입력 가능합니다");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = onlyInputNumber(avo.getSnumE()); // 민번 숫자만
		if(valflag == false){
			vvo.setMsg("주민등록번호는 숫자만 입력 가능합니다");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
//		valflag = scnumValidation(avo.getSnumF(),avo.getSnumE()); //유효 주빈 번호 확인
//		if(valflag == false){
//			vvo.setMsg("유효한 주민등록번호가 아닙니다.");
//			vvo.setResultfalg(valflag);
//			return vvo;
//		}
		
		valflag = isblank(avo.getSphone()); // 전화번호 앞자리 null check
		if(valflag == false){
			vvo.setMsg("핸드폰 번호를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = isblank(avo.getMphone()); // 전화번호 가운데자리 null check
		if(valflag == false){
			vvo.setMsg("핸드폰 번호를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}		
		
		valflag = isblank(avo.getEphone()); // 전화번호 뒷자리 null check
		if(valflag == false){
			vvo.setMsg("핸드폰 번호를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}	
		
		valflag = onlyInputNumber(avo.getSphone()); // 전화번호 숫자만
		if(valflag == false){
			vvo.setMsg("전화 번호는 숫자만 입력 가능합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = onlyInputNumber(avo.getMphone()); // 전화번호 숫자만
		if(valflag == false){
			vvo.setMsg("전화 번호는 숫자만 입력 가능합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = onlyInputNumber(avo.getEphone()); // 전화번호 숫자만
		if(valflag == false){
			vvo.setMsg("전화 번호는 숫자만 입력 가능합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = phoneLengthThree(avo.getSphone()); // 전홤번호 자리수
		if(valflag == false){
			vvo.setMsg("전화번호를 정확하게 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = phoneLengthThreeOrFour(avo.getMphone());// 전홤번호 자리수
		if(valflag == false){
			vvo.setMsg("전화번호를 정확하게 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = phoneLengthThreeOrFour(avo.getEphone()); // 전홤번호 자리수
		if(valflag == false){
			vvo.setMsg("전화번호를 정확하게 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		//TODO 폰번호 중복은 여기!!
		avo.getMvo().setIm_phone(avo.getSphone()+"-"+avo.getMphone()+"-"+avo.getEphone()); // 폰번호 중복
		valflag = service.updateChkDuplPhone(avo.getMvo());
		if(valflag == false){
			vvo.setMsg("이미 등록된 전화번호 입니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getMvo().getIm_email()); // 이메일 null chk
		if(valflag == false){
			vvo.setMsg("이메일 주소를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isMailFormCheck(avo.getMvo().getIm_email()); // 이메일 폼체크
		if(valflag == false){
			vvo.setMsg("이메일 주소 형식이 아닙니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		//TODO update 이메일 중복은 여기
		valflag = service.updateChkDuplEmail(avo.getMvo()); // 이메일 중복
		if(valflag == false){
			vvo.setMsg("이미 가입 된 이메일 주소 입니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = isblank(avo.getOutsideperson()); // 타업체 null chk
		if(avo.getChkTa() == 1 && valflag == false){
			vvo.setMsg("타업체 인력인 경우 회사명을 반드시 입력 하셔야 합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = isblank(avo.getMvo().getIm_postcode()); // 우편번호 chk null
		if(valflag == false){
			vvo.setMsg("우편번호를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		valflag = isblank(avo.getMvo().getIm_address()); // 주소 chk null
		if(valflag == false){
			vvo.setMsg("주소를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getMvo().getIm_detailaddr()); // 상세주소 null chk
		if(valflag == false){
			vvo.setMsg("상세 주소를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(avo.getMvo().getIm_skill()); // 스킬 null chk
		if(valflag == false){
			vvo.setMsg("사용 스킬을 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		// 경력이 존재할 때
		if(avo.getChkCareer() > 0){
			valflag = arrayNullCheck(avo.getIme_regi_date()); // 경력 시작 null chk
			if(valflag == false){
				vvo.setMsg("경력 사항에 입력하지 않은 곳이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = arrayNullCheck(avo.getIme_exit_date()); // 경력 종료 null chk
			if(valflag == false){
				vvo.setMsg("경력 사항에 입력하지 않은 곳이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = arrayNullCheck(avo.getIme_coname()); // 경력 회사 null chk
			if(valflag == false){
				vvo.setMsg("경력 사항에 입력하지 않은 곳이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = arrayNullCheck(avo.getIme_auth()); // 경력 직급 null chk
			if(valflag == false){
				vvo.setMsg("경력 사항에 입력하지 않은 곳이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = arrayNullCheck(avo.getIme_roll()); // 경력 역할 null chk
			if(valflag == false){
				vvo.setMsg("경력 사항에 입력하지 않은 곳이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = dateCompareCurr(avo.getIme_regi_date()); //경력 시작 현재 비교
			if(valflag == false){
				vvo.setMsg("경력 기간은 현재날짜 보다 이후 일 수 없습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = dateCompareCurr(avo.getIme_exit_date()); //경력 종료 현재 비교
			if(valflag == false){
				vvo.setMsg("경력 기간은 현재날짜 보다 이후 일 수 없습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = dateCompartFromTo(avo.getIme_regi_date(), avo.getIme_exit_date()); // 경력 시작일 종료일 비교
			if(valflag == false){
				vvo.setMsg("경력 종료 기간이 시작 기간보다 이전 일 수 없습니다. ");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = duplChkDate(avo.getIme_regi_date(), avo.getIme_exit_date()); // 경력 기간 중복 체크
			if(valflag == false){
				vvo.setMsg("중복된 경력 기간이 존재합니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
		}
		
		//라이센스 존재할 때
		if(avo.getChkLicense() > 0){
			valflag = arrayNullCheck(avo.getIml_lname()); // 자격증명 null chk
			if(valflag == false){
				vvo.setMsg("자격증란에 입력 하지 않은 값이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			valflag = arrayNullCheck(avo.getIml_acqdate()); // 자격증 취득일 null chk
			if(valflag == false){
				vvo.setMsg("자격증란에 입력 하지 않은 값이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			valflag = arrayNullCheck(avo.getIml_organization()); // 자격증명 발급기관 null chk
			if(valflag == false){
				vvo.setMsg("자격증란에 입력 하지 않은 값이 있습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
			valflag = dateCompareCurr(avo.getIml_acqdate()); //취득일 현재날짜 비교
			if(valflag == false){
				vvo.setMsg("자격증 취득일은 현재 날짜 보다 이후 일 수 없습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
			
		}
		vvo.setResultfalg(valflag);
		return vvo;
		
	}
	
	public ValidationVO addProjectValidation(ProjectVO pvo){
		
		boolean valflag = true;
		ValidationVO vvo = new ValidationVO();
		
		valflag = isblank(pvo.getIpl_pname()); // 프로젝트 명 null check
		if(valflag == false){
			vvo.setMsg("프로젝트 제목을 입력 해 주세요."); 
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(pvo.getIpl_client()); // 고객사명 null check
		if(valflag ==false){
			vvo.setMsg("고객사를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(pvo.getIpl_skill()); // skill null check
		if(valflag ==false){
			vvo.setMsg("프로젝트에 사용되는 스킬을 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(pvo.getIpl_postcode()); // post code null check
		if(valflag == false){
			vvo.setMsg("서비스 지역을 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(pvo.getIpl_address()); // address null check
		if(valflag ==false){
			vvo.setMsg("서비스 지역을 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(pvo.getIpl_detailaddr()); //detail address null check
		if(valflag == false){
			vvo.setMsg("상세주소를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(pvo.getIpl_charge()); // charge null check
		if(valflag ==false){
			vvo.setMsg("담당자를 입력 해 주세요.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		
		valflag = isblank(pvo.getIpl_sdate()); // start date null check
		if(valflag == false){
			vvo.setMsg("프로젝트 시작일은 반드시 입력하셔야합니다.");
			vvo.setResultfalg(valflag);
			return vvo;
		}
		if(pvo.getIpl_eptdate() != null && !("").equals(pvo.getIpl_eptdate()) ){
			
			String tempsdate[] = new String[1];
			String tempedate[] = new String[1];
			tempsdate[0] = pvo.getIpl_sdate();
			tempedate[0] = pvo.getIpl_eptdate();
			valflag = dateCompartFromTo(tempsdate, tempedate);
			if(valflag == false){
				vvo.setMsg("프로젝트 예상 종료일은 시작일 보다 빠를 수 없습니다.");
				vvo.setResultfalg(valflag);
				return vvo;
			}
		}
		
		
		vvo.setResultfalg(valflag);
		return vvo;
		
	}
}
