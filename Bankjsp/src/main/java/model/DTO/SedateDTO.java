package model.DTO;

public class SedateDTO {
	private int a_no;
	private String s_edate;
	
	public SedateDTO() {
		super();
	}
	public SedateDTO(int a_no, String s_edate) {
		super();
		this.a_no = a_no;
		this.s_edate = s_edate;
	}
	@Override
	public String toString() {
		return "s_edateDTO [a_no=" + a_no + ", s_edate=" + s_edate + "]";
	}
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	public String getS_edate() {
		return s_edate;
	}
	public void setS_edate(String s_edate) {
		this.s_edate = s_edate;
	}
	
	
	
	

}
