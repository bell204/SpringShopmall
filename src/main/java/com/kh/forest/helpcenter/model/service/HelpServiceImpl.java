package com.kh.forest.helpcenter.model.service;
 
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.forest.helpcenter.model.dao.HelpDao;
import com.kh.forest.helpcenter.model.exception.HelpException;
import com.kh.forest.helpcenter.model.vo.Commentary;
import com.kh.forest.helpcenter.model.vo.Notice;
import com.kh.forest.helpcenter.model.vo.PageInfo;
import com.kh.forest.helpcenter.model.vo.SearchCondition;

@Service
public class HelpServiceImpl implements HelpService{

	@Autowired
	private HelpDao hd;
		
	
	// 컨넥션 주입 = sqlSession 주입 (insert, update, delete인 경우만 트랜잭션 처리해서 주입하는듯)
	@Autowired
	private SqlSessionTemplate sqlSession;
 
 
	// 검색 결과 조회 (count로)
	@Override
	public int getHelpSearchResultListCount(SearchCondition sc) throws HelpException {
		return hd.getHelpSearchResultListCount(sc);
	}


	@Override
	public ArrayList<Notice> searchHelpResultList(SearchCondition sc, PageInfo pi) throws HelpException {
		return hd.getHelpSearchResultList(sc, pi);
	}


	// insert
	@Override
	public ArrayList<Commentary> insertCommentary(Commentary reply) throws HelpException {
		return hd.insertCommentary(reply);
	}


	@Override
	public int getHelpListCount() throws HelpException {
		return hd.getHelpListCount();
	}

	// insert
	@Override
	public int insertHelp(Notice notice) throws HelpException {
		return  hd.insertHelp(sqlSession, notice);
	}

  
	@Override
	public ArrayList<Notice> recoverHelpList(PageInfo pi) throws HelpException {
		return  hd.recoverHelpList(pi);
	}


	@Override
	public ArrayList<Notice> helpDetailSelectList(Notice notice) throws HelpException {
		return hd.helpDetailSelectList(notice);
	}
   
	
	@Override
	public Notice comparePassword(Notice notice) throws HelpException {
		return hd.comparePassword(notice);
	}

 

	
}
