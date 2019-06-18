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

public class BoardDeleteAction implements Action{

	//private final static String context = "BoardDeleteAction : ";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		int num = Integer.parseInt(request.getParameter("num"));
		
		HttpSession session = request.getSession();
		String sessionID = (String)session.getAttribute("id");
		
		BoardDAO boardDAO = new BoardDAO();
		Board board_id = boardDAO.findByID(num);

		if(sessionID != null) {
			if(sessionID.equals("admin") || board_id.getId().equals(sessionID)) {
				int board = boardDAO.delete(num);
				if(board == 1) {
					request.setAttribute("board", board);
					RequestDispatcher dis = request.getRequestDispatcher(url);
					dis.forward(request, response);
				} else if(board == 0) {
					MyUtils.script("DB error", response);
				} else if(board == -1) {
					MyUtils.script("Server error", response);
				}
			}
		}
		MyUtils.script("권한이 없습니다.", response);
	}

}
