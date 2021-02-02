package kr.or.ddit.user.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("user")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userService")
	private UserService userService;
	
	@RequestMapping(path = "allUser", method = RequestMethod.GET)
	public String allUser(Model model) {
		
		List<UserVo> userList = userService.selectAllUsers();
		
		
		model.addAttribute("userList", userList);
		return "/user/allUser";
	}
	
	
	//@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1", name="p") int page,
							 @RequestParam(defaultValue = "5") int pageSize,
							 int price) {
		logger.debug("page : {} , pageSize : {}, price : {} ", page, pageSize, price );
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		
		return "";
	}
	
	@RequestMapping("pagingUser")
	public String pagingUser(PageVo pageVo, Model model) {
		
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		
		List<UserVo> userList = (List<UserVo>)map.get("userList");
		int userCnt = (int)map.get("userCnt");
		logger.debug("userCnt : {}" ,userCnt);
		logger.debug("pageVo.getPageSize() : {}" ,pageVo.getPageSize());
		int pagination = (int)Math.ceil((double)userCnt/pageVo.getPageSize());
		
		model.addAttribute("userList", userList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageVo", pageVo);
		
		return "user/pagingUser";
	}
	
	
	@RequestMapping("user")
	public String user(Model model, String userid) {
		
		userService.selectUser(userid);
		model.addAttribute("user", userService.selectUser(userid));
		return "/user/user";
	}
	
	
	@RequestMapping(path="userModify", method = RequestMethod.GET)
	public String userModify(Model model, String userid) {
		
		userService.selectUser(userid);
		model.addAttribute("user", userService.selectUser(userid));
		return "/user/userModify";
	}
	
	
	@RequestMapping(path="userModify", method = RequestMethod.POST)
	public String userModify2(Model model, UserVo userVo, MultipartFile profile) {
		
		userVo.setFilename(profile.getOriginalFilename());
		userVo.setRealfilename(profile.getOriginalFilename());
		
		try {
			profile.transferTo(new File("d:\\upload\\" + profile.getOriginalFilename()));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		userService.modifyUser(userVo);
		
		return "redirect:/user/pagingUser";
	}
	
	
	
	@RequestMapping(path = "userDelete", method = RequestMethod.POST)
	public String userDelete(String userid) {
		userService.deleteUser(userid);
		return "redirect:/user/pagingUser";
	}
	

	
	@RequestMapping("registeUserView")
	public String registUserView() {
		return "user/registUser";
	}
	
	
	@RequestMapping(path="registUser", method = RequestMethod.POST)
	public String registUser(UserVo userVo, MultipartFile photo) {
		
		userVo.setFilename(photo.getOriginalFilename());
		userVo.setRealfilename(photo.getOriginalFilename());
		
		try {
			photo.transferTo(new File("d:\\upload\\" + photo.getOriginalFilename()));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		userService.insertUser(userVo);
		
		
		return "redirect:/user/pagingUser";
	}
	
	
}






