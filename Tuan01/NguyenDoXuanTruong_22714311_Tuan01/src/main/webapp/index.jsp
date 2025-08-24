<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br/>
<h2>Registration Form (GET)</h2>
<form method="get" action="registration-form" name="registrationFormGet">
    Name: <input type="text" name="name" required><br/>
    Email: <input type="email" name="email" required><br/>
    Password: <input type="password" name="password" required><br/>
    Facebook: <input type="text" name="facebook"><br/>
    Gender:
    <select name="gender">
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
    </select><br/>
    Short Bio:<br/>
    <textarea name="shortBio" rows="4" cols="40"></textarea><br/>
    <input type="checkbox" name="terms" value="accepted" required> I accept the terms<br/>
    <input type="submit" value="Register (GET)">
</form>
<br/>
<h2>Registration Form (POST)</h2>
<form method="post" action="registration-form" name="registrationFormPost">
    Name: <input type="text" name="name" required><br/>
    Email: <input type="email" name="email" required><br/>
    Password: <input type="password" name="password" required><br/>
    Facebook: <input type="text" name="facebook"><br/>
    Gender:
    <select name="gender">
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
    </select><br/>
    Short Bio:<br/>
    <textarea name="shortBio" rows="4" cols="40"></textarea><br/>
    <input type="checkbox" name="terms" value="accepted" required> I accept the terms<br/>
    <input type="submit" value="Register (POST)">
</form>
</body>
</html>