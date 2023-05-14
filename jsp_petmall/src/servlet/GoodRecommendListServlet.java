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

@WebServlet(name = "goodrecommendList", urlPatterns = { "/goodsrecommend_list" })
public class GoodRecommendListServlet extends HttpServlet
{
    private GoodsService gService;
    
    public GoodRecommendListServlet() {
        this.gService = new GoodsService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int type = Integer.parseInt(request.getParameter("type"));
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
            p = this.gService.getGoodsRecommendPage(type, p.getTotalPage());
        }
        request.setAttribute("p", (Object)p);
        request.setAttribute("t", (Object)type);
        request.getRequestDispatcher("goodsrecommend_list.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
}
