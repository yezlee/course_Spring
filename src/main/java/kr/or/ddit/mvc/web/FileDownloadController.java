package kr.or.ddit.mvc.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("file")
@Controller
public class FileDownloadController {

	@Resource(name ="userService")
	private UserService userService;
	
	
	@RequestMapping("/fileDownloadView")
	public String fileDownloadView(String userid, Model model) {
		//1. 다운로드 파일의 경로 => realFilename
		//2. 다운로드 시 보여줄 파일 명 => filename
		//1,2를 model에 넣어준다.
		//userid 파라미터를 보낸다고 가정하고 파라미터를 이용해서 해당 사용자의 사진정보(그게 두개지 realFilename,filename)
		//두개 사진정보를 조회해서 처리
		
		UserVo userVo = userService.selectUser(userid);
		
		model.addAttribute("realFilename", userVo.getRealfilename());
		model.addAttribute("filename", userVo.getFilename());
		
		return "fd";
		
		
		//위에 코드 이렇게 해주고
		//디비에다가 
		//sally	샐리121	sallyPass	2020/10/21  00:00:00	병아리	sally.png	d: \ upload\sally.png
		//이렇게 넣어주고
		//http://localhost/file/fileDownloadView?userid=sally
		//이렇게 하면 사진 다운로드됨
	}

	//request, reponse, session 이거 3개는 스프링이  객체를 미리 얻어올수있다 OutputStream os 이런거 http - 써가지고 안해도되고
	
	@RequestMapping("fileDownload")
	public void fileDownload(HttpServletResponse response, String userid) {
		
		UserVo userVo = userService.selectUser(userid);
		
		
		//Map<String, Object> model 모델 안에서 관련된 맵?
		//  d:\\upload\\sally.png
		String realFilename = userVo.getRealfilename();
		String filename = userVo.getFilename();

		
		//ProfileDownloadServlet.java 에서 썼던 코드
		//컨텐트타입을 알면 셋해주는게 좋지. 근데 모르면 안해도됨. 요즘에 똑똑해서 알아서 잡아줌. 
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		
		ServletOutputStream sos;
		
		
		try {
			sos = response.getOutputStream();
			
			FileInputStream fis = new FileInputStream(new File(realFilename));
			
			byte[] buf = new byte[512];
			
			while(fis.read(buf) != -1) {
				sos.write(buf);
			}
			
			fis.close();
			sos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//http://localhost/file/fileDownload?userid=sally
		//이걸로 하면 사진 다운 받아짐.
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


























