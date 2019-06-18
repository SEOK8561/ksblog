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
<div class="container">
<form id="form" name="form" action="member?cmd=memberJoinProc" method="POST" onsubmit="return validateJoin()">
	<input id="id" type="text" name="id" placeholder="아이디" required /> <input type="button" class="btn btn-primary" value="중복확인" onclick="validateDuplicateID()"><br />
	<input id="pw1" type="password" name="userPassword" placeholder="비밀번호" required/><br />
	<input id="pw2" type="password" placeholder="비밀번호 확인" required/><br />
	<div id="list"></div>
	<div id="callBackDiv">
	<table>
		<tr><td><input type="text" class="form-control" style="width:500px;" id="userAddr"  name="userAddr" placeholder="도로명주소" required readonly /></td><td><input type="button" class="btn btn-outline-primary" onClick="goPopup();" value="주소검색" /></td></tr>
		<tr><td><input type="text" class="form-control" style="width:500px;" id="userAddrDetail"  name="userAddrDetail" placeholder="고객 상세주소" required /></td></tr>
	</table>
	</div>
	<input type="email" name="userEmail" placeholder="이메일" required/><br />
	<input type="text" name="userPhone" placeholder="전화번호" required/><br />
	<div class="custom-control custom-radio custom-control-inline">
	<input type="radio" name="userGender" value="남" class="custom-control-input" id="customRadio" checked/>
	<label class="custom-control-label" for="customRadio">남</label>
	</div>
	<div class="custom-control custom-radio custom-control-inline">
	<input type="radio" name="userGender" value="여" class="custom-control-input" id="customRadio2" />
	<label class="custom-control-label" for="customRadio2">여</label>
	</div><br />
	<input type="submit" class="btn btn-primary" value="회원가입" />
	<a href="board?cmd=boardListPage" class="btn btn-light">돌아가기</a>
</form>
</div>
<p id="demo"></p>

<script>
let check = 1;
function loadAjax(id) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange=function() {
    if (this.readyState == 4 && this.status == 200) {
      	if(this.responseText=="ok"){
      		alert("중복된 아이디가 없습니다.");
      		check = 0;
      	} else{
      		alert("중복된 아이디가 있습니다.");
      		check = 1;
      	}
      	this.responseText;
    }
  };
  xhttp.open("GET", "rest?id="+id, true);
  xhttp.send();
}
</script>

<c:import url = "/base/footer.jsp"></c:import>
<script>
	function validateDuplicateID(){
		var id_element = document.querySelector("#id");
		var id = id_element.value;
		
		loadAjax(id);
	}
	function validateJoin(){
		if(check==1){
			alert('ID 중복확인을 해주세요.');
			return false;
		}
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