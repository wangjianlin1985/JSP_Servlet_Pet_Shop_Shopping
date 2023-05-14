// 
// 
// 

package servlet;

import model.Order;
import java.util.List;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.OrderService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "order_list", urlPatterns = { "/order_list" })
public class OrderListServlet extends HttpServlet
{
    private OrderService oService;
    
    public OrderListServlet() {
        this.oService = new OrderService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final User u = (User)request.getSession().getAttribute("user");
        if (u == null) {
            response.sendRedirect("/index");
            return;
        }
        final List<Order> list = this.oService.selectAll(u.getId());
        request.setAttribute("orderList", (Object)list);
        request.getRequestDispatcher("/order_list.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
}
