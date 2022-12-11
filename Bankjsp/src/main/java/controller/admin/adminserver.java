package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;


@WebServlet("/adminserver")
public class adminserver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public adminserver() {super();  }

    boolean threadS=false; // 다시 선언되면 안되니까 전역으로 선언
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(threadS==false) {
			paybackmain pay= new paybackmain();
			pay.start();
			threadS=true;
			
			response.sendRedirect("http://localhost:8080/Bankjsp/index.jsp");
		}
		
		else if(threadS==true) { // 이미 스레드 가동중
			response.sendRedirect("http://localhost:8080/Bankjsp/index.jsp");
			// 나중에 js에서 뭔가 해주면 이거 바꿀 예정
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}	
}
