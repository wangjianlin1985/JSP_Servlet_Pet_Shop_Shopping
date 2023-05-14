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

@WebServlet(name = "user_changeaddress", urlPatterns = { "/user_changeaddress" })
public class UserChangeAddressServlet extends HttpServlet
{
    private UserService uService;
    
    public UserChangeAddressServlet() {
        this.uService = new UserService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final User loginUser = (User)request.getSession().getAttribute("user");
        try {
            BeanUtils.copyProperties((Object)loginUser, (Object)request.getParameterMap());
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        this.uService.updateUserAddress(loginUser);
        request.setAttribute("msg", (Object)"\u6536\u4ef6\u4fe1\u606f\u66f4\u65b0\u6210\u529f\uff01");
        request.getRequestDispatcher("/user_center.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
}
