<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%-- page 지시어(directives) --%>
<%
	System.out.println("Helloworld"); // 이클립스 콘솔에 출력
	int sum =0;
	for(int i=1; i<=10; i++)
		sum += i;
	System.out.println("sum = "+ sum);
	
	long millis = new Date().getTime();
	System.out.println("date = "+ millis);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Basics</title>
</head>
<body>
	<h1>JSP Basics</h1>
	<h2>1부터 10까지의 합</h2>
	<ul>
		<li>Server : <%= sum %></li>
		<li>Client : <span id="sum"></span></li>
	</ul>
	
	<h2>시각출력</h2>
	<ul>
		<li>Server Time: <%= millis %></li>
		<li>Client Time: <span id="now"></span></li>
	</ul>
	
	<script>
		console.log('Hello world'); // 브라우저 콘솔에 출력
		
		// iffe
		(() => {
			let sum =0;
			for(let i=1; i<= 10; i++)
				sum += i;
			document.querySelector("#sum").innerHTML = sum;	
		// 시각 출력
			document.querySelector("#now").innerHTML = Date.now();
		})();
		
		
	</script>

</body>
</html>