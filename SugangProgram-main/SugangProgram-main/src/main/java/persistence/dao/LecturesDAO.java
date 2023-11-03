package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import persistence.dao.*;
import persistence.dto.*;
public class LecturesDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public LecturesDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insert(LecturesDTO lecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
            session.insert("mapper.Mapper.insertLectures", lecturesDTO);
            session.commit();
    }

    public List<LecturesDTO> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();
        List<LecturesDTO> tmpList = session.selectList("mapper.Mapper.selectLecturesAll");
        session.close();
        return tmpList;
    }

    public String selectByLectureId(String id) {
        SqlSession session = sqlSessionFactory.openSession();
        String tmpStr = session.selectOne("mapper.Mapper.selectLectureById", id);
        session.close();
        return tmpStr;
    }

    public List<LecturesDTO> selectByGrade(int grade) {
        SqlSession session = sqlSessionFactory.openSession();
        List<LecturesDTO> tmpList = session.selectList("mapper.Mapper.selectLecturesByGrade", grade);
        session.close();
        return tmpList;
    }

    public void updateAll(LecturesDTO lecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("mapper.Mapper.updateLecturesAll", lecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateTargetGrade(LecturesDTO lecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("mapper.Mapper.updateLecturesTargetGrade", lecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateTitle(LecturesDTO lecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("mapper.Mapper.updateLecturesTitle", lecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateSemester(LecturesDTO lecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("mapper.Mapper.updateLecturesSemester", lecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateCredit(LecturesDTO lecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("mapper.Mapper.updateLecturesTitle", lecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void delete(String lectureId) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.delete("mapper.Mapper.deleteLectures", lectureId);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

}
