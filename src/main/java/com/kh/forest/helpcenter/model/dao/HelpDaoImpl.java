package com.kh.forest.helpcenter.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.forest.helpcenter.model.exception.HelpException;
import com.kh.forest.helpcenter.model.vo.Commentary;
import com.kh.forest.helpcenter.model.vo.Notice;
import com.kh.forest.helpcenter.model.vo.PageInfo;
import com.kh.forest.helpcenter.model.vo.SearchCondition;

@Repository
public class HelpDaoImpl implements HelpDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
 
	
	@Override
	public Notice comparePassword(Notice notice) throws HelpException {
		notice = sqlSession.selectOne("Notice.selectComparePassword", notice);
		
		if(notice == null){
			throw new HelpException("암호 조회 실패");
		}
		
		
		return notice;
	}

	@Override
	public int getHelpSearchResultListCount(SearchCondition sc) throws HelpException {
		int result = 0;

		result = sqlSession.selectOne("Notice.searchResultListCount", sc);
		
		if(result==0){
			throw new HelpException("검색 실패");
		}

		return result;
	}

	@Override
	public ArrayList<Notice> getHelpSearchResultList(SearchCondition sc, PageInfo pi) throws HelpException {
		ArrayList<Notice> list = null;

		// 검색해보기 int offset이 뭐지?
		int offset = (pi.getCurrentPage() - 1) * pi.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getLimit());

		list = (ArrayList) sqlSession.selectList("Notice.selectSearchResultList", sc, rowBounds);

		if(list == null){
			throw new HelpException("검색 결과 조회 실패");
		}
		
		return list;
	}

	@Override
	public ArrayList<Commentary> insertCommentary(Commentary reply) throws HelpException {
		int result = 0;
		ArrayList<Commentary> replyList = null;

		result = sqlSession.insert("Commentary.insertCommentary", reply);

		if (result > 0) {
			replyList = (ArrayList) sqlSession.selectList("Commentary.selectCommentary", reply);
		}else{
			throw new HelpException("댓글 디비 저장 실패");
		}

		return replyList;
	}

	@Override
	public int getHelpListCount() throws HelpException {
		int result = 0;

		result = sqlSession.selectOne("Notice.selectListCount");

		if(result==0){
			throw new HelpException("목록 개수 조회 실패");
		}
		
		return result;
	}

	@Override
	public int insertHelp(SqlSessionTemplate sqlSession, Notice notice) throws HelpException {
		int result = 0;
		result = sqlSession.insert("Notice.insertNotice", notice);
		 
		if(result==0){
			throw new HelpException("사용자 정의 예외");
		}
		
		return result;
	}

	@Override
	public ArrayList<Notice> recoverHelpList(PageInfo pi) throws HelpException {
		ArrayList<Notice> list = null;
		
		int offset = (pi.getCurrentPage() - 1) * pi.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getLimit());
		list = (ArrayList) sqlSession.selectList("Notice.recoverSelectNotice", rowBounds);
		
		if(list==null){
			throw new HelpException("목록으로 되돌아가기 실패");
		}
		
		return list;
	 
	}

	@Override
	public ArrayList<Notice> helpDetailSelectList(Notice notice) throws HelpException {
		ArrayList<Notice> list = null;
		list = (ArrayList) sqlSession.selectList("Notice.helpDetailSelectList", notice);
		
		if(list==null){
			throw new HelpException("상세 문의 페이지 조회 실패");
		}
		
		return list;
	}
 
	
	
}
