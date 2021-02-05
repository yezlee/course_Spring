package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.validator.UserVoValidator;
import oracle.net.aso.h;

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
	
	@RequestMapping(path = "allUserTiles", method = RequestMethod.GET)
	public String allUserTiles(Model model) {
		
		List<UserVo> userList = userService.selectAllUsers();
		
		
		model.addAttribute("userList", userList);
		return "tiles.user.allUser";
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
	
	
	@RequestMapping("pagingUserTiles")
	public String pagingUserTiles(PageVo pageVo, Model model) {
		
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		
		List<UserVo> userList = (List<UserVo>)map.get("userList");
		int userCnt = (int)map.get("userCnt");
		logger.debug("userCnt : {}" ,userCnt);
		logger.debug("pageVo.getPageSize() : {}" ,pageVo.getPageSize());
		int pagination = (int)Math.ceil((double)userCnt/pageVo.getPageSize());
		
		model.addAttribute("userList", userList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageVo", pageVo);
		
		//tiles-definition에 설정한 name
		return "tiles.user.pagingUser";
	}
	
	
	//사용자 리스트가 없는 상태의 화면만 응답으로 생성
	@RequestMapping("pagingUserAjaxView")
	public String pagingUserAjaxView() {
		
		
		return "tiles.user.pagingUserAjax";
	}
	
	

	
	@RequestMapping("pagingUserAjax")
	public String pagingUserAjax(PageVo pageVo, Model model) {
		
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		
		List<UserVo> userList = (List<UserVo>)map.get("userList");
		int userCnt = (int)map.get("userCnt");
		logger.debug("userCnt : {}" ,userCnt);
		logger.debug("pageVo.getPageSize() : {}" ,pageVo.getPageSize());
		int pagination = (int)Math.ceil((double)userCnt/pageVo.getPageSize());
		
		model.addAttribute("userList", userList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageVo", pageVo);
		
		//tiles-definition에 설정한 name
		return "jsonView";
	}
	
	
	
	
	@RequestMapping("pagingUserAjaxHtml")
	public String pagingUserAjaxHtml(PageVo pageVo, Model model) {
		
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		
		List<UserVo> userList = (List<UserVo>)map.get("userList");
		int userCnt = (int)map.get("userCnt");
		logger.debug("userCnt : {}" ,userCnt);
		logger.debug("pageVo.getPageSize() : {}" ,pageVo.getPageSize());
		int pagination = (int)Math.ceil((double)userCnt/pageVo.getPageSize());
		
		model.addAttribute("userList", userList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageVo", pageVo);
		
		//tiles-definition에 설정한 name
		return "/user/pagingUserAjaxHtml";
		
		/*
		 *  pagingUserAjaxHtml ==> /WEB-INF/view/user/pagingUserAjaxHtml.jsp
		 * 
		 */
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("user")
	public String user(Model model, String userid) {
		
		userService.selectUser(userid);
		model.addAttribute("user", userService.selectUser(userid));
		return "tiles.user.user";
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
		return "tiles.user.registUser";
	}
	
	//BindingResult객체는 커맨드객체 바로 뒤에 인자로 기술해야한다.
	//validator의두번째 인자가 BindingResult가 되는것
	@RequestMapping(path="registUser", method = RequestMethod.POST)
	public String registUser(@Valid UserVo userVo, BindingResult result, MultipartFile photo, Model model) {
		//커맨드 객체?? 뒤에 바인딩객체 타입이 따라야한다??????
		//명령객체 uservo뒤에 바인딩 객체가 바로 뒤에 와야함. 이건 순서가 중요!!!!!!!!!!!!!
		
		//@Valid 이걸 쓰면서 아래코드를 지워도 된다. 
		//new UserVoValidator().validate(userVo, result);
		//여기서 부르면 이제 컨테이너에 담긴다?? 참조니까??
		
		//만약 에러면 등록페이지로 다시 돌아가라!
		if(result.hasErrors()) {
			logger.debug("result has error!!!!!!!!!!!!!!");
			return "user/registUser";
		}
		
		
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
	
	
	
	@RequestMapping("excelDownload")
	public String excelDownload(Model model) {
		
//		SELECT b.comments
//		FROM user_tab_columns a, user_col_comments b
//		WHERE a.table_name = b.table_name
//		AND a.column_name = b.column_name
//		AND a.table_name = 'EMP'
//		ORDER BY column_id;
		//이 쿼리를 하기가 귀찮아서 그냥 직접 넣는다
		
		
		List<String> header = new ArrayList<String>();
		header.add("사용자 아이디");
		header.add("사용자 이름");
		header.add("사용자 별명");
				
		model.addAttribute("header", header);
		model.addAttribute("data", userService.selectAllUsers());
		
		
		return "userExcelDownloadView";
	}
	
	
	// localhost/user/profile
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
		
		
		resp.setContentType("image");
		
		UserVo userVo = userService.selectUser(userid);
		String path="";
		
		if(userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
		}else {
			path = userVo.getRealfilename();
		}
		
		logger.debug("path : {}" , path);
		
		
		
		FileInputStream fis;
		try {
			
			fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				sos.write(buff);
			}
			
			fis.close();
			sos.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	@RequestMapping("profileDownload")
	public void profileDownload(String userid, HttpServletResponse resp, HttpServletRequest req) {
		
		UserVo userVo = userService.selectUser(userid);
		
		String path="";
		String filename = "";
		
		if(userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png"); 
			filename="unknown.png";
		}else {
			path = "d:\\upload\\" + userVo.getRealfilename();
			filename="userVo.getFilename()";
		}
		
		//컨텐트타입을 알면 셋해주는게 좋지. 근데 모르면 안해도됨. 요즘에 똑똑해서 알아서 잡아줌. 
		resp.setHeader("Content-Disposition", "attachment; filename=" + filename);
		
		logger.debug("path : {}" , path);
		
		
		FileInputStream fis;
		try {
			
			fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				sos.write(buff);
			}
			
			fis.close();
			sos.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
}






