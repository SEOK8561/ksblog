<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url = "/base/header.jsp"></c:import>
<%
	application.setAttribute("test", "어플리케이션");
%>
<div class="container">
  <h2>로그인 페이지</h2>
  <form action="member?cmd=memberLoginProc" method="POST">
    <div class="form-group">
      <label for="email">id:</label>
      <input type="text" class="form-control" placeholder="로그인" name="id">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" placeholder="패스워드" name="userPassword">
    </div>
    <div class="form-group form-check">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="idSave" value="on"> Remember me
      </label>
    </div>
    <button type="submit" class="btn btn-primary">로그인</button>
    <a href="board?cmd=boardListPage" class="btn btn-primary">돌아가기</a>
  </form>
</div>
<c:import url = "/base/footer.jsp"></c:import>
</body>
</html>