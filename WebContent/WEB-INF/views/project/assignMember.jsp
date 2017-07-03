<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<fmt:requestEncoding value ="utf-8"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--bootstrap  -->
<jsp:include page="../layout/bootstrap.jsp"/> 
<title>Insert title here</title>
</head>
<body>
<header>
  <h1>더보기(MORE) 버튼을 눌러 일정갯수의 리스트 더 보기</h1>
</header>
<div id="contents">
  <div id="js-load" class="main">
    <ul class="lists">
      <li class="lists__item js-load">test1</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test2</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test3</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test4</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test5</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test6</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test7</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test8</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test9</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test10</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test11</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test12</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test13</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test14</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test15</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test16</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test17</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test18</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test19</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test20</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test21</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test22</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test23</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test24</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
      <li class="lists__item js-load">test25</li>
      <li class="lists__item js-load">txttxttxttxttxttxt</li>
    </ul>
    <div id="js-btn-wrap" class="btn-wrap"> <a href="javascript:;" class="button">더보기</a> </div>
  </div>
  <footer>
    <p>(C) <a href="https://nanati.me/">nanati.me</a> by nanati</p>
  </footer>
</div>
</body>
<script type="text/javascript">
$(window).on('load', function () {
    load('#js-load', '4');
    $("#js-btn-wrap .button").on("click", function () {
        load('#js-load', '4', '#js-btn-wrap');
    })
});
 
function load(id, cnt, btn) {
    var girls_list = id + " .js-load:not(.active)";
    var girls_length = $(girls_list).length;
    var girls_total_cnt;
    if (cnt < girls_length) {
        girls_total_cnt = cnt;
    } else {
        girls_total_cnt = girls_length;
        $(button).hide();
    }
    $(girls_list + ":lt(" + girls_total_cnt + ")").addClass("active");
}
</script>
</html>