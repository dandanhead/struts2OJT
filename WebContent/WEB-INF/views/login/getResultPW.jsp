<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:requestEncoding value ="utf-8"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login Page</title>
<!--bootstrap  -->
<jsp:include page="../layout/bootstrap.jsp"/>
<style type="text/css">
div.layer{
	width: 50%;
	margin: auto;
}
</style>
</head>
<body>
<c:if test="${empty result}">
	<script type="text/javascript">
		alert("입력하신 정보가 잘못되었습니다. 다시 정확하게 입력해 주세요.");
		location.href = "goFindPW.do";
	</script>
</c:if>
<c:if test="${not empty result }">
		<br><br><br><br>
		<div class="layer">
			찾으시는 PW 는  <input type="text" class="form-control" width="10%" value="${result.im_pw }" readonly="readonly"> 입니다.
		</div>
</c:if>
</body>
</html>