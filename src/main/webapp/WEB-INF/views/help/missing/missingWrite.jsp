<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="/lee/resources/bootstrapk/css/bootstrap.min.css">
<style type="text/css">
</style>

<script type="text/javascript"
	src="/lee/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="/lee/resources/bootstrapk/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<div class="row">
		<div class="col-md-3">
			<jsp:include page="/WEB-INF/views/help/helpSide.jsp"></jsp:include>
		</div>
		<div class="col-md-7">
			<h2>분실물게시판 쓰기</h2>
			<div class="row">
				<form name="missingWrite" action="missingWriteOk.ju">
					<table class="table table-striped table table-hover" border="1">
						<tr>
							<th style="text-align: center; vertical-align: middle;">제목</th>
							<td><input type="text" class="form-control"
								name="missing_subject" placeholder="제목을 입력하세요"></td>

						</tr>
						<tr>
							<th style="text-align: center; vertical-align: middle;">작성자</th>
							<td class="text-center">${sid}</td>
						</tr>
						<tr>
							<th style="text-align: center; vertical-align: middle;">카테고리</th>
							<td>
								<div class="radio">
									<label><input type="radio" name="missing_cate"
										value="0" checked> 잃어버림 </label> <label><input
										type="radio" name="missing_cate" value="1"> 발견했음 </label>
								</div>
							</td>

						</tr>

						<tr>
							<th style="text-align: center; vertical-align: middle;">내용</th>
							<td><textarea class="form-control" rows="10"
									name="missing_content" placeholder="내용을 입력하세요"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="form-group"></div>
								<button type="submit" class="btn btn-default">등록</button>
								<button type="reset" class="btn btn-default">재작성</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<hr>
		</div>
	</div>

</body>
</html>
