package mng;

import data.Account;
import data.AccountChecker;
import tools.MyTool;

public class LogIn {
	private Account acc = null;

	public LogIn(Account acc) {
		this.acc = acc;
	}

	public static Account inputAccount() {
		Account acc = null;
		String accName = MyTool.readNonBlank("Enter the account name");
		String pwd;
		pwd = MyTool.readNonBlank("Enter the account pwd");
		String role = MyTool.readNonBlank("Enter the account role");
		acc = new Account(accName, pwd, role);
		return acc;
	}

	public Account getAcc() {
		return acc;
	}
}