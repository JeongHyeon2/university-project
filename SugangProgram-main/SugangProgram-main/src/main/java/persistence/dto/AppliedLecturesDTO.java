package persistence.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppliedLecturesDTO {

    private String lecture_id;
    private int class_id;
    private int student_id;

    public AppliedLecturesDTO() { }

    public AppliedLecturesDTO(String lecture_id, int class_id, int student_id) {
        this.lecture_id = lecture_id;
        this.class_id = class_id;
        this.student_id = student_id;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("강의코드:").append(lecture_id);
        sb.append(" 분반코드:").append(class_id);
        sb.append(" 학번:").append(student_id).append("\n");
        return sb.toString();
    }

}
