// 
// 
// 

package model;

public class OrderItem
{
    private int id;
    private float price;
    private int amount;
    private String goodsName;
    private Goods goods;
    private Order order;
    
    public void setName(final String name) {
        this.goodsName = name;
    }
    
    public String getGoodsName() {
        return this.goodsName;
    }
    
    public void setGoodsName(final String goodsName) {
        this.goodsName = goodsName;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public float getPrice() {
        return this.price;
    }
    
    public void setPrice(final float price) {
        this.price = price;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public void setAmount(final int amount) {
        this.amount = amount;
    }
    
    public Goods getGoods() {
        return this.goods;
    }
    
    public void setGoods(final Goods goods) {
        this.goods = goods;
    }
    
    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(final Order order) {
        this.order = order;
    }
    
    public OrderItem() {
    }
    
    public OrderItem(final float price, final int amount, final Goods goods, final Order order) {
        this.price = price;
        this.amount = amount;
        this.goods = goods;
        this.order = order;
    }
}
