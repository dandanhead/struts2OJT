package kr.co.ican.service.login;

import java.sql.SQLException;

import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemberVO;

// login services
public interface LoginServices {
	// check member
	public MemberVO checkMember(MemberVO mvo) throws SQLException;
	// find id
	public MemberVO findId(MemberVO mvo) throws SQLException;
	// find pw
	public MemberVO findPw(MemberVO mvo) throws SQLException;
	// 입사일 가져오기
	public ExperienceVO getMemberStartDate(MemberVO mvo) throws SQLException;
}
