<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
</head>
<body>
<c:import url = "/base/header.jsp"></c:import>
<form action="board?cmd=boardWriteProc" method="POST">
	<div class="container">
	<input type="text" class="form-control form-control" name="title" placeholder="제목을 입력해 주세요." /><br />
	<textarea id="summernote" name="content" /></textarea>
	<input type="hidden" name="id" value="<%= session.getAttribute("id") %>" /><br />
	<input type="submit" class="btn btn-primary" value="글쓰기" /> 
	</div>     
</form>
    <script>
      $('#summernote').summernote({
        placeholder: '내용을 입력해 주세요.',
        shortcuts: false,
        disableDragAndDrop: true,
        tabsize: 5,
        height: 500
      });
      $('.dropdown-toggle').dropdown();
	</script>
<c:import url = "/base/footer.jsp"></c:import>
</body>
</html>