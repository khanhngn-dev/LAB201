package main;

import data.ProductList;
import mng.Menu;

public class main {
	public static void main(String[] args) {
		ProductList pl = new ProductList();
		pl.initFromFile();
		String[] options = {"Print", "Create a product", "Check exists Product", "Search Product Information by Name", "Update Product", "Save Products to file", "Print list Products from the file"};
		Menu menu = new Menu(options);
		int choice;
		do {
			choice = menu.getChoice("Product management");
			switch (choice) {
				case 1 -> {
					pl.printProduct();
				}
				case 2 -> {
					pl.addProduct();
				}
				case 3 -> {
					pl.checkExistProduct();
				}
				case 4 -> {
					pl.searchProductInformationByName();
				}
				case 5 -> {
					pl.updateProduct();
				}
				case 6 -> {
					pl.saveToFile();
				}
				case 7 -> {
					pl.printListFromFile();
				}
			}
		} while (choice >= 1 && choice <= options.length);

	}
}