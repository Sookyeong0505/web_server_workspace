package com.sh.mvc.member.model.service;

import static com.sh.mvc.common.JdbcTemplate.*;

import java.sql.Connection;

import com.sh.mvc.member.model.dao.MemberDao;
import com.sh.mvc.member.model.vo.Member;

public class MemberService {
   public final MemberDao memberDao = new MemberDao();

   
   /**
    * DQL (select~)
    * 1. Connection 생성
    * 2. PrepareStatement 생성 및 ? 값 대입
    * 3. 실행 및 ResultSet 처리
    * 4. 자원반납
    * 
    * DML (insert,update,delete)
    * 1. Connection 생성 (setAutoCommit : false)
    * 2. PrepareStatement 생성 및 ? 값 대입
    * 3. 실행 및 int 반환
    * 4. 트랜잭션 (commit/rollback)
    * 5. 자원반납
    * 
    */
   public Member findByID(String memberId) {
      Connection conn = getConnection();
      Member member = memberDao.findById(conn, memberId);
      close(conn);
      return member;
   }
   
   public int insertMember(Member newMember) {
	   int result =0;
	   Connection conn = getConnection();
	   try {
		   result = memberDao.insertMember(conn, newMember);
		   commit(conn);
		
	   } catch (Exception e) {
		   e.printStackTrace();
		   rollback(conn);
	   } finally {
		   close(conn);		   
	   }
	   return result; 
   }
   
}