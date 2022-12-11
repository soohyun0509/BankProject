package model.DTO;

public class LoanDTO {
	private int l_no;
	private String lname;
	private String lcontent;
	private int limitmoney;
	private double rate;
	private int ltime;
    
    public LoanDTO() {}

	public LoanDTO(int l_no, String lname, String lcontent, int limitmoney, double rate, int ltime) {
		super();
		this.l_no = l_no;
		this.lname = lname;
		this.lcontent = lcontent;
		this.limitmoney = limitmoney;
		this.rate = rate;
		this.ltime = ltime;
	}

	public int getL_no() {
		return l_no;
	}

	public void setL_no(int l_no) {
		this.l_no = l_no;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getLcontent() {
		return lcontent;
	}

	public void setLcontent(String lcontent) {
		this.lcontent = lcontent;
	}

	public int getLimitmoney() {
		return limitmoney;
	}

	public void setLimitmoney(int limitmoney) {
		this.limitmoney = limitmoney;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getLtime() {
		return ltime;
	}

	public void setLtime(int ltime) {
		this.ltime = ltime;
	}

	@Override
	public String toString() {
		return "LoanDTO [l_no=" + l_no + ", lname=" + lname + ", lcontent=" + lcontent + ", limitmoney=" + limitmoney
				+ ", rate=" + rate + ", ltime=" + ltime + "]";
	}

	
	

    
    
}
