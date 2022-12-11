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

import model.DAO.ServiceDAO;
import model.DTO.ChattingDTO;

@WebServlet("/adminchatting")
public class adminchatting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public adminchatting() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        ArrayList<ChattingDTO> list = ServiceDAO.getInstance().chattinglist();
        JSONArray array = new JSONArray();
        for(ChattingDTO dto : list) {
           JSONObject object = new JSONObject();
           object.put("c_room", dto.getC_room() );
           object.put("name", dto.getName() );
           array.add(object); // JSONObject 객체를 리스트에 담기
        }
        
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array);            
     }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int roomnum=Integer.parseInt(request.getParameter("roomnum"));
		
		ArrayList<ChattingDTO> list = ServiceDAO.getInstance().chattinghistory(roomnum);
		JSONArray array = new JSONArray();
		for(ChattingDTO dto : list) {
			JSONObject object = new JSONObject();
			object.put("c_no", dto.getC_no() );
			object.put("c_room", dto.getC_room() );
			object.put("name", dto.getName() );
			object.put("account", dto.getAccount() );
			object.put("c_content", dto.getC_content() );
			object.put("c_date", dto.getC_date() );
			array.add(object); // JSONObject 객체를 리스트에 담기
		}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(array);

	}

}
