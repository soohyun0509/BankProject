package controller.bankpage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.DAO.ServiceDAO;
import model.DTO.SedateDTO;


@WebServlet("/savingcal")
public class savingcal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public savingcal() {super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int dayOfMonth = now.getDayOfMonth();
		String date = Integer.toString(dayOfMonth);
		System.out.println(date);
		
		ArrayList<SedateDTO> list = ServiceDAO.getInstance().s_edate(date);
		System.out.println(list);
		boolean result2 = false;
		for(int i = 0 ; i<list.size() ; i++) {
			int a_no = list.get(i).getA_no();
			
			String edate_year = (list.get(i).getS_edate()).substring(0,4);
			String edate_month = (list.get(i).getS_edate()).substring(5,7);
			String edate_day = (list.get(i).getS_edate()).substring(8,10);
			
			int edate_year_re = Integer.parseInt(edate_year);
			int edate_month_re = Integer.parseInt(edate_month);
			int edate_day_re = Integer.parseInt(edate_day);
			
			if(edate_month_re+1 == 13) {
				edate_month_re = 1;
				edate_year_re = edate_year_re+1;
			}else {
				edate_month_re = edate_month_re+1;
			}
			
			String year1 = String.format("%04d", edate_year_re);
			String month1 = String.format("%02d", edate_month_re);
			String date1 = String.format("%02d", edate_day_re);
			String s_edate = year1+"-"+month1+"-"+date1;
			System.out.println(s_edate);
			
			
			//String s_edate = edate_year_re+"-"+edate_month_re+"-"+edate_day;
			//System.out.println(edate_year_re);
			//System.out.println(edate_month_re);
			//System.out.println(edate_day);
			//System.out.println(s_edate);
			boolean result = ServiceDAO.getInstance().e_datesetting(a_no, s_edate);
			if(result == true) {result2 = result;}
		}
		response.getWriter().print(result2);
		boolean result3 = ServiceDAO.getInstance().savingcal(date);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
