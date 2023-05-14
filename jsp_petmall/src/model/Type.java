// 
// 
// 

package model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Type
{
    private int id;
    private String name;
    private String encodeName;
    
    public String getEncodeName() {
        return this.encodeName;
    }
    
    public void setEncodeName(final String encodeName) {
        this.encodeName = encodeName;
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
        try {
            this.encodeName = URLEncoder.encode(name, "utf-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public Type(final int id, final String name) {
        this.id = id;
        this.name = name;
    }
    
    public Type() {
    }
    
    public Type(final String name) {
        this.name = name;
    }
}
