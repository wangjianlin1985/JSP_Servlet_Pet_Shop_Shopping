// 
// 
// 

package dao;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.util.List;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import utils.DBUtil;
import model.User;

public class UserDao
{
    public void addUser(final User user) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "insert into user(username,email,password,name,phone,address,isadmin,isvalidate) values(?,?,?,?,?,?,?,?)";
        r.update(sql, new Object[] { user.getUsername(), user.getEmail(), user.getPassword(), user.getName(), user.getPhone(), user.getAddress(), user.isIsadmin(), user.isIsvalidate() });
    }
    
    public boolean isUsernameExist(final String username) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select * from user where username = ?";
        final User u = (User)r.query(sql, (ResultSetHandler)new BeanHandler((Class)User.class), new Object[] { username });
        return u != null;
    }
    
    public boolean isEmailExist(final String email) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select * from user where email = ?";
        final User u = (User)r.query(sql, (ResultSetHandler)new BeanHandler((Class)User.class), new Object[] { email });
        return u != null;
    }
    
    public User selectByUsernamePassword(final String username, final String password) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select * from user where username=? and password=?";
        return (User)r.query(sql, (ResultSetHandler)new BeanHandler((Class)User.class), new Object[] { username, password });
    }
    
    public User selectByEmailPassword(final String email, final String password) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select * from user where email=? and password=?";
        return (User)r.query(sql, (ResultSetHandler)new BeanHandler((Class)User.class), new Object[] { email, password });
    }
    
    public User selectById(final int id) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select * from user where id=?";
        return (User)r.query(sql, (ResultSetHandler)new BeanHandler((Class)User.class), new Object[] { id });
    }
    
    public void updateUserAddress(final User user) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "update user set name = ?,phone=?,address=? where id = ?";
        r.update(sql, new Object[] { user.getName(), user.getPhone(), user.getAddress(), user.getId() });
    }
    
    public void updatePwd(final User user) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "update user set password = ? where id = ?";
        r.update(sql, new Object[] { user.getPassword(), user.getId() });
    }
    
    public Long selectUserCount() throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select count(*) from user";
        return (Long)r.query(sql, (ResultSetHandler)new ScalarHandler());
    }
    
    public List selectUserList(final int pageNo, final int pageSize) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select * from user limit ?,?";
        return (List)r.query(sql, (ResultSetHandler)new BeanListHandler((Class)User.class), new Object[] { (pageNo - 1) * pageSize, pageSize });
    }
    
    public void delete(final int id) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "delete from user where id = ?";
        r.update(sql, (Object)id);
    }
}
