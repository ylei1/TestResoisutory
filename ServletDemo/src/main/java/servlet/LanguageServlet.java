package servlet;


import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import java.io.*;
import java.util.HashSet;
import java.util.Set;


@WebServlet(urlPatterns = "/pref")
public class LanguageServlet extends HttpServlet {

    private static Set<String> languageSet = new HashSet<String>();

    public LanguageServlet() {
        languageSet.add("en");
        languageSet.add("cn");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
        if (languageSet.contains(lang)) {
            // 创建一个新的Cookie:
            Cookie cookie = new Cookie("lang", lang);
            // 该Cookie生效的路径范围:
            cookie.setPath("/");
            // 该Cookie有效期:
            cookie.setMaxAge(8640000); // 8640000秒=100天
            // 将该Cookie添加到响应:
            resp.addCookie(cookie);
        }
        resp.sendRedirect("/");
    }
}
