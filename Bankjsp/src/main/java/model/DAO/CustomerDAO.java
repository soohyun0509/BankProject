package model.DAO;

import java.util.ArrayList;

import model.DTO.CustomerDTO;
import model.DTO.LoanDTO;

public class CustomerDAO extends DAO{
	
	private static CustomerDAO cdao=new CustomerDAO();
	public static CustomerDAO getInstance() {return cdao;}
	
	// 계좌생성 후 DB 입력
	public boolean signupDB(CustomerDTO dto) {
		String sql="insert into customer values(0,?,?,?,?,?,0,0,0)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getPhone());
			ps.setString(3, dto.getAddress());
			ps.setString(4, dto.getLockpassword());
			ps.setString(5, dto.getAccount());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("계좌DB 입력 오류"+e);}
		return false;		
	}
	//로그인
	public String loginDB(String account) {
		String sql="select lockpassword from customer where account=? ";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, account);
			rs=ps.executeQuery();
			if(rs.next()) {
				String lockpassword=rs.getString(1);
				return lockpassword;
			}
		} catch (Exception e) {System.out.println("로그인 DB 출력 오류"+e);}
		return null;
	}
	// 로그인 오류 횟수 증가
	public void loginerror(String account) {
		String sql="update customer set errorcount = errorcount +1 where account = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, account);
			ps.executeUpdate();
		} catch (Exception e) {System.out.println("error count 업데이트 실패"+e);}
	};
	//로그인 오류 횟수 검사
	public int errorcount(String account) {
		int error = 5;
		String sql="select errorcount from customer where account=? ";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, account);
			rs=ps.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) < error) {
					return 0; // 5회 미만 로그인 되어야함
					
				}else {return 1;}// 5회 이상 틀림	
			}
		} catch (Exception e) {System.out.println("로그인 횟수 오류"+e);}
		return 2; //계좌 존재X 
	}
	//계좌찾기
	public String search(String name, String phone) {
		String sql="select account from customer where name=? and phone=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			rs=ps.executeQuery();
			if(rs.next()) {
				String account=rs.getString(1);
				return account;
			}
		} catch (Exception e) {System.out.println("계좌찾기 DB 오류"+e);}
		return null;
	}
	
	
	// 보안카드 발급 -> 일단 발급먼저
	public boolean makeSecureCard(int a_no) {
		String sql="insert into securecard(a_no) values(?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, a_no);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("보안카드 레코드 생성 오류"+e);}
		return false;
	}
	
	// 보안카드 번호 생성
	public boolean secureCard(int key, String Num, int a_no) {
		String sql="update securecard set secure?=? where a_no=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, key);
			ps.setString(2, Num);
			ps.setInt(3, a_no);
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {System.out.println("보안카드 발급 DB 오류"+e);}
		return false;
	}
	
	//보안카드 번호 출력
	public String[]  printSecureCard(int a_no) {
		String sql="select * from securecard where a_no=?";

		String[] secureNums= new String[30] ;
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, a_no);
			rs=ps.executeQuery();
			if(rs.next()) {
				for(int i=2 ; i<=31; i++) {
					secureNums[i-2]=rs.getString(i);
				}
				
				return secureNums;
				
			}
		} catch (Exception e) {System.out.println("보안카드 번호 출력 DB 오류"+e);}
		return null;
	}
	
	
	// 보안카드 문제[ 1 ]  출력 
	public String checkSecureCard(String secureString1 , int a_no) {
		String sql = "select secure"+secureString1+" from securecard where s_no = ?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, a_no);
			rs=ps.executeQuery();
			if(rs.next()) {
			String result1=rs.getString(1);
			return result1;
			}
		} catch (Exception e) {System.out.println("보안카드 DB 오류"+e);}
		return checkAccount(null);
	}
	// 보안카드 문제[ 2 ]  출력 
	public String checkSecureCard2(String secureString1 , int a_no) {
		String sql = "select secure"+secureString1+" from securecard where s_no = ?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, a_no);
			rs=ps.executeQuery();
			if(rs.next()) {
			String result2=rs.getString(1);
			return result2;
			}
		} catch (Exception e) {System.out.println("보안카드 DB 오류"+e);}
		return null;
	}
	
	// 입금하기
	public boolean deposit(String account , int plusM) {
		String sql="update customer set depositmoney= depositmoney+? where account=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, plusM);
			ps.setString(2, account);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("입금하기 DB 오류"+e);}
		return false;
	}
	
	// 해당 계좌 정보 다 가져오기
	public CustomerDTO getInfo(String account) {
		String sql="select * from customer where account=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, account);
			rs=ps.executeQuery();
			if(rs.next()) {
				CustomerDTO dto= new CustomerDTO(rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getString(4),rs.getString(5),
						rs.getString(6), rs.getInt(7),rs.getInt(8),rs.getInt(9));
				return dto;
			}
		} catch (Exception e) {System.out.println("계좌정보 출력 DB 오류"+e);}
		return null;
	}
	
	// 본인계좌에서 출금
	public boolean withdrawal(String account, int withdrawM) {
		String sql="update customer set depositmoney= depositmoney-? where account=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, withdrawM);
			ps.setString(2, account);
			
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("계좌 출금 DB 오류"+e);}
		return false; 
	}
	
	//이체하려는 계좌가 존재하는지 체크하고 그 계좌의 이름 출력시키기
	public String checkAccount(String otherAccount) {
		String sql="select name from customer where account=?";
		String name=null; 
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, otherAccount);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {System.out.println("이체 계좌확인 DB 오류"+e);}
		return name;
	}
	
	//다른 계좌로 이체
	public boolean transfer(String account, String otherAccount, int transferM) {
		String sql="update customer set depositmoney=depositmoney-? where account=?";
		String sql2="update customer set depositmoney=depositmoney+? where account=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, transferM);
			ps.setString(2, account);
			ps.executeUpdate();
			///
			ps=con.prepareStatement(sql2);
			ps.setInt(1, transferM);
			ps.setString(2, otherAccount);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("이체 결과 DB 오류"+e);}
		return false;
	}
	
	
	// 대출 목록 가져오기
	// 관리자메뉴에서도 이거 사용하면 될것같음!
	public ArrayList<LoanDTO> getLoanContent() {
		String sql="select * from loan";
		ArrayList<LoanDTO> list= new ArrayList<>();
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				LoanDTO dto= new LoanDTO(rs.getInt(1), rs.getString(2),
						rs.getString(3),
						rs.getInt(4), rs.getDouble(5), rs.getInt(6));
				list.add(dto);
			}
			return list;
		} catch (Exception e) {System.out.println("대출 목록 출력 DB 오류"+e);}
		return list;
	}
	

	
	
	
}
