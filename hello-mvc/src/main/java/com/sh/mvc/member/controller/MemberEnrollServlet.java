package com.sh.mvc.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mvc.member.model.service.MemberService;
import com.sh.mvc.member.model.vo.Gender;
import com.sh.mvc.member.model.vo.Member;
import com.sh.mvc.member.model.vo.MemberRole;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final MemberService memberService = new MemberService();
       
	
	/**
	 * GET /member/memberEnroll
	 * - 회원가입 폼페이지 응답
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp")
		.forward(request, response);
	}

	/**
	 * POST /member/memberEnroll
	 * - DB에 회원정보 저장
	 * - insert into member values (?, ?, ...) 
	 * - 회원권한, 등록일 등 sql 기본값 처리
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		// 1. 사용자 입력값 처리
		// Member 객체로 변환
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String _birthday = request.getParameter("birthday");
		Date birthday = _birthday != null ? Date.valueOf(_birthday) : null;
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String _gender = request.getParameter("gender");
		Gender gender = _gender!=null ? Gender.valueOf(_gender) : null;
		String[] hobbies = request.getParameterValues("hobby");
		String hobby = hobbies!=null ? String.join(", ", hobbies) : null;
		
		

		Member newMember = new Member(memberId, password, name, null, gender, birthday, email, phone, hobby, 0, null);
		System.out.println(newMember);
		
		// 2. 업무로직 - db 저장 요청 (insert 요청)
		int result = memberService.insertMember(newMember);
		System.out.println(result);
		
		// 
		HttpSession session = request.getSession();
		session.setAttribute("msg", "성공적으로 회원등록을 했습니다.");
		
		// 3. 인덱스 페이지 리다이렉트
		response.sendRedirect(request.getContextPath() + "/");
	}

}
