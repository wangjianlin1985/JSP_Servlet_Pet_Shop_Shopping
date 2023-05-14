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
import service.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_user_delete", urlPatterns = { "/admin/user_delete" })
public class AdminUserDeleteServlet extends HttpServlet
{
    private UserService uService;
    
    public AdminUserDeleteServlet() {
        this.uService = new UserService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int id = Integer.parseInt(request.getParameter("id"));
        final boolean isSuccess = this.uService.delete(id);
        if (isSuccess) {
            request.setAttribute("msg", (Object)"\u5ba2\u6237\u5220\u9664\u6210\u529f");
        }
        else {
            request.setAttribute("failMsg", (Object)"\u5ba2\u6237\u6709\u4e0b\u7684\u8ba2\u5355\uff0c\u8bf7\u5148\u5220\u9664\u8be5\u5ba2\u6237\u4e0b\u7684\u8ba2\u5355\uff0c\u518d\u6765\u5220\u9664\u5ba2\u6237\uff01");
        }
        request.getRequestDispatcher("/admin/user_list").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
