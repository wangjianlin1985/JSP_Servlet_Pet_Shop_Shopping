// 
// 
// 

package servlet;

import java.util.List;
import java.util.Map;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.GoodsService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "IndexServlet", urlPatterns = { "/index" })
public class IndexServlet extends HttpServlet
{
    private GoodsService gService;
    
    public IndexServlet() {
        this.gService = new GoodsService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final Map<String, Object> ScrollGood = this.gService.getScrollGood();
        request.setAttribute("scroll", (Object)ScrollGood);
        final List<Map<String, Object>> newList = this.gService.getGoodsList(3);
        request.setAttribute("newList", (Object)newList);
        final List<Map<String, Object>> hotList = this.gService.getGoodsList(2);
        request.setAttribute("hotList", (Object)hotList);
        request.getRequestDispatcher("index.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
}
