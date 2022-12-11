package controller.bankpage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.CustomerDAO;
import model.DAO.DAO;
import model.DTO.CustomerDTO;


@WebServlet("/page/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public login() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//account에 맞는 비밀번호를 가져와서 복호화한다음에 입력한 비밀번호랑 일치하는지 확인해야됨
		
		
		
		String account= request.getParameter("account");
		String password= request.getParameter("password");
		CustomerDTO dto= getInfo(account);
		
		// 오류횟수 확인
		int error = CustomerDAO.getInstance().errorcount(account);
		int result=0;
		// 5회 카운트 횟수 미만시, true로 로그인 진행
		if(error == 0) {
			
			//account에 맞는 암호화비밀번호 가져오기
			String lockpassword=CustomerDAO.getInstance().loginDB(account);
			
			if(lockpassword!=null) {
				//복호화
				String realpassword=unlock(lockpassword);
				//입력한 비밀번호와 복호화된 비밀번호 대조
				if(password.equals(realpassword)) {
					result=1; // 1이면 로그인 성공
					//세션에 a_no 할당
					int a_no = dto.getA_no();
					// System.out.println(a_no);
					request.getSession().setAttribute("a_no", a_no);
					// 로그인 성공하면 세션 할당
					if(account.equals("1")) {
						result=3;
						response.getWriter().print(result);
						HttpSession session = request.getSession();
						session.setAttribute("account", account);
					}else {
						HttpSession session = request.getSession();
						session.setAttribute("account", account);
						response.getWriter().print(result);
					}
				}
				else {
					result=2;
					CustomerDAO.getInstance().loginerror(account);
					response.getWriter().print(result);
				}// 2면 비밀번호 틀림
			}
		}//doGet end
		else if(error==1){
		result = 4;
		response.getWriter().print(result);
		}else if(error==2){
			result=5; // 계좌존재X
			response.getWriter().print(result);
		}
	}
	
	
	
	
	
	
	// 계좌 정보 출력 로직
	CustomerDTO getInfo(String account) {
		return CustomerDAO.getInstance().getInfo(account);
	}
	
	//복호화 로직
	String unlock(String lockpassword) {
		String key = "84691753"; // 암호화 키 8자리
		byte[] keybyte = key.getBytes();
		
		String keybytebox="";
		for(int i=0; i<keybyte.length; i++) {
			keybytebox+=(keybyte[i]); 
		}
		
		Long keyNum= Long.parseLong(keybytebox); 
		
		// 18자씩 잘라서 배열에 담기
		String[] result=new String[4];
		result[0] = lockpassword.substring(0,18);
		result[1]  = lockpassword.substring(18,36);
		result[2] = lockpassword.substring(36,54);
		result[3] = lockpassword.substring(54,72);
	    
		// String -> long 변환
		Long[] resultLong= new Long[4];
		String[] passwordArray= new String[4];
		byte[] passwordByte=new byte[4];
		for(int i=0; i<resultLong.length; i++) {
			resultLong[i]=Long.parseLong(result[i]); //정수로 변환
			resultLong[i]=(resultLong[i] / keyNum); // 암호화키로 나누기
			passwordArray[i]=resultLong[i].toString(); //문자열로 변환
			passwordByte[i]=Byte.parseByte(passwordArray[i]); // 바이트배열에 넣기
		}
		
		String realpassword=new String(passwordByte); //바이트배열 String으로 변환
		if(realpassword.contains("+")) {realpassword=realpassword.replace("+", "8");}
		if(realpassword.contains("-")) {realpassword=realpassword.replace("-", "9");}

		
		return realpassword;
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
