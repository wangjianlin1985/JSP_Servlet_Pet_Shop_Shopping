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

@WebServlet(name = "admin_user_reset", urlPatterns = { "/admin/user_reset" })
public class AdminUserResetServlet extends HttpServlet
{
    private UserService uService;
    
    public AdminUserResetServlet() {
        this.uService = new UserService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final User u = new User();
        try {
            BeanUtils.copyProperties((Object)u, (Object)request.getParameterMap());
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        this.uService.updatePwd(u);
        request.getRequestDispatcher("/admin/user_list").forward((ServletRequest)request, (ServletResponse)response);
    }
}
