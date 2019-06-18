package com.cos.controller.board;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.controller.Action;
import com.cos.dao.BoardDAO;
import com.cos.domain.Board;
import com.cos.util.MyUtils;

public class BoardWriteProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		int readCount = 0;
		LocalDate createDate = LocalDate.now();
		LocalDate updateDate = LocalDate.now();
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setId(id);
		board.setReadCount(readCount);
		board.setCreateDate(createDate);
		board.setUpdateDate(updateDate);
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.save(board);
		
		if(result == 1) {
			RequestDispatcher dis = request.getRequestDispatcher(url);
			dis.forward(request, response);
		} else {
			MyUtils.script("error", response);
			System.out.println(result);
		}
	}

}
