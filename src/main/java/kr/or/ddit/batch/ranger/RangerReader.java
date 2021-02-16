package kr.or.ddit.batch.ranger;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

//처리할 데이터를 조회하는 역할
public class RangerReader implements ItemReader<String>{
	
	private List<String> rangers;
	private int index = 0;
	
	private static final Logger logger = LoggerFactory.getLogger(RangerReader.class);
	
	//디비랑 연결할게 아니어서 그냥 이렇게 데이터 넣어주고.
	public RangerReader() {
		rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("cony");
		rangers.add("moon");
		rangers.add("james");
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		//더 이상 읽을 데이터가 없다라는걸 알려주는 방법 : null을 리턴하면 됨.
		if(rangers.size() > index) {
			
			String ranger = rangers.get(index);
			index++;
			
			logger.debug("reader : {}", ranger);
//			return rangers.get(index++);
			return ranger;
			
		}else {
			index = 0;
			return null;
		}
	}
	

}
