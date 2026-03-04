package lambda.dto;

public class Order {

	public String id;
	public String itemName;
	public int quantity;

	public Order() {

	}

	public Order(String id, String itemName, int quantity) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.quantity = quantity;
	}




}
