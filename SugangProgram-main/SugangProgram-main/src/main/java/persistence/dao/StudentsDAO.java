package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dao.*;
import persistence.dto.*;
import persistence.dto.StudentsDTO;
import persistence.mapper.Mapper;

import java.util.List;

public class StudentsDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public StudentsDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insert(StudentsDTO studentsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.insertStudent(studentsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public List<StudentsDTO> selectAll(StudentsDTO studentsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<StudentsDTO> tmpList = mapper.selectStudentsAll(studentsDTO);
        session.close();
        return tmpList;
    }

    public List<StudentsDTO> selectById(StudentsDTO studentsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<StudentsDTO> tmpList = mapper.selectStudentById(studentsDTO);
        session.close();
        return tmpList;
    }

    public void updateAll(StudentsDTO studentsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateStudentAll(studentsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateName(StudentsDTO studentsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateStudentName(studentsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateGrade(StudentsDTO studentsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateStudentGrade(studentsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateBirthDate(StudentsDTO studentsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateStudentBirthDate(studentsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void deleteStudent(StudentsDTO studentsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.deleteStudent(studentsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }
    public void updatePhNum(StudentsDTO studentsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateStudentPhNum(studentsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

}