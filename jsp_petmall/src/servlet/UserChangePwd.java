// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import model.User;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "user_changepwd", urlPatterns = { "/user_changepwd" })
public class UserChangePwd extends HttpServlet
{
    private UserService uService;
    
    public UserChangePwd() {
        this.uService = new UserService();
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String password = request.getParameter("password");
        final String newPwd = request.getParameter("newPassword");
        final User u = (User)request.getSession().getAttribute("user");
        if (password.equals(u.getPassword())) {
            u.setPassword(newPwd);
            this.uService.updatePwd(u);
            request.setAttribute("msg", (Object)"\u4fee\u6539\u6210\u529f\uff01");
            request.getRequestDispatcher("/user_center.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
        else {
            request.setAttribute("failMsg", (Object)"\u4fee\u6539\u5931\u8d25\uff0c\u539f\u5bc6\u7801\u4e0d\u6b63\u786e\uff0c\u4f60\u518d\u60f3\u60f3\uff01");
            request.getRequestDispatcher("/user_center.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
}
