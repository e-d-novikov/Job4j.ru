package d.servlet.http.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * LogoutServlet.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * Метод doPost выходит из текущего аккаунта и переходит на страницу Login.jsp.
	 * @param request - запрос;
	 * @param response - ответ;
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("JSESSIONID")) {
					System.out.println("JSESSIONID=" + cookie.getValue());
					break;
				}
			}
		}
		HttpSession session = request.getSession(false);
		System.out.println("User=" + session.getAttribute("user"));
		if (session != null) {
			session.invalidate();
		}
		request.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(request, response);
	}
}
