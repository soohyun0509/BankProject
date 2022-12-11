package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.AdminDAO;
import model.DTO.LoanDTO;
import model.DTO.SavingDTO;


@WebServlet("/admin/admingsavingadd")
public class adminsavingadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public adminsavingadd() {   }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("asdasdasdasdasdasdasd");
		String s_name = request.getParameter("s_name");
		String s_content = request.getParameter("s_content");
		int s_limit = Integer.parseInt(request.getParameter("s_limit"));
		int s_rate = Integer.parseInt(request.getParameter("s_rate"));
		int s_month = Integer.parseInt(request.getParameter("s_month"));
	
		
		SavingDTO dto = new SavingDTO(0, s_name , s_content , s_limit , s_rate , s_month);
		boolean result=AdminDAO.getInstance().savingadd(dto);
		
		response.getWriter().print(result);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
