package kr.or.ddit.ioc.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConverter implements Converter<String, Date>{

	
	private String dateFormat;
	
	//xml파일에서 세터만 필요해서 만듦
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}



	//source : 2021-01-11 , 그리고 우린 암묵적으로 날짜 형식이 yyyy-mm-dd라고 하자
	@Override
	public Date convert(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
