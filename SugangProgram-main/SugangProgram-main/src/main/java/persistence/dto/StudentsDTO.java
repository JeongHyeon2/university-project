package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class StudentsDTO {

    private int student_id;
    private String name;
    private int grade;
    private String birth_date;
    private String phone_num;

    public StudentsDTO() { }

    public StudentsDTO(int student_id, String name, int grade, String birth_date,String phone_num) {
        this.student_id = student_id;
        this.name = name;
        this.grade = grade;
        this.birth_date = birth_date;
        this.phone_num = phone_num;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("학번:").append(student_id);
        sb.append(" 이름:").append(name);
        sb.append(" 학년:").append(grade);
        sb.append(" 전화번호:").append(phone_num);
        sb.append(" 생일:").append(birth_date).append("\n");
        return sb.toString();
    }

}
