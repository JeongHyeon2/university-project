package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import persistence.mapper.Mapper;
import persistence.dao.*;
import persistence.dto.*;
import java.util.List;

public class PeriodsDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public PeriodsDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insert(PeriodsDTO periodDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.insertPeriods(periodDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public List<PeriodsDTO> selectAll(PeriodsDTO periodsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<PeriodsDTO> tmpList = mapper.selectPeriodsAll(periodsDTO);
        session.close();
        return tmpList;
    }

    public List<PeriodsDTO> selectByGrade(PeriodsDTO periodsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<PeriodsDTO> tmpList = mapper.selectPeriodsByGrade(periodsDTO);
        session.close();
        return tmpList;
    }

    public void updateDates(PeriodsDTO periodsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updatePeriodsDates(periodsDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

}
