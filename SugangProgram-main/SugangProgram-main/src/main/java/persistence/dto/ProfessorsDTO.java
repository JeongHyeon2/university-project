package persistence.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorsDTO {

    private String professor_id;
    private String name;
    private String phone_num;
    private String birth_date;

    public ProfessorsDTO() { }

    public ProfessorsDTO(String professor_id, String name, String phone_num, String birth_date) {
        this.professor_id = professor_id;
        this.name = name;
        this.phone_num = phone_num;
        this.birth_date = birth_date;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("교원번호:").append(professor_id);
        sb.append(" 이름:").append(name);
        sb.append(" 전화번호:").append(phone_num);
        sb.append(" 생일:").append(birth_date).append("\n");
        return sb.toString();
    }

}
