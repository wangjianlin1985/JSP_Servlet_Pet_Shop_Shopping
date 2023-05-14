// 
// 
// 

package model;

public class User
{
    private int id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    private boolean isadmin;
    private boolean isvalidate;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "User [id=" + this.id + ", username=" + this.username + ", email=" + this.email + ", password=" + this.password + ", name=" + this.name + ", phone=" + this.phone + ", address=" + this.address + ", isadmin=" + this.isadmin + ", isvalidate=" + this.isvalidate + "]";
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
    
    public boolean isIsadmin() {
        return this.isadmin;
    }
    
    public void setIsadmin(final boolean isadmin) {
        this.isadmin = isadmin;
    }
    
    public boolean isIsvalidate() {
        return this.isvalidate;
    }
    
    public void setIsvalidate(final boolean isvalidate) {
        this.isvalidate = isvalidate;
    }
    
    public User(final int id, final String username, final String email, final String password, final String name, final String phone, final String address, final boolean isadmin, final boolean isvalidate) {
        this.isadmin = false;
        this.isvalidate = false;
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isadmin = isadmin;
        this.isvalidate = isvalidate;
    }
    
    public User(final String username, final String email, final String password, final String name, final String phone, final String address) {
        this.isadmin = false;
        this.isvalidate = false;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isadmin = false;
        this.isvalidate = false;
    }
    
    public User() {
        this.isadmin = false;
        this.isvalidate = false;
    }
}
