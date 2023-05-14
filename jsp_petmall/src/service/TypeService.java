// 
// 
// 

package service;

import java.sql.SQLException;
import model.Type;
import java.util.List;
import dao.TypeDao;

public class TypeService
{
    TypeDao tDao;
    
    public TypeService() {
        this.tDao = new TypeDao();
    }
    
    public List<Type> GetAllType() {
        List<Type> list = null;
        try {
            list = this.tDao.GetAllType();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Type selectTypeNameByID(final int typeid) {
        Type type = null;
        try {
            type = this.tDao.selectTypeNameByID(typeid);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }
    
    public Type select(final int id) {
        Type t = null;
        try {
            t = this.tDao.select(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
    
    public void insert(final Type t) {
        try {
            this.tDao.insert(t);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void update(final Type t) {
        try {
            this.tDao.update(t);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean delete(final int id) {
        try {
            this.tDao.delete(id);
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
