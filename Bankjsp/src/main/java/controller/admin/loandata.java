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

import model.DAO.AdminDAO;
import model.DTO.LoanDataDTO;


@WebServlet("/admin/loandata")
public class loandata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public loandata() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ArrayList<LoanDataDTO> list=AdminDAO.getInstance().getLoanData();
		
		JSONArray array= new JSONArray();
		for(LoanDataDTO dto : list) {
			JSONObject object = new JSONObject();
			object.put("p_no", dto.getP_no());
			object.put("name", dto.getName());
			object.put("lname", dto.getLname());
			object.put("lbalance", dto.getLbalance());
			object.put("ltime", dto.getLtime());
			object.put("account", dto.getAccount());
			object.put("repay", dto.getRepay());
			object.put("ldate", dto.getLdate());
			object.put("rate", dto.getRate());
			
			
			array.add(object);
		}
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(array);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
