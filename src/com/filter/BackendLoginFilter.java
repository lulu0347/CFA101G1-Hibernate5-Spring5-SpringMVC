package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admini.model.AdminiVO;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/backend/*")
public class BackendLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public BackendLoginFilter() {
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
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String uri = req.getRequestURI();

		if(uri.contains("item.jsp") || uri.contains("bid.jsp") || uri.contains("auct.jsp") || uri.contains("admini.jsp") || uri.contains("money.jsp") || uri.contains("repair.jsp") || uri.contains("used.jsp") || uri.contains("frontend.jsp")) {
			HttpSession session = req.getSession();
			Object adminiVO = session.getAttribute("adminiVO");
			System.out.println(adminiVO);
			if(adminiVO == null) {
				res.sendRedirect(req.getContextPath()+"/backend/login.jsp");		
			}
		}
			
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
