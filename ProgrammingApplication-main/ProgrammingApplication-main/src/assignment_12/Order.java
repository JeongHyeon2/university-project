package assignment_12;

public class Order {
	private Food food;
	private Person person;
	private int amount;

	public Order(Person p,Food f, int a) {
		person=p;
		food = f;
		amount = a;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person p) {
		person=p;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food f) {
		this.food = f;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
