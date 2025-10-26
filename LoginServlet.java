package com.myapp;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // hard-coded credentials (for demo)
        if ("admin".equals(username) && "password123".equals(password)) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Welcome, " + username + "!</h2>");
            out.println("</body></html>");
            out.close();
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Login Failed</h2>");
            out.println("<p>Invalid username or password.</p>");
            out.println("</body></html>");
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
