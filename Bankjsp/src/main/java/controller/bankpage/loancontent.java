package controller.bankpage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.DAO.CustomerDAO;
import model.DAO.ServiceDAO;
import model.DTO.LoanDTO;


@WebServlet("/page/loancontent")
public class loancontent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


    public loancontent() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// dao에 연결해서 대출 상품 정보 가져오기
		ArrayList<LoanDTO> list= ServiceDAO.getInstance().getLoanContent();
	
		JSONArray array= new JSONArray();
		for(int i=0; i<list.size(); i++) {
			JSONObject object = new JSONObject();
			object.put("lno", list.get(i).getL_no());
			object.put("lname", list.get(i).getLname());
			object.put("lcontent", list.get(i).getLcontent());
			object.put("limitmoney", list.get(i).getLimitmoney());
			object.put("rate", list.get(i).getRate());
			object.put("ltime", list.get(i).getLtime());
			
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
