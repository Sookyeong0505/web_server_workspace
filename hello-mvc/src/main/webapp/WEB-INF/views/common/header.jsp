<%@page import="com.sh.mvc.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% //이부분이 먼저 실행되고 html이 만들어짐
   String msg = (String) session.getAttribute("msg");
   if(msg != null) session.removeAttribute("msg"); //1회용
   //System.out.println("msg = " + msg);
   
   Member loginMember = (Member) session.getAttribute("loginMember");
   //System.out.println("loginMember = " + loginMember);
   
   Cookie[] cookies = request.getCookies();
   String saveId = null;
   if(cookies != null){
      for(Cookie cookie : cookies){
         String name  = cookie.getName();
         String value = cookie.getValue();
      //   System.out.println("[Cookie]" + name + " = " + value);
         if("saveId".equals(name))
            saveId = value;
      }
   }
   
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script>
window.onload = () =>{
   
   <% if(msg !=null){%>
   alert('<%= msg%>');
   <% }%>
   
 <% if(loginMember ==null){%>
   document.loginFrm.onsubmit = (e) => {
      // 아이디
       const memberId = e.target.memberId;
      if(!/^\w{4,}$/.test(memberId.value)){
         alert("아이디는 4글자 이상 입력하세요.");
         e.preventDefault();
         return;
      }
      
      // 비밀번호
      const password = e.target.password;
      if(!/^.{4,}$/.test(password.value)){
         alert("비밀번호를 4글자 이상 입력하세요.");
         e.preventDefault();
         return;
      }
   }
  <%}%>
};
</script>
</head>
<body>
   <div id="container">
      <header>
         <h1>Hello MVC</h1>
         <div class="login-container">
                <!-- 로그인폼 시작 -->
     <% if(loginMember == null){ %>
                <form    
                      id="loginFrm" 
                      name="loginFrm" 
                      action="<%= request.getContextPath() %>/member/login"
                         method="post">
                         
                         <!--request.getContextPath()는 /mvc-->
                    <table>
                        <tr>
                            <td>
                            <input type="text" name="memberId" id="memberId" placeholder="아이디" 
                            value="<%=saveId != null ? saveId : "" %>">
                            </td>
                            <td><input type="submit" value="로그인"></td>
                        </tr>
                        <tr>
                            <td><input type="password" name="password" id="password" placeholder="비밀번호"></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="checkbox" name="saveId" id="saveId" <%=saveId != null ? "checked" : "" %>/>
                                <label for="saveId">아이디저장</label>&nbsp;&nbsp;
                                <input 
                                type="button" value="회원가입"
                                onclick="location.href='<%= request.getContextPath() %>/member/memberEnroll';">
                            </td>
                        </tr>
                    </table>
                </form>
                <!-- 로그인폼 끝-->
      <% }else{ %>
                <!-- 로그인사용자정보 시작 -->
                <table id="login">
                  <tr>
                      <td><%= loginMember.getName() %>님, 안녕하세요.</td>
                  </tr>
                  <tr>
                      <td>
                          <input type="button" value="내정보보기">
                          <input 
                             type="button" 
                             value="로그아웃" 
                             onclick="location.href='<%= request.getContextPath() %>/member/logout';">
                      </td>
                  </tr>
              </table>
              <% } %>
         <!-- 로그인사용자정보 끝 -->
            </div>
        

            <!-- 메인메뉴 시작 -->
            <nav>
                <ul class="main-nav">
                    <li class="home"><a href="<%= request.getContextPath() %>">Home</a></li>
                    <li class="notice"><a href="#">공지사항</a></li>
                    <li class="board"><a href="#">게시판</a></li>
                </ul>
            </nav>
            <!-- 메인메뉴 끝-->
      </header>
      
      <section id="content">