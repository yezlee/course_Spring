package kr.or.ddit.login;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.test.config.WebTestConfig;

public class LoginControllerTest extends WebTestConfig{

	@Test
	public void viewTest() throws Exception{ //아래코드는 예외가 발생하는 코드라서 이거 안써주면 에러뜸
		
		//사용자는 이렇게 localhost/login/view 라고 쳐서 들어올거야 get방식으로
		mockMvc.perform(get("/login/view"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
	}

	
	@Test
	public void processSucessTest() throws Exception {
		mockMvc.perform(post("/login/process")
							.param("userid", "brown")
							.param("pass", "brownPass")
							.param("price", "1000"))
				.andExpect(view().name("main"))
				.andDo(print());
	}
	
	@Test
	public void processFailTest() throws Exception {
		mockMvc.perform(post("/login/process")
							.param("userid", "brown")
							.param("pass", "rownPass")
							.param("price", "1000"))
				.andExpect(view().name("redirect:/login/view"))
				.andDo(print());
	}
	
	
	
	
	
}
