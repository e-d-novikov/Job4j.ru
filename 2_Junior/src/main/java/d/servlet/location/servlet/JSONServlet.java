package d.servlet.location.servlet;

import d.servlet.location.objects.User;
import d.servlet.location.storage.UserStore;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
/**
 * JSONServlet.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class JSONServlet extends HttpServlet {
    /**
     * Метод отправляет с сервера список пользователей из UserStorage.
     * @param request - запрос.
     * @param response - ответ.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(UserStore.getInstance().getAll());
        response.getWriter().write(array.toString());
    }
    /**
     * Метод добавляет пользователя в UserStorage.
     * @param request - запрос.
     * @param response - ответ.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(stringBuilder.toString(), User.class);
        UserStore.getInstance().add(user);
    }
}
