package Filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet Filter implementation class encodingFilter
 */
public class encodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public encodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    request.setCharacterEncoding("utf-8");//统一字符编码格式
	    response.setCharacterEncoding("utf-8");//统一字符编码格式
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
