package com.jh.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jh.service.BoardService;
import com.jh.vo.BoardVO;
import com.jh.vo.PageMaker;
import com.jh.vo.SearchCriteria;

@Controller
@RequestMapping("/sboard")
public class SearchBoardController {
	
	/*
	 * 만약 반환 값이 없으면 RequestMapping에 뒤에 .jsp가 붙어서 그 jsp를 보여준다!
	 * 
	 * 컨트롤러 메서드에서 어노테이션이 없이 작성한 매개변수는 @RequestParam과 @ModelAttribute가 생략된것이다.
	 * 전자는 1:1 로 매칭하여 변수에 대입되고 후자는 vo나 dto(클래스)에 담겨지게 된다.
	 * @ModelAttribute 어노테이션이 붙은 객체가(여기서는 MemberInfo 객체) 자동으로 Model 객체에 추가되고 
	 * 따라서 MemberInfo 객체가 .jsp 뷰단까지 전달이 된다.
	 */
	
	
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private BoardService service;
	
	//목록
	@RequestMapping(value="/list", method= RequestMethod.GET)
	public void listPage(@ModelAttribute("cri")SearchCriteria cri, Model model) throws Exception{
		/*
		 * 스프링에서는 커맨드 객체(Command Object)를 지원하여 
		 * HTTP에서 들어오는 각 속성값들을 자동적으로 커맨드 객체에 바인딩하여 처리할 수 있게 하였다. 
		 * list?page=3, page=3이 cri에 page에 getPage()로 들어간다.
		 * 
		 * listPage()의 파라미터 설명 SearchCriteria cri를 파라미터로 사용하고, 
		 * 목록 데이터를 담아서 Model에 저장하여 작업한뒤 PageMaker를 구성하여 Model에 담는 작업
		 * 
		 */
		logger.info(cri.toString());
		
		//목록 데이터 list
		model.addAttribute("list", service.listSearchCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		//목록 데이터 개수
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	//상세보기 창
	@RequestMapping(value="/readPage", method= RequestMethod.GET)
	public void read(@RequestParam("bno")int bno,
			@ModelAttribute("cri")SearchCriteria cri, Model model) throws Exception{
		
		model.addAttribute(service.read(bno));
	}
	
	//상세보기에서 삭제
	@RequestMapping(value="/removePage", method= RequestMethod.POST)
	public String remove(@RequestParam("bno")int bno, SearchCriteria cri,
			RedirectAttributes rttr) throws Exception{
		
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/sboard/list";
	}
	
	//상세보기에서 수정창이동
	@RequestMapping(value="/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("bno")int bno,
			@ModelAttribute("cri")SearchCriteria cri, Model model) throws Exception{
	
		model.addAttribute("boardVO",service.read(bno));
	}
	
	//상세보기에서 수정
	@RequestMapping(value="/modifyPage", method= RequestMethod.POST)
	public String modifyPagingPOST(BoardVO board, SearchCriteria cri, RedirectAttributes rttr) throws Exception{
		
		logger.info(cri.toString());
		service.modify(board);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		logger.info(rttr.toString());
		
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public void registGET() throws Exception{
		
		logger.info("register get...........");
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		logger.info("register post..........");
		logger.info(board.toString());
		
		service.regist(board);
		
		//매개변수로 모델 말고 rttr써서 리다이렉트 시점에 한번만 데이터를 사용해서 uri에 보이지 않는 숨겨진 데이터의 형태로 전달
		//model.addAttribute("result","success");
		rttr.addFlashAttribute("msg", "success"); 
		
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value="/getAttach/{bno}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno")Integer bno)throws Exception{
		
		return service.getAttach(bno);
	}
}
