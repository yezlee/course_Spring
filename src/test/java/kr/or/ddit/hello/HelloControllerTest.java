package kr.or.ddit.hello;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

/*
 *  java - gui swing, awt, java fx, swt
 * 
 */

/*@ContextConfiguration(locations = {"classpath:/kr/or/ddit/config/spring/application-context.xml", "classpath:/kr/or/ddit/config/spring/root-context.xml"})
@WebAppConfiguration	//스프링 환경을 web기반의 application Context로 생성
@RunWith(SpringJUnit4ClassRunner.class)*/
public class HelloControllerTest extends WebTestConfig {

	//@Resource(name = "helloController")
	
	//스프링빈에 등록되어있는 타입을 보고 찾아가. 스프링컨테이너에 저장되어있는 타입이 몇개 있는데 거기에 HelloController 타입은 딱 하나야 그래서 @Autowired가 가능함
	//만약 동일한 타입의 스프링 빈이 여러개 있을 경우 @Qulifier 어노테이션을 통해 
	/*
	 * @Autowired private WebApplicationContext context;
	 * 
	 * private MockMvc mockMvc;
	 * 
	 * @Before public void setup() { mockMvc =
	 * MockMvcBuilders.webAppContextSetup(context).build(); }
	 */
	
	//localhost/hello/view
	@Test
	public void viewTest() throws Exception{
	    MvcResult mvcResult = mockMvc.perform(get("/hello/view"))
								.andExpect(status().isOk())
								.andExpect(view().name("hello"))
								.andExpect(model().attributeExists("userVo"))
								.andDo(print())
								.andReturn();
		
	    ModelAndView mav = mvcResult.getModelAndView();
//	    assertEquals("기대값", "실제값");
	    assertEquals("hello", mav.getViewName());
	    UserVo userVo = (UserVo)mav.getModel().get("userVo");
	    assertEquals("브라운", userVo.getUsernm());
	    
	    
	    
		//gwt가 한줄에 다들어갔어. 원래 gwt해서 따로따로 부르는데 위에 코드는 빌드업된 한문장임.  
	}

}












































