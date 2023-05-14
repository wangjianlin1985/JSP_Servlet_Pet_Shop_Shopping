// 
// 
// 

package dao;

import model.Recommend;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import model.Goods;
import org.apache.commons.dbutils.handlers.MapHandler;
import java.sql.SQLException;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.QueryRunner;
import utils.DBUtil;
import java.util.Map;
import java.util.List;

public class GoodsDao
{
    public List<Map<String, Object>> getGoodsList(final int recommendType) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select g.id,g.name,g.cover,g.price,t.name typename from recommend r,goods g,type t where type=? and r.goods_id=g.id and g.type_id=t.id";
        return (List<Map<String, Object>>)r.query(sql, (ResultSetHandler)new MapListHandler(), new Object[] { recommendType });
    }
    
    public Map<String, Object> getScrollGood() throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select g.id,g.name,g.cover,g.price  from recommend r,goods g where type=1 and r.goods_id=g.id";
        return (Map<String, Object>)r.query(sql, (ResultSetHandler)new MapHandler());
    }
    
    public List<Goods> selectGoodsByTypeID(final int typeID, final int pageNumber, final int pageSize) throws SQLException {
        if (typeID == 0) {
            final String sql = "select * from goods limit ? , ?";
            final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
            return (List<Goods>)r.query(sql, (ResultSetHandler)new BeanListHandler((Class)Goods.class), new Object[] { (pageNumber - 1) * pageSize, pageSize });
        }
        final String sql = "select * from goods where type_id=? limit ? , ?";
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        return (List<Goods>)r.query(sql, (ResultSetHandler)new BeanListHandler((Class)Goods.class), new Object[] { typeID, (pageNumber - 1) * pageSize, pageSize });
    }
    
    public Long getCountOfGoodsByTypeID(final int typeID) throws SQLException {
        String sql = "";
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        if (typeID == 0) {
            sql = "select count(*) from goods";
            return (Long)r.query(sql, (ResultSetHandler)new ScalarHandler());
        }
        sql = "select count(*) from goods where type_id=?";
        return (Long)r.query(sql, (ResultSetHandler)new ScalarHandler(), new Object[] { typeID });
    }
    
    public List<Goods> selectGoodsbyRecommend(final int type, final int pageNumber, final int pageSize) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        if (type == 0) {
            final String sql = " select g.id,g.name,g.cover,g.image1,g.image2,g.intro,g.price,g.stock,t.name typename from goods g,type t where g.type_id=t.id order by g.id limit ?,?";
            return (List<Goods>)r.query(sql, (ResultSetHandler)new BeanListHandler((Class)Goods.class), new Object[] { (pageNumber - 1) * pageSize, pageSize });
        }
        final String sql = " select g.id,g.name,g.cover,g.image1,g.image2,g.intro,g.price,g.stock,t.name typename from goods g,recommend r,type t where g.id=r.goods_id and g.type_id=t.id and r.type=? order by g.id limit ?,?";
        return (List<Goods>)r.query(sql, (ResultSetHandler)new BeanListHandler((Class)Goods.class), new Object[] { type, (pageNumber - 1) * pageSize, pageSize });
    }
    
    public Long getRecommendCountOfGoodsByTypeID(final int type) throws SQLException {
        if (type == 0) {
            return this.getCountOfGoodsByTypeID(0);
        }
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select count(*) from recommend where type=?";
        return (Long)r.query(sql, (ResultSetHandler)new ScalarHandler(), new Object[] { type });
    }
    
    public Goods getGoodsById(final int id) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select g.id,g.name,g.cover,g.image1,g.image2,g.price,g.intro,g.stock,t.id typeid,t.name typename from goods g,type t where g.id = ? and g.type_id=t.id";
        return (Goods)r.query(sql, (ResultSetHandler)new BeanHandler((Class)Goods.class), new Object[] { id });
    }
    
    public Long getSearchCount(final String keyword) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select count(*) from goods where name like ?";
        return (Long)r.query(sql, (ResultSetHandler)new ScalarHandler(), new Object[] { "%" + keyword + "%" });
    }
    
    public List<Goods> selectSearchGoods(final String keyword, final int pageNumber, final int pageSize) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select * from goods where name like ? limit ?,?";
        return (List<Goods>)r.query(sql, (ResultSetHandler)new BeanListHandler((Class)Goods.class), new Object[] { "%" + keyword + "%", (pageNumber - 1) * pageSize, pageSize });
    }
    
    public boolean isScroll(final Goods g) throws SQLException {
        return this.isRecommend(g, 1);
    }
    
    public boolean isHot(final Goods g) throws SQLException {
        return this.isRecommend(g, 2);
    }
    
    public boolean isNew(final Goods g) throws SQLException {
        return this.isRecommend(g, 3);
    }
    
    private boolean isRecommend(final Goods g, final int type) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "select * from recommend where type=? and goods_id=?";
        final Recommend recommend = (Recommend)r.query(sql, (ResultSetHandler)new BeanHandler((Class)Recommend.class), new Object[] { type, g.getId() });
        return recommend != null;
    }
    
    public void addRecommend(final int id, final int type) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "insert into recommend(type,goods_id) values(?,?)";
        r.update(sql, new Object[] { type, id });
    }
    
    public void removeRecommend(final int id, final int type) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "delete from recommend where type=? and goods_id=?";
        r.update(sql, new Object[] { type, id });
    }
    
    public void insert(final Goods g) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "insert into goods(name,cover,image1,image2,price,intro,stock,type_id) values(?,?,?,?,?,?,?,?)";
        r.update(sql, new Object[] { g.getName(), g.getCover(), g.getImage1(), g.getImage2(), g.getPrice(), g.getIntro(), g.getStock(), g.getType().getId() });
    }
    
    public void update(final Goods g) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "update goods set name=?,cover=?,image1=?,image2=?,price=?,intro=?,stock=?,type_id=? where id=?";
        r.update(sql, new Object[] { g.getName(), g.getCover(), g.getImage1(), g.getImage2(), g.getPrice(), g.getIntro(), g.getStock(), g.getType().getId(), g.getId() });
    }
    
    public void delete(final int id) throws SQLException {
        final QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        final String sql = "delete from goods where id = ?";
        r.update(sql, (Object)id);
    }
}
