package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.dao.*;
import persistence.dto.*;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.AccountsDTO;
import persistence.mapper.Mapper;

import java.util.List;

public class AccountsDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public AccountsDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insert(AccountsDTO accountsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        try {
            mapper.insertAccounts(accountsDTO);
            session.commit();
        } catch(Exception e) {
            System.out.println("오류 발생, 롤백 실행");
            session.rollback();
        } finally {
            session.close();
        }
    }

    public List<AccountsDTO> selectAll( ) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<AccountsDTO> tmpList = mapper.selectAccountsAll();
        session.close();
        return tmpList;
    }

    public List<AccountsDTO> selectById(AccountsDTO accountsDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        Mapper mapper = session.getMapper(Mapper.class);
        List<AccountsDTO> tmpList = mapper.selectAccountById(accountsDTO);
        session.close();
        return tmpList;
    }

    public void updatePW(AccountsDTO accountsDTO) {
       SqlSession session = sqlSessionFactory.openSession();
       Mapper mapper = session.getMapper(Mapper.class);
       try {
           mapper.updateAccountsPW(accountsDTO);
           session.commit();
       } catch(Exception e) {
           System.out.println("오류 발생, 롤백 실행");
           session.rollback();
       } finally {
           session.close();
       }
   }

   public void delete(AccountsDTO accountsDTO) {
       SqlSession session = sqlSessionFactory.openSession();
       Mapper mapper = session.getMapper(Mapper.class);
       try {
           mapper.deleteAccounts(accountsDTO);
           session.commit();
       } catch(Exception e) {
           System.out.println("오류 발생, 롤백 실행");
           session.rollback();
       } finally {
           session.close();
       }
   }

}
