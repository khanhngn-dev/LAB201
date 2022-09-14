package data;

public class Product {
	private static final char SEPARATOR = ',';
	public static final String ID_FORMAT = "P\\d{3}";
	private String id;
	private int count;
	private String name;

	public Product() {
		this.id = "";
		this.count = 0;
		this.name = "";
	}

	public Product(String id, int count, String name) {
		this.id = id;
		this.count = count;
		this.name = name;
	}

	public Product(String line) {
		String[] parts = line.split("" + SEPARATOR);
		this.id = parts[0].trim();
		this.count = Integer.parseInt(parts[1].trim());
		this.name=parts[2].trim();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return id + SEPARATOR + count + SEPARATOR + name;
	}
}