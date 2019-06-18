package com.cos.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.controller.Action;
import com.cos.dao.MemberDAO;
import com.cos.domain.Member;
import com.cos.util.MyUtils;

public class MemberEditAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url ="member/edit.jsp";
		HttpSession session = request.getSession();		//세션불러오는 코드
		String sessionID = (String)session.getAttribute("id");
		System.out.println(sessionID);

		MemberDAO memberDAO = new MemberDAO();
		Member member = memberDAO.findByUser(sessionID);
		
		request.setAttribute("member", member);
		
		if(member != null) {
			RequestDispatcher dis = request.getRequestDispatcher(url);
			dis.forward(request, response);
		} else {
			MyUtils.script("에러", response);
		}
	}
}
