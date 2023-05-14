// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import model.Type;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.TypeService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_type_add", urlPatterns = { "/admin/type_add" })
public class AdminTypeAddServlet extends HttpServlet
{
    private TypeService tService;
    
    public AdminTypeAddServlet() {
        this.tService = new TypeService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String name = request.getParameter("name");
        this.tService.insert(new Type(name));
        final TypeService tsService = new TypeService();
        request.getSession().setAttribute("typeList", (Object)tsService.GetAllType());
        request.getRequestDispatcher("/admin/type_list").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
