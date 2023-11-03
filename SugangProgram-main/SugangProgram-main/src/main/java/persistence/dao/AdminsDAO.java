package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dao.*;
import persistence.dto.*;
import persistence.mapper.Mapper;

import java.util.List;

public class AdminsDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public AdminsDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insert(AdminsDTO adminsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.insertAdmins(adminsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public List<AdminsDTO> selectAll(AdminsDTO adminsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<AdminsDTO> tmpList = mapper.selectAdminsAll(adminsDTO);
        session.close();
        return tmpList;
    }

    public void delete(AdminsDTO adminsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.deleteAdmins(adminsDTO);
            session.commit();
        } catch(Exception e){
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

}
