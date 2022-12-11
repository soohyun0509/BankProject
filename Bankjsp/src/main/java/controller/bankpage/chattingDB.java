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

import model.DAO.ServiceDAO;
import model.DTO.ChattingDTO;



@WebServlet("/chattingDB")
public class chattingDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public chattingDB() {   }

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 1번 2번 공통 실행
		String account = request.getParameter("account");
		
		String myname =ServiceDAO.getInstance().myname(account);
		
		// 내방 번호
		int myroom = ServiceDAO.getInstance().myroom(myname);
		
			// 내 방 여부
		boolean exists = ServiceDAO.getInstance().exists(myname);
		 
		int type=Integer.parseInt(request.getParameter("type")) ;
		
		String msg="";
		if(type==1) {response.getWriter().print(myroom); return;} // 방만 만들고 메소드 종료시키기
		
		if(type==2) {
			msg = request.getParameter("msg");
			
		}
		
		
		int roomnum=0;
		
		if(!account.equals("1")) {
				if(exists == false) {
					roomnum = ServiceDAO.getInstance().createroom(myname);
					
					boolean result = ServiceDAO.getInstance().chatting(roomnum ,myname , msg , account );
					
					if(result == true) {
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
				}else {
					boolean result = ServiceDAO.getInstance().chatting(myroom ,myname , msg , account );
					
					if(result == true) {
						ArrayList<ChattingDTO> list = ServiceDAO.getInstance().chattinghistory(myroom);
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
		
		}else if(account.equals("1")){
			
			roomnum=Integer.parseInt(request.getParameter("roomnum")) ;
			boolean result = ServiceDAO.getInstance().chatting(roomnum ,myname , msg , account );
			
			
			
			
			if(result == true) {
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
		
			
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
