package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dao.*;
import persistence.dto.*;
import persistence.mapper.Mapper;

import java.util.List;

public class ProfessorsDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public ProfessorsDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insert(ProfessorsDTO professorsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.insertProfessors(professorsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public List<ProfessorsDTO> selectAll(ProfessorsDTO professorsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<ProfessorsDTO> tmpList = mapper.selectProfessorsAll(professorsDTO);
        session.close();
        return tmpList;
    }

    public List<ProfessorsDTO> selectById(ProfessorsDTO professorsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<ProfessorsDTO> tmpList = mapper.selectProfessorById(professorsDTO);
        session.close();
        return tmpList;
    }

    public void updateAll(ProfessorsDTO professorsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateProfessorAll(professorsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateName(ProfessorsDTO professorsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateProfessorName(professorsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updatePhoneNum(ProfessorsDTO professorsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateProfessorPhoneNum(professorsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateBirthDate(ProfessorsDTO professorsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateProfessorBirthDate(professorsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void deleteProfessor(ProfessorsDTO professorsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.deleteProfessor(professorsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

}
