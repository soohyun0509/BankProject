package model.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import model.DTO.LoanDTO;
import model.DTO.LoanDataDTO;
import model.DTO.SavingDTO;


public class AdminDAO extends DAO{
	
	private static AdminDAO adao= new AdminDAO();
	public static AdminDAO getInstance() {return adao;}
	
	// 대출 목록 가져오기
	public ArrayList<LoanDTO> loanlist() {
		ArrayList<LoanDTO> list = new ArrayList<>();
		String sql = "select * from loan;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				LoanDTO dto= new LoanDTO(rs.getInt(1), rs.getString(2),
						rs.getString(3),
						rs.getInt(4), rs.getDouble(5), rs.getInt(6));
					list.add(dto);
			}
			return list;
		} catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	// 적금 목록 가져오기
	public ArrayList<SavingDTO> savinglist() {
		ArrayList<SavingDTO> list = new ArrayList<>();
		String sql = "select * from saving;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				SavingDTO dto= new SavingDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6));
						list.add(dto);
			}
			return list;
		} catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	
	// 대출 상품 삭제
	public boolean loandelete(int l_no) {
		String sql = "delete from loan where l_no="+l_no+"; ";
		try {
			ps = con.prepareStatement(sql);
			int count = ps.executeUpdate();
			if(count == 1)
				return true;
		} catch (Exception e) {System.out.println("대출삭제오류"+e);}
		return false;
	}
	// 적금 상품 삭제
		public boolean savingdelete(int s_no) {
			String sql = "delete from saving where s_no=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, s_no);
				int count = ps.executeUpdate();
				if(count == 1)
					return true;
			} catch (Exception e) {System.out.println("적금삭제오류"+e);}
			return false;
		}
	
	// 대출 상품 추가
	public boolean loanadd(LoanDTO dto) {
		String sql="insert into loan values(0, ?,?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, dto.getLname());
			ps.setString(2, dto.getLcontent());
			ps.setInt(3, dto.getLimitmoney());
			ps.setDouble(4, dto.getRate());
			ps.setInt(5, dto.getLtime());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {System.out.println("대출상품추가 오류"+e);} return false;
	}
	
	// 적금 상품 추가
	public boolean savingadd(SavingDTO dto) {
		String sql="insert into saving values(0, ?,?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, dto.getS_name());
			ps.setString(2, dto.getS_content());
			ps.setInt(3, dto.getS_limit());
			ps.setDouble(4, dto.getS_rate());
			ps.setInt(5, dto.getS_month());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {System.out.println("대출상품추가 오류"+e);} return false;
	}
	
	// 상환
	public boolean payBack() { // 날짜는 나중에 추가하기 // 금액도 변수로 넣어줘야함
		// 돈 빠지게
		String sql="update personalloan p , customer c , loan l set "
				+ " c.depositmoney=c.depositmoney-(((p.lbalance * l.rate) * l.ltime) / (l.ltime*12)) , "
				+ " p.lcount=p.lcount-1 "
				+ " where p.l_no = l.l_no and c.a_no=p.a_no "
				+ " and c.depositmoney>=(((p.lbalance * l.rate) * l.ltime) / (l.ltime*12))";
		// 안빠졌으면 미납횟수 증가
		String sql2="update personalloan p , customer c  , loan l set p.repay=p.repay+1 "
				+ " where p.a_no=c.a_no and c.depositmoney<(((p.lbalance * l.rate) * l.ltime) / (l.ltime*12))";
		try {
			ps=con.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println();
			
			ps=con.prepareStatement(sql2);
			ps.executeUpdate();
			System.out.println();
			return true;
		} catch (Exception e) {System.out.println("상환 DB 오류"+e);}return false;
		
	}
	
	// 미납횟수 있는 사람들만 돈 빠지게
	public boolean paybackAgain() {
		String sql="update personalloan p , customer c , loan l set "
				+ " c.depositmoney=c.depositmoney-(((p.lbalance * l.rate) * l.ltime) / (l.ltime*12)) , "
				+ " p.repay=p.repay-1 "
				+ " where p.a_no=c.a_no and p.repay>=1 and c.depositmoney>(((p.lbalance * l.rate) * l.ltime) / (l.ltime*12))";
		// 빠진 사람들은 repay 줄여줘야됨
		try {
			ps=con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("재상환 DB 오류"+e);}return false;
	}
	
	// 원금 상환
	public boolean endPoint() {
		String sql="update personalloan p , customer c , loan l set "
				+ "	c.depositmoney=c.depositmoney-(p.lbalance + (((p.lbalance * l.rate) * l.ltime) / (l.ltime*12)))  "
				+ "	where p.l_no=l.l_no and p.a_no=c.a_no and p.lcount<=0 ";
		String sql2="delete from personalloan where lcount=0";
		
		try {
			ps=con.prepareStatement(sql);
			ps.executeUpdate();
			
			ps=con.prepareStatement(sql2);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("원금상환 DB 오류"+e);}return false;
	
	
	}
	
	// 대출 상환 내역 출력
	public ArrayList<LoanDataDTO> getLoanData(){ // 이자율이랑 대출기간 가지고와서 이자 따로 구해주기!
		String sql= "select p.p_no , c.name , l.lname , p.lbalance , l.ltime , c.account , p.repay , p.ldate , l.rate "
				+ " from customer c , loan l , personalloan p "
				+ " where c.a_no=p.a_no and l.l_no=p.l_no";
		ArrayList<LoanDataDTO> list= new ArrayList<>();
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				LoanDataDTO dto= new LoanDataDTO(rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getInt(4), rs.getInt(5), 
						rs.getString(6), rs.getInt(7) , rs.getString(8) , rs.getFloat(9));
				list.add(dto);
			}
		} catch (Exception e)  {System.out.println("대출 상환 내역 출력 오류"+e);} return list;
	}
	
	
	
	
	
	
}
