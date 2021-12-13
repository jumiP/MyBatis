package member.model.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import member.model.exception.MemberException;
import member.model.vo.Member;

public class MemberDAO {

	public Member selectMember(SqlSession session, Member m) throws MemberException{
		
	/*
		session.selectOne(arg0);
		session.selectOne(arg0, arg1);
		
		- arg0 : 어느 매퍼에 있는 어떤 쿼리(id)에 접근할 것인가
		- arg1 : 쿼리로 보낼 데이터를 작성
		
	*/
		
		Member member = session.selectOne("memberMapper.loginMember", m);
		
		if(member == null) {	// 존재하지 않는 회원일 때 (로그인 실패)
			session.close();
			throw new MemberException("로그인에 실패하였습니다.");
		}
		
		return member;
	}

	public void insertMember(SqlSession session, Member m) throws MemberException {
		int result = session.insert("memberMapper.insertMember", m);
		
		if(result <= 0) {	// 에러 강제 발생
			session.rollback();
			session.close();
			throw new MemberException("회원가입에 실패하였습니다.");
		}
		
//		return result;
	}

	public void updateMember(SqlSession session, Member m) throws MemberException {
		int result = session.update("memberMapper.updateMember", m);
		
		if(result <= 0) {
			session.rollback();
			session.close();
			throw new MemberException("회원정보 수정에 실패하였습니다.");
		}
	}

	public void updatePwd(SqlSession session, HashMap<String, String> map) throws MemberException {
		int result = session.update("memberMapper.updatePwd", map);
		
		if(result <= 0) {
			session.rollback();
			session.close();
			throw new MemberException("비밀번호 수정에 실패하였습니다.");
		}
	}

	public void deleteMember(SqlSession session, String userId) throws MemberException {
		int result = session.update("memberMapper.deleteMember", userId);
		
		if(result <= 0) {
			session.rollback();
			session.close();
			throw new MemberException("회원 탈퇴에 실패하였습니다.");
		}
	}

}
