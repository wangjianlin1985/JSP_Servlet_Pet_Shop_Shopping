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

@WebServlet(name = "admin_order_status", urlPatterns = { "/admin/order_status" })
public class AdminOrderStatusServlet extends HttpServlet
{
    private OrderService oService;
    
    public AdminOrderStatusServlet() {
        this.oService = new OrderService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int id = Integer.parseInt(request.getParameter("id"));
        final int status = Integer.parseInt(request.getParameter("status"));
        this.oService.updateStatus(id, status);
        request.getRequestDispatcher("/admin/order_list?status=" + status).forward((ServletRequest)request, (ServletResponse)response);
    }
}
