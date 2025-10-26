package com.myapp;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class EmployeeServlet extends HttpServlet {

    // Database connection parameters – adjust as needed
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "rootpassword";

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String searchId = request.getParameter("empId");

        out.println("<html><body>");
        out.println("<h2>Employee List</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            if (searchId != null && !searchId.trim().isEmpty()) {
                // Search for specific employee
                PreparedStatement pst = conn.prepareStatement(
                    "SELECT EmpID, Name, Salary FROM Employee WHERE EmpID = ?");
                pst.setInt(1, Integer.parseInt(searchId));
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    out.println("<p>EmpID: " + rs.getInt("EmpID") + "</p>");
                    out.println("<p>Name: " + rs.getString("Name") + "</p>");
                    out.println("<p>Salary: " + rs.getDouble("Salary") + "</p>");
                } else {
                    out.println("<p>No employee found with ID " + searchId + "</p>");
                }

                rs.close();
                pst.close();
            } else {
                // Display all employees
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT EmpID, Name, Salary FROM Employee");

                out.println("<table border='1'><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("EmpID") + "</td>");
                    out.println("<td>" + rs.getString("Name") + "</td>");
                    out.println("<td>" + rs.getDouble("Salary") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");

                rs.close();
                st.close();
            }

            conn.close();

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

        // Search form
        out.println("<h3>Search Employee by ID</h3>");
        out.println("<form action='EmployeeServlet' method='get'>");
        out.println("EmpID: <input type='text' name='empId' />");
        out.println("<input type='submit' value='Search' />");
        out.println("</form>");

        out.println("</body></html>");
        out.close();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
