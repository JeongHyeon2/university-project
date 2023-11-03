package service;

import persistence.MyBatisConnectionFactory;
import persistence.dao.AccountsDAO;
import persistence.dao.StudentsDAO;
import persistence.dto.StudentsDTO;

import java.util.List;
import java.util.Scanner;

public class StudentsService {

    private final StudentsDAO studentsDAO;
    Scanner sc;

    public StudentsService(StudentsDAO studentsDAO) {
        this.studentsDAO = studentsDAO;
        sc = new Scanner(System.in);
    }

    public void create() {
        System.out.println("학번 | 이름 | 학년 | 생년월일 | 전화번호");
        String [] arr = sc.nextLine().split(" ");
        int sId = Integer.parseInt(arr[0]);
        String name = arr[1];
        int grade = Integer.parseInt(arr[2]);
        String bD = arr[3];
        String phNum = arr[4];

        StudentsDTO studentsDTO = new StudentsDTO(sId, name, grade, bD,phNum);
        studentsDAO.insert(studentsDTO);

        AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AccountsService accountsService = new AccountsService(accountsDAO);
        accountsService.create(Integer.toString(sId), bD.replaceAll("-", ""), 3);
    }

    public List<StudentsDTO> read() {
        StudentsDTO studentsDTO = new StudentsDTO();
        return studentsDAO.selectAll(studentsDTO);
    }

    public void updateAll() {
        StudentsDTO studentsDTO = new StudentsDTO();
        System.out.println("Id | 이름");
        String s = sc.nextLine();
        String [] arr = s.split(" ");

        int student_id = Integer.parseInt(arr[0]);

        String name = arr[1];
        studentsDTO.setStudent_id(student_id);
        studentsDTO.setName(name);
        studentsDAO.updateName(studentsDTO);
    }

    public void updateName() {
        StudentsDTO studentsDTO = new StudentsDTO();
        studentsDTO.setStudent_id(sc.nextInt());
        studentsDTO.setName(sc.next());
        studentsDAO.updateName(studentsDTO);
    }

    public void updateGrade() {
        StudentsDTO studentsDTO = new StudentsDTO();
        studentsDTO.setStudent_id(sc.nextInt());
        studentsDTO.setGrade(sc.nextInt());
        studentsDAO.updateGrade(studentsDTO);
    }

    public void updateBirthDate() {
        StudentsDTO studentsDTO = new StudentsDTO();
        studentsDTO.setStudent_id(sc.nextInt());
        studentsDTO.setBirth_date(sc.next());
        studentsDAO.updateBirthDate(studentsDTO);
    }

    public void delete() {
        StudentsDTO studentsDTO = new StudentsDTO();
        int id = sc.nextInt();
        studentsDTO.setStudent_id(id);
        studentsDAO.deleteStudent(studentsDTO);

        AccountsDAO accountsDAO = new AccountsDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AccountsService accountsService = new AccountsService(accountsDAO);
        accountsService.delete(id+"");
    }

}
