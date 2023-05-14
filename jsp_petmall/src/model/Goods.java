// 
// 
// 

package model;

public class Goods
{
    private int id;
    private String name;
    private String cover;
    private String image1;
    private String image2;
    private float price;
    private String intro;
    private int stock;
    private Type type;
    private boolean isScroll;
    private boolean isHot;
    private boolean isNew;
    
    public boolean getIsScroll() {
        return this.isScroll;
    }
    
    public void setScroll(final boolean isScroll) {
        this.isScroll = isScroll;
    }
    
    public boolean getIsHot() {
        return this.isHot;
    }
    
    public void setHot(final boolean isHot) {
        this.isHot = isHot;
    }
    
    public boolean getIsNew() {
        return this.isNew;
    }
    
    public void setNew(final boolean isNew) {
        this.isNew = isNew;
    }
    
    public void setTypeid(final int typeid) {
        if (this.type == null) {
            this.type = new Type();
        }
        this.type.setId(typeid);
    }
    
    public void setTypename(final String typename) {
        if (this.type == null) {
            this.type = new Type();
        }
        this.type.setName(typename);
    }
    
    @Override
    public String toString() {
        return "Goods [id=" + this.id + ", name=" + this.name + ", cover=" + this.cover + ", image1=" + this.image1 + ", image2=" + this.image2 + ", price=" + this.price + ", intro=" + this.intro + ", stock=" + this.stock + ", type=" + this.type + "]";
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getCover() {
        return this.cover;
    }
    
    public void setCover(final String cover) {
        this.cover = cover;
    }
    
    public String getImage1() {
        return this.image1;
    }
    
    public void setImage1(final String image1) {
        this.image1 = image1;
    }
    
    public String getImage2() {
        return this.image2;
    }
    
    public void setImage2(final String image2) {
        this.image2 = image2;
    }
    
    public float getPrice() {
        return this.price;
    }
    
    public void setPrice(final float price) {
        this.price = price;
    }
    
    public String getIntro() {
        return this.intro;
    }
    
    public void setIntro(final String intro) {
        this.intro = intro;
    }
    
    public int getStock() {
        return this.stock;
    }
    
    public void setStock(final int stock) {
        this.stock = stock;
    }
    
    public Type getType() {
        return this.type;
    }
    
    public void setType(final Type type) {
        this.type = type;
    }
    
    public Goods() {
    }
    
    public Goods(final int id, final String name, final String cover, final String image1, final String image2, final float price, final String intro, final int stock, final Type type) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.image1 = image1;
        this.image2 = image2;
        this.price = price;
        this.intro = intro;
        this.stock = stock;
        this.type = type;
    }
}
