<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 공통적으로 사용할 css, js 파일을 등록--%>

<!-- Bootstrap core CSS -->
<link href="${cp}/css/bootstrap.min.css" rel="stylesheet">

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>   
 <script src="${cp}/js/bootstrap.js"></script>
 <!-- 위둘의 순서는 제이쿼리가 먼저 와야지 -->
 
<!-- 원래 css는 상단에 js는 하단에 위치하는게 순서상으로 맞음. 그렇게 하려면 include를 두개로 나눠서 위에 아래에 위치해야함 -->