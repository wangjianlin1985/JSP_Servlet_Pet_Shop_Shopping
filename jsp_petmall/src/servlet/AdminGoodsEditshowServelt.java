// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import model.Goods;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.GoodsService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_goods_editshow", urlPatterns = { "/admin/goods_editshow" })
public class AdminGoodsEditshowServelt extends HttpServlet
{
    private GoodsService gService;
    
    public AdminGoodsEditshowServelt() {
        this.gService = new GoodsService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int id = Integer.parseInt(request.getParameter("id"));
        final Goods g = this.gService.getGoodsById(id);
        request.setAttribute("g", (Object)g);
        request.getRequestDispatcher("/admin/goods_edit.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
