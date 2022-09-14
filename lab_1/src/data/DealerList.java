package data;

import mng.LogIn;
import tools.MyTool;

import java.util.ArrayList;
import java.util.List;

public class DealerList extends ArrayList<Dealer> {
	private static final String PHONE_PATTERN = "\\d{9}|\\d{11}";
	LogIn loginObj = null;
	boolean changed = false;
	private String dataFile = "";

	public DealerList(LogIn loginObj) {
		super();
		this.loginObj = loginObj;
	}

	private void loadDealerFromFile() {
		List<String> lines = MyTool.readLinesFromFile(dataFile);
		for (String line : lines) {
			Dealer d = new Dealer(line);
			this.add(d);
		}
	}

	public void initWithFile() {
		Config cR = new Config();
		dataFile = cR.getDealerFile();
		loadDealerFromFile();
	}

	public DealerList getContinuingList() {
		DealerList newList = new DealerList(loginObj);
		for (Dealer dealer : this) {
			if (dealer.isContinuing()) {
				newList.add(dealer);
			}
		}
		return newList;
	}

	public DealerList getUnContinuingList() {
		DealerList newList = new DealerList(loginObj);
		for (Dealer dealer : this) {
			if (!dealer.isContinuing()) {
				newList.add(dealer);
			}
		}
		return newList;
	}

	public int searchDealer(String ID) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getID().equals(ID)) {
				return i;
			}
		}
		return -1;
	}

	public void searchDealer() {
		String id = MyTool.readPattern("Input ID to search", Dealer.ID_FORMAT).toUpperCase();
		int pos = searchDealer(id);
		if (pos == -1) {
			System.out.println("Not found");
			return;
		}
		System.out.println("Dealer with ID: " + id + " has index of " + pos);
	}

	public void addDealer() {
		String ID;
		String name;
		String addr;
		String phone;
		boolean continuing;
		int pos;
		do {
			ID = MyTool.readPattern("Input ID of new dealer", Dealer.ID_FORMAT);
			ID = ID.toUpperCase();
			pos = searchDealer(ID);
			if (pos != -1) {
				System.out.println("ID is duplicated");
			}
		} while (pos != -1);
		name = MyTool.readNonBlank("Name of new dealer").toUpperCase();
		addr = MyTool.readNonBlank("Address of new dealer").toUpperCase();
		phone = MyTool.readPattern("Phone of new dealer", Dealer.PHONE_FORMAT);
		continuing = true;
		Dealer d = new Dealer(ID, name, addr, phone, continuing);
		this.add(d);
		System.out.println("New dealer has been added");
		changed = true;
	}

	public void removeDealer() {
		String id = MyTool.readPattern("Input ID to remove", Dealer.ID_FORMAT).toUpperCase();
		int pos = searchDealer(id);
		if (pos == -1) {
			System.out.println("Not found");
			return;
		}
		this.get(pos).setContinuing(false);
		System.out.println("Dealer has been removed");
		changed = true;
	}

	public void updateDealer() {
		System.out.println("Dealer's ID needs updating: ");
		String ID = MyTool.readPattern("Input ID to update", Dealer.ID_FORMAT);
		int pos = searchDealer(ID);
		if (pos < 0) {
			System.out.println("Dealer " + ID + " not found!");
			return;
		}
		Dealer d = this.get(pos);
		String newName = "";
		System.out.print("New name, Enter for omitting: ");
		newName = MyTool.SC.nextLine().trim().toUpperCase();
		if (!newName.isEmpty()) {
			d.setName(newName);
			changed = true;
		}
		String newAddr = "";
		System.out.print("New address, Enter for omitting: ");
		newAddr = MyTool.SC.nextLine().trim().toUpperCase();
		if (!newAddr.isEmpty()) {
			d.setName(newAddr);
			changed = true;
		}
		String newPhone = "";
		boolean valid = true;
		do {
			System.out.print("New address, Enter for omitting: ");
			newPhone = MyTool.SC.nextLine().trim();
			if (newPhone.isEmpty()) {
				valid = true;
			} else if (newPhone.matches(Dealer.PHONE_FORMAT)) {
				d.setName(newPhone);
				changed = true;
				valid = true;
			} else {
				valid = false;
			}
		} while (!valid);
	}

	public void printAllDealers() {
		if (this.isEmpty()) {
			System.out.println("Empty list!");
			return;
		}
		for (Dealer dealer : this) {
			System.out.println(dealer);
		}
	}

	public void printContinuingDealers() {
		this.getContinuingList().printAllDealers();
	}

	public void printUnContinuingDealers() {
		this.getUnContinuingList().printAllDealers();
	}

	public void writeDealerToFile() {
		if (changed) {
			MyTool.writeFile(dataFile, this);
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