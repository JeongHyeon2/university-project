package week_3;

public class book {
	private String name,author;
	private int page;
	 public book() { this("Null", 0, "Null"); }
	 public book(String n, int p, String a) { name = n; page = p; author = a; }

	public void setBname(String bn) {
		name=bn;
	}
	public void setpage(int p) {
		page=p;
	}
	public void setAuthor(String a) {
		author=a;
	}
	public String getname() {
		return name;
	}
	public String getAuthor() {
		return author;
	}
	public int getpage() {
		return page;
	}

}