package model.DTO;

public class LoanDataDTO {

	// 대출 상환 내역 리스트 호출해줄 DTO 생성함 , 그냥 할까했는데 너무 많아...
	
	private int p_no; // 대출자 번호
	private String name; //대출자명
	private String lname; // 대출명
	private int lbalance; // 원금
	private int ltime; // 대출기간
	private String account; // 이체 계좌
	private int repay; // 납부상태
	private String ldate; // 대출 받은 날짜
	private float rate; // 납부상태
	
	
	
	public LoanDataDTO() {
		// TODO Auto-generated constructor stub
	}



	public LoanDataDTO(int p_no, String name, String lname, int lbalance, int ltime, String account, int repay,
			String ldate, float rate) {
		super();
		this.p_no = p_no;
		this.name = name;
		this.lname = lname;
		this.lbalance = lbalance;
		this.ltime = ltime;
		this.account = account;
		this.repay = repay;
		this.ldate = ldate;
		this.rate = rate;
	}



	public int getP_no() {
		return p_no;
	}



	public void setP_no(int p_no) {
		this.p_no = p_no;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



	public int getLbalance() {
		return lbalance;
	}



	public void setLbalance(int lbalance) {
		this.lbalance = lbalance;
	}



	public int getLtime() {
		return ltime;
	}



	public void setLtime(int ltime) {
		this.ltime = ltime;
	}



	public String getAccount() {
		return account;
	}



	public void setAccount(String account) {
		this.account = account;
	}



	public int getRepay() {
		return repay;
	}



	public void setRepay(int repay) {
		this.repay = repay;
	}



	public String getLdate() {
		return ldate;
	}



	public void setLdate(String ldate) {
		this.ldate = ldate;
	}



	public float getRate() {
		return rate;
	}



	public void setRate(float rate) {
		this.rate = rate;
	}



	@Override
	public String toString() {
		return "LoanDataDTO [p_no=" + p_no + ", name=" + name + ", lname=" + lname + ", lbalance=" + lbalance
				+ ", ltime=" + ltime + ", account=" + account + ", repay=" + repay + ", ldate=" + ldate + ", rate="
				+ rate + "]";
	}

	
		
	
	
	
}
