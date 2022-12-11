package controller.bankpage;

import java.io.IOException;
import java.util.Hashtable;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;

@ServerEndpoint("/chatting/{account}")
public class chatting {

	//접속한 세션을 저장할 뭔가를 만들어야됨
	public static Hashtable<Session, String> clients= new Hashtable<>(); // 그냥 account만 저장하면 안되나...
	// 아...메시지 보내줄때 세션 사용해서 보내줘야돼서 그런건가
	
	// 소켓 접속 , 종료 시 알람
	public JSONObject jsonAlarm(String content) {
		JSONObject object=new JSONObject();
		object.put("type", "alarm");
		object.put("content", content);
		return object;
	}
	public void sendAlarm(JSONObject object) throws IOException {
		for(Session s : clients.keySet()) { // 모든 세션에 뿌려주기
			s.getBasicRemote().sendText(object.toString());
			System.out.println(object.toString());
		}
		
	}
	
	@OnOpen
	public void onOpen(Session session , @PathParam("account") String account) throws IOException {
		//접속하면 세션 저장
		clients.put(session, account);
		
		
		
		// 알람 뿌려주기
		sendAlarm(jsonAlarm(account+"님이 접속했습니다."));
		
		
		System.out.println("세션접속!!!!!!!!!"+account);
	}	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session); // 나가면 저장된 세션 삭제
		System.out.println("나감");
	}
	
	@OnMessage
	public void onMessage(Session session , String msg) throws IOException {
		// 접속해있는 소켓을 다 불러서 메시지 다 보내주기
		for(Session s : clients.keySet()) {
			s.getBasicRemote().sendText(msg);
			
		}
		
	}
	
}
