package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.AdminDAO;
import model.DTO.LoanDTO;


@WebServlet("/admin/adminloanadd")
public class adminloanadd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public adminloanadd() {super();  }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String lname = request.getParameter("lname");
		String lcontent = request.getParameter("lcontent");
		int limitmoney = Integer.parseInt(request.getParameter("limitmoney"));
		double rate = Double.parseDouble(request.getParameter("rate"));
		int ltime = Integer.parseInt(request.getParameter("ltime"));
		
		
		LoanDTO dto= new LoanDTO(0, lname, lcontent, limitmoney, rate, ltime);
		boolean result=AdminDAO.getInstance().loanadd(dto);
		
		response.getWriter().print(result);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
