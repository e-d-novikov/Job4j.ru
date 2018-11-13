package d.servlet.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "<meta charset=\"UTF-16\">"
                + "<title>Title</title>"
                + "</head>"
                + "<body>"
                + "<form action='/user' method='post'>"
                + "<input type=\"hidden\" name=\"action\" value=\"update\">"
                + "<input type=\"hidden\" name=\"id\" value=\"" + req.getParameter("id") + "\">"
                + "Name: <input type='text' name='name' value='" + req.getParameter("name") + "'><br>"
                + "Login: <input type='text' name='login' value='" + req.getParameter("login") + "'><br>"
                + "E-mail: <input type='text' name='email' value='" + req.getParameter("email") + "'><br>"
                + "Date: <input type='text' name='date' value='" + req.getParameter("date") + "'><br>"
                + "<input type='submit' value='Update'>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
