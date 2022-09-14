import data.*;
import mng.LogIn;
import mng.Menu;
import tools.MyTool;

public class main {
	public static void main(String[] args) {
		Account acc = null;
		boolean cont, valid;
		do {
			cont = false;
			AccountChecker accChk = new AccountChecker();
			acc = LogIn.inputAccount();
			valid = accChk.check(acc);
			if (!valid) {
				cont = MyTool.readBool("Invalid account - Try again?");
			}
			if (!valid && !cont) {
				System.exit(0);
			}
		} while (cont);

		LogIn loginObj = new LogIn(acc);
		if (acc.getRole().equalsIgnoreCase("ACC-1")) {
			loadAccOneRole(loginObj);
		}
		if (acc.getRole().equalsIgnoreCase("BOSS"))
		{
			loadBossRole(loginObj);
		}
		if (acc.getRole().equalsIgnoreCase("ACC-2")) {
			loadAccTwoRole(loginObj);
		}
		System.out.println("Bye.");
	}

	public static void loadAccOneRole(LogIn loginObj) {
		String[] options = {"Add new dealer", "Search a dealer", "Remove a dealer", "Update a dealer", "Print all dealers", "Print continuing dealers", "Print UN-continuing dealers", "Write to file"
		};
		Menu menu = new Menu(options);
		DealerList dList = new DealerList(loginObj);
		dList.initWithFile();
		int choice = 0;
		do {
			choice = menu.getChoice("Managing dealers");
			switch (choice) {
				case 1 -> {
					dList.addDealer();
				}
				case 2 -> {
					dList.searchDealer();
				}
				case 3 -> {
					dList.removeDealer();
				}
				case 4 -> {
					dList.updateDealer();
				}
				case 5 -> {
					dList.printAllDealers();
				}
				case 6 -> {
					dList.printContinuingDealers();
				}
				case 7 -> {
					dList.printUnContinuingDealers();
				}
				default -> {
					if (dList.isChanged()) {
						boolean res = MyTool.readBool("Data changed. Write to file?");
						if (res) {
							dList.writeDealerToFile();
						}
					}
				}
			}
		} while (choice > 0 && choice < menu.size());
	}

	public static void loadBossRole(LogIn loginObj) {
		String[] options = {"Add new account", "Search account", "Remove account", "Update account", "Print all accounts", "Write to file"};
		Menu menu = new Menu(options);
		AccountList aList = new AccountList(loginObj);
		aList.initWithFile();
		int choice = 0;
		do {
			choice = menu.getChoice("Managing Accounts");
			switch (choice) {
				case 1 -> {
					aList.addAccount();
				}
				case 2 -> {
					aList.searchAccount();
				}
				case 3 -> {
					aList.removeAccount();
				}
				case 4 -> {
					aList.updateAccount();
				}
				case 5 -> {
					aList.printAllAccounts();
				}
				default -> {
					if (aList.isChanged()) {
						boolean res = MyTool.readBool("Data changed. Write to file?");
						if (res) {
							aList.writeAccountToFile();
						}
					}
				}
			}
		} while (choice > 0 && choice < menu.size());
	}

	public static void loadAccTwoRole(LogIn loginObj) {
		String[] options = {"Add new delivery", "Search delivery", "Remove delivery", "Update delivery", "Print all deliveries", "Write to file"};
		Menu menu = new Menu(options);
		DeliveryList dList = new DeliveryList(loginObj);
		dList.initWithFile();
		int choice = 0;
		do {
			choice = menu.getChoice("Managing deliveries");
			switch (choice) {
				case 1 -> {
					dList.addDelivery();
				}
				case 2 -> {
					dList.searchDelivery();
				}
				case 3 -> {
					dList.removeDelivery();
				}
				case 4 -> {
					dList.updateDelivery();
				}
				case 5 -> {
					dList.printAllDeliveries();
				}
				default -> {
					if (dList.isChanged()) {
						boolean res = MyTool.readBool("Data changed. Write to file?");
						if (res) {
							dList.writeDeliveryToFile();
						}
					}
				}
			}
		} while (choice > 0 && choice < menu.size());

	}
}