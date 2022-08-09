//package servlet;
//
//import javax.servlet.http.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.ServletException;
//import java.io.*;
//import java.util.HashMap;
//import java.util.Map;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.lang.reflect.Method;
//
//
//@WebServlet(urlPatterns = "/")
//public class DispatcherServlet extends HttpServlet {
//    private Map<String, GetDispatcher> getMappings = new HashMap<>();
//    private Map<String, PostDispatcher> postMappings = new HashMap<>();
//
//    public void init() throws ServletException {
//        this.getMappings = scanGetInControllers();
//        this.postMappings = scanPostInControllers();
//        this.viewEngine = new ViewEngine(getServletContext());
//    }
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//        String path = req.getRequestURI().substring(req.getContextPath().length());
//        // 根据路径查找GetDispatcher:
//        GetDispatcher dispatcher = this.getMappings.get(path);
//        if (dispatcher == null) {
//            // 未找到返回404:
//            resp.sendError(404);
//            return;
//        }
//        // 调用Controller方法获得返回值:
//        ModelAndView mv = dispatcher.invoke(req, resp);
//        // 允许返回null:
//        if (mv == null) {
//            return;
//        }
//        // 允许返回`redirect:`开头的view表示重定向:
//        if (mv.view.startsWith("redirect:")) {
//            resp.sendRedirect(mv.view.substring(9));
//            return;
//        }
//        // 将模板引擎渲染的内容写入响应:
//        PrintWriter pw = resp.getWriter();
//        this.viewEngine.render(mv, pw);
//        pw.flush();
//    }
//
//    class GetDispatcher {
//
//        Object instance; // Controller实例
//        Method method; // Controller方法
//        String[] parameterNames; // 方法参数名称
//        Class<?>[] parameterClasses; // 方法参数类型
//
//        public ModelAndView invoke(HttpServletRequest request, HttpServletResponse response) throws Exception {
//            Object[] arguments = new Object[parameterClasses.length];
//            for (int i = 0; i < parameterClasses.length; i++) {
//                String parameterName = parameterNames[i];
//                Class<?> parameterClass = parameterClasses[i];
//                if (parameterClass == HttpServletRequest.class) {
//                    arguments[i] = request;
//                } else if (parameterClass == HttpServletResponse.class) {
//                    arguments[i] = response;
//                } else if (parameterClass == HttpSession.class) {
//                    arguments[i] = request.getSession();
//                } else if (parameterClass == int.class) {
//                    arguments[i] = Integer.valueOf(getOrDefault(request, parameterName, "0"));
//                } else if (parameterClass == long.class) {
//                    arguments[i] = Long.valueOf(getOrDefault(request, parameterName, "0"));
//                } else if (parameterClass == boolean.class) {
//                    arguments[i] = Boolean.valueOf(getOrDefault(request, parameterName, "false"));
//                } else if (parameterClass == String.class) {
//                    arguments[i] = getOrDefault(request, parameterName, "");
//                } else {
//                    throw new RuntimeException("Missing handler for type: " + parameterClass);
//                }
//            }
//            return (ModelAndView) this.method.invoke(this.instance, arguments);
//        }
//
//        private String getOrDefault(HttpServletRequest request, String name, String defaultValue) {
//            String s = request.getParameter(name);
//            return s == null ? defaultValue : s;
//        }
//    }
//
//    class PostDispatcher {
//
//        Object instance; // Controller实例
//        Method method; // Controller方法
//        Class<?>[] parameterClasses; // 方法参数类型
//        ObjectMapper objectMapper; // JSON映射
//
//        public ModelAndView invoke(HttpServletRequest request, HttpServletResponse response) throws Exception {
//            Object[] arguments = new Object[parameterClasses.length];
//            for (int i = 0; i < parameterClasses.length; i++) {
//                Class<?> parameterClass = parameterClasses[i];
//                if (parameterClass == HttpServletRequest.class) {
//                    arguments[i] = request;
//                } else if (parameterClass == HttpServletResponse.class) {
//                    arguments[i] = response;
//                } else if (parameterClass == HttpSession.class) {
//                    arguments[i] = request.getSession();
//                } else {
//                    // 读取JSON并解析为JavaBean:
//                    BufferedReader reader = request.getReader();
//                    arguments[i] = this.objectMapper.readValue(reader, parameterClass);
//                }
//            }
//            return (ModelAndView) this.method.invoke(instance, arguments);
//        }
//    }
//}
