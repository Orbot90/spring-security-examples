package ru.orbot90.filters.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by root on 03.05.16.
 */
@WebFilter(urlPatterns = "/secured")
public class SesameFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object pass = request.getParameter("pass");

        boolean success = checkPass(pass) || randomAccess();

        if(!success) {
            ((HttpServletResponse)response).sendRedirect("/filter/");
        }
        chain.doFilter(request, response);
    }

    private boolean checkPass(Object pass) {

        if(pass == null) return false;

        String passString = (String)pass;
        return passString.equals("sesameopen");
    }

    private boolean randomAccess() {
        Random random = new Random();
        int randomInt = random.nextInt(100);
        return randomInt >= 70;
    }

    @Override
    public void destroy() {

    }
}
