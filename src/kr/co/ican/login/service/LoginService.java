package kr.co.ican.login.service;

import java.sql.Connection;
import java.sql.SQLException;
import kr.co.ican.login.dao.MemberDAO;
import kr.co.ican.util.GetDBConn;
import kr.co.ican.worker.dao.WorkerDAO;
import kr.co.ican.worker.vo.MemberVO;


// 로그인 관련 service
public class LoginService {
	
	// login check
	public MemberVO checkMember(MemberVO mvo)throws Exception{
	    Connection conn = null;
		MemberVO vo = new MemberVO();
		MemberDAO mdao = new MemberDAO();
		
		try {
			
			conn = GetDBConn.getConnection();
			//dao
			vo = mdao.loginCheck(conn, mvo);
			
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
		
		return vo;
	}
	
	// 아이디 찾기
	public MemberVO findId(MemberVO mvo) throws Exception{
		
		Connection conn = null;
		
		MemberVO vo = new MemberVO();
		MemberDAO mdao = new MemberDAO();
		try {
			conn = GetDBConn.getConnection();
			//dao
			vo = mdao.findId(conn,mvo);
		} catch (Exception e) {
			
			e.printStackTrace();
			vo = null;
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
		
		return vo;
	}

	// pw 찾기
	public MemberVO findPw(MemberVO mvo) throws Exception{
		
		Connection conn = null;
		
		MemberVO vo = new MemberVO();
		MemberDAO mdao = new MemberDAO();
		try {
			conn = GetDBConn.getConnection();
			//dao
			vo = mdao.findPw(conn , mvo);
		} catch (Exception e) {
			
			e.printStackTrace();
			vo = null;
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
		
		return vo;
	}
	
	//입사일 받아오기
	public String getRegiDate(MemberVO mvo) throws Exception{
		Connection conn = null;
		WorkerDAO wdao = new WorkerDAO();
		
		String resultstr = "";
		try {
			conn = GetDBConn.getConnection();
			resultstr = wdao.getRegiDate(conn, mvo);
		} catch (Exception e) {
			e.printStackTrace();
			mvo = null;
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
	
}
