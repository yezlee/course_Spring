package kr.or.ddit.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.or.ddit.user.model.UserVo;

public class UserVoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserVo.class.isAssignableFrom(clazz) ;
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserVo userVo = (UserVo)target;
		//저 타겟이 uservo이고 자주 쓸거같아서 미리 형변환
		
		//체크하고 싶은 검증 로직을 기술하고, 
		//에러로 판단되는 상환을 체크하여 Errors errors 이 자리에 에러를 기술/추가 하면 된다.
		
		//userid 길이가 5글자 이상(5글자 허용) userid는 field, length는 error이름을 내가 준거
		if(userVo.getUserid().length() < 5) {
			errors.rejectValue("userid", "length");
		}
		
		
		
	}

}
