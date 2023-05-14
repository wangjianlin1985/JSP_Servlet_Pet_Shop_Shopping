// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import model.Goods;
import model.Order;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.GoodsService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "goods_buy", urlPatterns = { "/goods_buy" })
public class GoodsBuyServlet extends HttpServlet
{
    private GoodsService gService;
    
    public GoodsBuyServlet() {
        this.gService = new GoodsService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        Order o = null;
        if (request.getSession().getAttribute("order") != null) {
            o = (Order)request.getSession().getAttribute("order");
        }
        else {
            o = new Order();
            request.getSession().setAttribute("order", (Object)o);
        }
        final int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        final Goods goods = this.gService.getGoodsById(goodsid);
        if (goods.getStock() > 0) {
            o.addGoods(goods);
            response.getWriter().print("ok");
        }
        else {
            response.getWriter().print("fail");
        }
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
}
