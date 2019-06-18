<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="/ksblog/css/styles.css">
<title>Insert title here</title>

</head>
<body>
<c:import url = "/base/header.jsp"></c:import>
<%
	pageContext.setAttribute("data", "안녕");
%>
<%-- session값 : ${sessionScope.id} <br />
request값 : ${requestScope.board.num} <br/>
page값 : ${pageScope.data} <br/>
application값 : ${applicationScope.test} <br/> --%>
<div class="container">
<table class="table" border="1">
	<thead class="thead-light">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>내용</th>
		<th>작성자아이디</th>
		<th>조회수</th>
		<th>작성일</th>
		<th>수정일</th>
	</tr>
	</thead>
	<thead class="thead-light">
	<tr>
		<td>${board.num}</td>
		<td>${board.title}</td>
		<td>${board.content}</td>
		<td>${board.id}</td>
		<td>${board.readCount}</td>
		<td>${board.createDate}</td>
		<td>${board.updateDate}</td>
	</tr>
	</thead>
	</table><br />
	<div class="input-group mb-3">
    	<input type="text" class="form-control" id="reply" placeholder="댓글을 입력하세요." cols="30"><br />
    	<button class="btn btn-primary" type="button" onclick="boardReplyWrite()">댓글</button>
  	</div>
	<div class="reply-box">
		<!--  for문이 돌아야 하는 곳  -->
		<div class="reply-item">
			<span class="reply-id">ssar</span>
			<span class="reply-createdate">2019-05-02 10:00:53</span>
			<div class="reply-delete">
			<i class="material-icons">clear</i>
			</div>
			<p class="reply-content">반갑습니다.</p>
		</div>
	</div>
<a href="board?cmd=boardDelete&num=${board.num}">삭제</a>
<a href="board?cmd=boardUpdate&num=${board.num}">수정</a>
<a href="board?cmd=boardListPage">돌아가기</a>
</div>
<c:import url = "/base/footer.jsp"></c:import>
<script>
		function boardReplyWrite(){
			//댓글
			let reply_el = document.querySelector("#reply");
			let content = reply_el.value;
			//아이디
			let id = "${sessionScope.id} ";
			//시간 2019-05-02 14:21:05
			let createDate = currentDate();
			//게시글 번호
			let boardNum = "${board.num}";
			//넘길 객체
			let replyDto = {
					"content": content,
					"id": id,
					"boardNum": boardNum
			};
			//서버구축되면 fetch()돌리기
			fetch("http://localhost:8000/ksblog/reply", {
				method:"POST",
				headers: {
					"Accept" : "text/plain",
					"Content-Type" : "application/json"
				},
				body : JSON.stringify(replyDto)
			}).then(function(res){
				return res.text();
			}).then(function(result){
				if(result == "ok"){
					let newDiv = document.createElement("div");
					newDiv.className = "reply-item";
					newDiv.innerHTML = '<span class="reply-id">'+id+'</span><span class="reply-createdate">'+createDate+'</span><div class="reply-delete"><i class="material-icons">clear</i></div><p class="reply-content">'+content+'</p>';
					document.querySelector(".reply-box").prepend(newDiv);
				}
			});
		}
	</script>
	<script src="/ksblog/js/util.js" ></script>
</body>
</html>