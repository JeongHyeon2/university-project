package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BuildingsDTO {

    private String building_id;

    public BuildingsDTO() { }

    public BuildingsDTO(String building_id) {
        this.building_id = building_id;
    }

}
