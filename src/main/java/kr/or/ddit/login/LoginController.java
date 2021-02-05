package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@ImportResource("classpath:/kr/or/ddit/config/spring/datasource-context.xml")
@RequestMapping("/login")
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Resource(name="userService")
	private UserService userService;
	
	
	
	//특정 파라미터의 값이 정해진 값과 일치할때만 해당 요청을 처리
	@RequestMapping(path="view", method = {RequestMethod.GET})
	public String view() {
		
		logger.debug("loginController.view()");
		
		return "login";
		
	}
	
	//근데 인젝션할때 타입이 뭐든 잘되긴함. 원래 파라미터 반환은 string인데
	//스프링에서 인젝션할때 아무타입이나 가능했음 데이트랑, 인트도 했었음
//	@RequestMapping("process")
	public String process(String userid, String pass, int price) {
		
		logger.debug("userid : {}", userid);
		logger.debug("pass : {}", pass);
		logger.debug("price : {}", price);
		
		return "";
	}
	
	
	@RequestMapping(path = "process",method = RequestMethod.POST)
	public String process(UserVo userVo, HttpSession session, RedirectAttributes ra) {
		logger.debug("userVo : {}", userVo);
		
		UserVo dbUser = userService.selectUser(userVo.getUserid());
		
		if(dbUser!=null && userVo.getPass().equals(dbUser.getPass())) {
			session.setAttribute("S_USER", dbUser);
			return "main";
		}else {
			
			//내부적으로 session을 사용하여 속성을 저장
			//리다이렉트 처리가 완료되면 스프링 프레임워크에서 자동으로 session 제거
			ra.addFlashAttribute("msg", "잘못된 정보 입니다.");
			
			//일반속성을 추가한 경우 : addAttribute
			//리다이렉트 페이지의 파라미터로 전달된다.
			ra.addAttribute("userid", "brown");
			
			return "redirect:/login/view";
		}
	}
	
	
}
