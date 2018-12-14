package d.servlet.http;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserServletTest {

    ValidateStub validate = new ValidateStub();
    User one = new User(
            0,
            "admin",
            "admin",
            "admin",
            "TestAdminName",
            "TestAdminSername",
            "admin@email.com");

    User two = new User(
            1,
            "user",
            "user",
            "user",
            "TestUserName",
            "TestUserSername",
            "user@email.com");

    @Before
    public void createUsers() {
        validate.add(one);
        validate.add(two);
    }

    @Test
    public void whenEditUser() throws ServletException, IOException {
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        String path = "/WEB-INF/views/UserPage.jsp";
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(req.getSession().getAttribute("login")).thenReturn("admin");
        when(req.getParameter("oldLogin")).thenReturn("admin");
        when(req.getParameter("oldPassword")).thenReturn("admin");
        when(req.getParameter("action")).thenReturn("apply");
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("login")).thenReturn("admin");
        when(req.getParameter("password")).thenReturn("admin");
        when(req.getParameter("role")).thenReturn("admin");
        when(req.getParameter("name")).thenReturn("EditName");
        when(req.getParameter("sername")).thenReturn("EditSername");
        when(req.getParameter("email")).thenReturn("edit@email.com");
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
        new UserServlet().doPost(req, resp);
        Assert.assertThat(validate.findById("admin").getName(), CoreMatchers.is("EditName"));
        Assert.assertThat(validate.findById("admin").getSername(), CoreMatchers.is("EditSername"));
        Assert.assertThat(validate.findById("admin").getEmail(), CoreMatchers.is("edit@email.com"));
    }
}

