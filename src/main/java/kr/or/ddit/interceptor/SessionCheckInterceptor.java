package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{
	
	
	
	//이건 지금 로그인 안한사람은 다른거 유저리스트이런거 못보게 하려고
	//그러니까 전에 만 하면 됨, prehandle
	//필터는 uri까지 로직에서 직접 다 설정해주고
	//인터셉터는 객체지향이지. -실제실행할 로직이랑 설정파일이랑 분리
	//로직은 딱 요렇게만 해놓고 설정파일에 uri를 적고
	// 이렇게 분리해놓으면 한쪽에 몰아놓는것보다 복잡해지긴 하지만 나중에 시스템이커졌을때 유지보수에 좋음
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 정상 접속인지 확인하는 로직 : session에 S_USER 속성이 있는지 검사
		if(request.getSession().getAttribute("S_USER") == null) {
			response.sendRedirect("/login/view");
			return false;
		}
		
		
		return true;
	}
}
