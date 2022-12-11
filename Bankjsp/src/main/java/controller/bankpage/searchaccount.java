package controller.bankpage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.CustomerDAO;
import model.DAO.ServiceDAO;


@WebServlet("/page/searchaccount")
public class searchaccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public searchaccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		
		String result=CustomerDAO.getInstance().search(name,phone);
		response.getWriter().print(result);
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
