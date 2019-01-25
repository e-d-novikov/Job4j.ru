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
 * AdminServlet.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    /**
     * Метод doGet добавляет в атрибут "users" массив всех пользователей на страницу AdminPage.jsp
     * @param request - запрос;
     * @param response - ответ;
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", ValidateService.getInstance().findAll());
        request.getRequestDispatcher("/WEB-INF/views/").forward(request, response);
    }
    /**
     * Метод doPost выполняет следующие действия:
     * "action" : "new_user" - направляет на страницу CreateUser.jsp;
     * "action" : "add_user" - добавляет пользователя в базу данных;
     * "action" : "edit" - направляет на страницу EditUser.jsp;
     * "action" : "apply" - вносит изменения в базу данных;
     * "action" : "delete" - удаляет пользователя из базы данных;
     * "action" : "back" - направляет на страницу UserPage.jsp.
     * @param request - запрос;
     * @param response - ответ;
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action").equals("new_user")) {
            request.getRequestDispatcher("/WEB-INF/views/CreateUser.jsp").forward(request, response);
        } else if (request.getParameter("action").equals("add_user")) {
            ValidateService.getInstance().add(new User(0,
                    request.getParameter("login"),
                    request.getParameter("password"),
                    request.getParameter("role"),
                    request.getParameter("name"),
                    request.getParameter("sername"),
                    request.getParameter("email")));
            request.setAttribute("users", ValidateService.getInstance().findAll());
            request.getRequestDispatcher("/WEB-INF/views/AdminPage.jsp").forward(request, response);
        } else if (request.getParameter("action").equals("edit")) {
            request.setAttribute("user", ValidateService.getInstance().findById(request.getParameter("login")));
            request.setAttribute("servlet", "AdminServlet");
            request.getRequestDispatcher("/WEB-INF/views/EditUser.jsp").forward(request, response);
        } else if (request.getParameter("action").equals("apply")) {
            String login = (String) request.getSession().getAttribute("login");
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
            } else {
                request.setAttribute("users", ValidateService.getInstance().findAll());
                request.getRequestDispatcher("/WEB-INF/views/AdminPage.jsp").forward(request, response);
            }
        } else if (request.getParameter("action").equals("delete")) {
            ValidateService.getInstance().delete(request.getParameter("login"));
            request.setAttribute("users", ValidateService.getInstance().findAll());
            request.getRequestDispatcher("/WEB-INF/views/AdminPage.jsp").forward(request, response);
        } else if (request.getParameter("action").equals("back")) {
            HttpSession session = request.getSession();
            request.setAttribute("user", ValidateService.getInstance().findById((String) session.getAttribute("login")));
            request.getRequestDispatcher("/WEB-INF/views/UserPage.jsp").forward(request, response);
        }
    }
}
