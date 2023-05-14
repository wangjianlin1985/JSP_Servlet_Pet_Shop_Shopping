// 
// 
// 

package model;

import java.util.ArrayList;
import java.util.HashMap;
import utils.PriceUtil;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class Order
{
    private int id;
    private float total;
    private int amount;
    private int status;
    private int paytype;
    private String name;
    private String phone;
    private String address;
    private Date datetime;
    private User user;
    private Map<Integer, OrderItem> itemMap;
    private List<OrderItem> itemList;
    
    public void setUsername(final String username) {
        (this.user = new User()).setUsername(username);
    }
    
    public void addGoods(final Goods g) {
        if (this.itemMap.containsKey(g.getId())) {
            final OrderItem item = this.itemMap.get(g.getId());
            item.setAmount(item.getAmount() + 1);
        }
        else {
            final OrderItem item = new OrderItem(g.getPrice(), 1, g, this);
            this.itemMap.put(g.getId(), item);
        }
        ++this.amount;
        this.total = PriceUtil.add(this.total, g.getPrice());
    }
    
    public List<OrderItem> getItemList() {
        return this.itemList;
    }
    
    public void setItemList(final List<OrderItem> itemList) {
        this.itemList = itemList;
    }
    
    public void lessen(final int goodsid) {
        if (this.itemMap.containsKey(goodsid)) {
            final OrderItem item = this.itemMap.get(goodsid);
            item.setAmount(item.getAmount() - 1);
            --this.amount;
            this.total = PriceUtil.subtract(this.total, item.getPrice());
            if (item.getAmount() <= 0) {
                this.itemMap.remove(goodsid);
            }
        }
    }
    
    public void delete(final int goodsid) {
        if (this.itemMap.containsKey(goodsid)) {
            final OrderItem item = this.itemMap.get(goodsid);
            this.total = PriceUtil.subtract(this.total, item.getAmount() * item.getPrice());
            this.amount -= item.getAmount();
            this.itemMap.remove(goodsid);
        }
    }
    
    public Map<Integer, OrderItem> getItemMap() {
        return this.itemMap;
    }
    
    public void setItemMap(final Map<Integer, OrderItem> itemMap) {
        this.itemMap = itemMap;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public float getTotal() {
        return this.total;
    }
    
    public void setTotal(final float total) {
        this.total = total;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public void setAmount(final int amount) {
        this.amount = amount;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
    
    public int getPaytype() {
        return this.paytype;
    }
    
    public void setPaytype(final int paytype) {
        this.paytype = paytype;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(final String address) {
        this.address = address;
    }
    
    public Date getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(final Date datetime) {
        this.datetime = datetime;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    public Order() {
        this.itemMap = new HashMap<Integer, OrderItem>();
        this.itemList = new ArrayList<OrderItem>();
    }
}
