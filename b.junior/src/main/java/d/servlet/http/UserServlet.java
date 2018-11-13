package d.servlet.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {

    private final ValidateService container = ValidateService.getInstance();
    private int amount = 0;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");

        StringBuilder sb = new StringBuilder("<table>");
        for (User user : container.findAll()) {
            sb.append("<tr>"

                    + "<td>"
                    + user.toString()
                    + "</td>"

                    + "<td>"
                    + "<form action=\"/edit\" method=\"get\">"
                    + "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">"
                    + "<input type=\"hidden\" name=\"name\" value=\"" + user.getName() + "\">"
                    + "<input type=\"hidden\" name=\"login\" value=\"" + user.getLogin() + "\">"
                    + "<input type=\"hidden\" name=\"email\" value=\"" + user.getEmail() + "\">"
                    + "<input type=\"hidden\" name=\"date\" value=\"" + user.getDate() + "\">"
                    + "<input type='submit' name='Редактировать'>"
                    + "</form>"
                    + "</td>"

                    + "<td>"
                    + "<form action=\"/user\" method=\"post\">"
                    + "<input type=\"hidden\" name=\"action\" value=\"delete\">"
                    + "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">"
                    + "<input type='submit' name='Удалить'>"
                    + "</form>"
                    + "</td>"

                    + "</tr>");
        }
        sb.append("</table>");

        PrintWriter writer = new PrintWriter(res.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "<meta charset=\"UTF-16\">"
                + "<title>Список пользователей</title>"
                + "</head>"
                + "<body>"
                + "<form action=\"/create\" method=\"get\">"
                + "<input type='submit' name='Создать'><br>"
                + sb
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if (req.getParameter("action").equals("add")) {
            container.add(new User(amount,
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email"),
                    req.getParameter("date")));
            amount++;
        } else if (req.getParameter("action").equals("update")) {
            container.update(Integer.valueOf(req.getParameter("id")),
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email"),
                    req.getParameter("date"));
        } else if (req.getParameter("action").equals("delete")) {
            container.delete(Integer.valueOf(req.getParameter("id")));
            amount--;
        }
        doGet(req, resp);
    }
}
