package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.AdminDAO;


@WebServlet("/admin/admingsavingdelete")
public class adminsavingdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public adminsavingdelete() {   }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int s_no = Integer.parseInt(request.getParameter("s_no"));
		boolean result = AdminDAO.getInstance().savingdelete(s_no);
		
		if(result) {
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(result);
		}else {
			System.out.println("글 삭제 실패");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
