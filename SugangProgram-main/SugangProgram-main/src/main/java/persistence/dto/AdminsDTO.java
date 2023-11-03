package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class AdminsDTO {

    private int admin_id;

    public AdminsDTO(){ }

    public AdminsDTO(int admin_id) {
        this.admin_id = admin_id;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID:").append(admin_id).append("\n");
        return sb.toString();
    }
}
