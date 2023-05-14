// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import model.User;
import java.util.Date;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import model.Order;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.OrderService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "order_confirm", urlPatterns = { "/order_confirm" })
public class OrderConfirmServlet extends HttpServlet
{
    private OrderService oService;
    
    public OrderConfirmServlet() {
        this.oService = new OrderService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final Order o = (Order)request.getSession().getAttribute("order");
        try {
            BeanUtils.copyProperties((Object)o, (Object)request.getParameterMap());
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        o.setDatetime(new Date());
        o.setStatus(2);
        o.setUser((User)request.getSession().getAttribute("user"));
        this.oService.addOrder(o);
        request.getSession().removeAttribute("order");
        request.setAttribute("msg", (Object)"\u8ba2\u5355\u652f\u4ed8\u6210\u529f\uff01");
        request.getRequestDispatcher("/order_success.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
}
