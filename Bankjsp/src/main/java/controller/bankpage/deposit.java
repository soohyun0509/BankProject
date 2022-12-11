package controller.bankpage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.CustomerDAO;




@WebServlet("/page/deposit")
public class deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public deposit() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//account 세션 받아와야함
		int plusM=Integer.parseInt(request.getParameter("plusM"));
		if(plusM<0) {
			
			String result="입금금액은 0원 이상이어야 합니다.";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(result);
			return;
		}
		String account = (String)request.getSession().getAttribute("account");
		
		boolean result=CustomerDAO.getInstance().deposit(account, plusM);
		response.getWriter().print(result);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
