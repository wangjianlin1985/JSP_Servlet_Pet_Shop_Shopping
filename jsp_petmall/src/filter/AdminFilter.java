// 
// 
// 

package filter;

import javax.servlet.FilterConfig;
import java.io.IOException;
import javax.servlet.ServletException;
import model.User;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;

@WebFilter(filterName = "AdminFilter", urlPatterns = { "/admin/*" })
public class AdminFilter implements Filter
{
    public void destroy() {
    }
    
    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest)req;
        final HttpServletResponse requestp = (HttpServletResponse)resp;
        final User u = (User)request.getSession().getAttribute("user");
        if (u == null || !u.isIsadmin()) {
            requestp.sendRedirect("../index.jsp");
        }
        else {
            chain.doFilter(req, resp);
        }
    }
    
    public void init(final FilterConfig config) throws ServletException {
    }
}
