package kr.co.ican.service.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.ican.dao.MemberDAO;
import kr.co.ican.dbconn.GetDBConn;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemberVO;



public class LoginServiceImpl implements LoginServices {
	
	@Override
	public MemberVO checkMember(MemberVO mvo) throws SQLException{
		
	    Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberVO vo = new MemberVO();
		MemberDAO mdao = new MemberDAO();
		
		conn = GetDBConn.getConnection();
		
		//dao
		vo = mdao.loginCheck(conn, psmt , rs, mvo);
		GetDBConn.close(conn, psmt, rs);
		
		return vo;
	}

	@Override
	public MemberVO findId(MemberVO mvo) throws SQLException {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberVO vo = new MemberVO();
		MemberDAO mdao = new MemberDAO();
		conn = GetDBConn.getConnection();
		
		//dao
		vo = mdao.findId(conn, psmt, rs, mvo);
		GetDBConn.close(conn, psmt, rs);
		
		return vo;
	}

	@Override
	public MemberVO findPw(MemberVO mvo) throws SQLException {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberVO vo = new MemberVO();
		MemberDAO mdao = new MemberDAO();
		conn = GetDBConn.getConnection();
		
		//dao
		vo = mdao.findPw(conn, psmt, rs, mvo);
		GetDBConn.close(conn, psmt, rs);
		
		return vo;
	}

	@Override
	public ExperienceVO getMemberStartDate(MemberVO mvo) throws SQLException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		ExperienceVO evo = new ExperienceVO();
		MemberDAO mdao = new MemberDAO();
		conn = GetDBConn.getConnection();
		
		//dao
		evo = mdao.getMemberStartDate(conn, psmt, rs, mvo);
		GetDBConn.close(conn, psmt, rs);
		
		return evo;
	}
	
	 
	
}
