package d.servlet.json;

import org.json.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(Location.getInstance().getCountries());
        resp.getWriter().write(array.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (req.getParameter("action").equals("get_regions")) {
            JSONArray array = new JSONArray(Location.getInstance().getRegions(req.getParameter("country")));
            resp.getWriter().write(array.toString());
        } else if (req.getParameter("action").equals("get_cities")) {
            JSONArray array = new JSONArray(Location.getInstance().getCities(req.getParameter("country"), req.getParameter("region")));
            resp.getWriter().write(array.toString());
        }
    }
}
