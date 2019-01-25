package d.servlet.cinema.servlets;

import d.servlet.cinema.storage.CinemaStorage;
import org.json.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Класс CinemaServlet.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class CinemaServlet extends HttpServlet {
    /**
     * Метод doGet передет массив билетов всего зала.
     * @param req - запрос;
     * @param resp - ответ;
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(CinemaStorage.getInstance().getAllPlaces());
        resp.getWriter().write(array.toString());
    }
    /**
     * Метод doPost оформляет покупку билета.
     * @param req - запрос;
     * @param resp - ответ;
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("place_id"));
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        CinemaStorage.getInstance().buyTicket(id, name, phone);
    }
}

