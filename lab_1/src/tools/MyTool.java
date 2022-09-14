package tools;

import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;

public class MyTool {
	public static final Scanner SC = new Scanner(System.in);

	public static boolean validStr(String str, String regex) {
		return str.matches(regex);
	}

	// Valid pwd contains at least 1 character, 1 digit and 1 special character with a minLen
	// Regex: \d - matches digit
	// Regex: \W [^a-zA-Z0-9] - matches not a character and not a digit
	public static boolean validPassword(String str, int minLen) {
		if (str.length() < minLen) {
			return false;
		}
		return str.matches(".*[a-zA-Z]+.*") && str.matches(".*[\\d]+.*") && str.matches(".*[\\W]+.*");
	}

	// Parse date into valid date
	// YYYY/dd/MM 2000/30/02 -> 2000/01/03
	public static Date parseDate(String dateStr, String dateFormat) {
		SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
		dF.applyPattern(dateFormat);
		try {
			long t = dF.parse(dateStr).getTime();
			return new Date(t);
		} catch (ParseException e) {
			System.out.println(e);
		}
		return null;
	}

	// Parse Date object into string with dateFormat
	public static String dateToStr(Date date, String dateFormat) {
		SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
		dF.applyPattern(dateFormat);
		return dF.format(date);
	}

	// Convert boolStr into boolean
	public static boolean parseBool(String boolStr) {
		char c = boolStr.trim().toUpperCase().charAt(0);
		return c == '1' || c == 'Y' || c == 'T';
	}

	public static String readNonBlank(String message) {
		String input = "";
		do {
			System.out.print(message + ": ");
			input = SC.nextLine().trim();
		} while (input.isEmpty());
		return input;
	}

	public static String readPattern(String message, String pattern) {
		boolean valid;
		String input = "";
		do {
			System.out.print(message + ": ");
			input = SC.nextLine().trim();
			valid = validStr(input, pattern);
		} while (!valid);
		return input;
	}

	public static boolean readBool(String message) {
		String input;
		System.out.print(message + "[1/0-Y/N-T/F]: ");
		input = SC.nextLine().trim();
		if (input.isEmpty()) {
			return false;
		}
		char c = Character.toUpperCase(input.charAt(0));
		return c == '1' || c == 'Y' || c == 'T';
	}

	public static List<String> readLinesFromFile(String filename) {
		try {
			File f = new File(filename);
			if (!f.exists()) {
				throw new FileNotFoundException(filename + " does not exist");
			}
			if (!f.isFile()) {
				throw new Exception(filename + " is not a valid file");
			}
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			ArrayList<String> list = new ArrayList<String>();
			while (br.ready()) {
				String temp = br.readLine().trim();
				if (!temp.isEmpty()) {
					list.add(temp);
				}
			}
			br.close();
			fr.close();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public static void writeFile(String filename, List list) {
		try {
			FileWriter fw = new FileWriter(filename);
			PrintWriter pw = new PrintWriter(fw);
			for (Object o : list) {
				pw.println(o);
			}
			pw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Phone: 9 or 11 digits - OR
		System.out.println("Tests with phone numbers:");
		System.out.println(validStr("012345678", "\\d{9}|\\d{11}"));
		System.out.println(validStr("01234567891", "\\d{9}|\\d{11}"));
		System.out.println(validStr("12345678", "\\d{9}|\\d{11}"));
		// Test password - OK
		System.out.println(validPassword("qwerty", 8)); // false
		System.out.println(validPassword("qwertyABC", 8)); // false
		System.out.println(validPassword("12345678", 8)); // false
		System.out.println(validPassword("qbc123456", 8)); // false
		System.out.println(validPassword("qbc@123456", 8)); // true
		// ID format D000 -> OK
		System.out.println("Tests with IDs:");
		System.out.println(validStr("A0001", "D\\d{3}"));
		System.out.println(validStr("10001", "D\\d{3}"));
		System.out.println(validStr("D0001", "D\\d{3}"));
		System.out.println(validStr("D101", "D\\d{3}"));
		//Test date format -> OK
		String s = dateToStr(new Date(),"yyyy/MM/dd");
		System.out.println(parseDate(s,"yyyy/MM/dd"));
		System.out.println(s);
		Date d = parseDate("2022:12:07", "yyyy:MM:dd");
		System.out.println(d);
		System.out.println(dateToStr(d, "dd/MM/yyyy")); // test OK
		d = parseDate("12/07/2022", "MM/dd/yyyy");
		System.out.println(d);
		d = parseDate("2022/07/12", "yyyy/dd/MM");
		System.out.println(d);
		d = parseDate("2000/29/02", "yyyy/dd/MM");
		System.out.println(d);
		d = parseDate("2000/30/02", "yyyy/dd/MM");
		System.out.println(d);
		d = parseDate("2000/40/16", "yyyy/dd/MM");
		System.out.println(d);
		// Test iput data -> ok
		String input = readNonBlank("Input a non-blank string");
		System.out.println(input);// OK
		input = readPattern("Phone 9/11 digits", "\\d{9}|\\d{11}");
		System.out.println(input);// OF
		input = readPattern("ID- format X00000", "X\\d{5}");
		System.out.println(input);// OK
		boolean b = readBool("Input boolean");
		System.out.println(b);// OK
	}
}