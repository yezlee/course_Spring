<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Signin Template for Bootstrap</title>

<%--
    <title>Signin Template for Bootstrap **</title>
    제목에 **이렇게 붙이고 실행함으로써 확인할수있어. 그리고 다시 **지우면 됨
    
    <!-- Bootstrap core CSS -->
    <link href="${cp}/css/bootstrap.min.css" rel="stylesheet"> 
	이 링크태그를 아래 include에 따라가면 있는 jsp파일에 넣어놓음
	그래서 주석처리    
--%>
	<%@ include file ="/WEB-INF/views/common/common_lib.jsp" %>
	<!-- common_lib.jsp의 내용을 지금 기술되는 부분에 코드를 복사해서 붙여넣기  -->
	
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
	<!-- https://github.com/js-cookie/js-cookie 요기서 가져온 스크립트-->
	
    <!-- Custom styles for this template -->
    <link href="${cp}/css/signin.css" rel="stylesheet">
    
    <script>
    	function getCookieValue(cookieStr, cookieName){
    		console.log("getCookieValue");	
    		
    		cookies = cookieStr.split("; ")
    		
    		for(var i=0; i<cookies.length; i++){
    			 cookiesarr = cookies[i].split("=");
    			if(cookiesarr[0] == cookieName) {
    				//객체비교는 equals로 한다!!! == 아니고!!!!
    				return cookiesarr[1];
    			}
    		}
    		
    	}
    	
    	//cookieName : 추가하고자 하는 쿠키 이름
    	//cookieValue : 쿠키의 값
    	//expires : 유효기간(일수)
    	function addCookie(cookieName, cookieValue, expires){
    		var dt = new Date(); // 지금 현재 날짜 ==> expires만큼 뒤의 미래 날짜로 변경
    		dt.setDate(dt.getDate() + parseInt(expires));
    		console.log(dt);
    		
    		document.cookie= cookieName + "=" + cookieValue + "; " + "path=/; expires=" + dt.toGMTString();
    	}
    	
    	function deleteCookie(cookieName){
    		addCookie(cookieName, "", -1);
    	}
    	
/*     	//brown
    	var cookieValue = getCookieValue(document.cookie, "userid");
    	console.log(cookieValue);
*/






		//html 문서 로딩이 완료되고 나서 실행되는 코드
		$(function(){
			
			<c:if test="${msg != null}">
				alert("${msg}" + "ra");
			</c:if>
			
			
			
		
			//userid, rememberme 쿠키를 확인하여 존재할 경우 값 설정, 체크
//			if($("#rememberme").is(":checked") == true){
			if(Cookies.get('rememberme') == "Y"){				
				$("#userid").val(Cookies.get('userid'))
				$("#rememberme").attr("checked", true);
			}
			
			
			//signin 아이디를 select
			$("#signin").on("click", function(){
				
				//rememberme 체크박스가 체크 되어있는지 확인
				//체크되어있을 경우
				if($("#rememberme").is(":checked") == true){
					//userid input에 있는 값을 userid쿠키로 저장
					Cookies.set("userid", $("#userid").val());

					//remeberme 쿠키로 Y값을 저장
					Cookies.set("rememberme", "Y");
				}	
				
				//체크 해제 되어있는 경우 : 더 이상 사용하지 않는 다는 의미이므로 userid, rememberme 쿠키 삭제
				else{
					Cookies.remove("userid");					
					Cookies.remove("rememberme");					
				}
				
				//form태그 이용하여 signin 요청
//				$("form태그select").submit();
				$("#frm").submit();
				
			});
		});
		
		
		
		
    </script>
    
  </head>

  <body>

    <div class="container">
		<%-- UNT_CD : ${param.UNT_CD} / <%=request.getParameter("UNT_CD")%> --%>
		<%-- cp : ${cp} / <%=application.getAttribute("cp") %> --%>
		
      <form class="form-signin" id="frm" action="${cp}/login/process" method="POST">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="userid" class="sr-only">userid</label>
        <input type="text" id="userid" name="userid" class="form-control" placeholder="Enter Id" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="pass" class="form-control" placeholder="Enter Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" id="rememberme" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" id="signin">Sign in</button>
      </form>

    </div> <!-- /container -->
   
  </body>
</html>
