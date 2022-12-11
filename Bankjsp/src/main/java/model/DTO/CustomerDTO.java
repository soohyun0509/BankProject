package model.DTO;

public class CustomerDTO {
	private int a_no;
	private String name;
	private String phone;
	private String address;	
	private String lockpassword;
	private String account;
	private int depositmoney;
	private int haveloan;
	private int errorcount;

	public CustomerDTO() {
		// TODO Auto-generated constructor stub
	}

	public CustomerDTO(int a_no, String name, String phone, String address, String lockpassword, String account,
			int depositmoney, int haveloan, int errorcount) {
		super();
		this.a_no = a_no;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.lockpassword = lockpassword;
		this.account = account;
		this.depositmoney = depositmoney;
		this.haveloan = haveloan;
		this.errorcount = errorcount;
	}

	public int getA_no() {
		return a_no;
	}

	public void setA_no(int a_no) {
		this.a_no = a_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLockpassword() {
		return lockpassword;
	}

	public void setLockpassword(String lockpassword) {
		this.lockpassword = lockpassword;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getDepositmoney() {
		return depositmoney;
	}

	public void setDepositmoney(int depositmoney) {
		this.depositmoney = depositmoney;
	}

	public int getHaveloan() {
		return haveloan;
	}

	public void setHaveloan(int haveloan) {
		this.haveloan = haveloan;
	}

	public int getErrorcount() {
		return errorcount;
	}

	public void setErrorcount(int errorcount) {
		this.errorcount = errorcount;
	}

	@Override
	public String toString() {
		return "CustomerDTO [a_no=" + a_no + ", name=" + name + ", phone=" + phone + ", address=" + address
				+ ", lockpassword=" + lockpassword + ", account=" + account + ", depositmoney=" + depositmoney
				+ ", haveloan=" + haveloan + ", errorcount=" + errorcount + "]";
	}
	
	
	
	
	
	
}