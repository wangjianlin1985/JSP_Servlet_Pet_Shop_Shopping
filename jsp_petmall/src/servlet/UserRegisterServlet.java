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

@WebServlet(name = "user_register", urlPatterns = { "/user_rigister" })
public class UserRegisterServlet extends HttpServlet
{
    private UserService uService;
    
    public UserRegisterServlet() {
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
            request.setAttribute("msg", (Object)"\u6ce8\u518c\u6210\u529f\uff0c\u8bf7\u767b\u5f55\uff01");
            request.getRequestDispatcher("user_login.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
        else {
            request.setAttribute("msg", (Object)"\u7528\u6237\u540d\u6216\u90ae\u7bb1\u91cd\u590d\uff0c\u8bf7\u91cd\u65b0\u586b\u5199\uff01");
            request.getRequestDispatcher("user_register.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
}
