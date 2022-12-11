package controller.bankpage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.CustomerDAO;
import model.DAO.ServiceDAO;
import model.DTO.CustomerDTO;


@WebServlet("/page/loanSignup")
public class loanSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public loanSignup() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인한 account랑도 맞는지 대조해야함!!
		//세션에서 account 가져오기
		String loginAccount=(String)request.getSession().getAttribute("account");
		// System.out.println(loginAccount);
		
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String account=request.getParameter("account");
		// System.out.println(account);
		int amount=Integer.parseInt(request.getParameter("amount"));
		
		// 로그인한 계좌가 아니면 애초에 안되게!
		if(!loginAccount.equals(account)) {response.getWriter().print("0"); return;}//계좌가 다릅니다!
		
		// 주소 4개 요청
		String maddress1=  request.getParameter("maddress1");
		String maddress2=  request.getParameter("maddress2");
		String maddress3=  request.getParameter("maddress3");
		String maddress4=  request.getParameter("maddress4");
		// 주소 4개 -> 하나의 변수
		String address= maddress1+","+maddress2+"," + maddress3 +","+ maddress4;
		
		 
		
		
		//여기서 맞는지 먼저 다 확인하고 전송해야겠음
		// 입력한 정보와 해당 계좌에 개인정보와 맞으면 dao 연결
		CustomerDTO dto= CustomerDAO.getInstance().getInfo(account);
		if(dto.getName().equals(name) && dto.getPhone().equals(phone) && dto.getAddress().equals(address)) {
			//제대로 맞게 입력됐으면 대출신청!
			// 개인정보는 확인했으니까 전달안해도 ok
			// 단, 회원번호랑 대출번호를 전달해줘야함
			int lno=Integer.parseInt(request.getParameter("lno")) ;   
			int ano=(Integer)request.getSession().getAttribute("a_no");
			boolean result= ServiceDAO.getInstance().loanSignup(ano, lno, amount);
			
			
			if(result) { // 정상적으로 출력했으면 입금까지 해주기
				result=ServiceDAO.getInstance().loanSuccess(ano, amount, lno);
				response.getWriter().print(result);// 정상처리 완료면 true 입금만 안됐으면 false
			} 
			else {response.getWriter().print("DB오류");} 
		}else {response.getWriter().print("2");}// 2면 등록돼있는 계좌정보와 다름
		
		
		
		
		
		
		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
