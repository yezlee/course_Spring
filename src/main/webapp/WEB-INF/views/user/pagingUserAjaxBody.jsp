<%@ page import="kr.or.ddit.common.model.PageVo"%>
<%@ page import="kr.or.ddit.user.model.UserVo"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  


<script type="text/javascript">
	//문서 로딩이 완료되고 나서 실행되는 영역
	$(function(){
		pagingUserAjax(1,5);
		
// 		$(".user").on("click", function(){
		$("#userTbody").on("click", ".user", function(){
			
			//this : 클릭 이벤트가 발생한 element
			//data-속성명 ex: data-userid 에서  userid만 .data("")안에 넣음됨. 속성명은 대소문자무시하고 다 소문자로 인식
			console.log($(this).data("userid"));
			
/* 			alert.log$(this).data("userid");
			예전에 콘솔로그 없을땐 얼럿창 많이씀
*/
			
			var userid = $(this).data("userid");
			$("#userid").val(userid);
			$("#frm").submit();
			
			
		});
	});
	
	
	
	function pagingUserAjax(page, pageSize){
		
		//ajax를 통해 사용자 리스트를 가져온다 : 1페이지를 가져온다, 1page/5pagesize
		$.ajax({
			
//			url : "/user/pagingUserAjax",
			url : "/user/pagingUserAjaxHtml",
			data : "page=" + page + "&pageSize=" + pageSize,
			success : function(data){
			//	console.log(data);
			
// 				var html= "";
// 				$.each(data.userList, function(i,user){
					
// 					html += "<tr class='user' data-userid='" + user.userid + "'>";
// 					html += "	<td>" + user.userid + "</td>";
// 					html += "	<td>" + user.usernm + "</td>";
// 					html += "	<td>" + user.alias + "</td>";
// 					html += "	<td>" + user.reg_dt_fmt + "</td>";
// 					html += "</tr> ";
// 				})
				
				var html = data.split("####################")
				
				//console.log(html);
				$("#userTbody").html(html[0]);
				$("#pagination").html(html[1]);
//				document.getElementById("userTbody").innerHTML
			}
			
		});
		
	}
</script>


	<form id="frm" action="/user/user">
		<input type="hidden" id="userid" name="userid" value=""/>
	</form>


	
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>사용자 아이디</th>
									<th>사용자 이름</th>
									<th>사용자 별명</th>
									<th>등록일시</th>
								</tr>
								
								
								<tbody id="userTbody">
								</tbody>
								
								
								
								
<%-- 									<c:forEach items="${userList}" var="user"> --%>
<%-- 										<tr class="user" data-userid="${user.userid}"> --%>
<%-- 											<td>${user.userid}</td> --%>
<%-- 											<td>${user.usernm}</td> --%>
<%-- 											<td>${user.alias}</td> --%>
<%-- 											<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy-MM-dd"/></td> --%>
<!-- 										</tr> -->
<%-- 									</c:forEach>	 --%>
							</table>
						</div>

						<a href="/user/registeUserView" class="btn btn-default pull-right">사용자 등록</a>
						<a href="/user/excelDownload" class="btn btn-default pull-right">엑셀 다운로드</a>

						<div class="text-center">
							<ul class="pagination" id="pagination">
							</ul>
						</div>
					</div>
				</div>
		
		
		
		
		
		
		
		

