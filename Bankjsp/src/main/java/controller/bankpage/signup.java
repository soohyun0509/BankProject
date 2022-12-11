package controller.bankpage;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.DAO.CustomerDAO;
import model.DTO.CustomerDTO;


@WebServlet("/page/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public signup() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String password=request.getParameter("password");
		
		//이름,핸드폰,비밀번호 제대로 입력받으면 계좌 생성해주기
		//계좌번호 생성
		String account= accountMake();
		
		// 비밀번호 암호화
		String lockPassword= makePassword(password);
		
		// 주소 4개 요청
		String maddress1=  request.getParameter("maddress1");
		String maddress2=  request.getParameter("maddress2");
		String maddress3=  request.getParameter("maddress3");
		String maddress4=  request.getParameter("maddress4");
		
		// 주소 4개 -> 하나의 변수
		String address= maddress1+","+maddress2+"," + maddress3 +","+ maddress4;
		
		
		
		//암호화된 비밀번호, 계좌 포함해서 DB업데이트
		CustomerDTO dto=new CustomerDTO(0, name, phone, address, lockPassword, account, 0 ,0, 0);
		
		boolean result=CustomerDAO.getInstance().signupDB(dto);
		
		// 제대로 업데이트됐으면 계좌 반환
		if(result) {response.getWriter().print(account);}
		
	}
	
	//계좌 생성 로직
	protected String accountMake() {
		int random = (int)(Math.random()*10000000);
	    // String 변환
	    String randomaccount = Integer.toString(random);
	    // String 사이에 특정 문자를 추가하려면 String은 변하지 못하므로 변할 수 있는 StringBuffer로 변환해줘야한다.
	    StringBuffer bufferaccount = new StringBuffer(randomaccount);
	    bufferaccount.insert(0 ,  "21-");
	    bufferaccount.insert(6 ,  "-");
	    // buffer -> String 변환
	    String account = bufferaccount.substring(0);
	    return account;
	}

	//비밀번호 암호화 로직
	protected String makePassword(String password) {
		
		String key = "84691753"; // 암호화 키 8자리
		if(password.contains("8")) {password=password.replace("8", "+");} // 9 ,8 숫자 넘쳐서 - 로 변환 문제 해결하기 위한...
		if(password.contains("9")) {password=password.replace("9", "-");}//이거 뭐로 바꾸기로 했더라...
		
		byte[] keybyte = key.getBytes();
		byte[] passwordbyte = password.getBytes();
		
	    
		String keybytebox="";
		for(int i=0; i<keybyte.length; i++) {
			keybytebox+=(keybyte[i]); //바이트열에 담긴 암호화키 숫자 하나씩 String으로 변환해서 누적 더하기
		}
		
		Long keyNum= Long.parseLong(keybytebox); // String으로 변환해서 한줄에 넣었던 암호화키 정수로 변환
	    
		Long[] array=new Long[4]; 

		
	    String lockPassword="";
	    for(int i=0; i<array.length;i++) {
	     array[i]=keyNum * passwordbyte[i]; // password바이트배열에 숫자를 하나씩 꺼내서 정수로 변환시킨 암호화키 곱하기
	     lockPassword+=array[i]; // 곱한걸 넣은 배열을 String으로 다 뽑아내기 
	    }

	    return lockPassword;
	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
