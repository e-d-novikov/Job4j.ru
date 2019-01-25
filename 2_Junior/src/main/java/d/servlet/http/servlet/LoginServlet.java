package d.servlet.http.servlet;

import d.servlet.http.object.User;
import d.servlet.http.store.ValidateService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * LoginServlet.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	/**
	 * Метод doGet направляет на страницу Login.jsp.
	 * @param request - запрос;
	 * @param response - ответ;
	 * @throws ServletException
	 * @throws IOException
	 */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(request, response);
    }

	/**
	 * Метод doPost выполняет проверку введенных логина и пароля.
	 * @param request - запрос;
	 * @param response - ответ;
	 * @throws ServletException
	 * @throws IOException
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("pwd");

		if (ValidateService.getInstance().isCredentional(login, password)) {
			User user = ValidateService.getInstance().findById(login);
			HttpSession session = request.getSession();
			session.setAttribute("login", login);
			session.setAttribute("role", user.getRole());
			request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/UserPage.jsp").forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}
	}
}
