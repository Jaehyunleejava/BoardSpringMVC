package com.jh.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jh.service.BoardService;
import com.jh.vo.BoardVO;
import com.jh.vo.Criteria;
import com.jh.vo.PageMaker;


//controller 객체에 사용!!!
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	/*
	 * 만약 반환 값이 없으면 RequestMapping에 뒤에 .jsp가 붙어서 그 jsp를 보여준다!
	 */
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception{
		logger.info("register get...........");
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		logger.info("register post..........");
		logger.info(board.toString());
		
		service.regist(board);
		
		//매개변수로 모델 말고 rttr써서 리다이렉트 시점에 한번만 데이터를 사용해서 uri에 보이지 않는 숨겨진 데이터의 형태로 전달
		//model.addAttribute("result","success");
		rttr.addFlashAttribute("msg", "success"); 
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/listAll", method= RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		
		logger.info("show all list.........");
		model.addAttribute("list", service.listAll());
	}
	
	@RequestMapping(value="/read", method= RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception{
		
		//아무런 이름 없이 데이터를 넣으면 자동으로 클래스의 이름을 소문자로 시작해서 사용한다.
		//즉, 'boardVO로' 저장된다.
		model.addAttribute(service.read(bno)); 
	}
	
	@RequestMapping(value="/readPage", method= RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, @ModelAttribute("cri")Criteria cri ,Model model) throws Exception{

		model.addAttribute(service.read(bno)); 
	}
	
	@RequestMapping(value="/remove", method= RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception{
		
		service.remove(bno);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/removePage", method= RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, Criteria cri,
			RedirectAttributes rttr) throws Exception{
		
		service.remove(bno);
		
		rttr.addAttribute("perPageNum", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/listPage";
	}
	
	
	//이 게시글 수정GET 방식은 수정 페이지로 이동만 하게 하는 메서드. 이때 다시 원래의 게시물 데이터를 읽어와 Model에 전달한다.
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception{
	
		model.addAttribute("boardVO",service.read(bno));
	}
	
	@RequestMapping(value="/modifyPage", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno")int bno,
			@ModelAttribute("cri")Criteria cri, Model model) throws Exception{
	
		model.addAttribute("boardVO",service.read(bno));
	}
	
	

	//게시글 수정 페이지에서 삭제시키는 메서드
	@RequestMapping(value="/modifyPage", method= RequestMethod.POST)
	public String modifyPOST(BoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception{
		
		service.modify(board);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/sboard/list";
	}
	
	//스프링 MVC의 컨트롤러는 특정 URL에 해당하는 메소드를 실행 할때, 파라미터의 타입을 보고, 해당 객체를 자동으로 생성해 낸다.
	//파라미터가 자동으로 수집되기 때문에, 바로 이전에 만든 Criteria라는 클래스를 그대로 사용할 수 있다.
	@RequestMapping(value="/listCri", method = RequestMethod.GET)
	public void listAll(Criteria cri, Model model)throws Exception{
		logger.info("show list Page with Criteria..................");
		
		model.addAttribute("list", service.listCriteria(cri));
	}
	
	@RequestMapping(value="/listPage", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri")Criteria cri, Model model) throws Exception{
		/*
		 * 스프링에서는 커맨드 객체(Command Object)를 지원하여 
		 * HTTP에서 들어오는 각 속성값들을 자동적으로 커맨드 객체에 바인딩하여 처리할 수 있게 하였다. 
		 * listPage?page=3, page=3이 cri에 page에 getPage()로 들어간다.
		 * 
		 * listPage()의 파라미터 설명 Criteria cri를 파라미터로 사용하고, 
		 * 목록 데이터를 잠아서 Model에 저장하여 작업한뒤 PageMaker를 구성하여 Model에 담는 작업
		 * 
		 */
		
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		//pageMaker.setTotalCount(131);
		
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMaker", pageMaker);
		
	}
	
}


