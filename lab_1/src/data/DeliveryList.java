package data;

import mng.LogIn;
import tools.MyTool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeliveryList extends ArrayList<Delivery> {
	LogIn loginObj = null;
	boolean changed = false;
	private String deliveryFile = "";

	public DeliveryList(LogIn loginObj) {
		super();
		this.loginObj = loginObj;
	}

	private void loadDeliveriesFromFile() {
		List<String> lines = MyTool.readLinesFromFile(deliveryFile);
		for (String line : lines) {
			Delivery d = new Delivery(line);
			this.add(d);
		}
	}

	public void initWithFile() {
		Config cR = new Config();
		deliveryFile = cR.getDeliveryFile();
		loadDeliveriesFromFile();
	}

	private int searchDelivery(String id) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	public void searchDelivery() {
		String id = MyTool.readPattern("Enter the delivery id to search", Delivery.ID_FORMAT).toUpperCase();
		int pos = searchDelivery(id);
		if (pos == -1) {
			System.out.println(id + "Not found");
			return;
		}
		System.out.println(id + " has index of " + pos);
	}

	public void addDelivery() {
		String id, dId;
		Dealer dealer;
		Product product;
		int pos;
		do {
			id = MyTool.readPattern("Enter the new delivery ID", Delivery.ID_FORMAT).toUpperCase();
			pos = searchDelivery(id);
			if (pos != -1) {
				System.out.println("ID is duplicated");
			}
		} while (pos != -1);
		DealerList dL = new DealerList(loginObj);
		dL.initWithFile();
		do {
			dId = MyTool.readPattern("Enter the dealer ID", Dealer.ID_FORMAT).toUpperCase();
			pos = dL.searchDealer(dId);
			if (pos == -1) {
				System.out.println(dId + " not found!");
			}
		} while (pos == -1);
		dealer = dL.get(pos);
		String productId = "", name = "";
		int count = 0;
		boolean valid = false;
		do {
			try {
				productId = MyTool.readPattern("Enter the product ID", Product.ID_FORMAT).toUpperCase();
				count = Integer.parseInt(MyTool.readNonBlank("Enter the number of items"));
				name = MyTool.readNonBlank("Enter the name of the product").toUpperCase();
				valid = true;
			} catch (Exception e) {
				System.out.println("Invalid input");
			}
		} while (!valid);
		product = new Product(productId, count, name);
		Delivery delivery = new Delivery(id, dealer, product, new Date());
		this.add(delivery);
		System.out.println("New delivery has been added");
		changed = true;
	}

	public void removeDelivery() {
		String id = MyTool.readPattern("Enter the product id to remove", Delivery.ID_FORMAT).toUpperCase();
		int pos = searchDelivery(id);
		if (pos == -1) {
			System.out.println(id + " not found!");
			return;
		}
		this.remove(pos);
		System.out.println("Delivery has been removed");
		changed = true;
	}

	private Dealer updateDeliveryDealer() {
		DealerList dL = new DealerList(loginObj);
		dL.initWithFile();
		boolean valid;
		String id = "";
		do {
			System.out.println("Enter the dealer ID, Enter for omitting");
			id = MyTool.SC.nextLine().trim().toUpperCase();
			if (!id.isEmpty()) {
				if (MyTool.validStr(id, Dealer.ID_FORMAT)) {
					int pos = dL.searchDealer(id);
					if (pos == -1) {
						valid = false;
					} else {
						return dL.get(pos);
					}
				} else {
					valid = false;
				}
			} else {
				valid = true;
			}
		} while (!valid);
		return null;
	}

	private Product updateDeliveryProduct() {
		boolean valid;
		String id = "";
		do {
			System.out.println("Enter the product ID, Enter for omitting");
			id = MyTool.SC.nextLine().trim().toUpperCase();
			if (!id.isEmpty()) {
				if (MyTool.validStr(id, Product.ID_FORMAT)) {
					do {
						try {
							int count = Integer.parseInt(MyTool.readNonBlank("Enter the number of items"));
							String name = MyTool.readNonBlank("Enter the name of the product").toUpperCase();
							return new Product(id, count, name);
						} catch (Exception e) {
							valid = false;
							System.out.println("Invalid input");
						}
					} while (!valid);
				} else {
					valid = false;
				}
			} else {
				valid = true;
			}
		} while (!valid);
		return null;
	}

	public void updateDelivery() {
		System.out.println("Delivery's ID needs updating: ");
		String id = MyTool.readPattern("Enter the delivery id to search", Delivery.ID_FORMAT).toUpperCase();
		int pos = searchDelivery(id);
		if (pos == -1) {
			System.out.println(id + " not found!");
			return;
		}
		Delivery delivery = this.get(pos);
		Dealer d = updateDeliveryDealer();
		if (d != null) {
			delivery.setDealer(d);
			changed = true;
		}
		Product p = updateDeliveryProduct();
		if (p != null) {
			delivery.setProduct(p);
			changed = true;
		}
		boolean update = MyTool.readBool("Do you want to update the Date to today?");
		if (update) {
			Date date = new Date();
			delivery.setDate(date);
			changed = true;
		}
	}

	public void printAllDeliveries() {
		if (this.isEmpty()) {
			System.out.println("Empty list!");
			return;
		}
		for (Delivery delivery : this) {
			System.out.println(delivery);
		}
	}

	public void writeDeliveryToFile() {
		if (changed) {
			MyTool.writeFile(deliveryFile, this);
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