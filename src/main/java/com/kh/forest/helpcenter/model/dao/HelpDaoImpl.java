package com.kh.forest.helpcenter.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.forest.helpcenter.model.vo.Commentary;
import com.kh.forest.helpcenter.model.vo.Notice;
import com.kh.forest.helpcenter.model.vo.PageInfo;
import com.kh.forest.helpcenter.model.vo.SearchCondition;

@Repository
public class HelpDaoImpl implements HelpDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
 
	
	// 암호 일치 여부 확인 나중에 넣기
	@Override
	public Notice comparePassword(Notice notice) {
		notice = sqlSession.selectOne("Notice.selectComparePassword", notice);
		return notice;
	}

	@Override
	public int getHelpSearchResultListCount(SearchCondition sc) {
		int result = 0;

		result = sqlSession.selectOne("Notice.searchResultListCount", sc);

		// 에러 처리 ( )

		return result;
	}

	@Override
	public ArrayList<Notice> getHelpSearchResultList(SearchCondition sc, PageInfo pi) {
		ArrayList<Notice> list = null;

		// 검색해보기 int offset이 뭐지?
		int offset = (pi.getCurrentPage() - 1) * pi.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getLimit());

		list = (ArrayList) sqlSession.selectList("Notice.selectSearchResultList", sc, rowBounds);

		// 에러 처리

		return list;
	}

	@Override
	public ArrayList<Commentary> insertCommentary(Commentary reply) {
		int result = 0;
		ArrayList<Commentary> replyList = null;

		result = sqlSession.insert("Commentary.insertCommentary", reply);

		if (result > 0) {
			replyList = (ArrayList) sqlSession.selectList("Commentary.selectCommentary", reply);
		}

		return replyList;
	}

	@Override
	public int getHelpListCount() {
		int result = 0;

		result = sqlSession.selectOne("Notice.selectListCount");

		// 에러 처리 ( )
		return result;
	}

	@Override
	public int insertHelp(SqlSessionTemplate sqlSession, Notice notice) {
		int result = 0;
		result = sqlSession.insert("Notice.insertNotice", notice);
		 
		return result;
	}

	@Override
	public ArrayList<Notice> recoverHelpList(PageInfo pi) {
		ArrayList<Notice> list = null;
		
		int offset = (pi.getCurrentPage() - 1) * pi.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getLimit());
		list = (ArrayList) sqlSession.selectList("Notice.recoverSelectNotice", rowBounds);
		
		return list;
	 
	}

	@Override
	public ArrayList<Notice> helpDetailSelectList(Notice notice) {
		ArrayList<Notice> list = null;
		list = (ArrayList) sqlSession.selectList("Notice.helpDetailSelectList", notice);
		
		return list;
	}
 
	
	
}
