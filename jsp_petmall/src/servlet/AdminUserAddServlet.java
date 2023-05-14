// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import model.User;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_user_add", urlPatterns = { "/admin/user_add" })
public class AdminUserAddServlet extends HttpServlet
{
    private UserService uService;
    
    public AdminUserAddServlet() {
        this.uService = new UserService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final User user = new User();
        try {
            BeanUtils.copyProperties((Object)user, (Object)request.getParameterMap());
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        if (this.uService.register(user)) {
            request.setAttribute("msg", (Object)"\u5ba2\u6237\u6dfb\u52a0\u6210\u529f\uff01");
            request.getRequestDispatcher("/admin/user_list").forward((ServletRequest)request, (ServletResponse)response);
        }
        else {
            request.setAttribute("failMsg", (Object)"\u7528\u6237\u540d\u6216\u90ae\u7bb1\u91cd\u590d\uff0c\u8bf7\u91cd\u65b0\u586b\u5199\uff01");
            request.setAttribute("u", (Object)user);
            request.getRequestDispatcher("/admin/user_add.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
    }
}
