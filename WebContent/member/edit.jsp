<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/ksblog/member/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(userAddr,userAddrDetail){
		document.form.userAddr.value = userAddr;
		document.form.userAddrDetail.value = userAddrDetail;		
}
</script>
</head>
<body>
<c:import url = "/base/header.jsp"></c:import>
<form id="form" name="form" action="member?cmd=memberEditProc" method="POST" onsubmit="return validateJoin()">
<div class="container">
<table border="1">
	<tr>
		<td>아이디</td><td><input name="id" type="text" value="${member.id}" readOnly/></td>
	</tr>
	<tr>
		<td>비밀번호</td><td><input id="pw1" type="password" name="userPassword" value="" placeholder="비밀번호" required /></td>
	</tr>
	<tr>
		<td>비밀번호확인</td><td><input id="pw2" type="password" name="userPassword" placeholder="비밀번호확인"/></td>
	</tr>
	<tr>
		<td>도로명주소</td><td><input type="button" onClick="goPopup();" value="주소검색" />
		<div id="list"></div>
		<div id="callBackDiv">
		<input type="text"  style="width:500px;" id="userAddr"  name="userAddr" placeholder="도로명주소" value="${member.userAddr}" required /></td>
	</tr>
	<tr>
		<td>상세주소</td><td><input type="text"  style="width:500px;" id="userAddrDetail"  name="userAddrDetail" placeholder="고객 상세주소" value="${member.userAddrDetail}" required /></td>
		</div>
	</tr>
	<tr>
		<td>E-mail</td><td><input type="email" name="userEmail" placeholder="이메일" value="${member.userEmail}" required/></td>
	</tr>
	<tr>
		<td>전화번호</td><td><input type="text" name="userPhone" placeholder="전화번호" value="${member.userPhone}" required/></td>
	</tr>
	<tr>
		<td>성별</td>
		<td>
		남 : <input type="radio" name="userGender" value="남" checked />
		여 : <input type="radio" name="userGender" value="여" />
		</td>
	<tr/>
	<tr>
		<td>회원가입일자</td><td>${member.createDate}</td>
	</tr>
	<tr>
		<td>회원수정일자</td><td>${member.updateDate}</td>
	</tr>
	
</table>
<input class="btn btn-primary" type="submit" />
<a href="board?cmd=boardListPage" class="btn btn-primary">돌아가기</a>
</div>
</form>

<c:import url = "/base/footer.jsp"></c:import>
<script>
function validateJoin(){
	var pw1 = document.querySelector("#pw1");
	var pw2 = document.querySelector("#pw2");
	
	console.log(pw1.value);
	console.log(pw2.value);
	
	if(pw1.value === pw2.value) {
		return true;
	} else {
		pw1.value="";
		pw2.value="";
		pw1.focus();
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
}
</script>
</body>
</html>