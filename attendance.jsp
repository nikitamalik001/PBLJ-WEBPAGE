<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Attendance</title>
</head>
<body>
    <h2>Mark Attendance</h2>
    <form action="AttendanceServlet" method="post">
        Student ID: <input type="text" name="studentId" /><br/>
        Date (yyyy-mm-dd): <input type="text" name="attDate" /><br/>
        Status:
        <select name="status">
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br/>
        <input type="submit" value="Submit Attendance" />
    </form>
</body>
</html>
