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
import model.DTO.LoanDTO;
import model.DTO.SavingDTO;


@WebServlet("/admin/adminsaving")
public class adminsaving extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public adminsaving() {   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<SavingDTO> list =  AdminDAO.getInstance().savinglist();
		
		JSONArray array = new JSONArray();
		for( int i = 0  ; i<list.size() ; i++ ) {
			JSONObject object = new JSONObject();
			object.put("s_no", list.get(i).getS_no() );
			object.put("s_name", list.get(i).getS_name() );
			object.put("s_content", list.get(i).getS_content() );
			object.put("s_rate", list.get(i).getS_rate() );
			object.put("s_limit", list.get(i).getS_limit() );
			object.put("s_month", list.get(i).getS_month() );
			array.add(object);
		}
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print( array );
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
