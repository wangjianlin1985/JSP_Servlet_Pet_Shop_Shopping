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
import service.OrderService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_order_delete", urlPatterns = { "/admin/order_delete" })
public class AdminOrderDeleteServlet extends HttpServlet
{
    private OrderService oService;
    
    public AdminOrderDeleteServlet() {
        this.oService = new OrderService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int id = Integer.parseInt(request.getParameter("id"));
        this.oService.delete(id);
        request.getRequestDispatcher("/admin/order_list").forward((ServletRequest)request, (ServletResponse)response);
    }
}
