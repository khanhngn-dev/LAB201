import data.Account;
import data.AccountChecker;
import data.DealerList;
import mng.LogIn;
import mng.Menu;
import tools.MyTool;

public class main {
	public static void main(String[] args) {
		Account acc = null;
		boolean cont = false;
		boolean valid = false;
		do {
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
			String[] options = {"Add new dealer", "Search a dealer", "Remove a dealer", "Update a dealer", "Print continuing dealers", "Print UN-continuing dealers", "Write to file"
			};
			Menu menu = new Menu(options);
			DealerList dList = new DealerList(loginObj);
			dList.initWithFile();
			int choice = 0;
		}
	}
}