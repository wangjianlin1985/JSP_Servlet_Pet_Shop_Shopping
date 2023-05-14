// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import model.Page;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.GoodsService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_goods_list", urlPatterns = { "/admin/goods_list" })
public class AdminGoodsListServlet extends HttpServlet
{
    private GoodsService gService;
    
    public AdminGoodsListServlet() {
        this.gService = new GoodsService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        int type = 0;
        if (request.getParameter("type") != null) {
            type = Integer.parseInt(request.getParameter("type"));
        }
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            }
            catch (Exception ex) {}
        }
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        Page p = this.gService.getGoodsRecommendPage(type, pageNumber);
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else if (pageNumber >= p.getTotalPage() + 1) {
            p = this.gService.getGoodsRecommendPage(type, pageNumber);
        }
        request.setAttribute("p", (Object)p);
        request.setAttribute("type", (Object)type);
        request.getRequestDispatcher("/admin/goods_list.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
