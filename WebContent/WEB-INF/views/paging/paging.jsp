<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value ="utf-8"/>
<%
	int totalRecordCount; //전체 글의 갯수 == list.size()
	int pageNumber; 	  //페이지 번호
	int pageCountPerScreen; //화면에 보일 페이지 번호
	int recordCountPerPage; //한 페이지의 글 수  == 15개로 잡음
	int totalPageCount; //모든 페이지 수;
	int screenStartPageIndex; //화면의 글의 시작 번호
	int screenEndPageIndex; //화면의 글의 끝 번호
	
	String st1 = request.getParameter("totalRecordCount"); // 17
	
	if(st1 == null){
		totalRecordCount = 0;
	}else{
		totalRecordCount = Integer.parseInt(st1);
	}
	
	String st2 = request.getParameter("pageNumber"); // 0
	if(st2 == null){
		pageNumber = 0;
	}else{
		pageNumber = Integer.parseInt(st2);
	}
	
	String st3 = request.getParameter("pageCountPerScreen"); // 1~10
	if(st3 == null){
		pageCountPerScreen = 0;
	}else{
		pageCountPerScreen = Integer.parseInt(st3);
	}
	
	String st4 = request.getParameter("recordCountPerPage"); // 15
	if(st4 == null){
		recordCountPerPage = 0;
	}else{
		recordCountPerPage = Integer.parseInt(st4);
	}
	//총 페이지 수를 구한다
	totalPageCount = totalRecordCount / recordCountPerPage;
	
	//남은 글 수를 구한다
	if(totalRecordCount % recordCountPerPage != 0){
		totalPageCount++;
	}
	
	screenStartPageIndex = ((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen;
	screenEndPageIndex = (((pageNumber + 1)/pageCountPerScreen) * pageCountPerScreen) + pageCountPerScreen ;
	
	if(screenEndPageIndex > totalPageCount){
		
		screenEndPageIndex = totalPageCount;
		
	}
	if((pageNumber+1) % pageCountPerScreen == 0){
		
		screenStartPageIndex = (((pageNumber + 1)/pageCountPerScreen) * pageCountPerScreen) - pageCountPerScreen;
		screenEndPageIndex = pageNumber + 1;
	}
	
	%>
	
	
	<br>
 	<nav style="text-align: center;">
	  <ul class="pagination">
	  <%
	  	if((screenEndPageIndex - screenStartPageIndex) <=  1){
	  		%>
			    <li>
			      <a href="#none" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
		    </li>
	  		<%
	  	}else{
	  		%>
			    <li>
			      <a href="#none" aria-label="Previous" onclick="goPage(0)">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
	  		<%
	  	}
	  %>
<%-- 	    <c:forEach begin="${ screenStartPageIndex}" end="${ screenEndPageIndex}" var="idx" step="1">
	    	<li><a href="#none" onclick="goPage(${idx})">${idx+1}</a></li>
	    </c:forEach> --%>
	    <%
		for(int idx = screenStartPageIndex ; idx < screenEndPageIndex; idx++){
			if(idx == pageNumber){
				%>
				<li><a href="#none"><%=idx+1%></a></li>
				<%
			}else{
				%>
				<li><a href="#none" onclick="goPage(<%=idx%>)"><%=idx+1%></a></li>
				<%
			}
		}
    
		int end_page = 0;
		if(totalPageCount > 0){
			end_page = totalPageCount - 1;
		}
		if((screenEndPageIndex - screenStartPageIndex) <=  1){
			%>
			    <li>
			      <a href="#none" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			<%
		}else{
			%>
			    <li>
			      <a href="#none" aria-label="Next" onclick="goPage(<%=end_page%>)">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			<%
		}
    %>
	  </ul>
	</nav>
