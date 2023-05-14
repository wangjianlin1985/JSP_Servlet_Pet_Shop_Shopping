// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import model.Page;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_user_list", urlPatterns = { "/admin/user_list" })
public class AdminUserListServlet extends HttpServlet
{
    private UserService uService;
    
    public AdminUserListServlet() {
        this.uService = new UserService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            }
            catch (Exception ex) {}
        }
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        Page p = this.uService.getUserPage(pageNumber);
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else if (pageNumber >= p.getTotalPage() + 1) {
            p = this.uService.getUserPage(pageNumber);
        }
        request.setAttribute("p", (Object)p);
        request.getRequestDispatcher("/admin/user_list.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
