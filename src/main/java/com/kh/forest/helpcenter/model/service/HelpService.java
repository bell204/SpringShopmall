package com.kh.forest.helpcenter.model.service;

import java.util.ArrayList;

import com.kh.forest.helpcenter.model.exception.HelpException;
import com.kh.forest.helpcenter.model.vo.Commentary;
import com.kh.forest.helpcenter.model.vo.Notice;
import com.kh.forest.helpcenter.model.vo.PageInfo;
import com.kh.forest.helpcenter.model.vo.SearchCondition;

public interface HelpService {
	
	int getHelpSearchResultListCount(SearchCondition sc) throws HelpException;

	ArrayList<Notice> searchHelpResultList(SearchCondition sc, PageInfo pi) throws HelpException;

	ArrayList<Commentary> insertCommentary(Commentary reply) throws HelpException;

	int getHelpListCount() throws HelpException;

	//ArrayList<Notice> insertHelp(Notice notice, PageInfo pi);

	ArrayList<Notice> recoverHelpList(PageInfo pi) throws HelpException;

	ArrayList<Notice> helpDetailSelectList(Notice notice) throws HelpException;

	Notice comparePassword(Notice notice) throws HelpException;

	int insertHelp(Notice notice) throws HelpException;

	ArrayList<Notice> updateDetailSelectList(Notice notice) throws HelpException;

	int updateDetailComplete(Notice notice);

	int deleteDetail(Notice notice);


}
