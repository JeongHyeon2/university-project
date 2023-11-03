package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class AccountsDTO {

    private String id;
    private String password;
    private int user_type;

    public AccountsDTO() { }

    public AccountsDTO(String id, String password, int user_type) {
        this.id = id;
        this.password = password;
        this.user_type = user_type;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID:").append(id);
        sb.append(" 비밀번호:").append(password);
        if(user_type==1) sb.append(" 관리자").append("\n");
        else if(user_type==2)sb.append(" 교수").append("\n");
        else if(user_type==3) sb.append(" 학생").append("\n");

        return sb.toString();
    }

}
