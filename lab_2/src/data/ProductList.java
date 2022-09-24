package data;

import MyTool.Tool;
import mng.Menu;

import java.util.ArrayList;
import java.util.UUID;

public class ProductList extends ArrayList<Product> {

	public void initFromFile() {
		ProductList pl = Tool.loadFromFile("test.txt");
		if (pl != null) {
			for (Product product : pl) {
				this.add(product);
			}
		}
	}

	public void addProduct() {
		String id = UUID.randomUUID().toString().split("-")[0]; // UUID generates a unique ID globally.
		int pos;
		String name;
		do {
			name = Tool.inputPattern("Enter the product name [AAAAA]", Product.NAME_FORMAT).toUpperCase();
			pos = searchProductName(name);
			if (pos != -1) {
				System.out.println("Product name already exists");
			}
		} while (pos != -1);
		double price = Tool.readNumInRange("Enter the price [0-" + Product.MAX_PRICE + "]", Product.MAX_PRICE);
		int quantity = Tool.readNumInRange("Enter the quantity [0-" + Product.MAX_QUANTITY + "]", Product.MAX_QUANTITY);
		String status = Tool.inputPattern("Enter the status [Available|Not Available]", "Available|Not Available");
		Product p = new Product(id, name, price, quantity, status);
		this.add(p);
		System.out.println("Product added successfully");
		Tool.askToContinue();
	}

	public int searchProductName(String name) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getProductName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public void checkExistProduct() {
		if (this.isEmpty()) {
			System.out.println("List is empty");
			return;
		}
		String name = Tool.inputNonBlank("Enter the product name").toUpperCase();
		int pos = searchProductName(name);
		if (pos != -1) {
			System.out.println("Exist Product");
		} else {
			System.out.println("No Product Found!");
		}
		Tool.askToContinue();
	}

	public void searchProductInformationByName() {
		if (this.isEmpty()) {
			System.out.println("List is empty");
			return;
		}
		String name = Tool.inputNonBlank("Enter parts of the product name").toUpperCase();
		int count = 0;
		for (Product product : this) {
			if (product.getProductName().contains(name)) {
				System.out.println((count + 1) + ". " + product);
				count++;
			}
		}
		if (count == 0) {
			System.out.println("Cannot find products containing \"" + name + "\"");
		}
		Tool.askToContinue();
	}

	public int searchProductID(String id) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getProductID().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	public void updateProductInformation() {
		String id = Tool.inputNonBlank("Enter the productID");
		int pos = searchProductID(id);
		if (pos == -1) {
			System.out.println(id + " doesn't exist");
			return;
		}
		Product p = this.get(pos);
		// Product name
		String name;
		boolean valid;
		do {
			System.out.print("Enter the new name, enter to skip: ");
			name = Tool.SC.nextLine().trim().toUpperCase();
			if (!name.isEmpty()) {
				if (name.matches(Product.NAME_FORMAT)) {
					if (searchProductName(name) == -1) {
						p.setProductName(name);
						valid = true;
					} else {
						System.out.println("Product name already used");
						valid = false;
					}
				} else {
					System.out.println("Name doesn't match format");
					valid = false;
				}
			} else {
				valid = true;
			}
		} while (!valid);
		// Price
		String temp;
		do {
			try {
				System.out.print("Enter the new price, enter to skip: ");
				temp = Tool.SC.nextLine().trim();
				if (!temp.isEmpty()) {
					double price = Double.parseDouble(temp);
					if (price >= 0 && price <= Product.MAX_PRICE) {
						p.setUnitPrice(price);
						valid = true;
					} else {
						valid = false;
					}
				} else {
					valid = true;
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
				valid = false;
			}
		} while (!valid);
		// Quantity
		do {
			try {
				System.out.print("Enter the new quantity, enter to skip: ");
				temp = Tool.SC.nextLine().trim();
				if (!temp.isEmpty()) {
					int price = Integer.parseInt(temp);
					if (price >= 0 && price <= Product.MAX_QUANTITY) {
						p.setQuantity(price);
						valid = true;
					} else {
						valid = false;
					}
				} else {
					valid = true;
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
				valid = false;
			}
		} while (!valid);
		String status;
		do {
			System.out.print("Enter the new status, enter to skip: ");
			status = Tool.SC.nextLine().trim();
			if (!status.isEmpty()) {
				if (status.matches("Available|Not available")) {
					p.setStatus(status);
					valid = true;
				} else {
					valid = false;
				}
			} else {
				valid = true;
			}
		} while (!valid);
		System.out.println("Update product successfully");
	}

	public void deleteProduct() {
		String id = Tool.inputNonBlank("Enter the productID");
		int pos = searchProductID(id);
		if (pos == -1) {
			System.out.println(id + " doesn't exist");
			return;
		}
		this.remove(pos);
		System.out.println(id + "has been deleted");
	}

	public void updateProduct() {
		String[] options = {"Update product information", "Delete product"};
		Menu menu = new Menu(options);
		int choice = menu.getChoice("Select");
		switch (choice) {
			case 1 -> {
				this.updateProductInformation();
			}
			case 2 -> {
				this.deleteProduct();
			}
			default -> {
				System.out.println("Invalid choice");
			}
		}
		Tool.askToContinue();
	}

	public void printProduct() {
		if (this.isEmpty()) {
			System.out.println("List is empty");
			return;
		}
		int count = 1;
		System.out.println("Product list");
		for (Product product : this) {
			System.out.println(count + ". " + product);
			count++;
		}
		Tool.askToContinue();
	}

	public void saveToFile() {
		Tool.writeToFile("test.txt",this);
		System.out.println("Write to file successfully");
		Tool.askToContinue();
	}

	public void printListFromFile() {
		ProductList pl  = Tool.loadFromFile("test.txt");
		System.out.println("Printing list from file");
		for (Product product : pl) {
			System.out.println(product);
		}
		Tool.askToContinue();
	}
}