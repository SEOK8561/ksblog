package com.cos.controller.member;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.controller.Action;
import com.cos.dao.MemberDAO;
import com.cos.domain.Member;
import com.cos.util.MyUtils;
import com.cos.util.SHA256;

public class MemberJoinProcAction implements Action {

	//private final static String context = "MemberJoinProcAction : ";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url ="index.jsp";
		String id = request.getParameter("id");
		String userPassword = request.getParameter("userPassword");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		String userGender = request.getParameter("userGender");
		String userAddr = request.getParameter("userAddr");
		String userAddrDetail = request.getParameter("userAddrDetail");
		/*System.out.println(context + id);
		System.out.println(context + userPassword);
		System.out.println(context + userEmail);
		System.out.println(context + userPhone);
		System.out.println(context + userGender);
		System.out.println(context + userAddr);
		System.out.println(context + userAddrDetail);*/
		
		String salt = "cos";
		userPassword = SHA256.getEncrypt(userPassword, salt);
		
		Member member = new Member();
		member.setId(id);
		member.setUserPassword(userPassword);
		member.setUserEmail(userEmail);
		member.setUserPhone(userPhone);
		member.setUserGender(userGender);
		member.setUserState(1);
		member.setCreateDate(LocalDate.now());
		member.setUpdateDate(LocalDate.now());
		member.setUserAddr(userAddr);
		member.setUserAddrDetail(userAddrDetail);

		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.save(member);
		System.out.println(result);
		if(result == 1) {
			RequestDispatcher dis = request.getRequestDispatcher(url);
			dis.forward(request, response);
		} else if(result == 0) {
			MyUtils.script("DB오류", response);
		} else if(result == -1) {
			MyUtils.script("서버오류", response);
		}
	}
}
