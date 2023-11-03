package service;

import persistence.dao.LectureRoomsDAO;
import persistence.dao.*;
import persistence.dto.*;
import java.util.List;
import java.util.Scanner;

public class LectureRoomsService {

    private final LectureRoomsDAO lectureRoomsDAO;
    private final Scanner sc;

    public LectureRoomsService(LectureRoomsDAO lectureRoomsDAO) {
        this.lectureRoomsDAO = lectureRoomsDAO;
        sc = new Scanner(System.in);
    }

    public void create() {
        System.out.println("등록할 건물 이름과 강의실을 입력하세요.");
        LectureRoomsDTO lectureRoomsDTO = new LectureRoomsDTO(sc.next(), sc.next());
        lectureRoomsDAO.insert(lectureRoomsDTO);
    }

    public List<LectureRoomsDTO> read() {
        LectureRoomsDTO lectureRoomsDTO = new LectureRoomsDTO();
        return lectureRoomsDAO.selectAll(lectureRoomsDTO);
    }

    public void delete() {
        System.out.println("삭제할 건물 이름과 강의실을 입력하세요.");
        LectureRoomsDTO lectureRoomsDTO = new LectureRoomsDTO(sc.next(), sc.next());
        lectureRoomsDAO.delete(lectureRoomsDTO);
    }

}
