package iuh.fit.se.nguyendoxuantruong_22714311_tuan01;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private String greeting;
    private String appName;

    public void init() {
        message = "Hello World!";
        greeting = getServletConfig().getInitParameter("greeting");
        appName = getServletContext().getInitParameter("appName");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Create a sample User object
        User user = new User(1, "Nguyen Van A", "nguyenvana@example.com");

        // Build JSON response with greeting and appName
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.createObjectNode()
            .put("greeting", greeting)
            .put("appName", appName)
            .putPOJO("user", user)
            .toString();

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Echo posted parameters
        java.util.Enumeration<String> paramNames = request.getParameterNames();
        ObjectMapper mapper = new ObjectMapper();
        com.fasterxml.jackson.databind.node.ObjectNode root = mapper.createObjectNode();
        root.put("greeting", greeting);
        root.put("appName", appName);
        com.fasterxml.jackson.databind.node.ObjectNode paramsNode = mapper.createObjectNode();
        while (paramNames.hasMoreElements()) {
            String param = paramNames.nextElement();
            paramsNode.put(param, request.getParameter(param));
        }
        root.set("postedParams", paramsNode);
        PrintWriter out = response.getWriter();
        out.print(root.toString());
        out.flush();
    }

    public void destroy() {
    }
}