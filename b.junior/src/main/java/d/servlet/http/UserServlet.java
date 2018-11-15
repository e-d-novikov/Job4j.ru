package d.servlet.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private int amount = 0;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if (req.getParameter("action").equals("add")) {
            ValidateService.getInstance().add(new User(amount,
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email")));
            amount++;
        } else if (req.getParameter("action").equals("update")) {
            ValidateService.getInstance().update(new User(Integer.valueOf(req.getParameter("id")),
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email")));
        } else if (req.getParameter("action").equals("delete")) {
            ValidateService.getInstance().delete(Integer.valueOf(req.getParameter("id")));
            amount--;
        }
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
