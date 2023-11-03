package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dao.*;
import persistence.dto.*;
import persistence.dto.AppliedLecturesDTO;
import persistence.dto.OpenedLecturesDTO;
import persistence.dto.StudentsDTO;
import persistence.mapper.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppliedLecturesDAO {

    private final SqlSessionFactory sqlSessionFactory;
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public AppliedLecturesDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insert(AppliedLecturesDTO appliedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.insertAL(appliedLecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public List<AppliedLecturesDTO> selectAll(AppliedLecturesDTO appliedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<AppliedLecturesDTO> tmpList = mapper.selectALAll(appliedLecturesDTO);
        session.close();
        return tmpList;
    }

    public List<AppliedLecturesDTO> selectByLectureAndClass(AppliedLecturesDTO appliedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<AppliedLecturesDTO> tmpList = mapper.selectALByLectureAndClassId(appliedLecturesDTO);
        session.close();
        return tmpList;
    }

    public List<AppliedLecturesDTO> selectByStudent(AppliedLecturesDTO appliedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<AppliedLecturesDTO> tmpList = mapper.selectALByStudentId(appliedLecturesDTO);
        session.close();
        return tmpList;
    }

    public List<StudentsDTO> selectStudent(String pId, String lectureId, int classId) {
        String query = "SELECT DISTINCT students.* FROM students,applied_lectures,opened_lectures " +
                "WHERE applied_lectures.lecture_id=opened_lectures.lecture_id " +
                "AND applied_lectures.class_id=opened_lectures.class_id " +
                "AND applied_lectures.student_id=students.student_id " +
                "AND opened_lectures.professor_id=? AND opened_lectures.lecture_id=? " +
                "AND opened_lectures.class_id=? ";
        List<StudentsDTO> tmpList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/sugang?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "gusdnr1919");
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1,pId);
            pstmt.setString(2,lectureId);
            pstmt.setInt(3,classId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String name = rs.getString("name");
                int grade = rs.getInt("grade");
                String birthDate = rs.getString("birth_date");
                String phNum = rs.getString("phone_num");
                StudentsDTO studentsDTO = new StudentsDTO(studentId, name, grade, birthDate,phNum);
                tmpList.add(studentsDTO);
            }
        } catch(SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    if (conn != null && !rs.isClosed())
                        rs.close();
                }
                if (conn != null && !pstmt.isClosed())
                    pstmt.close();
                if (conn != null && !conn.isClosed())
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tmpList;
    }

    public List<OpenedLecturesDTO> selectBySchedule(AppliedLecturesDTO appliedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<OpenedLecturesDTO> tmpList = mapper.selectALBySchedule(appliedLecturesDTO);
        session.close();
        return tmpList;
    }

    public List<StudentsDTO> selectStudentsByProfessor(OpenedLecturesDTO openedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<StudentsDTO> tmpList = mapper.selectALStudentsByProfessor(openedLecturesDTO);
        session.close();
        return tmpList;
    }

    public void delete(AppliedLecturesDTO appliedLecturesDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.deleteAL(appliedLecturesDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

//    public List<Map<String, Object>> readTest(Map<String, Object> param) {
//        SqlSession session = sqlSessionFactory.openSession();
//        return session.selectList("mapper.Mapper.selectStudentsByProfessor", param);
//    }

}
