package d.servlet.http.servlet;

import d.servlet.http.object.User;
import d.servlet.http.store.ValidateService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * UserServlet.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    /**
     * Метод doPost выполняет следующие действия:
     * "action" : "edit" - направляет на страницу EditUser.jsp;
     * "action" : "apply" - вносит изменения и направляет на страницу пользователя.
     * @param request - запрос;
     * @param response - ответ.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        if (request.getParameter("action").equals("edit")) {
            request.setAttribute("user", ValidateService.getInstance().findById(request.getParameter("login")));
            request.setAttribute("servlet", "UserServlet");
            request.getRequestDispatcher("/WEB-INF/views/EditUser.jsp").forward(request, response);
        } else if (request.getParameter("action").equals("apply")) {
            HttpSession session = request.getSession();
            String login = (String) session.getAttribute("login");
            String oldLogin = request.getParameter("oldLogin");
            String oldPassword = request.getParameter("oldPassword");
            String newLogin = request.getParameter("login");
            String newPassword = request.getParameter("password");
            ValidateService.getInstance().update(new User(Integer.valueOf(request.getParameter("id")),
                    request.getParameter("login"),
                    request.getParameter("password"),
                    request.getParameter("role"),
                    request.getParameter("name"),
                    request.getParameter("sername"),
                    request.getParameter("email")));
            if (login.equals(oldLogin)) {
                if (!oldLogin.equals(newLogin) || !oldPassword.equals(newPassword)) {
                    request.getRequestDispatcher("/LogoutServlet").forward(request, response);
                }
            }
            request.setAttribute("user", ValidateService.getInstance().findById((String) session.getAttribute("login")));
            request.getRequestDispatcher("/WEB-INF/views/UserPage.jsp").forward(request, response);
        }
    }
}
