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

public class BoardUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board/update.jsp";
		int num = Integer.parseInt(request.getParameter("num"));
		
		HttpSession session = request.getSession();
		String sessionID = (String)session.getAttribute("id");
		
		BoardDAO boardDAO = new BoardDAO();
		Board board = boardDAO.findByID(num);
		
		request.setAttribute("board", board);
		
		if(sessionID != null) {
			if(board.getId().equals(sessionID)) {
				if(board != null) {
					RequestDispatcher dis = request.getRequestDispatcher(url);
					dis.forward(request, response);
				} /*else {
					MyUtils.script("에러", response);
				}*/
			}
		}
		MyUtils.script("권한이 없습니다.", response);
	}
}
