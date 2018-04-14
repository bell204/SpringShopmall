package com.kh.forest.helpcenter.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.forest.helpcenter.model.vo.Commentary;
import com.kh.forest.helpcenter.model.vo.Notice;
import com.kh.forest.helpcenter.model.vo.PageInfo;
import com.kh.forest.helpcenter.model.vo.SearchCondition;

public interface HelpDao {
 
	ArrayList<Notice> insertHelp(SqlSessionTemplate sqlSession, Notice notice, PageInfo pi);
	int getHelpSearchResultListCount(SearchCondition sc);
	ArrayList<Notice> getHelpSearchResultList(SearchCondition sc, PageInfo pi);
	ArrayList<Commentary> insertCommentary(Commentary reply);
	int getHelpListCount();
	ArrayList<Notice> recoverHelpList(PageInfo pi);
	ArrayList<Notice> helpDetailSelectList(Notice notice);
	Notice comparePassword(Notice notice);

	

}
