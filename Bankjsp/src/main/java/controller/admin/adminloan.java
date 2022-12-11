package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.DTO.LoanDTO;
import model.DAO.AdminDAO;



@WebServlet("/admin/adminloan")
public class adminloan extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public adminloan() {super();  }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		AdminDAO adao = new AdminDAO();
		ArrayList<LoanDTO> list =  adao.getInstance().loanlist();
		
		JSONArray array = new JSONArray();
		for( int i = 0  ; i<list.size() ; i++ ) {
			JSONObject object = new JSONObject();
			object.put("l_no", list.get(i).getL_no() );
			object.put("lname", list.get(i).getLname() );
			object.put("lcontent", list.get(i).getLcontent() );
			object.put("limitmoney", list.get(i).getLimitmoney() );
			object.put("rate", list.get(i).getRate() );
			object.put("ltime", list.get(i).getLtime() );
			array.add(object);
		}
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print( array );
	
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
