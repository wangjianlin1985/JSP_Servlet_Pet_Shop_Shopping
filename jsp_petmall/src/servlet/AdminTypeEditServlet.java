// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import model.Type;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.TypeService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_type_edit", urlPatterns = { "/admin/type_edit" })
public class AdminTypeEditServlet extends HttpServlet
{
    private TypeService tService;
    
    public AdminTypeEditServlet() {
        this.tService = new TypeService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final Type t = new Type();
        try {
            BeanUtils.copyProperties((Object)t, (Object)request.getParameterMap());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.tService.update(t);
        final TypeService tsService = new TypeService();
        request.getSession().setAttribute("typeList", (Object)tsService.GetAllType());
        request.getRequestDispatcher("/admin/type_list").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
