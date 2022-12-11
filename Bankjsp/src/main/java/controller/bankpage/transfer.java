package controller.bankpage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.DAO.CustomerDAO;
import model.DTO.CustomerDTO;


@WebServlet("/page/transfer")
public class transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public transfer() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//세션먼저
		String account=(String)request.getSession().getAttribute("account");
		String otherAccount=request.getParameter("otherAccount");
		int transferM=Integer.parseInt(request.getParameter("transferM"));
		
		
		// 이체하려는 계좌가 존재하는지 확인먼저! -> 해당 계좌의 계좌주 가져오기!
		String tName=CustomerDAO.getInstance().checkAccount(otherAccount);
		if(tName!=null) { // null이면 DB오류거나 해당계좌없음!
			// 이체 시작
			// 이체금액이 잔액보다 많으면 안되게
			CustomerDTO dto=CustomerDAO.getInstance().getInfo(account);
			if(transferM > dto.getDepositmoney()) {
				response.getWriter().print(2); // 잔액부족
				return; // 밑에 실행안되게 종료
			}
			else {
				boolean result=CustomerDAO.getInstance().transfer(account, otherAccount, transferM);
				if(result) {
					response.setCharacterEncoding("UTF-8");
					response.getWriter().print(tName);
					} // 이체하려는 사람의 이름이 출력되면 정상처리완료
				else {response.getWriter().print(0);} // 0이면 DB오류
			}
			
		}else {// 존재하지않는계좌에 이체불가
			response.getWriter().print(3);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
