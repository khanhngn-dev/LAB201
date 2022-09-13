package data;

public class Account {
	private String accName;
	private String pwd;
	private String role;

	public Account() {
		this.accName = "";
		this.pwd = "";
		this.role = "";
	}

	public Account(String name, String pwd, String role) {
		this.accName = name;
		this.pwd = pwd;
		this.role = role;
	}

	public Account(Account account) {
		this.accName = account.accName;
		this.pwd = account.pwd;
		this.role = account.role;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String name) {
		this.accName = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}