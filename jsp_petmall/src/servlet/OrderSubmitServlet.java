// 
// 
// 

package servlet;

import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "order_submit", urlPatterns = { "/order_submit" })
public class OrderSubmitServlet extends HttpServlet
{
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            request.getRequestDispatcher("/order_submit.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
        else {
            request.setAttribute("failMsg", (Object)"\u8bf7\u767b\u5f55\u540e\uff0c\u518d\u63d0\u4ea4\u8ba2\u5355\uff01");
            request.getRequestDispatcher("/user_login.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
    }
}
