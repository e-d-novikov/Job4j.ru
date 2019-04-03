package d.servlet.cinema.servlets;

import javax.servlet.*;
import java.io.IOException;
/**
 * Класс JSONFilter.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class JSONFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
