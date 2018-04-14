package com.kh.forest.helpcenter.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.forest.helpcenter.model.service.HelpService;
import com.kh.forest.helpcenter.model.service.sha512;
import com.kh.forest.helpcenter.model.vo.Commentary;
import com.kh.forest.helpcenter.model.vo.Notice;
import com.kh.forest.helpcenter.model.vo.PageInfo;
import com.kh.forest.helpcenter.model.vo.SearchCondition;

@Controller
public class helpCenter {

	@Autowired
	private HelpService hs;
 
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
 
	@Autowired
	private sha512 sha512;

	
	// 댓글, 회원 등록시 다 유효성 검사, 에러 및 트랜잭션 처리만 하면끝 (AOP)
	
	// 1 메인 로딩 (get) (매개변수 x) // 헬프센터 메인은 디비에서 가져올 것 없음. 완료
	@RequestMapping(value = "helpCenter.help", method = RequestMethod.GET)
	public ModelAndView helpCenter(ModelAndView mv) {

		try {
			System.out.println("helpCenter");
			mv.setViewName("/helpCenter");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	// 2 문의 등록 버튼 클릭(get) // 여기도 디비에서 가져올 것 없음. 완료
	@RequestMapping(value = "RegisterHelp.help", method = RequestMethod.GET)
	public ModelAndView RegisterHelp(ModelAndView mv) {

		try {
			System.out.println("RegisterHelp");
			mv.setViewName("/RegisterHelp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	// 3 문의 등록 폼 제출 & 리다이렉트하여 문의 등록 리스트 호출(post) // 인서트와 셀렉트 따로
	@RequestMapping(value = "PersonalInquiryList.help", method = RequestMethod.POST)
	public String PersonalInquiryList(String NOTICE_TITLE, String NOTICE_CONTENT, String NOTICE_PWD, 
			HttpServletRequest request, Notice notice) {
 
		try {
			System.out.println(NOTICE_TITLE);
			System.out.println(NOTICE_CONTENT);
			System.out.println(NOTICE_PWD);

			notice.setNOTICE_PWD(sha512.encryptSHA512(NOTICE_PWD));
 
			 
			notice.setNOTICE_TITLE(NOTICE_TITLE);
			notice.setNOTICE_CONTENT(NOTICE_CONTENT);

   
			int result = hs.insertHelp(notice);
   
			// 리다이렉트를 쓰면 되니까 한번에 한 동작씩만 하게 하면 됨.

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:InquiryList.help";

	}

	// 4 메인 검색 (post) 완료
	@RequestMapping(value = "HelpCenterSearch.help", method = RequestMethod.POST)
	public ModelAndView HelpCenterSearch(ModelAndView mv, String searchCondition, String searchContent, Notice notice,
			HttpServletRequest request) {

		try {

			// 객체 클래스 활용해서 여기에 검색한 값 저장 후 전송
			SearchCondition sc = new SearchCondition();

			// select name
			System.out.println(searchCondition);

			// 빈칸 입력 검색값
			System.out.println(searchContent);

			// 제목(title)으로 검색할 수도
			// 내용(content)으로 검색할 수도

			// 객체에 저장
			if (searchCondition.equals("title")) {
				sc.setTitle(searchContent);
			}
			if (searchCondition.equals("content")) {
				sc.setContent(searchContent);
			}

			// 페이징 부분 변수 선언
			int currentPage;
			int limit;
			int maxPage;
			int startPage;
			int endPage;

			// 초기값 대입
			currentPage = 1;
			limit = 10;

			if (request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}

			int listCount;

			// 검색 선택 조건 객체를 매개변수로
			// count를 리턴받아야함
			listCount = hs.getHelpSearchResultListCount(sc);
			maxPage = (int) ((double) listCount / limit + 0.9);
			startPage = (((int) ((double) currentPage / limit + 0.9)) - 1) * limit + 1;
			endPage = startPage + limit - 1;

			if (maxPage < endPage) {
				endPage = maxPage;
			}

			// pageInfo 객체에 페이징에 필요한 변수들 다 저장.

			PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			ArrayList<Notice> list = hs.searchHelpResultList(sc, pi);

			mv.addObject("list", list);
			mv.addObject("pi", pi);
			mv.setViewName("/HelpCenterSearch");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	// 6 문의 등록 상세 페이지에서 댓글(post) (ajax) ( ) 한줄씩 나오게 수정(댓글 깊이 만들기) 1
	@RequestMapping(value = "Reply.help", method = RequestMethod.POST)
	public void ReplyHelp(ModelAndView mv, String ReplyHelp, Commentary reply, HttpServletResponse response) {

		// 맵형식
		ObjectMapper mapper = new ObjectMapper();

		try {
			System.out.println(ReplyHelp);

			reply.setComment_content(ReplyHelp);
			ArrayList<Commentary> replyList = hs.insertCommentary(reply);

			System.out.println(replyList);
			response.getWriter().print(mapper.writeValueAsString(replyList));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 7 일반 문의 완료
	@RequestMapping(value = "GeneralFAQ.help", method = RequestMethod.GET)
	public ModelAndView GeneralFAQ(ModelAndView mv) {

		try {
			System.out.println("GeneralFAQ");
			mv.setViewName("/GeneralFAQ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;

	}

	// 8 작가문의 완료
	@RequestMapping(value = "AuthorFAQ.help", method = RequestMethod.GET)
	public ModelAndView AuthorFAQ(ModelAndView mv) {

		try {
			System.out.println("AuthorFAQ");
			mv.setViewName("/AuthorFAQ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;

	}

	// 9 독자문의 완료
	@RequestMapping(value = "ReaderFAQ.help", method = RequestMethod.GET)
	public ModelAndView ReaderFAQ(ModelAndView mv) {

		try {
			System.out.println("ReaderFAQ");
			mv.setViewName("/ReaderFAQ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;

	}

	//10  문의 등록 리스트에서 상세 페이지 (get) select로 조회 완료
		@RequestMapping(value = "InquiryDetail.help", method = RequestMethod.GET)
		public ModelAndView InquiryDetail(ModelAndView mv, String NOTICE_NO, Notice notice) {

			try {
				notice.setNOTICE_NO(NOTICE_NO);
				ArrayList<Notice> list = hs.helpDetailSelectList(notice);
				
				System.out.println(list);
				mv.addObject("list", list);
				mv.setViewName("/InquiryDetail");

			} catch (Exception e) {
				e.printStackTrace();
			}
			return mv;

		}
	
	// 10 암호 모달 암호 입력시 (post 인데 SELECT ONE으로 조회) // 
	//암호 일치 x면 암호가 일치하지 않습니다 alert 뜨고 다시
 

	@RequestMapping(value = "comparePassword.help", method = RequestMethod.POST)
	public void comparePassword(String NOTICE_PWD, String NOTICE_NO, Notice notice, HttpServletResponse response) {

		try {
			 
			sha512 sha512 =new sha512(); 
			
			String encodePwd=sha512.encryptSHA512(NOTICE_PWD);
					
			System.out.println(encodePwd);
			System.out.println(NOTICE_NO);
			notice.setNOTICE_PWD(encodePwd);
			notice.setNOTICE_NO(NOTICE_NO);
			notice = hs.comparePassword(notice);
			
			String passwordCheck=notice.getNOTICE_CONTENT();
			
			System.out.println(notice);

			if (notice != null) {
				String check="암호가 맞습니다.";
				ObjectMapper mapper = new ObjectMapper();
				response.getWriter().print(mapper.writeValueAsString(passwordCheck));

			}



			// 에러 처리를 안 해서 그렇구나.

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 11 문의 목록으로 돌아갈 때 // 전체 셀렉트
	@RequestMapping(value = "InquiryList.help", method = RequestMethod.GET)
	public ModelAndView InquiryList(ModelAndView mv, HttpServletRequest request) {

		try {
			System.out.println("InquiryList");

			// 페이징 부분 변수 선언
			int currentPage;
			int limit;
			int maxPage;
			int startPage;
			int endPage;

			// 초기값 대입
			currentPage = 1;
			limit = 10;

			if (request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}

			int listCount;

			// 검색 선택 조건 객체를 매개변수로
			// count를 리턴받아야함
			listCount = hs.getHelpListCount();
			maxPage = (int) ((double) listCount / limit + 0.9);
			startPage = (((int) ((double) currentPage / limit + 0.9)) - 1) * limit + 1;
			endPage = startPage + limit - 1;

			if (maxPage < endPage) {
				endPage = maxPage;
			}

			// pageInfo 객체에 페이징에 필요한 변수들 다 저장.

			PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
  
			ArrayList<Notice> list = hs.recoverHelpList(pi);
			mv.addObject("list", list);
			mv.addObject("pi", pi);

			mv.setViewName("/PersonalInquiryList");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;

	}

}
