package mng;

import tools.MyTool;

import java.util.ArrayList;

public class Menu extends ArrayList<String> {
	public Menu() {
		super();
	}

	public Menu(String[] items) {
		super();
		for (String item : items) {
			this.add(item);
		}
	}

	public int getChoice(String title) {
		boolean valid = true;
		int choice = 0;
		do {
			try {
				System.out.println(title);
				for (int i = 0; i < this.size(); i++) {
					System.out.println((i+1) + ". " + this.get(i));
				}
				choice = Integer.parseInt(MyTool.SC.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid input");
				valid = false;
			}
		} while (!valid);
		return choice;
	}
}