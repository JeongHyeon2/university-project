package service;

import persistence.dao.BuildingsDAO;
import persistence.dao.*;
import persistence.dto.*;
import java.util.List;
import java.util.Scanner;

public class BuildingsService {

    private final BuildingsDAO buildingsDAO;
    private final Scanner sc;

    public BuildingsService(BuildingsDAO buildingsDAO) {
        this.buildingsDAO = buildingsDAO;
        sc = new Scanner(System.in);
    }

    public void create() {
        BuildingsDTO buildingsDTO = new BuildingsDTO();
        System.out.println("등록할 건물 이름을 입력하세요.");
        buildingsDTO.setBuilding_id(sc.next());
        buildingsDAO.insert(buildingsDTO);
    }

    public List<BuildingsDTO> read() {
        BuildingsDTO buildingsDTO = new BuildingsDTO();
        return buildingsDAO.selectAll(buildingsDTO);
    }

    public void delete() {
        BuildingsDTO buildingsDTO = new BuildingsDTO();
        System.out.println("삭제할 건물 이름을 입력하세요.");
        buildingsDTO.setBuilding_id(sc.next());
        buildingsDAO.delete(buildingsDTO);
    }

}
