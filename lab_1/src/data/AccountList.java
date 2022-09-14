package data;

import mng.LogIn;
import tools.MyTool;

import java.util.ArrayList;
import java.util.List;

public class AccountList extends ArrayList<Account> {
	LogIn loginObj = null;
	boolean changed = false;
	private String accFile = "";

	public AccountList(LogIn loginObj) {
		super();
		this.loginObj = loginObj;
	}

	private void loadAccountsFromFile() {
		List<String> lines = MyTool.readLinesFromFile(accFile);
		for (String line : lines) {
			Account d = new Account(line);
			this.add(d);
		}
	}

	public void initWithFile() {
		Config cR = new Config();
		accFile = cR.getAccountFile();
		loadAccountsFromFile();
	}

	private int searchAccount(String accName) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getAccName().equals(accName)) {
				return i;
			}
		}
		return -1;
	}

	public void searchAccount() {
		String accName = MyTool.readPattern("Enter the account name to search", Account.ID_FORMAT).toUpperCase();
		int pos = searchAccount(accName);
		if (pos == -1) {
			System.out.println(accName + "Not found");
			return;
		}
		System.out.println(accName + " has index of " + pos);
	}

	public void addAccount() {
		String accName;
		String pwd;
		String role;
		int pos;
		do {
			accName = MyTool.readPattern("Enter the new account name", Account.ID_FORMAT).toUpperCase();
			pos = searchAccount(accName);
			if (pos != -1) {
				System.out.println("ID is duplicated");
			}
		} while (pos != -1);
		pwd = MyTool.readNonBlank("Password of new account");
		role = MyTool.readNonBlank("Role of new account").toUpperCase();
		Account acc = new Account(accName, pwd, role);
		this.add(acc);
		System.out.println("New account has been added");
		changed = true;
	}

	public void removeAccount() {
		String accName = MyTool.readPattern("Enter the account name to remove", Account.ID_FORMAT).toUpperCase();
		int pos = searchAccount(accName);
		if (pos == -1) {
			System.out.println(accName + " not found!");
			return;
		}
		this.remove(pos);
		System.out.println("Account has been removed");
		changed = true;
	}

	public void updateAccount() {
		System.out.println("Account's ID needs updating: ");
		String accName = MyTool.readPattern("Enter the account name to search", Account.ID_FORMAT).toUpperCase();
		int pos = searchAccount(accName);
		if (pos == -1) {
			System.out.println(accName + " not found!");
			return;
		}
		Account acc = this.get(pos);
		String newAccName = "";
		boolean valid;
		do {
			System.out.print("New account name, Enter for omitting: ");
			newAccName = MyTool.SC.nextLine().trim();
			if (!newAccName.isEmpty()) {
				if (MyTool.validStr(newAccName, Account.ID_FORMAT)) {
					acc.setAccName(newAccName);
					valid = true;
					changed = true;
				} else {
					valid = false;
				}
			} else {
				valid = true;
			}
		} while (!valid);
		String newPwd = "";
		System.out.print("New password, Enter for omitting: ");
		newPwd = MyTool.SC.nextLine().trim();
		if (!newPwd.isEmpty()) {
			acc.setPwd(newPwd);
			changed = true;
		}
		String newRole = "";
		System.out.print("New role, Enter for omitting: ");
		newRole = MyTool.SC.nextLine().trim().toUpperCase();
		if (!newRole.isEmpty()) {
			acc.setRole(newRole);
			changed = true;
		}
	}

	public void printAllAccounts() {
		if (this.isEmpty()) {
			System.out.println("Empty list!");
			return;
		}
		for (Account acc : this) {
			System.out.println(acc);
		}
	}

	public void writeAccountToFile() {
		if (changed) {
			MyTool.writeFile(accFile, this);
			changed = false;
		}
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

}