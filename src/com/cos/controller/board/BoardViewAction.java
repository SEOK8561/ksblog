package com.cos.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.controller.Action;
import com.cos.dao.BoardDAO;
import com.cos.domain.Board;
import com.cos.util.MyUtils;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board/view.jsp";

		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("num : "+num);
		
		HttpSession session = request.getSession();
		String sessionID = (String)session.getAttribute("id");
		System.out.println(session.getAttribute("id"));
		System.out.println("ID : " +sessionID);

		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.updateReadCount(num);
		Board board = boardDAO.findByID(num);
		System.out.println(board.getId());
				
		if(board != null && result == 1) {
			request.setAttribute("board", board);
			RequestDispatcher dis = request.getRequestDispatcher(url);
			dis.forward(request, response);
		} else {
			MyUtils.script("server error", "board?cmd=boardList", response);
		}
		return;
		
	}
}
