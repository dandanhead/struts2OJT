<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${(empty login) or (isLogin == 0)}">

<%
	session.invalidate();
	
%>

<script>
	alert("로그아웃 되었습니다(doLogout).");
	self.location.href="start.do";
</script>
</c:if>