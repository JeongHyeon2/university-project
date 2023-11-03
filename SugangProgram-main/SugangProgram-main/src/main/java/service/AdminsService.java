package service;

import persistence.dao.AdminsDAO;
import persistence.dao.*;
import persistence.dto.*;
import java.util.List;
import java.util.Scanner;

public class AdminsService {

    private final AdminsDAO adminsDAO;
    Scanner sc;

    public AdminsService(AdminsDAO adminsDAO){
        this.adminsDAO = adminsDAO;
        sc = new Scanner(System.in);
    }

    public void create() {
        AdminsDTO adminsDTO = new AdminsDTO(sc.nextInt());
        adminsDAO.insert(adminsDTO);
    }

    public List<AdminsDTO> read() {
        AdminsDTO adminsDTO = new AdminsDTO();
        return adminsDAO.selectAll(adminsDTO);
    }

    public void delete(){
        AdminsDTO adminsDTO = new AdminsDTO(sc.nextInt());
        adminsDAO.delete(adminsDTO);
    }

}
