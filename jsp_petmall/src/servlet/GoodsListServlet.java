// 
// 
// 

package servlet;

import model.Page;
import model.Type;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.TypeService;
import service.GoodsService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "goods_List", urlPatterns = { "/goods_list" })
public class GoodsListServlet extends HttpServlet
{
    private GoodsService gService;
    private TypeService tService;
    
    public GoodsListServlet() {
        this.gService = new GoodsService();
        this.tService = new TypeService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if (request.getParameter("typeid") != null) {
            id = Integer.parseInt(request.getParameter("typeid"));
        }
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            }
            catch (Exception ex) {}
        }
        Type t = null;
        if (id != 0) {
            t = this.tService.selectTypeNameByID(id);
        }
        request.setAttribute("t", (Object)t);
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        Page p = this.gService.selectPageByTypeID(id, pageNumber);
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else if (pageNumber >= p.getTotalPage() + 1) {
            p = this.gService.selectPageByTypeID(id, p.getTotalPage());
        }
        request.setAttribute("p", (Object)p);
        request.setAttribute("id", (Object)String.valueOf(id));
        request.getRequestDispatcher("/goods_list.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
}
