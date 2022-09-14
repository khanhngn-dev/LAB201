package data;

import tools.MyTool;

import java.util.Date;

public class Delivery {
	public static final String ID_FORMAT = "DL\\d{3}";
	private static final char SEPARATOR = '-';
	private String id;
	private Dealer dealer;
	private Product product;
	private Date date;

	public Delivery() {
		this.id = "";
		this.dealer = new Dealer();
		this.product = new Product();
		this.date = new Date();
	}

	public Delivery(String id, Dealer dealer, Product product, Date date) {
		this.id = id;
		this.dealer = dealer;
		this.product = product;
		this.date = date;
	}

	public Delivery(String line) {
		String[] parts = line.split("" + Delivery.SEPARATOR);
		this.id = parts[0];
		this.dealer = new Dealer(parts[1]);
		this.product = new Product(parts[2]);
		this.date = MyTool.parseDate(parts[3], "yyyy/MM/dd");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return id + SEPARATOR + dealer.toString() + SEPARATOR + product.toString() + SEPARATOR + MyTool.dateToStr( date,"yyyy/MM/dd");
	}
}