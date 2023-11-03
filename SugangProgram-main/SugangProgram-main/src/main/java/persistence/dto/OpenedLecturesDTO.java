package persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class OpenedLecturesDTO {

    private String lecture_id;
    private int class_id;
    private String professor_id;
    private String building_id;
    private String room_id;
    private String schedule;
    private int applied_students_number;
    private int capacity;
    private boolean can_be_applied;
    private boolean can_modify_plan;
    private String plan;

    public OpenedLecturesDTO() { }

    public OpenedLecturesDTO(String lId, int cId, String pId, String bId, String rId, String schedule, int capacity) {
        lecture_id = lId;
        class_id = cId;
        professor_id = pId;
        building_id = bId;
        room_id = rId;
        this.schedule = schedule;
        applied_students_number = 0;
        this.capacity = capacity;
        can_be_applied = false;
        plan = null;
    }

    public String toString() {
       StringBuilder sb = new StringBuilder();
        sb.append("강의코드:").append(lecture_id);
        sb.append(" 분반코드:").append(class_id);
        sb.append(" 교원번호:").append(professor_id);
        sb.append(" 강의실:").append(building_id).append(room_id);
        sb.append(" 강의시간:").append(schedule);
        sb.append(" 수강인원:" ).append(applied_students_number);
        sb.append(" 정원:").append(capacity);
        sb.append(" 수강신청:");
        if(can_be_applied)
            sb.append("가능");
        else
            sb.append("불가능");
        sb.append(" 강의계획서: ");
        if(can_modify_plan)
            sb.append("작성가능\n");
        else
            sb.append("작성불가능\n");
        return sb.toString();
    }

}
