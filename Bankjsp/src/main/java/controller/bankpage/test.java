package controller.bankpage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.CustomerDAO;
import model.DAO.DAO;
import model.DTO.CustomerDTO;


@WebServlet("/page/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public test() { super();   }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int a_no = (int)request.getSession().getAttribute("a_no");
		String account = (String)request.getSession().getAttribute("account");
		String[] numbers = CustomerDAO.getInstance().printSecureCard(a_no);
		String numbersStr="";
		for(int i=0; i<numbers.length; i++) {
			numbersStr+=numbers[i];
		}
		response.getWriter().print(numbersStr);

		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
