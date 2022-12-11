package model.DTO;

public class ChattingDTO {
	
	private int c_no;
	private int c_room;
	private String name;
	private String account;
	private String c_content;
	private String c_date;
	
	public ChattingDTO() {super();	}
	
	
	
	public ChattingDTO(int c_room, String name) {
		super();
		this.c_room = c_room;
		this.name = name;
	}



	public ChattingDTO(int c_no, int c_room, String name, String account, String c_content, String c_date) {
		super();
		this.c_no = c_no;
		this.c_room = c_room;
		this.name = name;
		this.account = account;
		this.c_content = c_content;
		this.c_date = c_date;
	}

	@Override
	public String toString() {
		return "ChattingDTO [c_no=" + c_no + ", c_room=" + c_room + ", name=" + name + ", account=" + account
				+ ", c_content=" + c_content + ", c_date=" + c_date + "]";
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public int getC_room() {
		return c_room;
	}

	public void setC_room(int c_room) {
		this.c_room = c_room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	
	
	
}
