// 
// 
// 

package utils;

import java.sql.SQLException;
import java.sql.Connection;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;

public class DBUtil
{
    private static DataSource ds;
    
    static {
        DBUtil.ds = (DataSource)new ComboPooledDataSource();
    }
    
    public static DataSource getDataSource() {
        return DBUtil.ds;
    }
    
    public static Connection getConnection() throws SQLException {
        return DBUtil.ds.getConnection();
    }
}
