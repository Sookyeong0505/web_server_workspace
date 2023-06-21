package com.sh.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * - servlet class 만들기
 * - doPost 작성
 * - 사용자 입력값 출력
 * 
 * 
 * - /web
 * - context-path, context-root, app-name
 * - 언제만 context-path를 경로에 포함시켜야 할까?
 * - jsp, (서버 내부)
 * - xml, (서버 외부)
 * 
 *
 */
public class TestPersonServlet3 extends HttpServlet {
	
	// doPost 오버라이드 하기
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 1. 인코딩 처리 - utf-8 설정
		req.setCharacterEncoding("utf-8");
		
		// 2. 사용자 입력값 받기
		String name = req.getParameter("name");
		String color = req.getParameter("color");
		String animal = req.getParameter("animal");
		String[] foods = req.getParameterValues("food");
		
		// 업무로직(비지니스 로직) - DB (이것을 jsp로 어떻게 전달/처리?)
		String result = "당신은 멋쟁이!";
		// servlet에서 jsp로 데이터를 전달하는 방법
		// HttpServletRequest 속성으로 저장하고, jsp에서 꺼내쓴다.
		req.setAttribute("result", result); // name:String, value:Object
		
		List<String> samples = Arrays.asList("선글라스", "선크림", "수영복");
		req.setAttribute("samples", samples);
		
		// 배열로 해보기
		String[] items = {"선글라스", "선크림", "수영복"};
		req.setAttribute("items", items);
		
		// 3.jsp에 응답 html 작성 위임
		// path에서 /(root)는 src/main/webapp 디렉토리를 가리킨다.
		RequestDispatcher reqDispatcher = req.getRequestDispatcher("/servlet/view/testPerson3.jsp");
		// dispatch == send
		reqDispatcher.forward(req, resp);
		
	}

}
