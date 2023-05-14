// 
// 
// 

package servlet;

import model.Goods;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.GoodsService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "goods_detail", urlPatterns = { "/goods_detail" })
public class GoodsDetailServlet extends HttpServlet
{
    private GoodsService gService;
    
    public GoodsDetailServlet() {
        this.gService = new GoodsService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int id = Integer.parseInt(request.getParameter("id"));
        final Goods g = this.gService.getGoodsById(id);
        request.setAttribute("g", (Object)g);
        request.getRequestDispatcher("/goods_detail.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
}
