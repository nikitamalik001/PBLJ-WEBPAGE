package com.myapp;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {

    private static final String DB_URL  = "jdbc:mysql://localhost:3306/mydb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "rootpassword";

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String attDate   = request.getParameter("attDate");
        String status    = request.getParameter("status");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO Attendance (StudentID, AttDate, Status) VALUES (?, ?, ?)");
            pst.setString(1, studentId);
            pst.setDate(2, java.sql.Date.valueOf(attDate));
            pst.setString(3, status);

            int rows = pst.executeUpdate();

            out.println("<html><body>");
            if (rows > 0) {
                out.println("<h2>Attendance Marked Successfully</h2>");
            } else {
                out.println("<h2>Failed to mark attendance</h2>");
            }
            out.println("</body></html>");

            pst.close();
            conn.close();

        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h2>Error occurred</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
        out.close();
    }
}
