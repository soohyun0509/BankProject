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

import model.DAO.CustomerDAO;
import model.DAO.ServiceDAO;
import model.DTO.LoanDTO;
import model.DTO.SavingDTO;

@WebServlet("/page/savingSingup")
public class savingSingup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public savingSingup() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// dao에 연결해서 적금 상품 정보 가져오기
		ArrayList<SavingDTO> list= ServiceDAO.getInstance().getSavingContent();
	
		JSONArray array= new JSONArray();
		for(int i=0; i<list.size(); i++) {
			JSONObject object = new JSONObject();
			object.put("s_no", list.get(i).getS_no());
			object.put("s_name", list.get(i).getS_name());
			object.put("s_content", list.get(i).getS_content());
			object.put("s_rate", list.get(i).getS_rate());
			object.put("s_limit", list.get(i).getS_limit());
			object.put("s_month", list.get(i).getS_month());
			
			array.add(object);
			
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(array);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String name=request.getParameter("name");
		// 이름 맞는지 확인하고 적금가입하게 해야함..
		System.out.println("1");
		
		int a_no=(Integer)request.getSession().getAttribute("a_no");
		int s_pay=Integer.parseInt(request.getParameter("s_pay"));
		int s_no=Integer.parseInt(request.getParameter("s_no"));
		int s_month=Integer.parseInt(request.getParameter("s_month"));
		
		////////////////////////////////////////////////////////////////
		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		
		
		////////////////////////////////////////////////////////////////
		String s_sdate = (String)request.getParameter("s_sdate");
		int e_month = ServiceDAO.getInstance().savinge_date(s_no);
		String e_year = Integer.toString(year+e_month/12);
		String s_edate = e_year+'-'+s_sdate.substring(5,7)+'-'+s_sdate.substring(8,10);
		
	
		String account= savingMake();

		System.out.println(account);
		String savingaccount= ServiceDAO.getInstance().savingMake(a_no,account,s_pay,s_no,s_month,s_sdate,s_edate);
		System.out.println(savingaccount);
		response.getWriter().print(savingaccount);
	
	
	}

	//계좌 생성 로직
	public String savingMake() {
		int random = (int)(Math.random()*10000000);
	    // String 변환
	    String randomaccount = Integer.toString(random);
	    // String 사이에 특정 문자를 추가하려면 String은 변하지 못하므로 변할 수 있는 StringBuffer로 변환해줘야한다.
	    StringBuffer bufferaccount = new StringBuffer(randomaccount);
	    bufferaccount.insert(0 ,  "22-"); // 그냥 계좌는 22
	    bufferaccount.insert(6 ,  "-");
	    // buffer -> String 변환
	    String account = bufferaccount.substring(0);
	    return account;
	}
	
	
	
}
