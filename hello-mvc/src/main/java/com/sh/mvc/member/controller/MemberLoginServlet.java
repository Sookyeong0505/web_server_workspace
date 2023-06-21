package com.sh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mvc.member.model.service.MemberService;
import com.sh.mvc.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private final MemberService memberService = new MemberService();

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //브라우저에서 요청을해야 servlet에서 응답
      
      //0. 인코딩처리
      request.setCharacterEncoding("utf-8");
      
      //1. 사용자 입력값(memberId, password)
      String memberId = request.getParameter("memberId");
      String password = request.getParameter("password");
      System.out.println("memberId = " + memberId);
      System.out.println("password = " + password);
      
      //2. 업무로직 - 로그인확인 
      // 아이디로 db에서 조회 select * from member where member_id = ?
      // member객체가 null이 아니면서 비밀번호가 일치하면 로그인성공
      // member객체가 null이거나 비밀번호가 일치하지 않으면 로그인실패
      Member member = memberService.findByID(memberId);
      System.out.println("member@servlet = " + member);
      
      HttpSession session = request.getSession();
      if(member != null && password.equals(member.getPassword())) {
    	  // 로그인 성공
    	  session.setAttribute("loginMember", member);
    	  session.setAttribute("msg", "로그인에 성공했습니다.");
      } else {
    	  // 로그인 실패
    	  session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
      }

      //3. 응답처리 (리다이렉트)
      response.sendRedirect(request.getContextPath() + "/"); // redirect를 통한 url 변경
      
      
//      RequestDispatcher reqDispatcher = request.getRequestDispatcher("/index.jsp");
//      reqDispatcher.forward(request, response);
      
      
   }

}