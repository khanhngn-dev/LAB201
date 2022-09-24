package MyTool;

import data.Product;
import data.ProductList;

import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class Tool {
	public static Scanner SC = new Scanner(System.in);

	public static String inputNonBlank(String message) {
		do {
			try {
				System.out.print(message + ": ");
				String temp = SC.nextLine().trim();
				if (!temp.isEmpty()) {
					return temp;
				}
				System.out.println("Input cannot be empty");
			} catch (Exception e) {
				System.out.println("Invalid input");
			}
		} while (true);
	}

	public static String inputPattern(String message, String pattern) {
		do {
			try {
				System.out.print(message + ": ");
				String temp = SC.nextLine().trim();
				if (!temp.isEmpty()) {
					if (temp.matches(pattern)) {
						return temp;
					} else {
						System.out.println("Input does not match the required pattern");
					}
				} else {
					System.out.println("Input cannot be empty");
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
			}
		} while (true);
	}

	public static double readNum(String message) {
		double num = 0;
		boolean valid;
		do {
			try {
				num = Double.parseDouble(inputNonBlank(message));
				valid = true;
			} catch (Exception e) {
				System.out.println("Invalid input");
				valid = false;
			}
		} while (!valid);
		return num;
	}

	public static int readNumInRange(String message, int RANGE) {
		int num = -1;
		do {
			try {
				num = Integer.parseInt(inputNonBlank(message));
			} catch (Exception e) {
				System.out.println("Invalid input");
				num = -1;
			}
		} while (num < 0 || num > RANGE);
		return num;
	}

	public static double readNumInRange(String message, double RANGE) {
		double num = 0;
		do {
			try {
				num = Double.parseDouble(inputNonBlank(message));
			} catch (Exception e) {
				System.out.println("Invalid input");
				num = -1;
			}
		} while (num < 0.0 || num > RANGE);
		return num;
	}

	public static ProductList loadFromFile(String filename) {
		File f = new File(filename);
		if (!f.exists()) {
			System.out.println(filename + "does not exist");
			return null;
		}
		if (!f.isFile()) {
			System.out.println(filename + "is not supported");
			return null;
		}
		ProductList pl = new ProductList();
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String temp;
			while ((temp = br.readLine()) != null) {
				Product p = new Product(temp);
				pl.add(p);
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pl;
	}

	public static void writeToFile(String filename, ProductList pl) {
		File f = new File(filename);
		if (!f.exists()) {
			System.out.println(filename + "does not exist");
			return;
		}
		if (!f.isFile()) {
			System.out.println(filename + "is not supported");
			return;
		}
		try {
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Product product : pl) {
				bw.write(product.toString());
			}
			bw.flush(); // Flush the remaining bytes.
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void askToContinue() {
		String temp = inputPattern("Do you want to continue? [Y|N]","^Y|N|y|n$").toUpperCase();
		if (temp.equals("N")) {
			System.exit(0);
		}
	}
}