package model.DTO;

public class SavingDataDTO {
	private int ps_no;
	private int s_no;
	private int a_no;
	private String s_account;
	private String s_start;
	private String s_end;
	private int s_month;
	private int s_repay;
	
	public SavingDataDTO() {	}
	
	

	public SavingDataDTO(int a_no, String s_start) {
		super();
		this.a_no = a_no;
		this.s_start = s_start;
	}



	public SavingDataDTO(int ps_no, int s_no, int a_no, String s_account, String s_start, String s_end, int s_month,
			int s_repay) {
		super();
		this.ps_no = ps_no;
		this.s_no = s_no;
		this.a_no = a_no;
		this.s_account = s_account;
		this.s_start = s_start;
		this.s_end = s_end;
		this.s_month = s_month;
		this.s_repay = s_repay;
	}

	@Override
	public String toString() {
		return "SavingDataDTO [ps_no=" + ps_no + ", s_no=" + s_no + ", a_no=" + a_no + ", s_account=" + s_account
				+ ", s_start=" + s_start + ", s_end=" + s_end + ", s_month=" + s_month + ", s_repay=" + s_repay + "]";
	}

	public int getPs_no() {
		return ps_no;
	}

	public void setPs_no(int ps_no) {
		this.ps_no = ps_no;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public int getA_no() {
		return a_no;
	}

	public void setA_no(int a_no) {
		this.a_no = a_no;
	}

	public String getS_account() {
		return s_account;
	}

	public void setS_account(String s_account) {
		this.s_account = s_account;
	}

	public String getS_start() {
		return s_start;
	}

	public void setS_start(String s_start) {
		this.s_start = s_start;
	}

	public String getS_end() {
		return s_end;
	}

	public void setS_end(String s_end) {
		this.s_end = s_end;
	}

	public int getS_month() {
		return s_month;
	}

	public void setS_month(int s_month) {
		this.s_month = s_month;
	}

	public int getS_repay() {
		return s_repay;
	}

	public void setS_repay(int s_repay) {
		this.s_repay = s_repay;
	}
	
	
	
}
