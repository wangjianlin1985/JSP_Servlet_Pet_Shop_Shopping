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
import service.TypeService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_type_delete", urlPatterns = { "/admin/type_delete" })
public class AdminTypeDeleteServlet extends HttpServlet
{
    private TypeService tService;
    
    public AdminTypeDeleteServlet() {
        this.tService = new TypeService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int id = Integer.parseInt(request.getParameter("id"));
        final boolean isSuccess = this.tService.delete(id);
        if (isSuccess) {
            request.setAttribute("msg", (Object)"\u5220\u9664\u6210\u529f");
            final TypeService tsService = new TypeService();
            request.getSession().setAttribute("typeList", (Object)tsService.GetAllType());
        }
        else {
            request.setAttribute("failMsg", (Object)"\u7c7b\u76ee\u4e0b\u5305\u542b\u836f\u54c1\uff0c\u65e0\u6cd5\u76f4\u63a5\u5220\u9664\u7c7b\u76ee\uff01");
        }
        request.getRequestDispatcher("/admin/type_list").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
