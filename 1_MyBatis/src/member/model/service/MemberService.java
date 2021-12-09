package member.model.service;

import static common.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;

import member.model.dao.MemberDAO;
import member.model.exception.MemberException;
import member.model.vo.Member;

public class MemberService {
	
	private MemberDAO mDAO = new MemberDAO();

	public Member selectMember(Member m) throws MemberException {
		SqlSession session = getSqlSession();
		Member member = mDAO.selectMember(session, m);
		
		session.close();
		
		return member;
	}
	

}
