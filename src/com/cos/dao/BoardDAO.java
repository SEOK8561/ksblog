package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.cos.domain.Board;
import com.cos.dto.BoardUpdateDTO;
import com.cos.util.Code;
import com.cos.util.DBManager;
import com.cos.util.MyUtils;

public class BoardDAO {
	private PreparedStatement pstmt;
	private ResultSet rs;

	public List<Board> findAll() {
		final String SQL = "SELECT num, title, content, id, readCount, createDate, updateDate FROM board ORDER BY num DESC";
		Connection conn = DBManager.getConnection();

		List<Board> list = new ArrayList<Board>();
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				Board board = new Board();
				board.setNum(rs.getInt("num"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setId(rs.getString("id"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(MyUtils.StringToLocalDate(rs.getString("createDate")));
				board.setUpdateDate(MyUtils.StringToLocalDate(rs.getString("updateDate")));

				list.add(board);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public List<Board> findAll(int start, int end) {
		final String SQL = "select (select count(*) from board), num, title, content, id, readcount, createdate, updatedate, mynum \r\n" + 
				"from (\r\n" + 
				"select num, title, content, id, readcount, createdate, updatedate, rownum as mynum\r\n" + 
				"from board\r\n" + 
				"order by rownum desc)\r\n" + 
				"where mynum > ? and mynum < ?";
		Connection conn = DBManager.getConnection();

		List<Board> list = new ArrayList<Board>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if(rs.isFirst()) {
					Code.setMaxListNum(rs.getInt(1));
				}
				Board board = new Board();
				board.setNum(rs.getInt("num"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setId(rs.getString("id"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(MyUtils.StringToLocalDate(rs.getString("createDate")));
				board.setUpdateDate(MyUtils.StringToLocalDate(rs.getString("updateDate")));
				list.add(board);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public int save(Board board) {
		final String SQL = "INSERT INTO board(num, title, content, id, readCount, createDate, updateDate) VALUES(board_seq.nextval, ?,?,?,?,?,?)";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getId());
			pstmt.setInt(4, board.getReadCount());
			pstmt.setString(5, board.getCreateDate().toString());
			pstmt.setString(6, board.getUpdateDate().toString());
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -1;
	}
	
	public Board findByID(int num) {
		final String SQL = "SELECT * FROM board WHERE num=?";
		Connection conn = DBManager.getConnection();
		Board board = new Board();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setNum(rs.getInt("num"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setId(rs.getString("id"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(MyUtils.StringToLocalDate(rs.getString("createDate")));
				board.setUpdateDate(MyUtils.StringToLocalDate(rs.getString("updateDate")));
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return null;
	}
	public int delete(int num) {
		final String SQL = "DELETE FROM board WHERE num = ?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -1;
	}
	
	public int updateReadCount(int num) {
		final String SQL = "UPDATE board SET readcount = readcount + 1 WHERE num = ?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -1;
	}
	
	public int update(BoardUpdateDTO bDto) {
		final String SQL = "UPDATE board SET title = ?, content = ? WHERE num = ?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bDto.getTitle());
			pstmt.setString(2, bDto.getContent());
			pstmt.setInt(3, bDto.getNum());
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -1;
	}
}
