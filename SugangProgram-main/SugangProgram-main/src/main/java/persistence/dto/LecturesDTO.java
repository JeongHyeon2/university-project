package persistence.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturesDTO {

    private String lecture_id;
    private int target_grade;
    private String title;
    private int semester;
    private int credit;

    public LecturesDTO() { }

    public LecturesDTO(String lecture_id, int target_grade, String title, int semester, int credit) {
        this.lecture_id = lecture_id;
        this.target_grade = target_grade;
        this.title = title;
        this.semester = semester;
        this.credit = credit;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("강의코드:").append(lecture_id);
        sb.append(" 과목명:").append(title);
        sb.append(" 대상학년:").append(target_grade);
        sb.append(" 학기:").append(semester);
        sb.append(" 학점:").append(credit).append("\n");
        return sb.toString();
    }

}
