package week_3;

public class Magazine extends book {
	private String date;
	public Magazine() { this("Null", 0, "Null", "Null"); }
	public Magazine(String n, int p, String a, String d) { super(n, p, a); date = d;}

	public void setDate(String d) {
		date=d;
	}
	public String getDate() {
		return date;
	}

}


