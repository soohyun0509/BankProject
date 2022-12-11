package model.DTO;

public class SavingDTO {
	
	private int s_no;
	private String s_name;
	private String s_content;
	private int s_rate;
	private int s_limit;
	private int s_month;
	
	public SavingDTO() {	}

	public SavingDTO(int s_no, String s_name, String s_content, int s_rate, int s_limit, int s_month) {
		super();
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_content = s_content;
		this.s_rate = s_rate;
		this.s_limit = s_limit;
		this.s_month = s_month;
	}

	@Override
	public String toString() {
		return "SavingDTO [s_no=" + s_no + ", s_name=" + s_name + ", s_content=" + s_content + ", s_rate=" + s_rate
				+ ", s_limit=" + s_limit + ", s_month=" + s_month + "]";
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_content() {
		return s_content;
	}

	public void setS_content(String s_content) {
		this.s_content = s_content;
	}

	public int getS_rate() {
		return s_rate;
	}

	public void setS_rate(int s_rate) {
		this.s_rate = s_rate;
	}

	public int getS_limit() {
		return s_limit;
	}

	public void setS_limit(int s_limit) {
		this.s_limit = s_limit;
	}

	public int getS_month() {
		return s_month;
	}

	public void setS_month(int s_month) {
		this.s_month = s_month;
	}
	
	
	
	
	
	
}
