// 
// 
// 

package filter;

import javax.servlet.FilterConfig;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;

@WebFilter(filterName = "EncodeFilter", urlPatterns = { "/*" })
public class EncodeFilter implements Filter
{
    public void destroy() {
    }
    
    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        chain.doFilter(req, resp);
    }
    
    public void init(final FilterConfig config) throws ServletException {
    }
}
