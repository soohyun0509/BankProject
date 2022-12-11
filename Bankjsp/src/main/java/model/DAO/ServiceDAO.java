package model.DAO;

import java.sql.Statement;
import java.util.ArrayList;

import model.DTO.ChattingDTO;
import model.DTO.ChattingRoomDTO;
import model.DTO.LoanDTO;
import model.DTO.SavingDTO;
import model.DTO.SedateDTO;


public class ServiceDAO extends DAO{

	private static ServiceDAO sdao=new ServiceDAO();
	public static ServiceDAO getInstance() {return sdao;}
	
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
	
	// 대출 신청 폼 전송
	//servlet에서 개인정보 맞는지 확인하고 오니까 여기서 변수로 받을필요없음
	public boolean loanSignup(int ano, int lno ,int amount) {
		String sql="insert into personalloan(lbalance,a_no,l_no) values(?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setInt(2, ano);
			ps.setInt(3, lno);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("대출신청 DB 업데이트 오류"+e);} return false;
	}
	
	// 대출신청 완료됐으면 DB에 입금시켜주기
	public boolean loanSuccess(int ano,int amount,int lno ) {
		String sql="update personalloan p , customer c , loan l set "
				+ " c.depositmoney=c.depositmoney+? , "
				+ " c.haveloan=c.haveloan+1 , "
				+ " p.lcount=(l.ltime*12)  "
				+ " where p.l_no=l.l_no and p.a_no=c.a_no and c.a_no=? and p.l_no=?";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setInt(2, ano);
			ps.setInt(3, lno);
			
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("대출 금액 업데이트 오류"+e);} return false;
	}
	
	
	
	
	
	// 내 방 여부 확인
	public boolean exists(String myname) {
		String sql = "select * from chattingroom where name = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, myname);
			rs = ps.executeQuery();
			if(rs.next())
				return true;
		} catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	
	// 채팅 내 이름 가져오기 
	public String myname( String account ) {
		String sql = "select name from customer where account = ?";
		String name = "";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, account);
			rs = ps.executeQuery();
			if( rs.next() ) {
				name = rs.getString(1);
				return name;
			}
			System.out.println(name);
		}catch (Exception e) {System.out.println(e);} return null;
	}
	
	// 내 채팅방 번호 가져오기
	public int myroom( String name ) {
		String sql = "select c_room from chattingroom where name = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if( rs.next() ) {
				return rs.getInt(1);
			}
			System.out.println(name);
		}catch (Exception e) {System.out.println(e);} return 0;
	}
		
		
	
	// 내 이름 넣어 방 만들어 방 번호 가져오기
	public int createroom(String name) {
		String sql="insert into chattingroom(name) values(?)";
		try {
			ps=con.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);			
			ps.executeUpdate();
			int roomnum = 0;
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				roomnum = rs.getInt(1);
			}
			return roomnum;
		} catch (Exception e) {System.out.println("내 이름 넣어 방 번호 가져오기"+e);} return 0;
	}
	
	// 채팅 내용 담기
	public boolean chatting(int roomnum , String myname, String msg, String account) {
		String sql="insert into chatting(c_room , name, c_content, account) values(?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, roomnum);
			ps.setString(2, myname);
			ps.setString(3, msg);
			ps.setString(4, account);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("채팅 내용 담기"+e);} return false;
	}
	
	
	// 채팅 내용 가져오기
	public ArrayList<ChattingDTO> chattinghistory(int roomnum){
		ArrayList<ChattingDTO> list = new ArrayList<>();
		String sql = "select * from Chatting where c_room ="+roomnum;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				ChattingDTO dto = new ChattingDTO(
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)
						);
				list.add(dto);
			}
			return list;
		} catch (Exception e) {	System.out.println(e);	}
		return null;
	}

	
	//채팅 리스트 가져오기
   public ArrayList<ChattingDTO> chattinglist(){
      ArrayList<ChattingDTO> list = new ArrayList<>();
      String sql = "select * from chattingroom";
      try {
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         while(rs.next()){
        	 ChattingDTO dto = new ChattingDTO( 
        			 rs.getInt(1),  rs.getString(2));
            list.add(dto);
         }
         return list;
      } catch (Exception e) {   System.out.println(e);   }
      return null;
   }

   // 적금 목록 호출하기
	public ArrayList<SavingDTO> getSavingContent() {
		String sql="select * from saving";
		ArrayList<SavingDTO> list= new ArrayList<>();
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				SavingDTO dto= new SavingDTO(rs.getInt(1), rs.getString(2),
						rs.getString(3),
						rs.getInt(4), rs.getInt(5), rs.getInt(6));
				list.add(dto);
			} 
			return list;
			
		} catch (Exception e) {System.out.println("적금 목록 출력 DB 오류"+e);}
		return list;
	}
	
   
   
	 // 적금 계좌 생성
	 public String savingMake(int a_no,String account,int s_pay,int s_no, int s_month,String s_sdate,String s_edate) {
		 String sql="insert into personalsaving(s_no,s_account,s_month,s_pay,a_no,s_sdate,s_edate) values(?,?,?,?,?,?,?)";
		
		 try {
			ps=con.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, s_no);
			ps.setString(2, account);
			ps.setInt(3, s_month);
			ps.setInt(4, s_pay);
			ps.setInt(5, a_no);
			ps.setString(6, s_sdate);
			ps.setString(7, s_edate);
			ps.executeUpdate();
			rs=ps.getGeneratedKeys();
			if(rs.next()) {
				int ps_num=rs.getInt(1);
				sql="select s_account from personalsaving where ps_num=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, ps_num);
				rs=ps.executeQuery();
				if(rs.next()) {
					return rs.getString(1);
				}
			}
		} catch (Exception e) {System.out.println("적금 등록 DB 오류"+e);}return null;
	 }
	//적금 정산
	 public boolean savingcal(String date ) {
		String sql = "update customer c , saving s, personalsaving ps set c.depositmoney = c.depositmoney - ps.s_pay , ps.s_balance = (ps.s_balance + ps.s_pay) where c.depositmoney > ps.s_pay and ps.s_sdate like '%"+date+"'";
		try {
			ps=con.prepareStatement(sql);
			//ps.setString(1, s_edate);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("적금 정산 업데이트 :::"+ e);}
		return false;
	}
	 
	//해당 적금상품의 기간 가져오기
	public int savinge_date(int s_no) {
		String sql = "select s_month from saving where s_no = "+s_no;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {System.out.println("해당 적금 상품의 기간 가져오기"+e);	}
		return 0;
	}
	
	//s_edate 가져오기
	public ArrayList<SedateDTO> s_edate(String date) {
		String sql = "select ps.a_no, ps.s_edate from customer c , saving	 s, personalsaving ps where c.depositmoney < ps.s_pay and ps.s_sdate like '%"+date+"'";
		ArrayList<SedateDTO> list= new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			//ps.setString(1, date);
			rs = ps.executeQuery();
			System.out.println("에이 여긴 오겠지");
			while( rs.next() ) {
				System.out.println("여기 들어옴??");
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				SedateDTO dto= new SedateDTO(
						rs.getInt(1),
						rs.getString(2));
						list.add(dto);
			}
			return list;
		} catch (Exception e) {System.out.println("오늘 날짜 적금 가입한 사람번호랑 만기일 가져오기"+e);	}
		return null;
	}
	
	public boolean e_datesetting(int a_no, String s_edate) {
		String sql = "update personalsaving set s_edate = ? , s_repay = s_repay+1 where a_no = ? ";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, s_edate);
			ps.setInt(2, a_no);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("e_date 업데이트 :::"+ e);}
		return false;
	}
	
	
}
