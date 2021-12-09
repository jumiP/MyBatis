package member.model.dao;

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

}
