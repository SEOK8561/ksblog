package com.cos.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.controller.Action;
import com.cos.dao.BoardDAO;
import com.cos.dto.BoardUpdateDTO;
import com.cos.util.MyUtils;

public class BoardUpdateProcAction implements Action {

	private final static String context = "BoardUpdateProcAction : ";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(context + num);
		System.out.println(context + title);
		System.out.println(context + content);
		
		System.out.println();
		
		String url = "board?cmd=boardView&num="+num;
		
		BoardUpdateDTO bDto = new BoardUpdateDTO();
		bDto.setNum(num);
		bDto.setTitle(title);
		bDto.setContent(content);
		
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.update(bDto);
		System.out.println(result);
		
		if(result == 1) {
			RequestDispatcher dis = request.getRequestDispatcher(url);
			dis.forward(request, response);
		} else {
			MyUtils.script("에러", response);
			System.out.println(result);
		}
		
	}

}
