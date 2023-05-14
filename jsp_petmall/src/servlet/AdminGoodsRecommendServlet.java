// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.GoodsService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_goods_recommend", urlPatterns = { "/admin/goods_recommend" })
public class AdminGoodsRecommendServlet extends HttpServlet
{
    private GoodsService gService;
    
    public AdminGoodsRecommendServlet() {
        this.gService = new GoodsService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int id = Integer.parseInt(request.getParameter("id"));
        final String method = request.getParameter("method");
        final int typeTarget = Integer.parseInt(request.getParameter("typeTarget"));
        if (method.equals("add")) {
            this.gService.addRecommend(id, typeTarget);
        }
        else {
            this.gService.removeRecommend(id, typeTarget);
        }
        request.getRequestDispatcher("/admin/goods_list").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
