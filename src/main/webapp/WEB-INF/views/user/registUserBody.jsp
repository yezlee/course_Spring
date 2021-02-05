<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    


<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	
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


	
		
				<form method="post" class="form-horizontal" role="form" action="/user/registUser" enctype="multipart/form-data">
					
					<!-- pageContext.getRequest.contextPath 이걸 EL을 쓰면 
					
					<%-- action="${cp} --%> 이거를 
					
					action="${cp} 이렇게 바꿈. 
					그래서 web module에서 /를 /jsp로 바꿔줬을때 에러뜨지않고 알아서 잘 경로를 찾음.
					그말인 즉슨 잘 적용이 되었다는거! 
					
					-->
					
					
					
					<div class="form-group">
						<label for="userid" class="col-sm-2 control-label"><spring:message code="USERID"/></label>
						<div class="col-sm-10">
						
						
						Spring Message : <spring:message code="LANG"/>
						Spring Message : <spring:message code="GREETING" arguments="brown"/>
						
<%-- 						<% String userid = request.getParameter("userid");
							userid = userid == null ? "" : userid; %>
							
							이걸 했던 이유는, 등록폼이 진짜 처음 사용자 등록할때랑/ 사용자등록을 실패했을때 그 입력값을 불러오기 위해 쓰이는 두가지 용도로 사용되는데,
							널처리를 안해주고 value값에 그냥 바로 getParameter해버리면 최초 사용자 등록할때도 널값이 뜬다.
							그게 보기 싫으니까 위에 삼항연산자를 써서 널처리를 해결한건데
							그거 때문에 두줄이나 길어지니까 그게 보기 싫어서 EL을 쓰는거지. EL을 쓰는 이유를 다시한번 상기.
						
						
							
 --%>						<input type="text" class="form-control" id="userid" name="userid" placeholder="아이디를 입력하세요." value="${user.userid}"/> 
 							
 							
 							<form:errors path="userVo.userid"/>
							
 						
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
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="USERNM"/></label>
						<div class="col-sm-10">
								<input type="text" class="form-control" id="userNm" name="usernm" placeholder="이름을 입력하세요." value="${user.usernm}">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ALIAS"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userAlias" name="alias" placeholder="별명을 입력하세요." >
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="PASS"/></label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pass" name="pass" placeholder="Password">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="REG_DT"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="reg_dt" name="reg_dt" placeholder="2021.01.13">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ZIPCODE"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" readonly>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ADDR1"/></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addr1" name="addr1" placeholder="주소" readonly>
						</div>
						<div class="col-sm-2">
							<button type="button" id="addrBtn" class="btn btn-default">주소 검색</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ADDR2"/></label>
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
				
				<select name="lang">
					<option value="">언어설정</option>
					<option value="ko">한국어</option>
					<option value="en">영어</option>
				</select>
				
				<script>
				$(function (){
					$("select[name=lang]").val("${param.lang}");
					$("select[name=lang]").on("change", function(){
						document.location="/user/registeUserView?lang=" + $(this).val();
					})
				})
				</script>
				
				
				
				
				
				
				
				
				
				
				
				