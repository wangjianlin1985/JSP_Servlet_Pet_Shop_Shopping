// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "user_logout", urlPatterns = { "/user_logout" })
public class UserLogoutServlet extends HttpServlet
{
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect("index");
    }
}
