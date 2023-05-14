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

@WebServlet(name = "admin_user_editshow", urlPatterns = { "/admin/user_editshow" })
public class AdminUserEditshowServlet extends HttpServlet
{
    private UserService uService;
    
    public AdminUserEditshowServlet() {
        this.uService = new UserService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int id = Integer.parseInt(request.getParameter("id"));
        final User user = this.uService.selectById(id);
        request.setAttribute("u", (Object)user);
        request.getRequestDispatcher("/admin/user_edit.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
}
