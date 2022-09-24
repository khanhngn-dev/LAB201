package data;

public class Product {
	public static final String NAME_FORMAT = "[A-Za-z]{5}";
	public static final String SEPARATOR = "-";
	public static final double MAX_PRICE = 10000;
	public static final int MAX_QUANTITY = 1000;
	private String productID;
	private String productName;
	private double unitPrice;
	private int quantity;
	private String status;

	public Product() {
		this.productID = "";
		this.productName = "";
		this.unitPrice = 0.0;
		this.quantity = 0;
		this.status = "Available";
	}

	public Product(String productID, String productName, double unitPrice, int quantity, String status) {
		this.productID = productID;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.status = status;
	}

	public Product(String line) {
		String[] parts = line.split("-");
		this.productID = parts[0].trim();
		this.productName = parts[1].trim().toUpperCase();
		this.unitPrice = Double.parseDouble(parts[2]);
		this.quantity = Integer.parseInt(parts[3]);
		this.status = parts[4].trim();
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return productID + SEPARATOR + productName + SEPARATOR + unitPrice + SEPARATOR + quantity + SEPARATOR + status;
	}
}