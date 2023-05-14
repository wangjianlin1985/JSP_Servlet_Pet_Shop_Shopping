// 
// 
// 

package model;

import java.util.List;

public class Page
{
    private int pageNumber;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<Object> list;
    
    public void SetPageSizeAndTotalCount(final int pageSize, final int totalCount) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = (int)Math.ceil(totalCount / pageSize);
    }
    
    public int getPageNumber() {
        return this.pageNumber;
    }
    
    public void setPageNumber(final int pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
    
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }
    
    public int getTotalCount() {
        return this.totalCount;
    }
    
    public void setTotalCount(final int totalCount) {
        this.totalCount = totalCount;
    }
    
    public int getTotalPage() {
        return this.totalPage;
    }
    
    public void setTotalPage(final int totalPage) {
        this.totalPage = totalPage;
    }
    
    public List<Object> getList() {
        return this.list;
    }
    
    public void setList(final List<Object> list) {
        this.list = list;
    }
}
