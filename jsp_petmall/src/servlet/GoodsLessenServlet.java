// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import model.Order;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "goods_lessen", urlPatterns = { "/goods_lessen" })
public class GoodsLessenServlet extends HttpServlet
{
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final Order o = (Order)request.getSession().getAttribute("order");
        final int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        o.lessen(goodsid);
        response.getWriter().print("ok");
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
}
