package board.model.service;

import static common.Template.getSqlSession;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import board.model.dao.BoardDAO;
import board.model.exception.BoardException;
import board.model.vo.Board;
import board.model.vo.PageInfo;

public class BoardService {
	
	private BoardDAO bDAO = new BoardDAO();

	public int getListCount() throws BoardException {
		SqlSession session = getSqlSession();
		
		int listCount = bDAO.getListCount(session);
				
		session.close();
		
		return listCount;
	}

	public ArrayList<Board> selectBoardList(PageInfo pi) throws BoardException {
		SqlSession session = getSqlSession();
		
		ArrayList<Board> list = bDAO.selectBoardList(session, pi);
		
		session.close();
		return list;
	}

	public Board selectBoardDetail(int bId) throws BoardException {
		SqlSession session = getSqlSession();
		
		bDAO.updateCount(session, bId);
		
		Board b = bDAO.selectBoardDetail(session, bId);
		
		session.commit();
		session.close();
		return b;
	}

	public int getSearchResultListCount(HashMap<String, String> map) throws BoardException {
		SqlSession session = getSqlSession();
		
		int listCount = bDAO.getSearchResultListCount(session, map);
		
		session.close();
		
		return listCount;
	}

	public ArrayList<Board> selectSearchResultList(PageInfo pi, HashMap<String, String> map) throws BoardException {
		SqlSession session = getSqlSession();
		
		ArrayList<Board> list = bDAO.selectSearchResultList(session, pi, map);
		
		session.close();
		return list;
	}
	

}
