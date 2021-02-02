package kr.or.ddit.hello;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.service.UserService;

@RequestMapping("hello")
@Controller
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	
	@Resource(name = "userService")
	private UserService userService;
	
	
	// localhost/view/view.do 
	// localhost/hello/view ==> localhost/view
	// 스프링에서 컨트롤러 만드는 어노테이션 @RequestMapping("Hello")
	// 위 리퀘스트맵핑에 hello가 없으면 @RequestMapping("hello/view")
	@RequestMapping("view")
	public String view(Model model) {
		
		logger.debug("HelloController.view() : {}", userService.selectUser("brown"));
		
		//이게 지금은 돼도 다른거할때 안될수있어
		//request.setAttribute("userVo", userService.getUser("brown"));
		model.addAttribute("userVo", userService.selectUser("brown"));
		
		return "hello";
		//리턴해주는게 jsp파일이름이랑 같아야함
	}
}
