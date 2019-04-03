package d.servlet.location.servlet;

import d.servlet.location.storage.LocationStorage;
import org.json.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Класс LocationServlet отвечает за загрузку списка стран, регионов и населенных пунктов..
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class LocationServlet extends HttpServlet {
    /**
     * Метод отправляет список стран на страницу.
     * @param request - запрос.
     * @param response - ответ.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(LocationStorage.getInstance().getCountries());
        response.getWriter().write(array.toString());
    }
    /**
     * Метод отправляет список регионов и населенных пунктов на страницу.
     * @param request - запрос.
     * @param response - ответ.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (request.getParameter("action").equals("get_regions")) {
            JSONArray array = new JSONArray(LocationStorage.getInstance().getRegions(request.getParameter("country")));
            response.getWriter().write(array.toString());
        } else if (request.getParameter("action").equals("get_cities")) {
            JSONArray array = new JSONArray(LocationStorage.getInstance().getCities(request.getParameter("country"), request.getParameter("region")));
            response.getWriter().write(array.toString());
        }
    }
}
