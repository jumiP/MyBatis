package member.model.service;

import static common.Template.getSqlSession;

import java.util.HashMap;

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

	public void insertMember(Member m) throws MemberException {
		SqlSession session = getSqlSession();
		
		mDAO.insertMember(session, m);
		
		session.commit();
		session.close();
		
//		return result;	// return 안 해줘도 됨
	}

	public void updateMember(Member m) throws MemberException {
		SqlSession session = getSqlSession();
		
		mDAO.updateMember(session, m);
		
		session.commit();
		session.close();
		
	}

	public void updatePwd(HashMap<String, String> map) throws MemberException {
		SqlSession session = getSqlSession();
		mDAO.updatePwd(session, map);
		
		session.commit();
		session.close();
		
	}

	public void deleteMember(String userId) throws MemberException {
		SqlSession session = getSqlSession();
		
		mDAO.deleteMember(session, userId);
		session.commit();
		session.close();
		
	}
	

}
