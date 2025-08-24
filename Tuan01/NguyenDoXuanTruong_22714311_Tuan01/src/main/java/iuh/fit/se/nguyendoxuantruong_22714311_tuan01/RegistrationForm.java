package iuh.fit.se.nguyendoxuantruong_22714311_tuan01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registration-form")
public class RegistrationForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "POST");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, String method) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String facebook = request.getParameter("facebook");
        String gender = request.getParameter("gender");
        String shortBio = request.getParameter("shortBio");
        String terms = request.getParameter("terms");

        boolean valid = name != null && !name.isEmpty()
                && email != null && !email.isEmpty()
                && password != null && !password.isEmpty()
                && terms != null && terms.equals("accepted");

        out.println("<html><head><title>Result Page</title></head><body>");
        out.println("<h2>Registration Result (" + method + ")</h2>");
        if (valid) {
            out.println("<b>Name:</b> " + name + "<br/>");
            out.println("<b>Email:</b> " + email + "<br/>");
            out.println("<b>Facebook:</b> " + (facebook != null ? facebook : "") + "<br/>");
            out.println("<b>Gender:</b> " + (gender != null ? gender : "") + "<br/>");
            out.println("<b>Short Bio:</b> <pre>" + (shortBio != null ? shortBio : "") + "</pre><br/>");
            out.println("<b>Terms accepted:</b> Yes<br/>");
            out.println("<b>Password:</b> (hidden for security)<br/>");
        } else {
            out.println("<span style='color:red;'>Please fill in all required fields and accept the terms.</span><br/>");
            out.println("<a href='index.jsp'>Back to Registration</a>");
        }
        out.println("<hr><b>Note:</b> Compare the HTTP Request/Response in browser dev tools for GET vs POST.");
        out.println("</body></html>");
    }
}

