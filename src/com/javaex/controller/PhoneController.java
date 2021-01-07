package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 컨트롤러 테스트
		System.out.println("controller");

		// 파라미터 action 값을 읽는다
		String action = request.getParameter("action");
		System.out.println("=====action======");
		System.out.println(action);
		
		// action = ? --> ? 에 해당하는 일을 한다


		// action list --> list 출력
		if ("list".equals(action)) {
			System.out.println("리스트 처리");
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getList();

			// 데이터 전달
			// request.setAttribute("별명", 실제 데이터);
			request.setAttribute("pList", personList);

			// html --> 복잡 --> jsp에서 만들어주는 게 편하다
			// jsp에 포워드 시킴
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/list.jsp"); // path-jsp 위치를 알려줌
			rd.forward(request, response);
		} else if("wform".equals(action)) {
			System.out.println("등록 폼 처리");
			
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/writeForm.jsp");
			rd.forward(request, response);
		} else if("insert".equals(action)) {
			System.out.println("전화번호 저장");
			
			//파라미터 3개 값 받으ㅡㄴ 거 꺼내기
			String name = request.getParameter("name");	//action은 이미 실행되어 있음으로 다시 적용하지 않음
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//personVo로 묶음
			PersonVo personVo = new PersonVo(name, hp, company);
	
			//new dao
			PhoneDao phoneDao = new PhoneDao();		//dao 메모리 생성
			phoneDao.phoneInsert(personVo);			//저장
			//dao.phoneInsert(personVo)
			
			//리다이렉트로 주소로 응답해줌
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		} else if("delete".equals(action)) {
			System.out.println("전화번호 삭제");
			
			int personId = Integer.parseInt(request.getParameter("id"));

			
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.getDelete(personId);
			
			//삭제 후 리다이렉트 주소로 응답해주기
			response.sendRedirect("/phonebook2/pbc?action=list");
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 위 코드를 같이 쓰고 싶으면 이 기본 생성 코드를 사용
		// doGet(request, response);
	}

}
