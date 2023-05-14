// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import model.User;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "user_login", urlPatterns = { "/user_login" })
public class UserLoginServlet extends HttpServlet
{
    private UserService uService;
    
    public UserLoginServlet() {
        this.uService = new UserService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String ue = request.getParameter("ue");
        final String password = request.getParameter("password");
        final User user = this.uService.login(ue, password);
        if (user == null) {
            request.setAttribute("failMsg", (Object)"\u7528\u6237\u540d\u3001\u90ae\u7bb1\u6216\u8005\u5bc6\u7801\u9519\u8bef\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55\uff01");
            request.getRequestDispatcher("/user_login.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
        else {
            request.getSession().setAttribute("user", (Object)user);
            request.getRequestDispatcher("/user_center.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
}
