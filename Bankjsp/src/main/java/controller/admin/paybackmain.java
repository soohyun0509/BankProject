package controller.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import model.DAO.AdminDAO;
import model.DAO.CustomerDAO;
import model.DAO.DAO;
import model.DAO.ServiceDAO;
import model.DTO.SedateDTO;
public class paybackmain extends Thread{
	
	public paybackmain() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void run() {
	
		check();
	}
	

	
	public void check() {
			while(true) {
				Calendar c= Calendar.getInstance();
				int minute= c.get(Calendar.MINUTE);
				int second=c.get(Calendar.SECOND);
				int milliSecond=c.get(Calendar.MILLISECOND);
				try {
					Thread.sleep(1000);  // 이게 시간이 흐르게 만들어서 시계처럼 작동하는듯!
					AdminDAO.getInstance().endPoint();
					
					//안빠진 사람들 10초 뒤에 또 빠지게 하기
					if(second==30 || second==50 || second ==20) { // 시간이 1초 흐를때마다 계속 체크할 수 있게
						boolean result=AdminDAO.getInstance().payBack();
						saving();
						System.out.println(result+"1번");
					}
					if(second==40) {
						// 제때 안빠진 사람들 돈 한번 더 빼기
						boolean result=AdminDAO.getInstance().paybackAgain();
					}
					
				} catch (InterruptedException e) {System.out.println("시계 오류" +e);
			}
		}
	}
	
	
	public void saving() {
		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
				LocalDate now = LocalDate.now();
				int year = now.getYear();
				int dayOfMonth = now.getDayOfMonth();
				String date = Integer.toString(dayOfMonth);
				
				// 적금 낼 돈 없는 사람 repay 올려주고 만기월 계싼
				ArrayList<SedateDTO> list = ServiceDAO.getInstance().s_edate(date);
				System.out.println(list);
				boolean result2 = false;
				for(int i = 0 ; i<list.size() ; i++) {
					int a_no = list.get(i).getA_no();
					
					String edate_year = (list.get(i).getS_edate()).substring(0,4);
					String edate_month = (list.get(i).getS_edate()).substring(5,7);
					String edate_day = (list.get(i).getS_edate()).substring(8,10);
					System.out.println("DB에서 가져온 년"+edate_year);
					System.out.println("DB에서 가져온 월"+edate_month);
					System.out.println("DB에서 가져온 일"+edate_day);
					
					int edate_year_re = Integer.parseInt(edate_year);
					int edate_month_re = Integer.parseInt(edate_month);
					int edate_day_re = Integer.parseInt(edate_day);
					
					// 가져온 만기월 +1 했을 때, 12월을 넘어가 13이면 년도를 올리고, 월을 1월로 설정
					if(edate_month_re+1 == 13) {
						edate_month_re = 1;
						edate_year_re = edate_year_re+1;
					}else {
						edate_month_re = edate_month_re+1;
					}
					
					// 한자리 수 월일때 자리수 채우기
					String year1 = String.format("%04d", edate_year_re);
					String month1 = String.format("%02d", edate_month_re);
					String date1 = String.format("%02d", edate_day_re);
					String s_edate = year1+"-"+month1+"-"+date1;
					
					System.out.println("DB에서 가져온 년 수정"+year1);
					System.out.println("DB에서 가져온 월 수정"+month1);
					System.out.println("DB에서 가져온 일 수정"+date1);
					System.out.println("새로운 만기일"+s_edate);
					
					
					boolean result = ServiceDAO.getInstance().e_datesetting(a_no, s_edate);
					if(result == true) {result2 = result;}
				}
				//response.getWriter().print(result2);
				boolean result3 = ServiceDAO.getInstance().savingcal(date);
			}
	
}