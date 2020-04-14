<%@ page import="java.sql.SQLException" %>
<%@ page import="vedder.jsp.JSPHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet/less" type="text/css" href="main.less">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@400;500;531;600;700;800;900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:ital,wght@0,700;1,700&display=swap" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.9.0/less.min.js"></script>
    <title>Dieters</title>
</head>
<body>
<header class="login-header header">
    <h1 class="title">DIETERS - login page</h1>
</header>
<main class="login">
    <form class="login-form" action="index.jsp">
        Please, enter your credentials
        <div class="login-input-wrapper">
            <label class="login-form__label label">
                login
                <input name="login" class="login-form__input input" type="text">
            </label>
            <label class="login-form__label label">
                password
                <input name="password" class="login-form__input input" type="password">
            </label>
        </div>
        <input class="login-form__button button" type="submit">
    </form>
    <%
        boolean isUserExisting = false;
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            isUserExisting = JSPHelper.checkCredentials(login, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (!isUserExisting) {
            System.out.println("user doesn't exist");
        }
    %>
</main>
</body>
</html>

