package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.LecturesDTO;
import persistence.dto.OpenedLecturesDTO;
import persistence.mapper.Mapper;
import persistence.dao.*;
import persistence.dto.*;
import java.util.List;
import java.util.Map;

public class OpenedLecturesDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public OpenedLecturesDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insert(OpenedLecturesDTO openedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.insertOL(openedLecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public List<OpenedLecturesDTO> selectAll(OpenedLecturesDTO openedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<OpenedLecturesDTO> tmpList = mapper.selectOLAll(openedLecturesDTO);
        session.close();
        return tmpList;
    }

    public List<OpenedLecturesDTO> selectByLectureAndClassId(String lectureId, int classId) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        openedLecturesDTO.setLecture_id(lectureId);
        openedLecturesDTO.setClass_id(classId);
        List<OpenedLecturesDTO> tmpList = mapper.selectOLByLectureAndClassId(openedLecturesDTO);
        session.close();
        return tmpList;
    }

    public List<OpenedLecturesDTO> selectByGrade(int grade) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<OpenedLecturesDTO> tmpList = mapper.selectOLByGrade(grade);
        session.close();
        return tmpList;
    }

    public List<OpenedLecturesDTO> selectByProfessorId(ProfessorsDTO professorsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<OpenedLecturesDTO> tmpList = mapper.selectOLByProfessorId(professorsDTO);
        session.close();
        return tmpList;
    }

    public List<Map<String, Object>> selectByGradeAndProfessor(Map<String, Object> param) {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> tmpList = session.selectList("mapper.Mapper.selectOLByGradeAndProfessor", param);
        session.close();
        return tmpList;
    }

    public List<OpenedLecturesDTO> selectPlanByLectureAndClassId(String lId ,int cId) {
        SqlSession session = sqlSessionFactory.openSession();
        OpenedLecturesDTO openedLecturesDTO = new OpenedLecturesDTO();
        openedLecturesDTO.setClass_id(cId);
        openedLecturesDTO.setLecture_id(lId);
        Mapper mapper = session.getMapper(Mapper.class);
        List<OpenedLecturesDTO> tmpStr = mapper.selectOLPlanByLectureAndClassId(openedLecturesDTO);
        session.close();
        return tmpStr;
    }

    public void updateAll(OpenedLecturesDTO openedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);

        mapper.updateOLAll(openedLecturesDTO);
        session.commit();

    }

    public void updateProfessor(OpenedLecturesDTO openedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateOLProfessor(openedLecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateLectureRoom(OpenedLecturesDTO openedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateOLRoom(openedLecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }
    public List<OpenedLecturesDTO> selectScheduleByProfessorId(String professor_id) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<OpenedLecturesDTO> tmpList = mapper.selectScheduleByProfessorId(professor_id);
        session.close();
        return tmpList;
    }


    public void updateSchedule(OpenedLecturesDTO openedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateOLSchedule(openedLecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateAppliedStudentsNum(OpenedLecturesDTO openedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateOLAppliedStudentsNum(openedLecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateCapacity(OpenedLecturesDTO openedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateOLCapacity(openedLecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateCanBeAppliedTrue(LecturesDTO lecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateCanBeAppliedTrue(lecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateCanBeAppliedFalse(LecturesDTO lecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateCanBeAppliedFalse(lecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateConfirmedPlanTrue() {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateOLCan_modify_planTrue();
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updateConfirmedPlanFalse( ) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateOLCan_modify_planFalse();
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void updatePlan(OpenedLecturesDTO openedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.updateOLPlan(openedLecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void delete(OpenedLecturesDTO openedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.deleteOL(openedLecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

}
