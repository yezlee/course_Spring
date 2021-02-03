package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;


//controller에서 개발자가 model객체에 realFilename = 실제 디스크에 저장된 경로와 파일명을,
//							 		 filename = 은 업로드 당시의 파일명을 속성에 저장 하자고 약속하자.		
public class FileDownloadView extends AbstractView{

	
	//FileDownloadView extends AbstractView{ d요것만 하면 파일다운르드하는 응답을 만들수있어
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		//Map<String, Object> model 모델 안에서 관련된 맵?
		//  d:\\upload\\sally.png
		String realFilename = (String)model.get("realFilename");
		String filename = (String)model.get("filename");

		
		//ProfileDownloadServlet.java 에서 썼던 코드
		//컨텐트타입을 알면 셋해주는게 좋지. 근데 모르면 안해도됨. 요즘에 똑똑해서 알아서 잡아줌. 
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		
		ServletOutputStream sos = response.getOutputStream();
		FileInputStream fis = new FileInputStream(new File(realFilename));
		
		byte[] buf = new byte[512];
		
		while(fis.read(buf) != -1) {
			sos.write(buf);
		}
		
		fis.close();
		sos.close();
		
	}

}
