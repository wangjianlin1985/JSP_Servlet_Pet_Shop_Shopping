// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import model.Type;
import java.util.List;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.TypeService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admi_type_list", urlPatterns = { "/admin/type_list" })
public class AdminTypeListServlet extends HttpServlet
{
    private TypeService tService;
    
    public AdminTypeListServlet() {
        this.tService = new TypeService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final List<Type> list = this.tService.GetAllType();
        request.setAttribute("list", (Object)list);
        request.getRequestDispatcher("/admin/type_list.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
