package com.kh.forest.helpcenter.model.service;

import java.util.ArrayList;

import com.kh.forest.helpcenter.model.vo.Commentary;
import com.kh.forest.helpcenter.model.vo.Notice;
import com.kh.forest.helpcenter.model.vo.PageInfo;
import com.kh.forest.helpcenter.model.vo.SearchCondition;

public interface HelpService {
	
	int getHelpSearchResultListCount(SearchCondition sc);

	ArrayList<Notice> searchHelpResultList(SearchCondition sc, PageInfo pi);

	ArrayList<Commentary> insertCommentary(Commentary reply);

	int getHelpListCount();

	ArrayList<Notice> insertHelp(Notice notice, PageInfo pi);

	ArrayList<Notice> recoverHelpList(PageInfo pi);

	ArrayList<Notice> helpDetailSelectList(Notice notice);

	Notice comparePassword(Notice notice);


}
