// 
// 
// 

package servlet;

import model.Page;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.net.URLEncoder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.GoodsService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "goods_search", urlPatterns = { "/goods_search" })
public class GoodsSearchServlet extends HttpServlet
{
    private GoodsService gService;
    
    public GoodsSearchServlet() {
        this.gService = new GoodsService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String keyword = request.getParameter("keyword");
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
        Page p = this.gService.getSearchGoodsPage(keyword, pageNumber);
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else if (pageNumber >= p.getTotalPage() + 1) {
            p = this.gService.getSearchGoodsPage(keyword, pageNumber);
        }
        request.setAttribute("p", (Object)p);
        request.setAttribute("keyword", (Object)URLEncoder.encode(keyword, "utf-8"));
        request.getRequestDispatcher("/goods_search.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
}
