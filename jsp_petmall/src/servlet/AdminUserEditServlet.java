// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import model.User;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_user_edit", urlPatterns = { "/admin/user_edit" })
public class AdminUserEditServlet extends HttpServlet
{
    private UserService uService;
    
    public AdminUserEditServlet() {
        this.uService = new UserService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final User u = new User();
        try {
            BeanUtils.copyProperties((Object)u, (Object)request.getParameterMap());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.uService.updateUserAddress(u);
        request.getRequestDispatcher("/admin/user_list").forward((ServletRequest)request, (ServletResponse)response);
    }
}
