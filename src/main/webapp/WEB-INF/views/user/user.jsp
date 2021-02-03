<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
/* 	사용자 수정 : 메소드가 get이어야하고 - 수정화면 띄우는거 자체는 겟이지 - action = /userModify
	사용자 삭제 : 메소드는 post이어야하고 - method : post, action = /deleteUser
	파라미터는 둘다 userid하나만 있으면 됨 */
	
	//문서로딩이 완료 되었을 때
	$(function(){
		$("#modifyBtn").on("click", function(){
			$("#frm").attr("method", "get");
			$("#frm").attr("action", "/user/userModify");
			$("#frm").submit();
		});
		
		$("#deleteBtn").on("click", function(){
			$("#frm").attr("method", "post");
			$("#frm").attr("action", "/user/userDelete");
			$("#frm").submit();
		});
	})
	//하나의 폼태그를 사용해서 어떤 버튼을 클릭했느냐에 따라서 보내는 속성값을 다르게 하는 위 방식 종종 사용됨
	
</script>


				<div class="row">
						<form class="form-horizontal" id="frm" role="form" action="/user/userModify">
							<input type="hidden" name="userid" value="${user.userid}">
							
							<div class="form-group">
								<label class="col-sm-2 control-label">사용자 사진</label>
								<div class="col-sm-10">
									<%-- <img src="${cp}/profile/${user.userid}.png"> 이제 이거 안씀. 아래거 쓰지.--%>
		
									<a href="/user/profileDownload?userid=${user.userid}">
										<img src="/user/profile?userid=${user.userid}">
									</a>
		
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
								<div class="col-sm-10">
										<label class="control-label">${user.userid}</label>
								</div>
							</div>
		
		
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
								<div class="col-sm-10">
										<label class="control-label">${user.usernm}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">별명</label>
								<div class="col-sm-10">
									<label class="control-label">${user.alias}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="pass" class="col-sm-2 control-label">Password</label>
								<div class="col-sm-10">
									<label class="control-label">${user.pass}</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="pass" class="col-sm-2 control-label">등록일시</label>
								<div class="col-sm-10">
									<label class="control-label"><fmt:formatDate value="${user.reg_dt}" pattern="yyyy-MM-dd"/></label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="pass" class="col-sm-2 control-label">우편번호</label>
								<div class="col-sm-10">
									<label class="control-label">${user.zipcode}</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="pass" class="col-sm-2 control-label">주소</label>
								<div class="col-sm-10">
									<label class="control-label">${user.addr1}</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="pass" class="col-sm-2 control-label">상세주소</label>
								<div class="col-sm-10">
									<label class="control-label">${user.addr2}</label>
								</div>
							</div>
		
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<!-- 
									사용자 수정 : 메소드가 get이어야하고 - 수정화면 띄우는거 자체는 겟이지 - action = /userModify
									사용자 삭제 : 메소드는 post이어야하고 - method : post, action = /deleteUser
									파라미터는 둘다 userid하나만 있으면 됨
									
									버튼 타입 서브밋이었던걸 버튼으로 바꿈.
									 -->
									<button type="button" id="modifyBtn" class="btn btn-default">사용자 수정</button>
									<button type="button" id="deleteBtn" class="btn btn-default">사용자 삭제</button>
								</div>
							</div>
						</form>
					</div>
