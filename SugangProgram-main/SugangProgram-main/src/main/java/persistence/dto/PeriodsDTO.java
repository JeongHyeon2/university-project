package persistence.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeriodsDTO {

    private int grade;
    private String start_date;
    private String end_date;

    public PeriodsDTO() { }

    public PeriodsDTO(int grade, String start_date, String end_date) {
        this.grade = grade;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(grade == 0)
            sb.append("강의계획서 수정 기간");
        else
            sb.append(grade).append("학년 수강신청 기간");
        sb.append("시작날짜:").append(start_date);
        sb.append(" 종료날짜:").append(end_date).append("\n");
        return sb.toString();
    }

}
