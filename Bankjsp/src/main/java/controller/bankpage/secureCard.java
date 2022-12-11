package controller.bankpage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.DAO.CustomerDAO;
import model.DTO.CustomerDTO;


@WebServlet("/page/secureCard")
public class secureCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public secureCard() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String account= request.getParameter("account");
		CustomerDTO dto= getInfo(account);
		boolean made=makeSecureCard(dto.getA_no());
		if(made) {
			boolean result=secureCardNum(dto.getA_no());
			if(result) {
				String[] numbers=printSecureCard(dto.getA_no());

				String numbersStr="";
				for(int i=0; i<numbers.length; i++) {
					numbersStr+=numbers[i];
				}
				response.getWriter().print(numbersStr);
			}
			
		}
	}

	
	// 계좌 정보 출력 로직
	CustomerDTO getInfo(String account) {
		return CustomerDAO.getInstance().getInfo(account);
	}
	
	
	//보안카드 발급 -> 레코드 생성 먼저
	boolean makeSecureCard(int a_no) {
		return CustomerDAO.getInstance().makeSecureCard(a_no);
	}
	
	// 보안카드 번호생성 
	boolean secureCardNum(int a_no) {
		
		HashMap<Integer, String> secureNum = new HashMap<Integer, String>();

        for( int i = 1 ; i <= 30 ; i++) {
           int num= (int)(Math.random()*9999);
           secureNum.put(i, String.format("%04d",num));
          
        }
      
        boolean result=false;
        for(int i=1; i<=30; i++) {
        	result=CustomerDAO.getInstance().secureCard(i, secureNum.get(i), a_no);
        }
        return result;
	}
	
	//보안카드 번호 출력

	String[]  printSecureCard(int a_no) {
		return CustomerDAO.getInstance().printSecureCard(a_no);
	}	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
