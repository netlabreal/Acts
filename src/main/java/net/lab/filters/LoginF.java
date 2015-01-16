package net.lab.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by lab on 13.11.2014.
 */
public class LoginF implements Filter{
    final static Logger log = Logger.getLogger(LoginF.class);
    private ArrayList<String> urlList;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String url = req.getServletPath();
        log.info("С адреса: "+req.getRemoteAddr()+" переход на "+url);

        HttpSession session = req.getSession();
        String uname = (String)session.getAttribute("username");

        if(uname!=null){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            if(url.equals("/welcome.jsp") || url.equals("/roptions.jsp")|| url.equals("/data")|| url.equals("/adr")) {
                RequestDispatcher rd = servletRequest.getRequestDispatcher("/login.jsp");
                rd.forward(servletRequest, servletResponse);
            }
            else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
    @Override
    public void destroy() {
    }
}
