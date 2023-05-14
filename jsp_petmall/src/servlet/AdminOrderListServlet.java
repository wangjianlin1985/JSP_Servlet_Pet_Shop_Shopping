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
import service.OrderService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_order_list", urlPatterns = { "/admin/order_list" })
public class AdminOrderListServlet extends HttpServlet
{
    private OrderService oService;
    
    public AdminOrderListServlet() {
        this.oService = new OrderService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        int status = 0;
        if (request.getParameter("status") != null) {
            status = Integer.parseInt(request.getParameter("status"));
        }
        request.setAttribute("status", (Object)status);
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
        Page p = this.oService.getOrderPage(status, pageNumber);
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else if (pageNumber >= p.getTotalPage() + 1) {
            p = this.oService.getOrderPage(status, pageNumber);
        }
        request.setAttribute("p", (Object)p);
        request.getRequestDispatcher("/admin/order_list.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
}
