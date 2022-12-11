package controller.bankpage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.CustomerDAO;
import model.DTO.CustomerDTO;




@WebServlet("/page/withdraw")
public class withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public withdraw() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 출금금액이 잔액보다 많으면 안되게
		
		String account=(String)request.getSession().getAttribute("account");
		int withdrawM=Integer.parseInt(request.getParameter("withdrawM"));
		
		CustomerDTO dto=CustomerDAO.getInstance().getInfo(account);
		if(withdrawM > dto.getDepositmoney()) {
			response.getWriter().print(2); // 2면 잔액부족
			return;
		}
		boolean result=CustomerDAO.getInstance().withdrawal(account, withdrawM);
		if(result) {response.getWriter().print(1);}// 1이면 정상처리완료
		else {response.getWriter().print(0);}// 0이면 DB오류
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
