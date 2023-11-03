package persistence.dao;
import persistence.dao.*;
import persistence.dto.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import persistence.dto.BuildingsDTO;
import persistence.mapper.Mapper;

import java.util.List;

public class BuildingsDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public BuildingsDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insert(BuildingsDTO buildingsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.insertBuildings(buildingsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public List<BuildingsDTO> selectAll(BuildingsDTO buildingsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<BuildingsDTO> tmpList = mapper.selectBuildingsAll(buildingsDTO);
        session.close();
        return tmpList;
    }

    public void delete(BuildingsDTO buildingsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.deleteBuildings(buildingsDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

}
