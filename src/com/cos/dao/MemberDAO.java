package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.domain.Member;
import com.cos.dto.MemberEditDTO;
import com.cos.util.DBManager;
import com.cos.util.MyUtils;

public class MemberDAO {

	private PreparedStatement pstmt;
	private ResultSet rs;

	public int save(Member member) {
		final String SQL = "INSERT INTO member(num, id, userPassword, userEmail, userPhone, userGender, userState, createDate, updateDate, userAddr, userAddrDetail) VALUES(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getUserPassword());
			pstmt.setString(3, member.getUserEmail());
			pstmt.setString(4, member.getUserPhone());
			pstmt.setString(5, member.getUserGender());
			pstmt.setInt(6, member.getUserState());
			pstmt.setString(7, member.getCreateDate().toString());
			pstmt.setString(8, member.getUpdateDate().toString());
			pstmt.setString(9, member.getUserAddr());
			pstmt.setString(10, member.getUserAddrDetail());
			
			int result = pstmt.executeUpdate();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -1;
	}
	public int findByidAnduserPassword(String id, String userPassword) {
		final String SQL = "SELECT count(*) FROM member WHERE id=? AND userPassword=?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, userPassword);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int result = rs.getInt(1);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return -1;
	}
	public int findByid(String id) {
		final String SQL = "SELECT count(*) FROM member WHERE id=?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int result = rs.getInt(1);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return -1;
	}
	public Member findByUser(String id) {
		final String SQL = "SELECT * FROM member WHERE id=?";
		Connection conn = DBManager.getConnection();
		Member member = new Member();
		try {
			pstmt = conn.prepareStatement(SQL);	//pstmt:완성되지않은 쿼리를 가지고 있음
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();	//데이터베이스에서 결과값만 가져옴, rs는 첫번째 커서를 가리킨다.
			if(rs.next()) {
				member.setId(rs.getString("id"));
				member.setUserPassword(rs.getString("userPassword"));
				member.setUserEmail(rs.getString("userEmail"));
				member.setUserPhone(rs.getString("userPhone"));
				member.setUserGender(rs.getString("userGender"));
				member.setUserState(rs.getInt(1));
				member.setCreateDate(MyUtils.StringToLocalDate(rs.getString("createDate")));
				member.setUpdateDate(MyUtils.StringToLocalDate(rs.getString("updateDate")));
				member.setUserAddr(rs.getString("userAddr"));
				member.setUserAddrDetail(rs.getString("userAddrDetail"));
				return member;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return null;
	}
	public int edit(MemberEditDTO eDto) {
		final String SQL = "UPDATE member SET userPassword = ?, userEmail = ?, userPhone = ?, userGender = ?, updateDate = ?, userAddr = ?, userAddrDetail = ? WHERE id = ?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, eDto.getUserPassword());
			pstmt.setString(2, eDto.getUserEmail());
			pstmt.setString(3, eDto.getUserPhone());
			pstmt.setString(4, eDto.getUserGender());
			pstmt.setString(5, eDto.getUpdateDate().toString());
			pstmt.setString(6, eDto.getUserAddr());
			pstmt.setString(7, eDto.getUserAddrDetail());
			pstmt.setString(8, eDto.getId());
			

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
