package persistence.dao;
import persistence.dao.*;
import persistence.dto.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import persistence.dto.LectureRoomsDTO;
import persistence.mapper.Mapper;

import java.util.List;

public class LectureRoomsDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public LectureRoomsDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insert(LectureRoomsDTO lectureRoomsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.insertLR(lectureRoomsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        }
        session.close();
    }

    public List<LectureRoomsDTO> selectAll(LectureRoomsDTO lectureRoomsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<LectureRoomsDTO> tmpList = mapper.selectLRAll(lectureRoomsDTO);
        session.close();
        return tmpList;
    }

    public void delete(LectureRoomsDTO lectureRoomsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.deleteLR(lectureRoomsDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

}
