package d.servlet.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {

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
                + "<input type=\"hidden\" name=\"action\" value=\"add\">"
                + "Name: <input type='text' name='name'><br>"
                + "Login: <input type='text' name='login'><br>"
                + "E-mail: <input type='text' name='email'><br>"
                + "Date: <input type='text' name='date'><br>"
                + "<input type='submit' value='Create'>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
