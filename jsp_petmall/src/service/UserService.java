// 
// 
// 

package service;

import java.util.List;
import model.Page;
import java.sql.SQLException;
import model.User;
import dao.UserDao;

public class UserService
{
    private UserDao uDao;
    
    public UserService() {
        this.uDao = new UserDao();
    }
    
    public boolean register(final User user) {
        try {
            if (this.uDao.isUsernameExist(user.getUsername())) {
                return false;
            }
            if (this.uDao.isEmailExist(user.getEmail())) {
                return false;
            }
            this.uDao.addUser(user);
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public User login(final String ue, final String password) {
        User user = null;
        try {
            user = this.uDao.selectByUsernamePassword(ue, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            return user;
        }
        try {
            user = this.uDao.selectByEmailPassword(ue, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            return user;
        }
        return null;
    }
    
    public User selectById(final int id) {
        User u = null;
        try {
            u = this.uDao.selectById(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
    
    public void updateUserAddress(final User user) {
        try {
            this.uDao.updateUserAddress(user);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updatePwd(final User user) {
        try {
            this.uDao.updatePwd(user);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Page getUserPage(final int pageNumber) {
        final Page p = new Page();
        p.setPageNumber(pageNumber);
        final int pageSize = 7;
        int totalCount = 0;
        try {
            totalCount = this.uDao.selectUserCount().intValue();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list = null;
        try {
            list = this.uDao.selectUserList(pageNumber, pageSize);
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
        p.setList(list);
        return p;
    }
    
    public boolean delete(final int id) {
        try {
            this.uDao.delete(id);
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
