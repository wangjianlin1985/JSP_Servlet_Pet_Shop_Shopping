// 
// 
// 

package dao;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DBUtil;
import java.util.List;
import model.OrderItem;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.math.BigInteger;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import model.Order;
import java.sql.Connection;

public class OrderDao
{
    public void insertOrder(final Connection con, final Order order) throws SQLException {
        final QueryRunner r = new QueryRunner();
        final String sql = "insert into `order`(total,amount,status,paytype,name,phone,address,datetime,user_id) values(?,?,?,?,?,?,?,?,?)";
        r.update(con, sql, new Object[] { order.getTotal(), order.getAmount(), order.getStatus(), order.getPaytype(), order.getName(), order.getPhone(), order.getAddress(), order.getDatetime(), order.getUser().getId() });
    }
    
    public int getLastInsertId(final Connection con) throws SQLException {
        final QueryRunner r = new QueryRunner();
        final String sql = "select last_insert_id()";
        final BigInteger bi = (BigInteger)r.query(con, sql, (ResultSetHandler)new ScalarHandler());
        return Integer.parseInt(bi.toString());
    }
    
    public void insertOrderItem(final Connection con, final OrderItem item) throws SQLException {
        final QueryRunner r = new QueryRunner();
        final String sql = "insert into orderitem(price,amount,goods_id,order_id) values(?,?,?,?)";
        r.update(con, sql, new Object[] { item.getPrice(), item.getAmount(), item.getGoods().getId(), item.getOrder().getId() });
    }
    
    public List<Order> selectAll(final int userid) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select * from `order` where user_id=? order by datetime desc";
        return (List<Order>)r.query(sql, (ResultSetHandler)new BeanListHandler((Class)Order.class), new Object[] { userid });
    }
    
    public List<OrderItem> selectAllItem(final int orderid) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select i.id,i.price,i.amount,g.name from orderitem i,goods g where order_id=? and i.goods_id=g.id";
        return (List<OrderItem>)r.query(sql, (ResultSetHandler)new BeanListHandler((Class)OrderItem.class), new Object[] { orderid });
    }
    
    public Long getOrderCount(final int status) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "";
        if (status == 0) {
            sql = "select count(*) from `order`";
            return (Long)r.query(sql, (ResultSetHandler)new ScalarHandler());
        }
        sql = "select count(*) from `order` where status=?";
        return (Long)r.query(sql, (ResultSetHandler)new ScalarHandler(), new Object[] { status });
    }
    
    public List<Order> selectOrderList(final int status, final int pageNumber, final int pageSize) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        if (status == 0) {
            final String sql = "select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id order by o.datetime desc limit ?,?";
            return (List<Order>)r.query(sql, (ResultSetHandler)new BeanListHandler((Class)Order.class), new Object[] { (pageNumber - 1) * pageSize, pageSize });
        }
        final String sql = "select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id and o.status=? order by o.datetime desc limit ?,?";
        return (List<Order>)r.query(sql, (ResultSetHandler)new BeanListHandler((Class)Order.class), new Object[] { status, (pageNumber - 1) * pageSize, pageSize });
    }
    
    public void updateStatus(final int id, final int status) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "update `order` set status=? where id = ?";
        r.update(sql, new Object[] { status, id });
    }
    
    public void deleteOrder(final Connection con, final int id) throws SQLException {
        final QueryRunner r = new QueryRunner();
        final String sql = "delete from `order` where id = ?";
        r.update(con, sql, (Object)id);
    }
    
    public void deleteOrderItem(final Connection con, final int id) throws SQLException {
        final QueryRunner r = new QueryRunner();
        final String sql = "delete from orderitem where order_id=?";
        r.update(con, sql, (Object)id);
    }
}
