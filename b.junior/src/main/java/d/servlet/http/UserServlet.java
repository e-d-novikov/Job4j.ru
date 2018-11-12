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
        PrintWriter writer = new PrintWriter(res.getOutputStream());
        for (User user : container.findAll()) {
            writer.append(user.toString());
        }
        writer.flush();
    }

    //Запросы для тестирования
    //user?action=add&name=TestName&login=TestLogin&email=test@email.com&date=date
    //user?action=add&name=TestName2&login=TestLogin2&email=test2@email.com&date=12112018
    //user?action=update&id=0&name=UpdateName&login=UpdateLogin2&UpdateEmail=test2@email.com&date=12112018
    //user?action=delete&id=1
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
        }
    }
}
