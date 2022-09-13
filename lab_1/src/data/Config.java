package data;

import tools.MyTool;

import java.util.List;

public class Config {
	private static final String CONFIG_FILE = "DealerData/config.txt";
	private String accountFile;
	private String dealerFile;
	private String deliveryFile;

	public Config() {
		readData();
	}

	public String getAccountFile() {
		return accountFile;
	}

	public String getDealerFile() {
		return dealerFile;
	}

	public String getDeliveryFile() {
		return deliveryFile;
	}

	private void readData() {
		List<String> lines = MyTool.readLinesFromFile(CONFIG_FILE);
		for (String line : lines) {
			line = line.toUpperCase();
			String[] parts = line.split(":");
			if (line.contains("ACCOUN")) {
				accountFile = "DealerData/" + parts[1].trim();
			}
			if (line.contains("DEALE")) {
				dealerFile = "DealerData/" + parts[1].trim();
			}
			if (line.contains("DELIVER")) {
				deliveryFile = "DealerData/" + parts[1].trim();
			}
		}
	}
}