package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LectureRoomsDTO {

    private String building_id;
    private String room_id;

    public LectureRoomsDTO() { }

    public LectureRoomsDTO(String building_id, String room_id) {
        this.building_id = building_id;
        this.room_id = room_id;
    }

}
