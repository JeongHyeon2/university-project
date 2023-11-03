package °úÁ¦2_DoublyLikedList;

public class Student implements Comparable<Student>{

		private String studentNum, name;

		public Student(String s, String n) {
			studentNum = s;
			name = n;
		}

		public Student() {

		}

		public void setStudentNum(String s) {
			studentNum = s;
		}

		public void setName(String n) {
			name = n;
		}

		public String getStudenNum() {
			return studentNum;
		}

		public String getName() {
			return name;
		}

		@Override
		public int compareTo(Student o) {
			if (this.getStudenNum().compareTo(o.getStudenNum()) > 0) {
				return 1;
			}
			if (this.getStudenNum().compareTo(o.getStudenNum()) < 0) {
				return -1;
			}
			return 0;
		}
	

}
