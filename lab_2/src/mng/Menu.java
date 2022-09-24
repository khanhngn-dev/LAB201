package mng;

import MyTool.Tool;

public class Menu {
	String[] options;

	public Menu(String[] options) {
		this.options = options;
	}

	public int getChoice(String title) {
		System.out.println(title);
		for (int i = 0; i < options.length; i++) {
			System.out.println((i+1) + ". " + options[i]);
		}
		System.out.println("Others-Quit");
		return (int) Tool.readNum("Select an option");
	}
}