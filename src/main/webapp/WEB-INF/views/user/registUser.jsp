<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>user</title>

<!-- Bootstrap core CSS -->
<%@include file="/WEB-INF/views/common/common_lib.jsp"%>
<link href="${cp}/css/dashboard.css" rel="stylesheet">
<link href="${cp}/css/blog.css" rel="stylesheet">

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	
	<% UserVo userVo = (UserVo)(request.getAttribute("userVo")); %>
	//RegistUser.java여기에서 req.setAttribute("userVo", userVo); 이렇게 setAttribute해줬으니까 getAttribute해주는거지
	
	$(function(){
		<% if(userVo != null){ %>
			
			var user_id = "<%=userVo.getUserid()%>"
			var user_nm = "<%=userVo.getUsernm()%>"
			var user_pass = "<%=userVo.getPass()%>"
			var user_alias = "<%=userVo.getAlias()%>"
			var user_addr1 = "<%=userVo.getAddr1()%>"
			var user_addr2 = "<%=userVo.getAddr2()%>"
			var user_zipcode = "<%=userVo.getZipcode()%>"
		
			$("#userid").val(user_id);
			$("#userNm").val(user_nm);
			$("#userAlias").val(user_pass);
			$("#pass").val(user_pass);
			$("#addr1").val(user_addr1);
			$("#addr2").val(user_addr2);
			$("#zipcode").val(user_zipcode);
			
		<%}%>			
	})
	
	
	$(function(){
		//주소 검색 버튼이 클릭 되었을 때 다음 주소 api 팝업을 연다.
		$("#addrBtn").on("click", function(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		            console.log(data);
		            
		            $("#addr1").val(data.roadAddress); //도로주소 
		            $("#zipcode").val(data.zonecode);  //우편번호
		            
		            //사용자 편의성을 위해 상세주소 입력 input태그로 focus설정
		            $("#addr2").focus(); //이러면 커서가 이리로 딱 간다
		        }
		    }).open();
		})
	})
	

</script>


</head>
<body>
<body>
<%@include file="/WEB-INF/views/common/header.jsp"%>
	
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
			<!-- left -->
			<%@include file="/WEB-INF/views/common/left.jsp"%>
			</div>
	</div>
	</div>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">JSP/SPRING</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Dashboard</a></li>
					<li><a href="#">Settings</a></li>
					<li><a href="#">Profile</a></li>
					<li><a href="#">Help</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			
				<form method="post" class="form-horizontal" role="form" action="/user/registUser" enctype="multipart/form-data">
					
					<!-- pageContext.getRequest.contextPath 이걸 EL을 쓰면 
					
					<%-- action="${cp} --%> 이거를 
					
					action="${cp} 이렇게 바꿈. 
					그래서 web module에서 /를 /jsp로 바꿔줬을때 에러뜨지않고 알아서 잘 경로를 찾음.
					그말인 즉슨 잘 적용이 되었다는거! 
					
					-->
					
					<div class="form-group">
						<label for="userid" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
						
						
<%-- 						<% String userid = request.getParameter("userid");
							userid = userid == null ? "" : userid; %>
							
							이걸 했던 이유는, 등록폼이 진짜 처음 사용자 등록할때랑/ 사용자등록을 실패했을때 그 입력값을 불러오기 위해 쓰이는 두가지 용도로 사용되는데,
							널처리를 안해주고 value값에 그냥 바로 getParameter해버리면 최초 사용자 등록할때도 널값이 뜬다.
							그게 보기 싫으니까 위에 삼항연산자를 써서 널처리를 해결한건데
							그거 때문에 두줄이나 길어지니까 그게 보기 싫어서 EL을 쓰는거지. EL을 쓰는 이유를 다시한번 상기.
						
						
							
 --%>						<input type="text" class="form-control" id="userid" name="userid" placeholder="아이디를 입력하세요." /> 
 						
							
 						
 							<%-- value="<%=userid%> 이렇게 했었는데, 위에 제이쿼리로 해서 이거 안써도 됨
 								근데 el로도 표현 가능함. value="${param.userid}"	 --%> 
						</div>
						
						
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
							<input type="file" class="form-control" name="photo" />
						</div>
					
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
								<input type="text" class="form-control" id="userNm" name="usernm" placeholder="이름을 입력하세요.">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userAlias" name="alias" placeholder="별명을 입력하세요.">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pass" name="pass" placeholder="Password">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">등록일시</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="reg_dt" name="reg_dt" placeholder="2021.01.13">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" readonly>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">도로주소</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addr1" name="addr1" placeholder="주소" readonly>
						</div>
						<div class="col-sm-2">
							<button type="button" id="addrBtn" class="btn btn-default">주소 검색</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" id="regist_btn" class="btn btn-default">사용자 등록하기</button>
							<!-- 타입이 submit이면 폼태그를 전송하는거.
							그래서 폼태그에 method를 post방식으로 적어주고 그 폼태그가 가는 서블릿에 가서 
							포스트 방식에다가 로직을 적어주면 되는거지 -->
						</div>
					</div>
					
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>