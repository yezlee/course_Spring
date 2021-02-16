package kr.or.ddit.batch.yogurt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import kr.or.ddit.yogurt.model.CycleVo;
import kr.or.ddit.yogurt.model.DailyVo;

public class YogurtProcessor implements ItemProcessor<CycleVo, List<DailyVo>>{

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Value("#{jobParameters[dt]}")
	private Date dt;
	
	@Override
	public List<DailyVo> process(CycleVo item) throws Exception {
		//dt : 202102, item : cid-1, pid-100, day-2, cnt-1 
		// 			위에거를 가지고 로직을 통해서
		//			이렇게 만들어야함
		//==> 				  cid-1, pid-100, dt-20210201, cnt-1
		//==> 				  cid-1, pid-100, dt-20210208, cnt-1
		//==> 				  cid-1, pid-100, dt-20210215, cnt-1
		//==> 				  cid-1, pid-100, dt-20210222, cnt-1
		
		// 1일부터 28일까지 루프를 돌면서
		// if(요일 == item.요일과 같은지 체크)
		//		해당 일자로 일 실적(하루실적?) 데이터를 생성
		
		
		
		//======================
		//해당 년월의 마지막 날짜(date)
		//해당 년월의 첫번째 날짜 - 1일 (date)
		
		//현재 날짜 시간
		Calendar cal = Calendar.getInstance();
		
		//dt가 갖고있는 시간날짜가 
		cal.setTime(dt);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDt = cal.getTime();
		//20210201 02:00
		
		
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		
		//20210201 02:00:00.5
		//Date startDt = cal.getTime();
		
		List<DailyVo> dailyVoList = new ArrayList<DailyVo>(); 
		while(endDt.compareTo(cal.getTime()) > 0) {
			
			//20210201 ==> 주간요일
			if(item.getDay() == cal.get(Calendar.DAY_OF_WEEK)) {
				//cid, pid, dt(yyyyMMdd), cnt
				dailyVoList.add(new DailyVo(item.getCid(), item.getPid(), sdf.format(cal.getTime()), item.getCnt()));
			}
			
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		}
		
		
		
		return dailyVoList;
	}

}
