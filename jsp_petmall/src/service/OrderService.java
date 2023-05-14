// 
// 
// 

package service;

import model.Page;
import java.util.List;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.SQLException;
import model.OrderItem;
import utils.DBUtil;
import model.Order;
import dao.OrderDao;

public class OrderService
{
    private OrderDao oDao;
    
    public OrderService() {
        this.oDao = new OrderDao();
    }
    
    public void addOrder(final Order order) {
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);
            this.oDao.insertOrder(con, order);
            final int id = this.oDao.getLastInsertId(con);
            order.setId(id);
            for (final OrderItem item : order.getItemMap().values()) {
                this.oDao.insertOrderItem(con, item);
            }
            con.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
    
    public List<Order> selectAll(final int userid) {
        List<Order> list = null;
        try {
            list = this.oDao.selectAll(userid);
            for (final Order o : list) {
                final List<OrderItem> l = this.oDao.selectAllItem(o.getId());
                o.setItemList(l);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Page getOrderPage(final int status, final int pageNumber) {
        final Page p = new Page();
        p.setPageNumber(pageNumber);
        final int pageSize = 10;
        int totalCount = 0;
        try {
            totalCount = this.oDao.getOrderCount(status).intValue();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list = null;
        try {
            list = this.oDao.selectOrderList(status, pageNumber, pageSize);
            for (final Object o : list) {
                final List<OrderItem> l = this.oDao.selectAllItem(((Order)o).getId());
                ((Order)o).setItemList(l);
            }
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
        p.setList(list);
        return p;
    }
    
    public void updateStatus(final int id, final int status) {
        try {
            this.oDao.updateStatus(id, status);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(final int id) {
        Connection con = null;
        try {
            con = DBUtil.getDataSource().getConnection();
            con.setAutoCommit(false);
            this.oDao.deleteOrderItem(con, id);
            this.oDao.deleteOrder(con, id);
            con.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
