package kr.co.ican.worker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kr.co.ican.worker.vo.ExperienceVO;
import kr.co.ican.worker.vo.MemLicenseVO;
import kr.co.ican.worker.vo.MemberVO;

// 사원  관련  DAO
public class WorkerDAO {
	
	
	public List<MemberVO> getWorkerList(Connection conn, MemberVO mvo) throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;
        List<MemberVO> list = new ArrayList<MemberVO>();
        int cnt = 1;
       	String sql = "";
       	try {
       		// 첫 시작 & 전체 검색 
           	sql = " SELECT IM_IDX, IM_NAME, IM_DNAME, IM_PHONE , IM_AUTH, IM_STATUS , YEAR#, MONTH# "
           		+ " FROM( "
           		+ "       SELECT "
           		+ "              ROW_NUMBER() OVER (ORDER BY IM_IDX) AS RNUM, "
           		+ "              IM_IDX, "
           		+ "              IM_NAME, "
           		+ "              IM_DNAME, "
           		+ "              IM_PHONE, "
           		+ "              IM_AUTH, "
           		+ "              IM_STATUS, "
           		+ "              TRUNC(DATETERM / 12) AS YEAR#, "
           		+ "              TRUNC(MONTHS_BETWEEN (SYSDATE, ADD_MONTHS (MINDATE, 12 * TRUNC (DATETERM / 12)))) AS MONTH#"
           		+ "  	  FROM "
           		+ "             ICAN_MEMBER IM LEFT JOIN ("
           		+ "                                       SELECT "
           		+ "                                               IME_IM_IDX,"
           		+ "                                               MIN(IME_REGI_DATE) AS MINDATE, "
           		+ "                                               MONTHS_BETWEEN (SYSDATE, MIN(IME_REGI_DATE)) AS DATETERM "
           		+ "                                       FROM ICAN_MEM_EXP group by IME_IM_IDX "
           		+ "                                       ) IME"
           		+ "              ON IM.IM_IDX = IME.IME_IM_IDX "
           		+ "       WHERE "
           		+ "               IM_RESIGN = 0 "
           		+ "       )"
           		+ " WHERE RNUM BETWEEN ? AND ? ";
           	
        	psmt = conn.prepareStatement(sql);
           	psmt.setInt(cnt++, mvo.getStart());
           	psmt.setInt(cnt++, mvo.getEnd());
     
           	rs = psmt.executeQuery();
      	   
    	    while (rs.next()) {
    	   		cnt = 1;
    	       	MemberVO vo = new MemberVO();
    	       	vo.setIm_idx(rs.getInt(cnt++));
    	       	vo.setIm_name(rs.getString(cnt++));
    	       	vo.setIm_dname(rs.getString(cnt++));
    	       	vo.setIm_phone(rs.getString(cnt++));
    	       	vo.setIm_auth(rs.getInt(cnt++));
    	       	vo.setIm_status(rs.getInt(cnt++));
    	       	vo.setExpYear(rs.getInt(cnt++));
    	       	vo.setExpMonth(rs.getInt(cnt++));
    	       	
    			list.add(vo);
    	     }
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
	    
        return list;
	}

	public int getWorkerCount(Connection conn) throws Exception {
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
	    int result= 0;
	    String sql = "";
	    
	    try {
	    	
	    	sql = " SELECT NVL(COUNT(*), 0) AS CNT FROM ICAN_MEMBER WHERE IM_RESIGN = 0 ";
	 		
	 	    psmt = conn.prepareStatement(sql);
	 		
	 		rs = psmt.executeQuery();
	 		
	 		while (rs.next()) {
	 	   		 
	 			result = rs.getInt(1);
	 		}
	 		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
	    return result;
	}
	
	// 휴대폰 중복찾기
	public boolean chkDuplPhone(MemberVO mvo, Connection conn) throws Exception{
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int cnt = 1;
		int result = 0;
		try {

			String sql = "  SELECT NVL(COUNT(*), 0) FROM ICAN_MEMBER WHERE IM_PHONE = ?  ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, mvo.getIm_phone());
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				cnt = 1;
				result = rs.getInt(cnt++);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
		
		return result > 0 ? false : true;
	}
	
	// update 휴대폰 중복찾기
	public boolean updateChkDuplPhone(MemberVO mvo, Connection conn) throws Exception{
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int cnt = 1;
		int result = 0;
		try {
			
			String sql = "  SELECT NVL(COUNT(*), 0) FROM (SELECT IM_PHONE FROM ICAN_MEMBER WHERE IM_IDX != ? ) WHERE IM_PHONE = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_idx());
			psmt.setString(cnt++, mvo.getIm_email());
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				cnt = 1;
				result = rs.getInt(cnt++);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
		
		return result > 0 ? false : true;
	}
	// 이메일 중복찾기
	public boolean chkDuplEmail(MemberVO mvo, Connection conn) throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int cnt = 1;
		int result = 0;
		try {

			String sql = "  SELECT NVL(COUNT(*), 0) FROM ICAN_MEMBER WHERE IM_EMAIL = ?  ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, mvo.getIm_email());
			
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				cnt = 1;
				result = rs.getInt(cnt++);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
		return result > 0 ? false : true;
	}
	// 이메일 중복찾기
	public boolean updateChkDuplEmail(MemberVO mvo, Connection conn) throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int cnt = 1;
		int result = 0;
		
		try {
			String sql = "  SELECT NVL(COUNT(*), 0) FROM (SELECT IM_EMAIL FROM ICAN_MEMBER WHERE IM_IDX != ? ) WHERE  IM_EMAIL = ?  ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_idx());
			psmt.setString(cnt++, mvo.getIm_email());
			
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				cnt = 1;
				result = rs.getInt(cnt++);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
	
		return result > 0 ? false : true;
	}
	// 주민등록번호 중복찾기
	public boolean chkDuplScnum(MemberVO mvo, Connection conn) throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;
		int cnt = 1;
		
		try {
			String sql = "  SELECT NVL(COUNT(*), 0) FROM ICAN_MEMBER WHERE IM_SCNUM = ?  ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, mvo.getIm_scnum());
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				cnt = 1;
				result = rs.getInt(cnt++);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
		
		return result > 0 ? false : true;
	}
	// 사원 기본정보 insert
	public boolean addWorkerInfo(MemberVO mvo , Connection conn)throws Exception { // 사원 기본정보 넣기
        
		PreparedStatement psmt = null;
		String sql = "";
        int cnt = 1;
        int result = 0;
        
        try {
        	
        	sql = " INSERT INTO "
					+ "		ICAN_MEMBER("
					+ "					IM_IDX, IM_PW, IM_DNAME, IM_NAME, IM_PHONE, IM_EMAIL, IM_RESIGN, IM_STATUS, IM_SCNUM, IM_ADDRESS, "
					+ "					IM_DETAILADDR, IM_POSTCODE, IM_AUTH, IM_SKILL"
					+ "					) "
				 +" VALUES(MEMBER_SEQ.NEXTVAL, ? , ? , ? , ? , ? , 0 , 0 , ? , ? , ? , ? , ? , ?)";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(cnt++, mvo.getIm_pw()); // 패스워드
			psmt.setString(cnt++, mvo.getIm_dname()); // 부서이름
			psmt.setString(cnt++, mvo.getIm_name()); //이름
			psmt.setString(cnt++, mvo.getIm_phone()); //전화번호
			psmt.setString(cnt++, mvo.getIm_email()); //이메일
			psmt.setString(cnt++, mvo.getIm_scnum()); //주번
			psmt.setString(cnt++, mvo.getIm_address()); //집주소
			psmt.setString(cnt++, mvo.getIm_detailaddr());
			psmt.setString(cnt++, mvo.getIm_postcode());
			psmt.setInt(cnt++, mvo.getIm_auth()); // 직급
			psmt.setString(cnt++, mvo.getIm_skill()); //스킬
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
			
		return result > 0 ? true : false;
	}
	// 기본 경력 insert
	public boolean basicWorkerExp(MemberVO mvo, Connection conn) throws Exception{
		
		PreparedStatement psmt = null;
		String sql = "";
        int cnt = 1;
        int result = 0;
        try {
        	if("타업체인력".equals(mvo.getIm_dname())){
    			sql = " INSERT INTO "
    					+ "			ICAN_MEM_EXP ( "
    					+ "							IME_IM_IDX, IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL"
    					+ "						 ) "
    					+ " VALUES(MEMBER_SEQ.CURRVAL , SYSDATE, NULL, ? , ? , '미배정') ";
    			
    			psmt = conn.prepareStatement(sql);
    			psmt.setString(cnt++, mvo.getOutsideperson());
    			psmt.setInt(cnt++, mvo.getIm_auth());
    		}else{
    			sql = " INSERT INTO "
    					+ "			ICAN_MEM_EXP ( "
    					+ "							IME_IM_IDX, IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL"
    					+ "						 ) "
    					+ " VALUES(MEMBER_SEQ.CURRVAL , SYSDATE, NULL, '아이캔매니지먼트(주)', ? , '미배정') ";

    			psmt = conn.prepareStatement(sql);
    			psmt.setInt(cnt++, mvo.getIm_auth());
    		}
    		
    		result = psmt.executeUpdate();
    		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}

		return result > 0 ? true : false;
	}
	// 경력 insert
	public boolean addWorkerExp(ExperienceVO evo, Connection conn) throws Exception{
		
		PreparedStatement psmt = null;
		String sql = "";
        int cnt = 1;
        int result = 0;
        
        try {
        	sql = " INSERT INTO "
    				+ "			ICAN_MEM_EXP ( "
    				+ "							IME_IM_IDX, IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL"
    				+ "						 ) "
    			+ " VALUES ( MEMBER_SEQ.CURRVAL , ? , ? , ?, ? , ?) ";

    		psmt = conn.prepareStatement(sql);
    		psmt.setString(cnt++, evo.getIme_regi_date());
    		psmt.setString(cnt++, evo.getIme_exit_date());
    		psmt.setString(cnt++, evo.getIme_coname());
    		psmt.setInt(cnt++, evo.getIme_auth());
    		psmt.setString(cnt++, evo.getIme_roll());
    		
    		result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
			
		return result > 0 ? true : false;
	}
	
	// 자격증 insert
	public boolean addWorkerLicense(MemLicenseVO mlvo, Connection conn) throws Exception{
		
		PreparedStatement psmt = null;
		String sql = "";
		int cnt = 1;
		int result = 0;
		try {
			sql = " INSERT INTO "
					+ "			ICAN_MEM_LICENSE ( "
					+ "								IML_IM_IDX, IML_LNAME , IML_ACQDATE, IML_ORGANIZATION "
					+ "							 ) "
					+ " VALUES( MEMBER_SEQ.CURRVAL , ? , ? , ?) ";

			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, mlvo.getIml_lname());
			psmt.setString(cnt++, mlvo.getIml_acqdate());
			psmt.setString(cnt++, mlvo.getIml_organization());

			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}

		return result > 0 ? true : false;
	}
	
	
	// 사원 상세정보 가져오기
	public MemberVO getMemberDetail(Connection conn, int im_idx)throws Exception {
		
		PreparedStatement psmt = null;
        ResultSet rs = null;
        MemberVO mvo = null;
        String sql = "";
        int cnt = 1;
        try {
        	sql = " SELECT "
        			+ " 	IM_IDX, IM_PW, IM_DNAME, IM_NAME, IM_PHONE, IM_EMAIL, IM_STATUS, IM_SCNUM, IM_ADDRESS, "
        			+ " 	IM_DETAILADDR,IM_POSTCODE, IM_AUTH, IM_SKILL "
        			+ " FROM "
        			+ " 	ICAN_MEMBER WHERE IM_IDX = ? ";
        		psmt = conn.prepareStatement(sql);
        		psmt.setInt(1, im_idx);
        		
        		rs = psmt.executeQuery();
        		
        		while (rs.next()) {
        			mvo = new MemberVO();
        			cnt = 1;
        			mvo.setIm_idx(rs.getInt(cnt++));
        			mvo.setIm_pw(rs.getString(cnt++));
        			mvo.setIm_dname(rs.getString(cnt++));
        			mvo.setIm_name(rs.getString(cnt++));
        			mvo.setIm_phone(rs.getString(cnt++));
        			mvo.setIm_email(rs.getString(cnt++));
        			mvo.setIm_status(rs.getInt(cnt++));
        			mvo.setIm_scnum(rs.getString(cnt++));
        			mvo.setIm_address(rs.getString(cnt++));
        			mvo.setIm_detailaddr(rs.getString(cnt++));
        			mvo.setIm_postcode(rs.getString(cnt++));
        			mvo.setIm_auth(rs.getInt(cnt++));
        			mvo.setIm_skill(rs.getString(cnt++));
        			
        		}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			
			if(rs != null){
				rs.close();
			}
		}
        
		return mvo;
	}
	
	// 사원 라이센스 정보 가져오기
	public List<MemLicenseVO> getMemberLicenses(MemLicenseVO lvo , Connection conn) throws Exception{
		
		PreparedStatement psmt = null;
        ResultSet rs = null;
        int cnt = 1;
        String sql = "";
		List<MemLicenseVO> liclist = new ArrayList<MemLicenseVO>();
		try {
			sql = "  SELECT "
					+ "		IML_IM_IDX, IML_LNAME, IML_ACQDATE, IML_ORGANIZATION "
					+ " FROM "
					+ "		ICAN_MEM_LICENSE "
					+ " WHERE "
					+ "		IML_IM_IDX = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, lvo.getIml_im_idx());

			rs = psmt.executeQuery();

			while (rs.next()) {
				cnt = 1;
				MemLicenseVO mlvo = new MemLicenseVO();
				mlvo.setIml_im_idx(rs.getInt(cnt++));
				mlvo.setIml_lname(rs.getString(cnt++));
				mlvo.setIml_acqdate(rs.getString(cnt++));
				mlvo.setIml_organization(rs.getString(cnt++));

				liclist.add(mlvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
		return liclist;
	}
	
	//사원 경력 정보 가져오기
	public List<ExperienceVO> getMemberExperiences(ExperienceVO evo, Connection conn) throws Exception{
		PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        int cnt = 1;
        List<ExperienceVO> elist = new ArrayList<ExperienceVO>();
        try {
        	sql = " SELECT "
    				+ "		IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL "
    			+ " FROM ("
    			+ "			SELECT "
    			+ "					ROW_NUMBER() OVER (ORDER BY IME_REGI_DATE DESC) AS RNUM, IME_REGI_DATE, "
    			+ "					IME_EXIT_DATE, IME_CONAME, IME_AUTH , IME_ROLL "
    			+ "           FROM "
    			+ "					ICAN_MEM_EXP "
    			+ "       	  WHERE "
    			+ "					IME_IM_IDX = ? "
    			+ "				AND IME_EXIT_DATE IS NOT NULL "
    			+ "		 ) "
    			+ " WHERE RNUM BETWEEN ? AND ? ";
    		
    		psmt = conn.prepareStatement(sql);
    		psmt.setInt(cnt++, evo.getIme_im_idx());
    		psmt.setInt(cnt++, evo.getStart());
    		psmt.setInt(cnt++, evo.getEnd());
    		
    		rs = psmt.executeQuery();
    		
    		while (rs.next()) {
    			
    			cnt = 1;
    			ExperienceVO expvo = new ExperienceVO();
    			expvo.setIme_regi_date(rs.getString(cnt++));
    			expvo.setIme_exit_date(rs.getString(cnt++));
    			expvo.setIme_coname(rs.getString(cnt++));
    			expvo.setIme_auth(rs.getInt(cnt++));
    			expvo.setIme_roll(rs.getString(cnt++));
    			
    			elist.add(expvo);
    		}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
		return elist;
	}
	
	public int getWorkerExpCount(ExperienceVO evo , Connection conn ) throws Exception{
		PreparedStatement psmt = null;
        ResultSet rs = null;
        int result= 0;
        String sql = "";
        try {
        	 sql = " SELECT NVL(COUNT(*) , 0) AS CNT FROM ICAN_MEM_EXP WHERE IME_IM_IDX = ? ";
     		psmt = conn.prepareStatement(sql);
     		psmt.setInt(1, evo.getIme_im_idx());
     		
     		rs = psmt.executeQuery();
     		
     		while (rs.next()) {
     	   		 
     			result = rs.getInt(1);
     		}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			
			if(rs != null){
				rs.close();
			}
				
		}
        return result;
	}
	
	public int getWorkerLicCount(MemLicenseVO licvo, Connection conn ) throws Exception{
		PreparedStatement psmt = null;
        ResultSet rs = null;
        int result= 0;
        String sql = "";
        
        try {
        	 sql = " SELECT NVL(COUNT(*) , 0) AS CNT FROM ICAN_MEM_LICENSE WHERE IML_IM_IDX = ? ";
     		psmt = conn.prepareStatement(sql);
     		psmt.setInt(1, licvo.getIml_im_idx());
     		
     		rs = psmt.executeQuery();
     		
     		while (rs.next()) {
     	   		 
     			result = rs.getInt(1);
     		}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
	        
        return result;
	}
	
	// get Member Start Date
	public String getRegiDate(Connection conn, MemberVO mvo) throws Exception {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String resultstr = "";
		int cnt = 1;
		String sql = "";
		
		try {
			
			sql = " SELECT TRUNC(IME_REGI_DATE) FROM ICAN_MEM_EXP WHERE IME_IM_IDX = ? AND IME_EXIT_DATE IS NULL ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_idx());

			rs = psmt.executeQuery();

			while (rs.next()) {
				cnt = 1;

				resultstr = rs.getString(cnt++);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
		// return
		return resultstr;
	}
	
	public ExperienceVO getOutSidePersonCompany(int idx, Connection conn) throws Exception{
		
		ExperienceVO vo = new ExperienceVO();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int cnt = 1;
		String sql = "";
		
		try {
			sql = " SELECT IME_CONAME FROM ICAN_MEM_EXP WHERE IME_IM_IDX = ? AND IME_EXIT_DATE IS NULL ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, idx);
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				cnt = 1;
				vo.setIme_coname(rs.getString(cnt++));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
				
		return vo;
	}
	
	
	public boolean updateWorkerInfo(MemberVO mvo, Connection conn) throws Exception{
		PreparedStatement psmt = null;
		
		String sql = "";
        int cnt = 1;
        int result = 0;
        try {
			sql = " UPDATE ICAN_MEMBER SET IM_PW = ? , IM_DNAME = ? , IM_PHONE = ?, IM_EMAIL = ? ,IM_ADDRESS = ?, IM_DETAILADDR = ? ,"
				+ " IM_POSTCODE = ? , IM_AUTH = ? , IM_SKILL = ?  WHERE IM_IDX = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, mvo.getIm_pw());
			psmt.setString(cnt++, mvo.getIm_dname());
			psmt.setString(cnt++, mvo.getIm_phone());
			psmt.setString(cnt++, mvo.getIm_email());
			psmt.setString(cnt++, mvo.getIm_address());
			psmt.setString(cnt++, mvo.getIm_detailaddr());
			psmt.setString(cnt++, mvo.getIm_postcode());
			psmt.setInt(cnt++, mvo.getIm_auth());
			psmt.setString(cnt++, mvo.getIm_skill());
			psmt.setInt(cnt++, mvo.getIm_idx());
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
		return result > 0 ? true : false;
	}
	
	public boolean chkExistLicence(MemberVO mvo, Connection conn) throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;

		int result = 0;
		int cnt = 1;
		String sql = "";

		try {
			sql = "  SELECT NVL(COUNT(*), 0) FROM ICAN_MEM_LICENSE WHERE IML_IM_IDX = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_idx());

			rs = psmt.executeQuery();

			while (rs.next()) {
				cnt = 1;
				result = rs.getInt(cnt++);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
		
		return result > 0 ? true : false;
	}
	
	public boolean preupdateWorkerLicense(MemberVO mvo, Connection conn) throws Exception{
		PreparedStatement psmt = null;
		String sql = "";
        int cnt = 1;
        int result = 0;
        try {
        	
			sql = " DELETE FROM ICAN_MEM_LICENSE WHERE IML_IM_IDX = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_idx());
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
		return result > 0 ? true : false;
	}
	
	public boolean updateWorkerLicense(MemLicenseVO mlvo, Connection conn , MemberVO mvo) throws Exception {
		PreparedStatement psmt = null;
		String sql = "";
		int cnt = 1;
		int result = 0;
		try {
			sql = " INSERT INTO " 
				    + "ICAN_MEM_LICENSE ( "
				    + "						IML_IM_IDX, IML_LNAME ,"
					+ "						IML_ACQDATE, "
					+ "						IML_ORGANIZATION "
					+ "					) "
					+ " VALUES( ? , ? , ? , ?) ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_idx());
			psmt.setString(cnt++, mlvo.getIml_lname());
			psmt.setString(cnt++, mlvo.getIml_acqdate());
			psmt.setString(cnt++, mlvo.getIml_organization());

			result = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
		return result > 0 ? true : false;
	}
	
	
	public boolean chkExistExp(MemberVO mvo, Connection conn) throws Exception{
		PreparedStatement psmt = null;
        ResultSet rs = null;
        
        int result= 0;
        int cnt = 1;
        String sql = "";
        
        try {
        	sql = "  SELECT NVL(COUNT(*), 0) FROM ICAN_MEM_EXP WHERE IME_IM_IDX = ? AND IME_EXIT_DATE IS NOT NULL ";
        	psmt = conn.prepareStatement(sql);
        	psmt.setInt(cnt++, mvo.getIm_idx());
        	
        	rs =psmt.executeQuery();
        	
        	while (rs.next()) {
				cnt = 1;
        		result = rs.getInt(cnt++);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}
        
		return result > 0 ? true : false ;
	}
	
	public boolean preupdateWorkerExp(MemberVO mvo, Connection conn)throws Exception{
		PreparedStatement psmt = null;
		String sql = "";
        int cnt = 1;
        int result = 0;
        try {
			sql = " DELETE FROM ICAN_MEM_EXP WHERE IME_IM_IDX = ? AND IME_EXIT_DATE IS NOT NULL ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_idx());
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
        
		return result > 0 ? true : false;
	}
	
	public boolean updateWorkerExp(ExperienceVO evo, Connection conn, MemberVO mvo )throws Exception {
		
		PreparedStatement psmt = null;
		String sql = "";
        int cnt = 1;
        int result = 0;
        try {
        	
			sql = " INSERT INTO ICAN_MEM_EXP(IME_IM_IDX, IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL) "
				+ " VALUES(? , ? , ? , ?, ? , ? ) ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_idx());
			psmt.setString(cnt++, evo.getIme_regi_date());
			psmt.setString(cnt++, evo.getIme_exit_date());
			psmt.setString(cnt++, evo.getIme_coname());
			psmt.setInt(cnt++, evo.getIme_auth());
			psmt.setString(cnt++, evo.getIme_roll());
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(psmt != null){
				psmt.close();
			}
		}
        
		return result > 0 ? true : false;
	}
	
	public boolean updateWorkerConame(MemberVO mvo, Connection conn) throws Exception{
		PreparedStatement psmt = null;
		String sql = "";
		int cnt = 1;
		int result = 0;
		try {
			sql = " UPDATE "
				+ "			ICAN_MEM_EXP "
				+ " SET "
				+ "			IME_CONAME = ? "
				+ " WHERE "
				+ "			IME_IM_IDX = ? "
				+ "	AND "
				+ "			IME_EXIT_DATE IS NULL ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, mvo.getOutsideperson());
			psmt.setInt(cnt++, mvo.getIm_idx());
			
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
		
		return result > 0 ? true : false;
	}
	
	public boolean updateWorkerConameReturn(MemberVO mvo, Connection conn) throws Exception{
		PreparedStatement psmt = null;
		String sql = "";
		int cnt = 1;
		int result = 0;
		try {
			sql = " UPDATE "
				+ "			ICAN_MEM_EXP "
				+ " SET "
				+ "			IME_CONAME = ? "
				+ " WHERE "
				+ "			IME_IM_IDX = ? "
				+ "	AND "
				+ "			IME_EXIT_DATE IS NULL ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, "아이캔매니지먼트(주)");
			psmt.setInt(cnt++, mvo.getIm_idx());
			
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				psmt.close();
			}
		}
		
		return result > 0 ? true : false;
	}
	
	public String getProjectName(Connection conn, int idx) throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "";
		String result = "";
		int cnt = 1;
		try {
			sql = " SELECT "
					+ "			IPL_PNAME "
					+ " FROM "
					+ "			ICAN_PROJECT_LIST IPL "
					+ " LEFT JOIN  "
					+ "			ICAN_PROJECT_JOIN_LIST IPJL "
					+ "	ON "
					+ "		IPL.IPL_IDX = IPJL.IPJL_IPL_IDX "
					+ "	WHERE "
					+ "		IPJL.IPJL_IM_IDX = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, idx);
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				cnt = 1;
				result = rs.getString(cnt++);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public int chkParticipation(Connection conn, int idx)throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "";
		int cnt = 1;
		int result = 0;
		try {
			
			sql = " SELECT "
				+ " 		IPL.IPL_IDX "
				+ " FROM "
				+ " 		ICAN_PROJECT_LIST IPL "
				+ " INNER JOIN "
				+ "			ICAN_PROJECT_JOIN_LIST IPJL "
				+ " ON "
				+ "			IPL.IPL_IDX = IPJL.IPJL_IPL_IDX "
				+ " WHERE "
				+ "			IPJL.IPJL_IM_IDX = ? "
				+ " AND "
				+ "			IPL.IPL_EDATE IS NULL ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, idx);
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				cnt = 1;
				result = rs.getInt(cnt++);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = 1;
			throw e;
		}finally {
			if(psmt != null){
				try {
					psmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			if(rs != null){
				try {
					rs.close();
				} catch (Exception e2) {
					
				}
			}
		}
		
		return result;
		
	}
	
	public boolean doResignWorker(Connection conn, int idx) throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "";
		int cnt = 1;
		int result = 0;
		try {
			
			sql = " UPDATE ICAN_MEMBER SET IM_RESIGN = 1 WHERE IM_IDX = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, idx);
			
			result = psmt.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(psmt != null){
				try {
					psmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(rs != null){
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return result > 0 ? true : false;
	}
	
	public boolean setWorkersExitDate(Connection conn, int idx) throws Exception{
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "";
		int cnt = 1;
		int result = 0;
		try {
			
			sql = " UPDATE ICAN_MEM_EXP SET IME_EXIT_DATE = SYSDATE WHERE IME_IM_IDX = ? AND IME_EXIT_DATE IS NULL ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, idx);
			
			result = psmt.executeUpdate();
			System.out.println("setExitdate DAO result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(rs != null){
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			if(psmt != null){
				try {
					psmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return result > 0 ? true : false;
	}
}

