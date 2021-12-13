package board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import board.model.exception.BoardException;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import member.model.exception.MemberException;


public class BoardDAO {

	public int getListCount(SqlSession session) throws BoardException {
		int listCount = session.selectOne("boardMapper.getListCount");
		
		if(listCount <= 0) {
			session.close();
			throw new BoardException("게시물 조회 실패");
		}
		return listCount;
	}

	public ArrayList<Board> selectBoardList(SqlSession session, PageInfo pi) throws BoardException {
		
//		RowBounds rowBounds = new RowBounds(offset, limit);
//		offset : 몇 개의 게시글을 건너뛸 것인지
//		limit : 몇 개의 게시글을 가져올 것인지
		
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		ArrayList<Board> list = (ArrayList)session.selectList("boardMapper.selectBoardList", null, rowBounds);
		
		if(list == null) {
			session.close();
			throw new BoardException("게시물 조회 실패하였습니다.");
		}
		
		return list;
	}

	public void updateCount(SqlSession session, int bId) throws BoardException {
		int result = session.update("boardMapper.updateCount", bId);
		
		if(result <= 0) {
			session.rollback();
			session.close();
			throw new BoardException("게시물 조회 증가 실패");
		}
	}

	public Board selectBoardDetail(SqlSession session, int bId) throws BoardException {
		Board b = session.selectOne("boardMapper.selectBoardDetail", bId);
		
		if(b == null) {
			session.close();
			throw new BoardException("게시물 상세 조회 실패");
		}
		
		return b;
	}

	public int getSearchResultListCount(SqlSession session, HashMap<String, String> map) throws BoardException {
		int listCount = session.selectOne("boardMapper.getSearchResultListCount", map);
		
		if(listCount <= 0) {
			session.close();
			throw new BoardException("검색 결과의 개수 조회 실패");
		}
		return listCount;
	}

	public ArrayList<Board> selectSearchResultList(SqlSession session, PageInfo pi, HashMap<String, String> map) throws BoardException {

		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		ArrayList<Board> list = (ArrayList)session.selectList("boardMapper.selectSearchResultList", map, rowBounds);
		
		if(list == null) {
			session.close();
			throw new BoardException("검색 결과 리스트 조회 실패");
		}
		
		return list;
	}

}
