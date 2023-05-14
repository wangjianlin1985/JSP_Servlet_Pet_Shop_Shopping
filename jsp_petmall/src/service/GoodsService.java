// 
// 
// 

package service;

import java.util.Iterator;
import model.Page;
import model.Goods;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import dao.GoodsDao;

public class GoodsService
{
    private GoodsDao gDao;
    
    public GoodsService() {
        this.gDao = new GoodsDao();
    }
    
    public List<Map<String, Object>> getGoodsList(final int recommendType) {
        List<Map<String, Object>> list = null;
        try {
            list = this.gDao.getGoodsList(recommendType);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Map<String, Object> getScrollGood() {
        Map<String, Object> scroolGood = null;
        try {
            scroolGood = this.gDao.getScrollGood();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return scroolGood;
    }
    
    public List<Goods> selectGoodsByTypeID(final int typeID, final int pageNumber, final int pageSize) {
        List<Goods> list = null;
        try {
            list = this.gDao.selectGoodsByTypeID(typeID, pageNumber, pageSize);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Page selectPageByTypeID(final int typeID, final int pageNumber) {
        final Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            totalCount = this.gDao.getCountOfGoodsByTypeID(typeID).intValue();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list = null;
        try {
            list = this.gDao.selectGoodsByTypeID(typeID, pageNumber, 8);
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
        p.setList(list);
        return p;
    }
    
    public Page getGoodsRecommendPage(final int type, final int pageNumber) {
        final Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            totalCount = this.gDao.getRecommendCountOfGoodsByTypeID(type).intValue();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list = null;
        try {
            list = this.gDao.selectGoodsbyRecommend(type, pageNumber, 8);
            for (final Object g : list) {
                ((Goods)g).setScroll(this.gDao.isScroll(((Goods)g)));
                ((Goods)g).setHot(this.gDao.isHot(((Goods)g)));
                ((Goods)g).setNew(this.gDao.isNew(((Goods)g)));
            }
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
        p.setList(list);
        return p;
    }
    
    public Goods getGoodsById(final int id) {
        Goods g = null;
        try {
            g = this.gDao.getGoodsById(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }
    
    public Page getSearchGoodsPage(final String keyword, final int pageNumber) {
        final Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            totalCount = this.gDao.getSearchCount(keyword).intValue();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list = null;
        try {
            list = this.gDao.selectSearchGoods(keyword, pageNumber, 8);
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
        p.setList(list);
        return p;
    }
    
    public void addRecommend(final int id, final int type) {
        try {
            this.gDao.addRecommend(id, type);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void removeRecommend(final int id, final int type) {
        try {
            this.gDao.removeRecommend(id, type);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insert(final Goods goods) {
        try {
            this.gDao.insert(goods);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void update(final Goods goods) {
        try {
            this.gDao.update(goods);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(final int id) {
        try {
            this.gDao.delete(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
