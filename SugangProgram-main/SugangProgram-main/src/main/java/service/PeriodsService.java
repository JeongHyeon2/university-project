package service;

import persistence.dao.PeriodsDAO;
import persistence.dao.*;
import persistence.dto.*;
import java.util.List;

public class PeriodsService {

    private final PeriodsDAO periodsDAO;

    public PeriodsService(PeriodsDAO periodsDAO){
        this.periodsDAO = periodsDAO;
    }

    public void create(int grade, String start, String end) {
        PeriodsDTO periodsDTO = new PeriodsDTO(grade, start, end);
        periodsDAO.insert(periodsDTO);
    }

    public List<PeriodsDTO> read() {
        PeriodsDTO periodsDTO = new PeriodsDTO();
        return periodsDAO.selectAll(periodsDTO);
    }

    public List<PeriodsDTO> readByGrade(int grade) {
        PeriodsDTO periodsDTO = new PeriodsDTO();
        periodsDTO.setGrade(grade);
        return periodsDAO.selectByGrade(periodsDTO);
    }

    public void update(int grade, String start, String end) {
        PeriodsDTO periodsDTO = new PeriodsDTO(grade, start, end);
        periodsDAO.updateDates(periodsDTO);
    }

}
