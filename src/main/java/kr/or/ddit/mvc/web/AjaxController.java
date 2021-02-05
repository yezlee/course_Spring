package kr.or.ddit.mvc.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.or.ddit.user.model.UserVo;

@RequestMapping("ajax")
@Controller
public class AjaxController {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	//이건 @RequestMapping 얘보다 먼저 실행한다.
	// 얘가 반환하는 어떤 값을 모델 객체에 넣어준다.
	@ModelAttribute(name = "")
	public List<String> rangers(){
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		rangers.add("james");
		rangers.add("moon");
		
		return rangers;
	}
	
	
	
	@RequestMapping("view")
	public String view() {
		return "ajax/ajaxView";
	}
	
	@RequestMapping("form")
	public String form(UserVo userVo) {
		logger.debug("userVo : {}", userVo);
		return "jsonView";
	}
	
	
	//진짜로 아작스는 아니지만 이름을 통일성있게 하려고 ㅇ이케줌
	//localhost/ajax/jsonView
	
	//json응답을 만들어 내기 위해서 하는거고
	//json응답을 만들어내는 방법이 한가지가 아니다.
	//view name을 리턴할거야
	//컨트롤러가 반환하는 view name과 동일한 이름의 스프링 빈이 있으면 해당 스프링 빈을 view 객체로 사용한다.
	//==> application-context.xml에서 만든 뷰객체를 이용하려면 return "jsonView"; 반납 해야해.
	@RequestMapping("jsonView")
	public String jsonView() {
		
		//@ModelAttribute 이거 실행하면서 모델객체에 반환해 주니까 이제 모델을 안써도돼!! 이건 프레임워크니까 가능한거고
		// 이 룰을, 틀을 따라야지 - 프레임워크
		//model.addAttribute("rangers", rangers);
		
		return "jsonView";
		
		
		//MappingJackson2JsonView이걸 안하면 할때마다 jsp 다 만들어야함
		
	}
	
	//이렇게 뷰객체 반납하는게 가능한데, 별로 바람직하진 않어. 그냥xml에 한번에 정의? 해놓는게 낫지
	@RequestMapping("jsonViewViewObj")
	public View jsonViewViewObj() {
		return new MappingJackson2JsonView();
	}
	
	
	@RequestMapping("jsonViewMav")
	public ModelAndView jsonViewMav() {
		ModelAndView mav = new ModelAndView();
		//지금 하는거 과거스탈
		
		mav.setViewName("jsonView");
		//이러면 똑같이 jsonView 이거 반납하는거야

		return mav;
	}
	
	
	
	
	
	
	
	
}
