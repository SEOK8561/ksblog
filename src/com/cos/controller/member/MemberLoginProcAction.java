package com.cos.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.controller.Action;
import com.cos.dao.MemberDAO;
import com.cos.util.MyUtils;
import com.cos.util.SHA256;

public class MemberLoginProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url ="index.jsp";
		String id = request.getParameter("id");
		String userPassword = request.getParameter("userPassword");
		String idSave = request.getParameter("idSave");
		
		if(idSave != null) {		
			if(idSave.equals("on")) {
				Cookie cookie = new Cookie("cookieID", id);
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
			} 
		} else {
			Cookie cookie = new Cookie("cookieID", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		String salt = "cos";
		userPassword = SHA256.getEncrypt(userPassword, salt);
		
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.findByidAnduserPassword(id, userPassword);
		
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);

			RequestDispatcher dis = request.getRequestDispatcher(url);
			dis.forward(request, response);
		} else if(result == 0) {
			MyUtils.script("아이디 혹은 비밀번호가 일치하지 않습니다.", response);
		} else {
			MyUtils.script("서버에러", response);
		}
	}

}
