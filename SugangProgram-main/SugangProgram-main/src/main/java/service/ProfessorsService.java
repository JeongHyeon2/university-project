package service;

import persistence.MyBatisConnectionFactory;
import persistence.dao.AccountsDAO;
import persistence.dao.ProfessorsDAO;
import persistence.dao.*;
import persistence.dto.*;
import persistence.dao.*;
import persistence.dto.*;
import java.util.List;
import java.util.Scanner;

public class ProfessorsService {

    private final ProfessorsDAO professorsDAO;
    Scanner sc;

    public ProfessorsService(ProfessorsDAO professorsDAO) {
        this.professorsDAO = professorsDAO;
        sc = new Scanner(System.in);
    }

    public void create() {
        System.out.print("교원번호 | 이름 | 전화번호 | 생년월일:");
        String []arr = sc.nextLine().split(" ");
        String pId = arr[0];
        String name = arr[1];
        String phNum = arr[2];
        String birthDate = arr[3];

        ProfessorsDTO pDTO = new ProfessorsDTO(pId, name, phNum, birthDate);
        professorsDAO.insert(pDTO);

        AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AccountsService accountsService = new AccountsService(accountsDAO);
        accountsService.create(pId,birthDate.replaceAll("-",""),2);
    }

    public List<ProfessorsDTO> read() {
        ProfessorsDTO professorsDTO = new ProfessorsDTO();
        return professorsDAO.selectAll(professorsDTO);
    }

    public void updateAll() {
        ProfessorsDTO professorsDTO = new ProfessorsDTO();
        professorsDTO.setProfessor_id(sc.next());
        professorsDTO.setName(sc.next());
        professorsDTO.setPhone_num(sc.next());
        professorsDTO.setBirth_date(sc.next());
        professorsDAO.updateAll(professorsDTO);
    }

    public void updateName() {
        ProfessorsDTO professorsDTO = new ProfessorsDTO();
        professorsDTO.setProfessor_id(sc.next());
        professorsDTO.setName(sc.next());
        professorsDAO.updateName(professorsDTO);
    }

    public void updatePhoneNum() {
        ProfessorsDTO professorsDTO = new ProfessorsDTO();
        System.out.print("Input Professor_id:");
        String pid = sc.nextLine();
        System.out.print("Input Phone_num:");
        String phNum = sc.nextLine();

        professorsDTO.setProfessor_id(pid);
        professorsDTO.setPhone_num(phNum);

        professorsDAO.updatePhoneNum(professorsDTO);
    }

    public void updateBirthDate() {
        ProfessorsDTO professorsDTO = new ProfessorsDTO();
        professorsDTO.setProfessor_id(sc.next());
        professorsDTO.setBirth_date(sc.next());
        professorsDAO.updateBirthDate(professorsDTO);
    }

    public void delete() {
        ProfessorsDTO professorsDTO = new ProfessorsDTO();
        String id = sc.next();
        professorsDTO.setProfessor_id(id);
        professorsDAO.deleteProfessor(professorsDTO);

        AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AccountsService accountsService = new AccountsService(accountsDAO);
        accountsService.delete(id);
    }

}
