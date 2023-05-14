// 
// 
// 

package dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.SQLException;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.QueryRunner;
import utils.DBUtil;
import model.Type;
import java.util.List;

public class TypeDao
{
    public List<Type> GetAllType() throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select * from type";
        return (List<Type>)r.query(sql, (ResultSetHandler)new BeanListHandler((Class)Type.class));
    }
    
    public Type selectTypeNameByID(final int typeid) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select * from type where id=?";
        return (Type)r.query(sql, (ResultSetHandler)new BeanHandler((Class)Type.class), new Object[] { typeid });
    }
    
    public Type select(final int id) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select * from type where id = ?";
        return (Type)r.query(sql, (ResultSetHandler)new BeanHandler((Class)Type.class), new Object[] { id });
    }
    
    public void insert(final Type t) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "insert into type(name) values(?)";
        r.update(sql, (Object)t.getName());
    }
    
    public void update(final Type t) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "update type set name=? where id = ?";
        r.update(sql, new Object[] { t.getName(), t.getId() });
    }
    
    public void delete(final int id) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "delete from type where id = ?";
        r.update(sql, (Object)id);
    }
}
