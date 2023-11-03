package service;

import persistence.dao.AccountsDAO;
import persistence.dao.*;
import persistence.dto.*;
import java.util.List;

public class AccountsService {

    private final AccountsDAO accountsDAO;

    public AccountsService(AccountsDAO accountsDAO) {
        this.accountsDAO = accountsDAO;
    }

    public void create(String id, String password, int type) {
        AccountsDTO accountsDTO = new AccountsDTO(id, password, type);
        accountsDAO.insert(accountsDTO);
    }

    public List<AccountsDTO> read() {
        AccountsDTO accountsDTO = new AccountsDTO();
        return accountsDAO.selectAll();
    }

    public List<AccountsDTO> findPassword(String id){
        AccountsDTO accountsDTO = new AccountsDTO();
        accountsDTO.setId(id);
        return accountsDAO.selectById(accountsDTO);
    }

    public void update(String password) {
        AccountsDTO accountsDTO = new AccountsDTO();
        accountsDTO.setPassword(password);
        accountsDAO.updatePW(accountsDTO);
    }

    public void delete(String id) {
        AccountsDTO accountsDTO = new AccountsDTO();
        accountsDTO.setId(id);
        accountsDAO.delete(accountsDTO);
    }

}