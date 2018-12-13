package d.servlet.http;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", ValidateService.getInstance().findAll());
        req.getRequestDispatcher("/WEB-INF/views/AdminPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("new_user")) {
            req.getRequestDispatcher("/WEB-INF/views/CreateUser.jsp").forward(req, resp);
        } else if (req.getParameter("action").equals("add_user")) {
                ValidateService.getInstance().add(new User(0,
                        req.getParameter("login"),
                        req.getParameter("password"),
                        req.getParameter("role"),
                        req.getParameter("name"),
                        req.getParameter("sername"),
                        req.getParameter("email")));
                req.setAttribute("users", ValidateService.getInstance().findAll());
                req.getRequestDispatcher("/WEB-INF/views/AdminPage.jsp").forward(req, resp);
            } else if (req.getParameter("action").equals("edit")) {
                req.setAttribute("user", ValidateService.getInstance().findById(req.getParameter("login")));
                req.setAttribute("servlet", "AdminServlet");
                req.getRequestDispatcher("/WEB-INF/views/EditUser.jsp").forward(req, resp);
            } else if (req.getParameter("action").equals("apply")) {
                HttpSession session = req.getSession();
                String login = (String) session.getAttribute("login");
                User user = ValidateService.getInstance().findById(login);
                String oldLogin = req.getParameter("oldLogin");
                String oldPassword = req.getParameter("oldPassword");
                String newLogin = req.getParameter("login");
                String newPassword = req.getParameter("password");
                ValidateService.getInstance().update(new User(Integer.valueOf(req.getParameter("id")),
                        req.getParameter("login"),
                        req.getParameter("password"),
                        req.getParameter("role"),
                        req.getParameter("name"),
                        req.getParameter("sername"),
                        req.getParameter("email")));
                if (login.equals(oldLogin)) {
                    if (!oldLogin.equals(newLogin) || !oldPassword.equals(newPassword)) {
                        req.getRequestDispatcher("/LogoutServlet").forward(req, resp);
                    }
                } else {
                    req.setAttribute("users", ValidateService.getInstance().findAll());
                    req.getRequestDispatcher("/WEB-INF/views/AdminPage.jsp").forward(req, resp);
                }
            } else if (req.getParameter("action").equals("delete")) {
                ValidateService.getInstance().delete(req.getParameter("login"));
                req.setAttribute("users", ValidateService.getInstance().findAll());
                req.getRequestDispatcher("/WEB-INF/views/AdminPage.jsp").forward(req, resp);
            } else if (req.getParameter("action").equals("back")) {
                HttpSession session = req.getSession();
                req.setAttribute("user", ValidateService.getInstance().findById((String) session.getAttribute("login")));
                req.getRequestDispatcher("/WEB-INF/views/UserPage.jsp").forward(req, resp);
            }
        }
    }
